package main.controller;

import javafx.scene.control.Alert;
import javafx.util.Pair;
import main.java.com.lynden.gmapsfx.GoogleMapView;
import main.java.com.lynden.gmapsfx.MapComponentInitializedListener;


import main.java.com.lynden.gmapsfx.javascript.event.UIEventType;
import main.java.com.lynden.gmapsfx.javascript.object.*;
import main.fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.java.com.lynden.gmapsfx.service.geocoding.*;
import main.model.WaterReport;
import main.model.User;
import netscape.javascript.JSObject;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;


public class ViewMapScreenController implements Initializable, MapComponentInitializedListener {

    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    private HashMap<String, Pair<String, String>> uniqueLocationTable;

    private GeocodingService geocodingService;
    /*
    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

    @FXML
    private void initialize() {
        mapView.addMapInializedListener(this);
    }
    */


    public void setMainApp(MainFXApplication main, User currentUser) {
        mainFXApplication = main;
        this.currentUser = currentUser;
        uniqueLocationTable = new HashMap<>();

        mapView.addMapInializedListener(this);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }


    @Override
    public void mapInitialized() {
        geocodingService = new GeocodingService();
        markerInitialize();


        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(33.780064,-84.389363))
                .mapType(MapTypeIdEnum.ROADMAP)
                .mapMarker(true)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);


        /*InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                + "Current Location: Safeway<br>"
                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, myLocationMarker);*/
    }

    private void markerInitialize() {
        List<WaterReport> allReports = getAllReports();
        String[] uniqueLocations = uniqueLocationTable.keySet().toArray(new String[0]);
        List<LatLong> coordinateList = new ArrayList<>();

        for (int i = 0; i < uniqueLocations.length; i++) {
            String currentAddress = uniqueLocations[i].replace("\n",",");

            geocodingService.geocode(currentAddress, (GeocodingResult[] results, GeocoderStatus status) -> {
                LatLong latLong = null;
                if( status == GeocoderStatus.ZERO_RESULTS) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "No matching address found");
                    alert.show();
                    latLong = null;
                } else if( results.length > 1 ) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Multiple results found, showing the first one.");
                    alert.show();
                    latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                } else {
                    latLong = new LatLong(results[0].getGeometry().getLocation().getLatitude(), results[0].getGeometry().getLocation().getLongitude());
                }

                coordinateList.add(latLong);
                createMarker(coordinateList, uniqueLocations);
            });

        }



    }

    private void createMarker(List<LatLong> coordinateList, String[] uniqueLocations) {
        for (int i = 0; i < coordinateList.size(); i++) {
            LatLong latLong = coordinateList.get(i);
            if (latLong != null) {
                String address = uniqueLocations[i];
                Pair<String, String> current = uniqueLocationTable.get(address);
                createMarker(latLong, "Water Type: " + current.getKey().toString() + "<br>" + "Water Condition: " + current.getValue().toString());
            }
        }
    }


    private void createMarker(LatLong latLong, String content) {
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(latLong);
        markerOptions1.visible(true);
        Marker myLocationMarker = new Marker(markerOptions1);
        map.addMarker(myLocationMarker);

        map.addUIEventHandler(myLocationMarker, UIEventType.click, (JSObject obj) -> {
            InfoWindow information = new InfoWindow();
            information.setContent(content);
            information.open(map, myLocationMarker);
        });



    }
    private List<WaterReport> getAllReports() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT reportnumber, name, date, time, location, watertype, watercondition FROM WATERREPORT ORDER BY date DESC, time DESC";
            ResultSet rs = stmt.executeQuery(sql);
            List<WaterReport> reportList = new ArrayList<>();
            while (rs.next()) {
                if (!uniqueLocationTable.containsKey(rs.getString("location").replace("\n",","))) {
                    uniqueLocationTable.put(rs.getString("location").replace("\n",","), new Pair<String, String>(rs.getString("watertype"), rs.getString("watercondition")));
                }
                WaterReport temp = new WaterReport(rs.getInt("reportnumber"), rs.getString("date"), rs.getString("time"), rs.getString("name"), rs.getString("location"), rs.getString("watertype"), rs.getString("watercondition"));
                reportList.add(temp);
            }
            return reportList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
