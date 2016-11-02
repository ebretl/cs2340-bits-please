package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by francestsenn on 11/2/16.
 */
public class ReportManager {

    public ObservableList<QualityReport> getAllQualityReports() {
        Connection conn;
        Statement stmt;
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
            return FXCollections.observableList(reportList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteQualityReport(ObservableList<QualityReport> selectedDelete, ObservableList<QualityReport> mainList) {
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            for (QualityReport current : selectedDelete) {
                String sql = "DELETE FROM QUALITYREPORT WHERE reportnumber = '" + current.get_reportnumber() + "';";
                stmt.executeUpdate(sql);
                mainList.remove(current);
            }
        } catch (Exception ignored) {

        }
    }

    public void submitQualityReport(String user, String location, OverallConditionEnum overallCondition, String virusPPM, String contaminantPPM) {
        int reportNumber;
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT MAX(reportnumber) AS MAX FROM QUALITYREPORT";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                reportNumber = rs.getInt("MAX") + 1;
            } else {
                reportNumber = 1;
            }
            String date = LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getDayOfMonth();
            String time = LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond();
            sql = "INSERT INTO `QUALITYREPORT` (`reportnumber`, `date`, `time`, `name`, `location`, `overallcondition`, `virusPPM`, `contaminantPPM`) VALUES ('" + reportNumber + "', '" + date + "', '" + time + "', '" + user + "', '" + location + "', '" + overallCondition + "', '" + virusPPM + "', '" + contaminantPPM + "')";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<WaterReport> getAllWaterReports() {
        Connection conn;
        Statement stmt;
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
            return FXCollections.observableList(reportList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteWaterReport(ObservableList<WaterReport> selectedDelete, ObservableList<WaterReport> mainList) {
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            for (WaterReport current : selectedDelete) {
                String sql = "DELETE FROM WATERREPORT WHERE reportnumber = '" + current.get_reportnumber() + "';";
                stmt.executeUpdate(sql);
                mainList.remove(current);
            }
        } catch (Exception ignored) {

        }
    }

    public void submitWaterReport(String user, String location, WaterTypeEnum waterType, WaterConditionEnum waterCondition) {
        int reportNumber;
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT MAX(reportnumber) AS MAX FROM WATERREPORT";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                reportNumber = rs.getInt("MAX") + 1;
            } else {
                reportNumber = 1;
            }
            String date = LocalDateTime.now().getYear() + "-" + LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getDayOfMonth();
            String time = LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute() + ":" + LocalDateTime.now().getSecond();
            sql = "INSERT INTO `WATERREPORT` (`reportnumber`, `date`, `time`, `name`, `location`, `watertype`, `watercondition`) VALUES ('" + reportNumber + "', '" + date + "', '" + time + "', '" + user + "', '" + location + "', '" + waterType + "', '" + waterCondition + "')";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
