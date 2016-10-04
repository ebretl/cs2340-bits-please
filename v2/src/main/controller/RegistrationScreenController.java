package main.controller;

import main.fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import main.model.UserTypeEnum;

import java.sql.*;

/**
 * Created by Abhay Dalmia on 9/24/2016.
 */
public class RegistrationScreenController {

    private MainFXApplication mainFXApplication;

    public void setMainApp(MainFXApplication main) {
        mainFXApplication = main;
    }

    @FXML
    private TextField usernameField;

    @FXML
    private TextField fullnameField;

    @FXML
    private TextField passwordField;

    @FXML
    private ComboBox<UserTypeEnum> usertypeField;

    @FXML
    private void initialize() {
        usertypeField.getItems().clear();
        usertypeField.setItems((ObservableList<UserTypeEnum>) FXCollections.observableArrayList(UserTypeEnum.values()));
        usertypeField.setValue(FXCollections.observableArrayList(UserTypeEnum.values()).get(0));
    }

    @FXML
    private void backPressed() {
        mainFXApplication.showWelcomeScreen();
    }

    @FXML
    private void registerPressed() {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT username FROM USER WHERE username = '" + usernameField.getText() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainFXApplication.getStage());
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("There is already a registered user with this username");
                alert.setContentText("Kindly use something else!");
                alert.showAndWait();
            } else if (usernameField.getText() == null || passwordField.getText() == null || fullnameField.getText() == null
                    || usernameField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || fullnameField.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainFXApplication.getStage());
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("You left one or more fields blank!");
                alert.setContentText("You must provide your name, a unique username and password for access to your account!");
                alert.showAndWait();
            } else {
                stmt = conn.createStatement();
                UserTypeEnum usertype = (UserTypeEnum) usertypeField.getSelectionModel().getSelectedItem();
                System.out.println(usertype.toString());
                sql = "INSERT INTO `USER` (`username`, `password`, `fullname`, `ban`, `attempt`, `type`) VALUES ('" + usernameField.getText() + "', '" + passwordField.getText() + "', '" + fullnameField.getText() + "', '0', '0', '" + usertype.toString() +"')";
                stmt.executeUpdate(sql);
                mainFXApplication.showWelcomeScreen();
            }
        } catch(SQLException se){
            mainFXApplication.showDatabaseError();
            se.printStackTrace();
        } catch(Exception e){
            mainFXApplication.showDatabaseError();
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null)
                    stmt.close();
            } catch(SQLException se2) {
            }
            try{
                if(conn!=null)
                    conn.close();
            } catch(SQLException se){
            }
        }
    }

}
