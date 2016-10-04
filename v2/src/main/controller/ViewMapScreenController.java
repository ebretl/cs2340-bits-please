package main.controller;

import main.java.com.lynden.gmapsfx.GoogleMapView;
import main.java.com.lynden.gmapsfx.MapComponentInitializedListener;


import main.java.com.lynden.gmapsfx.javascript.object.*;
import main.fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import main.model.WaterReport;
import main.model.User;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Abhay Dalmia on 9/26/2016.
 */
public class ViewMapScreenController implements Initializable, MapComponentInitializedListener {

    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private GoogleMapView mapView;

    private GoogleMap map;

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

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }


    @Override
    public void mapInitialized() {
        System.out.println("Hi");
        LatLong joeSmithLocation = new LatLong(47.6197, -122.3231);
        LatLong joshAndersonLocation = new LatLong(47.6297, -122.3431);
        LatLong bobUnderwoodLocation = new LatLong(47.6397, -122.3031);
        LatLong tomChoiceLocation = new LatLong(47.6497, -122.3325);
        LatLong fredWilkieLocation = new LatLong(47.6597, -122.3357);


        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(47.6097, -122.3331))
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
        markerOptions1.position(joeSmithLocation);

        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions2.position(joshAndersonLocation);

        MarkerOptions markerOptions3 = new MarkerOptions();
        markerOptions3.position(bobUnderwoodLocation);

        MarkerOptions markerOptions4 = new MarkerOptions();
        markerOptions4.position(tomChoiceLocation);

        MarkerOptions markerOptions5 = new MarkerOptions();
        markerOptions5.position(fredWilkieLocation);

        Marker joeSmithMarker = new Marker(markerOptions1);
        Marker joshAndersonMarker = new Marker(markerOptions2);
        Marker bobUnderwoodMarker = new Marker(markerOptions3);
        Marker tomChoiceMarker= new Marker(markerOptions4);
        Marker fredWilkieMarker = new Marker(markerOptions5);

        map.addMarker( joeSmithMarker );
        map.addMarker( joshAndersonMarker );
        map.addMarker( bobUnderwoodMarker );
        map.addMarker( tomChoiceMarker );
        map.addMarker( fredWilkieMarker );

        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                + "Current Location: Safeway<br>"
                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, fredWilkieMarker);
    }
    


    private List<WaterReport> getAllReports() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT reportnumber, name, date, time, location, watertype, watercondition FROM WATERREPORT";
            ResultSet rs = stmt.executeQuery(sql);
            List<WaterReport> reportList = new ArrayList<>();
            while (rs.next()) {
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
