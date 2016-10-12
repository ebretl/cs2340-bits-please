package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import fxapp.MainFXApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.QualityReport;
import model.User;
import model.UserTypeEnum;
import model.WaterReport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ViewQualityReportScreenController {
    private MainFXApplication mainFXApplication;

    private User currentUser;

    @FXML
    TableView<QualityReport> mainTable;

    @FXML
    TableColumn<QualityReport, Integer> reportnumber;

    @FXML
    TableColumn<QualityReport, String> date1;

    @FXML
    TableColumn<QualityReport, String> time1;

    @FXML
    TableColumn<QualityReport, String> location1;

    @FXML
    TableColumn<QualityReport, String> overallcondition;

    @FXML
    TableColumn<QualityReport, Integer> virusPPM;

    @FXML
    TableColumn<QualityReport, Integer> contaminantPPM;

    private ObservableList<QualityReport> mainList;
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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainFXApplication.getStage());
            alert.setTitle("Error!");
            alert.setHeaderText("There are no reports in database to show!");
            alert.setContentText("Add a report first!");
            alert.showAndWait();
            throw new IllegalArgumentException();
        } else {
            reportnumber.setCellValueFactory(new PropertyValueFactory<QualityReport, Integer>("_reportnumber"));
            date1.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("_date"));
            time1.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("_time"));
            location1.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("_location"));
            overallcondition.setCellValueFactory(new PropertyValueFactory<QualityReport, String>("_overallcondition"));
            virusPPM.setCellValueFactory(new PropertyValueFactory<QualityReport, Integer>("_virusPPM"));
            contaminantPPM.setCellValueFactory(new PropertyValueFactory<QualityReport, Integer>("_contaminantPPM"));

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
            ObservableList<QualityReport> selectedDelete =  mainTable.getSelectionModel().getSelectedItems();
            Connection conn = null;
            Statement stmt = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
                stmt = conn.createStatement();
                for (int i = 0; i < selectedDelete.size(); i++) {
                    QualityReport current = selectedDelete.get(i);
                    String sql = "DELETE FROM QUALITYREPORT WHERE reportnumber = '" + current.get_reportnumber() +"';";
                    stmt.executeUpdate(sql);
                    mainList.remove(current);
                }
                if (mainList.size() == 0) {
                    mainFXApplication.showMainApplicationScreen();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainFXApplication.getStage());
                    alert.setTitle("All Reports Deleted!");
                    alert.setHeaderText("There are no reports in database to show!");
                    alert.setContentText("You'll be redirected to the Main Application Screen.");
                    alert.showAndWait();
                }
            } catch (Exception e) {

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

    private ObservableList<QualityReport> getAllReports() {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT reportnumber, name, date, time, location, overallcondition, virusPPM, contaminantPPM FROM QUALITYREPORT";
            ResultSet rs = stmt.executeQuery(sql);
            List<QualityReport> reportList = new ArrayList<>();
            while (rs.next()) {
                QualityReport temp = new QualityReport(rs.getInt("reportnumber"), rs.getString("date"), rs.getString("time"), rs.getString("name"), rs.getString("location"), rs.getString("overallcondition"), rs.getInt("virusPPM"), rs.getInt("contaminantPPM"));
                reportList.add(temp);
            }
            return FXCollections.observableList(reportList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
