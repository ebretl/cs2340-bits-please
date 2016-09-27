package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.QualityReport;
import model.User;
import model.WaterReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ViewQualityReportScreenController {
    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    TableView<QualityReport> mainTable;

    @FXML
    TableColumn<QualityReport, Integer> reportnumber;

    @FXML
    TableColumn<QualityReport, String> date1;

    @FXML
    TableColumn<QualityReport, String> time1;

    @FXML
    TableColumn<QualityReport, String> location1;

    @FXML
    TableColumn<QualityReport, String> overallcondition;

    @FXML
    TableColumn<QualityReport, Integer> virusPPM;

    @FXML
    TableColumn<QualityReport, Integer> contaminantPPM;

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
        List<QualityReport> mainList = getAllReports();
        if (mainList == null || mainList.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Error!");
            alert.setHeaderText("There are no reports in database to show!");
            alert.setContentText("Add a report first!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        } else {
            reportnumber.setCellValueFactory(new PropertyValueFactory<QualityReport, Integer>("_reportnumber"));
            date1.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("_date"));
            time1.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("_time"));
            location1.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("_location"));
            overallcondition.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("_overallcondition"));
            virusPPM.setCellValueFactory(new PropertyValueFactory<QualityReport, Integer>("_virusPPM"));
            contaminantPPM.setCellValueFactory(new PropertyValueFactory<QualityReport, Integer>("_contaminantPPM"));

            mainTable.getItems().setAll(mainList);
        }
    }

    @FXML
    private void backPressed() {
        mainFXApplication.showMainApplicationScreen();
    }

    private List<QualityReport> getAllReports() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT reportnumber, name, date, time, location, overallcondition, virusPPM, contaminantPPM FROM QUALITYREPORT";
            ResultSet rs = stmt.executeQuery(sql);
            List<QualityReport> reportList = new ArrayList<>();
            while (rs.next()) {
                QualityReport temp = new QualityReport(rs.getInt("reportnumber"), rs.getString("date"), rs.getString("time"), rs.getString("name"), rs.getString("location"), rs.getString("overallcondition"), rs.getInt("virusPPM"), rs.getInt("contaminantPPM"));
                reportList.add(temp);
            }
            return reportList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
