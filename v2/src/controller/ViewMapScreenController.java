package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;


import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import model.WaterReport;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Abhay Dalmia on 9/26/2016.
 */
public class ViewMapScreenController implements MapComponentInitializedListener{

    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    private AnchorPane anchorpane;

    private GoogleMapView mapView;

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
        mapView = new GoogleMapView();
        //mapView.addMapInializedListener(this);
        //anchorpane.getChildren().add(mapView);
    }

    @FXML
    public void mapInitialized() {
        MapOptions mapOptions = new MapOptions();
        mapOptions.mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(12);
    }

    @FXML
    private void backPressed() {
        mainFXApplication.showMainApplicationScreen();
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
