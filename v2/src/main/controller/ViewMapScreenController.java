package main.controller;

import javafx.util.Pair;
import main.java.com.lynden.gmapsfx.GoogleMapView;
import main.java.com.lynden.gmapsfx.MapComponentInitializedListener;


import main.java.com.lynden.gmapsfx.javascript.object.*;
import main.fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.java.com.lynden.gmapsfx.service.geocoding.GeocoderRequest;
import main.java.com.lynden.gmapsfx.service.geocoding.GeocodingService;
import main.java.com.lynden.gmapsfx.service.geocoding.GeocodingServiceCallback;
import main.model.WaterReport;
import main.model.User;

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

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }


    @Override
    public void mapInitialized() {

        List<WaterReport> allReports = getAllReports();
        String[] uniqueLocations = uniqueLocationTable.keySet().toArray(new String[0]);
        for (int i = 0; i < uniqueLocations.length; i++) {
            System.out.println(uniqueLocations[i]);
            Pair<String, String> current = uniqueLocationTable.get(uniqueLocations[i]);
            System.out.println(current.getKey().toString());
            System.out.println(current.getValue().toString());
        }
        //GeocodingService getLatLongObject = new GeocodingService();
        //getLatLongObject.geocode("930 Spring St NW, Atlanta, GA 30309");


        LatLong myLocation = new LatLong(33.7800640,-84.3893630);



        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(myLocation)
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);

        map = mapView.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(myLocation);
        Marker myLocationMarker = new Marker(markerOptions1);

        map.addMarker(myLocationMarker);

        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                + "Current Location: Safeway<br>"
                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, myLocationMarker);
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
                if (!uniqueLocationTable.containsKey(rs.getString("location"))) {
                    uniqueLocationTable.put(rs.getString("location"), new Pair<String, String>(rs.getString("watertype"), rs.getString("watercondition")));
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
