package controller;

import fxapp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by francestsenn on 9/23/16.
 */
public class TemporaryAppScreenController {
    private Main mainApplication;

    @FXML
    private Button logoutButton;


    public void setMainApp(Main mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    public void logoutAction (ActionEvent event) throws Exception {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainApplication.showWelcomeScreen(window);
    }
}
