package controller;

import fxapp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.User;

public class LoginScreenController {

    private Main mainApplication;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    public void setMainApp(Main mainApplication) {
        this.mainApplication = mainApplication;
    }

    @FXML
    void authenticateLogin(ActionEvent e) {
        User userAttempt = new User(usernameField.getText(), passwordField.getText());
        if(mainApplication.attemptUserLogin(userAttempt)) {
            //todo enter into application
            //instead of displaying this stupid alert thing

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setContentText("You are now logged in.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Authenticating");
            alert.setContentText("Username and/or password is incorrect.");
            alert.showAndWait();
        }
    }

}
