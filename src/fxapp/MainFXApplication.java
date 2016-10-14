package fxapp;


import controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;


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

    private FXMLLoader getLoader(String path) {
            java.net.URL location = this.getClass().getClassLoader().getResource(path);
            if(location != null) {
                return new FXMLLoader(location);
            } else {
                System.err.println("Error: location for "+path+" is null");
                return null;
            }
    }

    public void showWelcomeScreen() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = getLoader("WelcomeScreen.fxml");
            //loader.setLocation();
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
            FXMLLoader loader = getLoader("LoginScreen.fxml");
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
            FXMLLoader loader = getLoader("MainApplicationScreen.fxml");
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
            FXMLLoader loader = getLoader("RegistrationScreen.fxml");
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
            FXMLLoader loader = getLoader("SubmitWaterReportScreen.fxml");
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
            FXMLLoader loader = getLoader("SubmitQualityReportScreen.fxml");
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
            FXMLLoader loader = getLoader("UserProfileScreen.fxml");
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
            FXMLLoader loader = getLoader("ViewWaterReportScreen.fxml");
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
            FXMLLoader loader = getLoader("ViewQualityReportScreen.fxml");
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
            FXMLLoader loader = getLoader("ViewMapScreen.fxml");
            AnchorPane MainAppPane = loader.load();
            Stage mapScreen = new Stage();
            ViewMapScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mapScreen.setTitle("View Map");
            mapScreen.setScene(scene);
            mapScreen.show();
        } catch (IllegalArgumentException A) {

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void showGraphParameterScreen() {
        try {
            FXMLLoader loader = getLoader("GraphParameterScreen.fxml");
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
            FXMLLoader loader = getLoader("ADMINMainApplicationScreen.fxml");
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

    public void showBlockUserScreen() {
        try {
            FXMLLoader loader = getLoader("BlockUserScreen.fxml");
            Pane MainAppPane = loader.load();

            BlockUserScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("Block User - ADMIN");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        } catch (IllegalArgumentException A) {

        }

    }

    public void showUnblockUserScreen() {
        try {
            FXMLLoader loader = getLoader("UnblockUserScreen.fxml");
            Pane MainAppPane = loader.load();

            UnblockUserScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("Unblock User - ADMIN");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        } catch (IllegalArgumentException A) {

        }

    }

    public void showBanUserScreen() {
        try {
            FXMLLoader loader = getLoader("BanUserScreen.fxml");
            Pane MainAppPane = loader.load();

            BanUserScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("Ban User - ADMIN");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        } catch (IllegalArgumentException A) {

        }

    }

    public void showUnbanUserScreen() {
        try {
            FXMLLoader loader = getLoader("UnbanUserScreen.fxml");
            Pane MainAppPane = loader.load();

            UnbanUserScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("Unban User - ADMIN");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        } catch (IllegalArgumentException A) {

        }

    }

    public void showDeleteAccountScreen() {
        try {
            FXMLLoader loader = getLoader("DeleteAccountScreen.fxml");
            Pane MainAppPane = loader.load();

            DeleteAccountScreenController controller = loader.getController();
            controller.setMainApp(this, currentUser);

            Scene scene = new Scene(MainAppPane);
            mainScreen.setTitle("DELETE ACCOUNT - ADMIN");
            mainScreen.setScene(scene);
            mainScreen.show();
        } catch (IOException e){
            e.printStackTrace();
        } catch (IllegalArgumentException A) {

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
