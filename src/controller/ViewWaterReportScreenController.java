package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.UserTypeEnum;
import model.WaterReport;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ViewWaterReportScreenController {
    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    TableView<WaterReport> mainTable;

    @FXML
    TableColumn<WaterReport, Integer> reportnumber;

    @FXML
    TableColumn<WaterReport, String> date1;

    @FXML
    TableColumn<WaterReport, String> time1;

    @FXML
    TableColumn<WaterReport, String> location1;

    @FXML
    TableColumn<WaterReport, String> watertype;

    @FXML
    TableColumn<WaterReport, String> watercondition;

    private ObservableList<WaterReport> mainList;
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
        mainList = getAllReports();
        if (mainList == null || mainList.size() == 0) {
            mainFXApplication.showMainApplicationScreen();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Error!");
            alert.setHeaderText("There are no reports in database to show!");
            alert.setContentText("Add a report first!");
            alert.showAndWait();
        } else {
            reportnumber.setCellValueFactory(new PropertyValueFactory<>("_reportnumber"));
            date1.setCellValueFactory(new PropertyValueFactory<>("_date"));
            time1.setCellValueFactory(new PropertyValueFactory<>("_time"));
            location1.setCellValueFactory(new PropertyValueFactory<>("_location"));
            watertype.setCellValueFactory(new PropertyValueFactory<>("_watertype"));
            watercondition.setCellValueFactory(new PropertyValueFactory<>("_watercondition"));
            mainTable.setItems(mainList);
        }
    }

    @FXML
    private void backPressed() {
        mainFXApplication.showMainApplicationScreen();
    }

    @FXML
    private void deletePressed() {
        if (currentUser.get_type().equals(UserTypeEnum.MANAGER.toString())) {
            ObservableList<WaterReport> selectedDelete =  mainTable.getSelectionModel().getSelectedItems();
            Connection conn;
            Statement stmt;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
                stmt = conn.createStatement();
                for (WaterReport current : selectedDelete) {
                    String sql = "DELETE FROM WATERREPORT WHERE reportnumber = '" + current.get_reportnumber() + "';";
                    stmt.executeUpdate(sql);
                    mainList.remove(current);
                }
            } catch (Exception ignored) {

            }


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Error!");
            alert.setHeaderText("Only managers can delete reports!!");
            alert.setContentText("Talk to a system admin if you think this is in error!");
            alert.showAndWait();
        }
    }

    private ObservableList<WaterReport> getAllReports() {
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT reportnumber, name, date, time, location, watertype, watercondition FROM WATERREPORT";
            ResultSet rs = stmt.executeQuery(sql);
            List<WaterReport> reportList = new ArrayList<>();
            while (rs.next()) {
                WaterReport temp = new WaterReport(rs.getInt("reportnumber"), rs.getString("date"), rs.getString("time"), rs.getString("name"), rs.getString("location"), rs.getString("watertype"), rs.getString("watercondition"));
                reportList.add(temp);
            }
            return FXCollections.observableList(reportList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
