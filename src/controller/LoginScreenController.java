package controller;

import fxapp.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

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

    //will be in registration screen
    void repeatUserAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Username");
        alert.setContentText("That username is already in use.");
        alert.showAndWait();
    }

    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            mainApplication.showWelcomeScreen(window);
        } catch(Exception e) {
            //todo better logging
            System.err.println(e.getMessage());
        }
    }

}
