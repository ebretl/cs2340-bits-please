package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Abhay Dalmia on 9/24/2016.
 */
public class MainApplicationScreenController {

    private MainFXApplication mainFXApplication;

    private User currentUser;
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
    /**
     * If logout button is pressed, redirects to the Welcome Screen
     */
    private void logoutButtonPressed() {
        mainFXApplication.showWelcomeScreen();
    }

    @FXML
    private void editUserProfilePressed() {
        mainFXApplication.showEditUserProfileScreen();
    }

    @FXML
    private void submitWaterReportPressed() {
        if (currentUser.get_ban() == 0) {
            mainFXApplication.showSubmitWaterReportScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Error!");
            alert.setHeaderText("You have been banned from submitting water reports!");
            alert.setContentText("Kindly talk to an administrator if you think this has been done in error!");
            alert.showAndWait();
        }
    }

    @FXML
    private void viewWaterReportPressed() {
        mainFXApplication.showViewWaterReportScreen();
    }
}
