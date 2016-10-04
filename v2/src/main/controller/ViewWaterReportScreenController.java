package main.controller;

import main.fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.model.WaterReport;
import main.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhay Dalmia on 9/26/2016.
 */
public class ViewWaterReportScreenController {
    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    TableView<WaterReport> mainTable;

    @FXML
    TableColumn<WaterReport, Integer> reportnumber;

    @FXML
    TableColumn<WaterReport, String> date1;

    @FXML
    TableColumn<WaterReport, String> time1;

    @FXML
    TableColumn<WaterReport, String> location1;

    @FXML
    TableColumn<WaterReport, String> watertype;

    @FXML
    TableColumn<WaterReport, String> watercondition;

    /**
     * Gets an instance of the current main application running
     * @param main the instance of the current application running
     *             A reference of this is stored in a local variable
     */
    public void setMainApp(MainFXApplication main, User currentUser) {
        mainFXApplication = main;
        this.currentUser = currentUser;
        initializeTable();
    }

    @FXML
    private void initializeTable() {
        List<WaterReport> mainList = getAllReports();
        if (mainList == null || mainList.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Error!");
            alert.setHeaderText("There are no reports in database to show!");
            alert.setContentText("Add a report first!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        } else {
            reportnumber.setCellValueFactory(new PropertyValueFactory<WaterReport, Integer>("_reportnumber"));
            date1.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("_date"));
            time1.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("_time"));
            location1.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("_location"));
            watertype.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("_watertype"));
            watercondition.setCellValueFactory(new PropertyValueFactory<WaterReport, String>("_watercondition"));
            mainTable.getItems().setAll(mainList);
        }
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
