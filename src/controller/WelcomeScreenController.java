package controller;

import fxapp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenController {

    private Main mainApplication;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    public void setMainApp(Main mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    public void loginButtonAction (ActionEvent event) throws IOException {
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            mainApplication.showLoginScreen(app_stage);
        } catch(Exception e) {
            //todo better logging
            System.err.println(e.getMessage());
        }
    }



    @FXML
    public void registerButton (ActionEvent event) {

    }
}