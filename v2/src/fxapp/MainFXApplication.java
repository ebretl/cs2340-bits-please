package fxapp;


import controller.*;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.WaterReport;
import model.User;

import java.io.IOException;
import java.util.Observable;
import java.util.logging.Level;



public class MainFXApplication extends Application{


    private Stage mainScreen;
    private Pane rootLayout;
    private User currentUser = new User();
    @Override
    public void start(Stage primaryStage) {
        mainScreen = primaryStage;
        showWelcomeScreen();
    }

    public Stage getStage() {
        return mainScreen;
    }

    public void showWelcomeScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/WelcomeScreen.fxml"));
            rootLayout = loader.load();
            // Give the controller access to the main app.
            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(this);

            // Set the Main App title
            mainScreen.setTitle("Water Purity Application");

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();
            currentUser = new User();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/LoginScreen.fxml"));
            Pane LoginPane = loader.load();

            LoginScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(LoginPane);
            mainScreen.setTitle("Login");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void showMainApplicationScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/MainApplicationScreen.fxml"));
            Pane MainAppPane = loader.load();

            MainApplicationScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("MainApp");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void showRegistrationScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/RegistrationScreen.fxml"));
            Pane MainAppPane = loader.load();

            RegistrationScreenController controller = loader.getController();
            controller.setMainApp(this);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("Registration");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void showSubmitWaterReportScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/SubmitWaterReportScreen.fxml"));
            Pane MainAppPane = loader.load();

            SubmitWaterReportScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("Submit Water Report!");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void showSubmitQualityReportScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/SubmitQualityReportScreen.fxml"));
            Pane MainAppPane = loader.load();

            SubmitQualityReportScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("Submit Quality Report!");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void showEditUserProfileScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/UserProfileScreen.fxml"));
            Pane MainAppPane = loader.load();

            UserProfileScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("Edit User Profile");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showViewWaterReportScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/ViewWaterReportScreen.fxml"));
            Pane MainAppPane = loader.load();

            ViewWaterReportScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("View Water Reports");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IllegalArgumentException A) {

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showViewQualityReportScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/ViewQualityReportScreen.fxml"));
            Pane MainAppPane = loader.load();

            ViewQualityReportScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("View Quality Reports");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IllegalArgumentException A) {

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showViewMapScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/ViewMapScreen.fxml"));
            Pane MainAppPane = loader.load();

            ViewMapScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("View Map");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IllegalArgumentException A) {

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showGraphParameterScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/GraphParameterScreen.fxml"));
            Pane MainAppPane = loader.load();

            GraphParameterScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("Graph Parameters");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IllegalArgumentException A) {

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showADMINMainApplicationScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/ADMINMainApplicationScreen.fxml"));
            Pane MainAppPane = loader.load();

            ADMINMainApplicationScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("MainApp - ADMIN");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }

    public void showDatabaseError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainScreen);
        alert.setTitle("Database Error!");
        alert.setHeaderText("The application couldn't connect to the database!");
        alert.setContentText("Try again or call an admin staff if problem persists!");
        alert.showAndWait();
    }
}
