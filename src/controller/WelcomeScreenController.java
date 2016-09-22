package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomeScreenController {

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    @FXML
    public void loginButtonAction (ActionEvent event) throws IOException {
        Parent loginScreen_parent = FXMLLoader.load(getClass().getResource("../view/LoginScreen.fxml"));
        Scene loginScreen_scene = new Scene(loginScreen_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(loginScreen_scene);
        app_stage.show();
    }



    @FXML
    public void registerButton (ActionEvent event) {

    }
}