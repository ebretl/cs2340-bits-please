package controller;

import fxapp.MainFXApplication;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import model.User;
import model.UserTypeEnum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;

public class GraphParameterScreenController {

    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    private ComboBox<Integer> yearField;

    @FXML
    private ComboBox<String> virusField;

    @FXML
    private ComboBox<String> locationField;

    public void setMainApp(MainFXApplication main, User currentUser) {
        mainFXApplication = main;
        this.currentUser = currentUser;
        initalizeComboBox();
    }

    @FXML
    public void showGraphPressed() {
        Connection conn = null;
        Statement stmt = null;
        int[] xval = {1,2,3,4,5,6,7,8,9,10,11,12};
        int [] yval = new int[12];
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
            plotGraph(xval, yval);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < yval.length; i++) {
            System.out.println(yval[i]);
        }
    }

    private void initalizeComboBox() {
        ObservableList<Integer> years = getYears();
        if (years.size() == 0) {
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
            yearField.valueProperty().addListener(new ChangeListener<Integer>() {
                @Override
                public void changed(ObservableValue ov, Integer notUsed, Integer yearSelected) {
                    ObservableList<String> location = getLocations(yearSelected);
                    locationField.getItems().clear();
                    locationField.setItems(location);
                    locationField.setValue(location.get(0));
                }
            });
            yearField.setValue(years.get(0));
        }


    }

    @FXML
    public void backPressed() {
        mainFXApplication.showMainApplicationScreen();
    }

    private ObservableList<Integer> getYears() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT DISTINCT YEAR(date) AS year FROM QUALITYREPORT";
            ResultSet rs = stmt.executeQuery(sql);
            ObservableList<Integer> years = FXCollections.observableArrayList();
            while (rs.next()) {
                years.add(rs.getInt("year"));
            }
            return years;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private ObservableList<String> getLocations(int yearSelected) {
        ObservableList<String> location = FXCollections.observableArrayList();
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT DISTINCT location FROM QUALITYREPORT WHERE YEAR(date) = '" + yearSelected + "'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                location.add(rs.getString("location"));
            }
            return location;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void plotGraph(int[] xval, int[] yval) {
        Stage graphStage = new Stage();
        graphStage.setTitle("Historical Graph View!");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        yAxis.setLabel(virusField.getSelectionModel().getSelectedItem());

        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        XYChart.Series series = new XYChart.Series();
        lineChart.setTitle(virusField.getSelectionModel().getSelectedItem() + " PPM for " + locationField.getSelectionModel().getSelectedItem() + " for " + yearField.getSelectionModel().getSelectedItem());
        lineChart.setLegendVisible(false);
        for (int i = 0; i < xval.length; i++) {
            series.getData().add(new XYChart.Data(xval[i], yval[i]));
        }

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        graphStage.setScene(scene);
        graphStage.show();
    }
}
