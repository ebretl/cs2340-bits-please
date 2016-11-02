package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by francestsenn on 11/2/16.
 */
public class GraphManager {

    public ObservableList<Integer> getYears() {
        Connection conn;
        Statement stmt;
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

    public ObservableList<String> getLocations(int yearSelected) {
        ObservableList<String> location = FXCollections.observableArrayList();
        Connection conn;
        Statement stmt;
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

    public void plotGraph(int[] xval, Integer[] yval, String virus, String location, Integer year) {
        Stage graphStage = new Stage();
        graphStage.setTitle("Historical Graph View!");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of Month");
        yAxis.setLabel(virus);

        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        lineChart.setTitle(virus + " PPM for " + location + " for " + year);
        lineChart.setLegendVisible(false);
        for (int i = 0; i < xval.length; i++) {
            if (yval[i] != null) {
                series.getData().add(new XYChart.Data(xval[i], yval[i]));
            }
        }

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        graphStage.setScene(scene);
        graphStage.show();
    }
}
