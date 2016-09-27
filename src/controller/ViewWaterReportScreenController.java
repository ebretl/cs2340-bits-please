package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Report;
import model.User;
import model.WaterCondition;
import model.WaterType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Abhay Dalmia on 9/26/2016.
 */
public class ViewWaterReportScreenController {
    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    TableView<Report> mainTable;

    @FXML
    TableColumn<Report, Integer> reportnumber;

    @FXML
    TableColumn<Report, String> date1;

    @FXML
    TableColumn<Report, String> time1;

    @FXML
    TableColumn<Report, String> location1;

    @FXML
    TableColumn<Report, String> watertype;

    @FXML
    TableColumn<Report, String> watercondition;

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
        List<Report> mainList = getAllReports();
        if (mainList.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Error!");
            alert.setHeaderText("There are no reports in database to show!");
            alert.setContentText("Add a report first!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        } else {
            reportnumber.setCellValueFactory(new PropertyValueFactory<Report, Integer>("_reportnumber"));
            date1.setCellValueFactory(new PropertyValueFactory<Report, String>("_date"));
            time1.setCellValueFactory(new PropertyValueFactory<Report, String>("_time"));
            location1.setCellValueFactory(new PropertyValueFactory<Report, String>("_location"));
            watertype.setCellValueFactory(new PropertyValueFactory<Report, String>("_watertype"));
            watercondition.setCellValueFactory(new PropertyValueFactory<Report, String>("_watercondition"));
            mainTable.getItems().setAll(mainList);
        }
    }

    @FXML
    private void backPressed() {
        mainFXApplication.showMainApplicationScreen();
    }

    private List<Report> getAllReports() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT reportnumber, name, date, time, location, watertype, watercondition FROM WATERREPORT";
            ResultSet rs = stmt.executeQuery(sql);
            List<Report> reportList = new ArrayList<>();
            while (rs.next()) {
                Report temp = new Report(rs.getInt("reportnumber"), rs.getString("date"), rs.getString("time"), rs.getString("name"), rs.getString("location"), rs.getString("watertype"), rs.getString("watercondition"));
                reportList.add(temp);
            }
            return reportList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
