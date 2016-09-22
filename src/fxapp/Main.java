package fxapp;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage window) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
        Scene scene =  new Scene(root);
        window.setScene(scene);
        window.show();
    }



    public static void main(String[] args) {
        launch(args);
    }

}