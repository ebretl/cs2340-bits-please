package main.controller;

import main.fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import main.model.User;
import main.model.UserTypeEnum;


public class ADMINMainApplicationScreenController {

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
    private void banPressed() {
    }

    @FXML
    private void unbanPressed() {

    }

    @FXML
    private void blockPressed() {

    }

    @FXML
    private void unblockPressed() {

    }

    @FXML
    private void deleteAccountPressed() {

    }
}
