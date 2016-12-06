package controller;

import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import model.User;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Properties;
import javax.mail.Session;
import javax.mail.MessagingException;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.AddressException;


/**
 * Controls the Forgot Password Screen
 */
public class ForgotPasswordScreenController {
    private MainFXApplication mainFXApplication;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    private User currentUser;

    /**
     * @param main a reference (link) to our main class
     * @param currentUser the current user using the app
     */
    public void setMainApp(MainFXApplication main, User currentUser) {
        mainFXApplication = main;
        this.currentUser = currentUser;
    }

    @FXML
    private void backPressed() {
        mainFXApplication.showLoginScreen();
    }

    @FXML
    private void submitPressed() {

        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT username, password, fullname, " +
                    "ban, attempt, type, emailaddress, homeaddress, company," +
                    " jobtitle, department FROM USER WHERE username = '" + usernameField.getText().trim() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainFXApplication.getStage());
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText("Incorrect username!");
                alert.showAndWait();
            } else {
                createEmail(rs);
                mainFXApplication.showLoginScreen();
            }
        } catch (Exception e) {
            mainFXApplication.showDatabaseError();
            e.printStackTrace();
        } finally {
            close (stmt, conn);

        }

    }




    private static void sendEmail(String sendTo, String password) {
        String from = "bitsplease86@gmail.com";
        String pass = "bitsplease";
        String[] to = {sendTo};
        String subject = "Current Password: Team 76";
        String body = "Your current password is:\n\n\n" + password;


        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (InternetAddress toAddres : toAddress) {
                message.addRecipient(Message.RecipientType.TO, toAddres);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }




    private void createEmail(ResultSet rs) throws Exception {
        String emailaddress = rs.getString("emailaddress");

        if (emailaddress.equals(emailField.getText())) {
            String password = rs.getString("password");

            sendEmail(emailaddress, password);

        } else {
            badFields();
        }
    }


    private void close(Statement stmt, Connection conn) {
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



    private void badFields() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(mainFXApplication.getStage());
        alert.setTitle("Invalid Fields");
        alert.setHeaderText("Please correct invalid fields");
        alert.setContentText("Incorrect email!");
        alert.showAndWait();
    }
}
