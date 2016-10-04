package main.controller;

import javafx.scene.control.Alert;
import main.fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import main.model.User;
import main.model.WaterConditionEnum;
import main.model.WaterTypeEnum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;


public class SubmitWaterReportScreenController {
    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    private ComboBox waterConditionField;

    @FXML
    private ComboBox waterTypeField;

    @FXML
    private TextArea locationField;



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
        waterConditionField.getItems().clear();
        waterConditionField.setItems((ObservableList<WaterConditionEnum>) FXCollections.observableArrayList(WaterConditionEnum.values()));
        waterConditionField.setValue(FXCollections.observableArrayList(WaterConditionEnum.values()).get(0));

        waterTypeField.getItems().clear();
        waterTypeField.setItems((ObservableList<WaterTypeEnum>) FXCollections.observableArrayList(WaterTypeEnum.values()));
        waterTypeField.setValue(FXCollections.observableArrayList(WaterTypeEnum.values()).get(0));
    }

    @FXML
    private void backPressed() {
        mainFXApplication.showMainApplicationScreen();
    }

    @FXML
    private void submitPressed() {
        int reportNumber;
        if (locationField == null || locationField.getText() == null || locationField.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Error!");
            alert.setHeaderText("You left some fields blank!");
            alert.setContentText("You have to provide a location!");
            alert.showAndWait();
        } else {
            Connection conn = null;
            Statement stmt = null;
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
                sql = "INSERT INTO `WATERREPORT` (`reportnumber`, `date`, `time`, `name`, `location`, `watertype`, `watercondition`) VALUES ('" + reportNumber + "', '" + date + "', '" + time + "', '" + currentUser.get_username() + "', '" + locationField.getText().trim() + "', '" + waterTypeField.getSelectionModel().getSelectedItem() + "', '" + waterConditionField.getSelectionModel().getSelectedItem() + "')";
                stmt.executeUpdate(sql);
                mainFXApplication.showMainApplicationScreen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
