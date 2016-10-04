package main.controller;

import main.fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import main.model.User;
import main.model.UserTypeEnum;

import java.sql.*;

public class LoginScreenController {
    private MainFXApplication mainFXApplication;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private User currentUser;
    public void setMainApp(MainFXApplication main, User currentUser) {
        mainFXApplication = main;
        this.currentUser = currentUser;
    }

    @FXML
    private void backPressed() {
        mainFXApplication.showWelcomeScreen();
    }

    @FXML
    private void loginPressed() {

        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT username, password, fullname, ban, attempt, type, emailaddress, homeaddress, company, jobtitle, department FROM USER WHERE username = '" + usernameField.getText() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainFXApplication.getStage());
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText("Incorrect username!");
                alert.showAndWait();
            } else {
                if (checkUserCredentials(rs)) {
                    currentUser.set_username(rs.getString("username"));
                    currentUser.set_fullname(rs.getString("fullname"));
                    currentUser.set_ban(rs.getInt("ban"));
                    currentUser.set_type(rs.getString("type"));
                    currentUser.set_emailaddress(rs.getString("emailaddress"));
                    currentUser.set_homeaddress(rs.getString("homeaddress"));
                    currentUser.set_company(rs.getString("company"));
                    currentUser.set_jobtitle(rs.getString("jobtitle"));
                    currentUser.set_department(rs.getString("department"));
                    if (currentUser.get_type().equals(UserTypeEnum.ADMIN.toString())) {
                        mainFXApplication.showADMINMainApplicationScreen();
                    } else {
                        mainFXApplication.showMainApplicationScreen();
                    }

                }
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

    private boolean checkUserCredentials(ResultSet rs) {
        try {
            if (rs.getInt("attempt") > 2 && !currentUser.get_type().equals(UserTypeEnum.ADMIN.toString())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainFXApplication.getStage());
                alert.setTitle("Account Locked!");
                alert.setHeaderText("You have more than 3 incorrect logins!");
                alert.setContentText("Incorrect password!");
                alert.showAndWait();
                return false;
            } else {
                if ((rs.getString("password").equals(passwordField.getText()))) {
                    Connection conn = null;
                    Statement stmt = null;
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
                    stmt = conn.createStatement();
                    String sql = "UPDATE USER SET attempt = '0' WHERE username = '" + usernameField.getText() + "'";
                    stmt.executeUpdate(sql);
                    return true;
                } else {
                    int newAttempt = rs.getInt("attempt") + 1;
                    Connection conn = null;
                    Statement stmt = null;
                    Class.forName("com.mysql.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
                    stmt = conn.createStatement();
                    String sql = "UPDATE USER SET attempt = '" + newAttempt + "' WHERE username = '" + usernameField.getText() + "'";
                    stmt.executeUpdate(sql);

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainFXApplication.getStage());
                    alert.setTitle("Invalid Fields");
                    alert.setHeaderText("Please correct invalid fields");
                    alert.setContentText("Incorrect password!");
                    alert.showAndWait();
                    return false;


                }
            }
        } catch (Exception e) {
            return false;
        }

    }
}
