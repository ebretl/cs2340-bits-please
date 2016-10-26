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
import model.UserManager;
import model.UserTypeEnum;
import model.WaterReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;


public class BlockUserScreenController {

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
     */
    public void setMainApp(MainFXApplication main, User currentUser) {
        mainFXApplication = main;
        this.currentUser = currentUser;
        initializeTable();
    }

    @FXML
    private void initializeTable() {
        mainList = new UserManager().getUnblockedUsers();
        if (mainList == null || mainList.size() == 0) {
            mainFXApplication.showADMINMainApplicationScreen();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Oops!");
            alert.setHeaderText("There are no users to block!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        } else {
            mainTable.setId("mainTable");
            username.setCellValueFactory(new PropertyValueFactory<User, String>("_username"));
            fullname.setCellValueFactory(new PropertyValueFactory<User, String>("_fullname"));
            type.setCellValueFactory(new PropertyValueFactory<User, String>("_type"));
            email.setCellValueFactory(new PropertyValueFactory<User, String>("_emailaddress"));
            address.setCellValueFactory(new PropertyValueFactory<User, String>("_homeaddress"));
            mainTable.setItems(mainList);
        }
    }

    @FXML
    private void backPressed() {
        mainFXApplication.showADMINMainApplicationScreen();
    }

    @FXML
    private void blockPressed() {
            ObservableList<User> selectedDelete =  mainTable.getSelectionModel().getSelectedItems();
            Connection conn = null;
            Statement stmt = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
                stmt = conn.createStatement();
                for (int i = 0; i < selectedDelete.size(); i++) {
                    User current = selectedDelete.get(i);
                    String sql = "UPDATE USER SET attempt = '99' WHERE username = '" + current.get_username() +"';";
                    stmt.executeUpdate(sql);
                    mainList.remove(current);
                }
                if (mainList.size() == 0) {
                    mainFXApplication.showADMINMainApplicationScreen();

                }
            } catch (Exception e) {

            }
    }
}
