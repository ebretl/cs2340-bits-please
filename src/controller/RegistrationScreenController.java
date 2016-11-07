package controller;

import fxapp.MainFXApplication;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.UserTypeEnum;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Controls the registraion screen
 */
public class RegistrationScreenController {

    private MainFXApplication mainFXApplication;

    /**
     * Gets an instance of the current main application running
     * @param main the instance of the current application running
     *             A reference of this is stored in a local variable
     */
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
        usertypeField.setItems(FXCollections.observableArrayList(UserTypeEnum.values()));
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
            javafx.stage.Stage m1 = mainFXApplication.getStage();
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT username FROM USER WHERE username = '" + usernameField.getText().trim() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(m1);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("There is already a registered user with this username");
                alert.setContentText("Kindly use something else!");
                alert.showAndWait();
            } else if ((usernameField.getText() == null) || (passwordField.getText() == null)
                    || (fullnameField.getText() == null)
                    || usernameField.getText().trim().isEmpty() ||
                    passwordField.getText().trim().isEmpty() || fullnameField.getText().trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(m1);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("You left one or more fields blank!");
                alert.setContentText("You must provide your name, a un" +
                        "ique username and password for access to your account!");
                alert.showAndWait();
            } else {
                stmt = conn.createStatement();
                UserTypeEnum usertype = usertypeField.getSelectionModel().getSelectedItem();
                //System.out.println(usertype.toString());
                sql = "INSERT INTO `USER` (`username`, `password`, `fullname`, `ban`, " +
                        "`attempt`, `type`) VALUES ('" + usernameField.getText().trim() + "', '" +
                        passwordField.getText() + "', '" + fullnameField.getText().trim() + "', '0', '0', '" +
                        usertype.toString() +"')";
                stmt.executeUpdate(sql);
                mainFXApplication.showWelcomeScreen();
            }
        } catch(Exception e){
            mainFXApplication.showDatabaseError();
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null) {
                    stmt.close();
                }
            } catch(SQLException ignored) {
            }
            try{
                if(conn!=null) {
                    conn.close();
                }
            } catch(SQLException ignored){
            }
        }
    }

}
