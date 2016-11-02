package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.GraphManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GraphParameterScreenController {

    private MainFXApplication mainFXApplication;

    @FXML
    private ComboBox<Integer> yearField;

    @FXML
    private ComboBox<String> virusField;

    @FXML
    private ComboBox<String> locationField;

    private GraphManager graphManager;

    public void setMainApp(MainFXApplication main) {
        mainFXApplication = main;
        graphManager = new GraphManager();
        initalizeComboBox();
    }

    @FXML
    public void showGraphPressed() {
        Connection conn;
        Statement stmt;
        int[] xval = {1,2,3,4,5,6,7,8,9,10,11,12};
        Integer [] yval = new Integer[12];
        int yearSelected = yearField.getSelectionModel().getSelectedItem();
        String locationSelected = locationField.getSelectionModel().getSelectedItem();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql;
            if (virusField.getSelectionModel().getSelectedItem().trim().equals("Virus")) {
                sql = "SELECT AVG(virusPPM) AS AVERAGE, MONTH(date) AS MNTH FROM QUALITYREPORT WHERE YEAR(date) = '" + yearSelected + "' AND LOWER(location) = LOWER('" + locationSelected + "') GROUP BY MONTH(date)";
            } else {
                sql = "SELECT AVG(contaminantPPM) AS AVERAGE, MONTH(date) AS MNTH FROM QUALITYREPORT WHERE YEAR(date) = '" + yearSelected + "' AND LOWER(location) = LOWER('" + locationSelected + "') GROUP BY MONTH(date)";
            }
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                yval[rs.getInt("MNTH") - 1] = rs.getInt("AVERAGE");
            }
            graphManager.plotGraph(xval, yval, virusField.getSelectionModel().getSelectedItem(), locationField.getSelectionModel().getSelectedItem(), yearField.getSelectionModel().getSelectedItem());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initalizeComboBox() {
        ObservableList<Integer> years = graphManager.getYears();
        if ((years != null ? years.size() : 0) == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Database Error!");
            alert.setHeaderText("The application couldn't connect to the database!");
            alert.setContentText("Try again or call an admin staff if problem persists!");
            alert.showAndWait();
            mainFXApplication.showMainApplicationScreen();
        } else {
            virusField.getItems().setAll("Virus", "Contaminant");
            virusField.setValue("Virus");
            yearField.getItems().clear();

            yearField.setItems(years);
            yearField.valueProperty().addListener((ov, notUsed, yearSelected) -> {
                ObservableList<String> location = graphManager.getLocations(yearSelected);
                locationField.getItems().clear();
                locationField.setItems(location);
                locationField.setValue(location != null ? location.get(0) : null);
            });
            yearField.setValue(years.get(0));
        }


    }

    @FXML
    public void backPressed() {
        mainFXApplication.showMainApplicationScreen();
    }

}
