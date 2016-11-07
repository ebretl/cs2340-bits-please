package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import fxapp.MainFXApplication;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * controls the delete account screen
 */
public class DeleteAccountScreenController {

    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    TableView<User> mainTable;

    @FXML
    TableColumn<User, String> username;

    @FXML
    TableColumn<User, String> fullname;

    @FXML
    TableColumn<User, String> type;

    @FXML
    TableColumn<User, String> email;

    @FXML
    TableColumn<User, String> address;


    private ObservableList<User> mainList;
    /**
     * Gets an instance of the current main application running
     * @param main the instance of the current application running
     *             A reference of this is stored in a local variable
     * @param currentUser the current user using the app
     */
    public void setMainApp(MainFXApplication main, User currentUser) {
        mainFXApplication = main;
        this.currentUser = currentUser;
        initializeTable();
    }

    @FXML
    private void initializeTable() {
        mainList = getUsers();
        if ((mainList == null) || mainList.isEmpty()) {
            mainFXApplication.showADMINMainApplicationScreen();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Oops!");
            alert.setHeaderText("There are no accounts to delete!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        } else {
            mainTable.setId("mainTable");
            username.setCellValueFactory(new PropertyValueFactory<>("_username"));
            fullname.setCellValueFactory(new PropertyValueFactory<>("_fullname"));
            type.setCellValueFactory(new PropertyValueFactory<>("_type"));
            email.setCellValueFactory(new PropertyValueFactory<>("_emailaddress"));
            address.setCellValueFactory(new PropertyValueFactory<>("_homeaddress"));
            mainTable.setItems(mainList);
        }
    }

    @FXML
    private void backPressed() {
        mainFXApplication.showADMINMainApplicationScreen();
    }

    @FXML
    private void deletePressed() {
            ObservableList<User> selectedDelete =  mainTable.getSelectionModel().getSelectedItems();
            Connection conn;
            Statement stmt;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://d" +
                        "b4free.net:3306/bitsplease", "bitsplease", "bitsplease");
                stmt = conn.createStatement();
                for (User current : selectedDelete) {
                    String sql = "DELETE FROM USER WHERE username = '" + current.get_username() + "';";
                    stmt.executeUpdate(sql);
                    mainList.remove(current);
                }
                if (mainList.isEmpty()) {
                    mainFXApplication.showADMINMainApplicationScreen();

                }
            } catch (Exception ignored) {

            }
    }

    private ObservableList<User> getUsers() {
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT username, password, " +
                    "fullname, ban, attempt, type, emailad" +
                    "dress, homeaddress, company, jobtitle, department FROM USER";
            ResultSet rs = stmt.executeQuery(sql);
            List<User> reportList = new ArrayList<>();
            while (rs.next()) {
                String username = rs.getString("username");
                String curUser = currentUser.get_username();
                if (!username.equals(curUser)) {
                    User temp = new User(rs.getString("username"),
                            rs.getString("fullname"), rs.getInt("ban"), rs.getString("type"),
                            rs.getString("emailaddress"), rs.getString("homeaddress"),
                            rs.getString("company"), rs.getString("jobtitle"), rs.getString("department"));
                    reportList.add(temp);
                }

            }
            return FXCollections.observableList(reportList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
