package main.controller;

import main.fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.model.OverallConditionEnum;
import main.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;


public class SubmitQualityReportScreenController {

    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    private ComboBox overallConditionField;

    @FXML
    private TextArea locationField;

    @FXML
    private TextField virusPPM;

    @FXML
    private TextField contaminantPPM;


    /**
     * Gets an instance of the current main application running
     * @param main the instance of the current application running
     *             A reference of this is stored in a local variable
     */
    public void setMainApp(MainFXApplication main, User currentUser) {
        mainFXApplication = main;
        this.currentUser = currentUser;
    }

    @FXML
    private void initialize() {
        overallConditionField.getItems().clear();
        overallConditionField.setItems((ObservableList<OverallConditionEnum>) FXCollections.observableArrayList(OverallConditionEnum.values()));
        overallConditionField.setValue(FXCollections.observableArrayList(OverallConditionEnum.values()).get(0));

    }

    @FXML
    private void backPressed() {
        mainFXApplication.showMainApplicationScreen();
    }

    @FXML
    private void submitPressed() {
        int reportNumber;
        Connection conn = null;
        Statement stmt = null;
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
            sql = "INSERT INTO `QUALITYREPORT` (`reportnumber`, `date`, `time`, `name`, `location`, `overallcondition`, `virusPPM`, `contaminantPPM`) VALUES ('" + reportNumber + "', '" + date + "', '" + time + "', '" + currentUser.get_username() + "', '" + locationField.getText() + "', '" + overallConditionField.getSelectionModel().getSelectedItem() + "', '" + virusPPM.getText() + "', '" + contaminantPPM.getText() + "')";
            System.out.println(sql);
            stmt.executeUpdate(sql);
            mainFXApplication.showMainApplicationScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
