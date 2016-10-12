package main.controller;

import main.fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import main.model.User;
import main.model.UserTypeEnum;


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

    @FXML
    private void viewMapPressed() {
        mainFXApplication.showViewMapScreen();
    }

    @FXML
    private void submitQualityReportPressed() {
        if (currentUser.get_type().equals(UserTypeEnum.WORKER.toString()) ||currentUser.get_type().equals(UserTypeEnum.MANAGER.toString())) {
            mainFXApplication.showSubmitQualityReportScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Error!");
            alert.setHeaderText("Only Managers and Wokers can submit a Quality Report!");
            alert.setContentText("Kindly talk to an administrator if you think this has been done in error!");
            alert.showAndWait();
        }
    }

    @FXML
    private void viewQualityReportPressed() {
        if (currentUser.get_type().equals(UserTypeEnum.MANAGER.toString())) {
            mainFXApplication.showViewQualityReportScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Error!");
            alert.setHeaderText("Only Managers can view Quality Reports!");
            alert.setContentText("Kindly talk to an administrator if you think this has been done in error!");
            alert.showAndWait();
        }
    }


    @FXML
    private void historicalGraphPressed() {
        if (currentUser.get_type().equals(UserTypeEnum.MANAGER.toString())) {
            mainFXApplication.showGraphParameterScreen();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Error!");
            alert.setHeaderText("Only Managers can view Historical Graphs!");
            alert.setContentText("Kindly talk to an administrator if you think this has been done in error!");
            alert.showAndWait();
        }

    }
}
