package main.controller;

import main.fxapp.MainFXApplication;

import javafx.fxml.FXML;


public class WelcomeScreenController {

    private MainFXApplication mainFXApplication;

    public void setMainApp(MainFXApplication main) {
        mainFXApplication = main;
    }

    @FXML
    private void loginButtonPressed() {
        mainFXApplication.showLoginScreen();
    }

    @FXML
    private void registerButtonPressed() {
        mainFXApplication.showRegistrationScreen();
    }

}
