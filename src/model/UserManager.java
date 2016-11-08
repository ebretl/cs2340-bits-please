package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the user information. This is service provider.
 */
public class UserManager {
    /**
     * Accesses the database to get all users that have not been banned.
     * This finds only users with the actual "user" distinction.
     * @return an observable list of all the unbanned users
     */
    public ObservableList<User> getUnbannedUsers() {
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT username, password, fullname, ban, attempt, " +
                    "type, emailaddress, homeaddress, company, jobtitle, department FROM USER";
            ResultSet rs = stmt.executeQuery(sql);
            List<User> reportList = new ArrayList<>();
            while (rs.next()) {
                String userType = UserTypeEnum.USER.toString();
                String responseType = rs.getString("type");
                if (userType.equals(responseType) && (rs.getInt("ban") == 0)) {
                    User temp = new User(rs.getString("username"), rs.getString("fullname"),
                            rs.getInt("ban"), rs.getString("type"), rs.getString("emailaddress"),
                            rs.getString("homeaddress"), rs.getString("company"), rs.getString("jobtitle"),
                            rs.getString("department"));
                    reportList.add(temp);
                }

            }
            return FXCollections.observableList(reportList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Accesses the database to get all users that have been banned.
     * This finds only users with the actual "user" distinction.
     * @return an observable list of all the banned users
     */
    public ObservableList<User> getBannedUsers() {
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT username, password, fullname, ban, attempt, type, emailaddress, " +
                    "homeaddress, company, " +
                    "jobtitle, department FROM USER";
            ResultSet rs = stmt.executeQuery(sql);
            List<User> reportList = new ArrayList<>();
            while (rs.next()) {
                String responseType = rs.getString("type");
                String userType = UserTypeEnum.USER.toString();
                if (userType.equals(responseType) && (rs.getInt("ban") == 1)) {
                    User temp = new User(rs.getString("username"), rs.getString("fullname"), rs.getInt("ban"),
                            rs.getString("type"), rs.getString("emailaddress"), rs.getString("homeaddress"),
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

    /**
     * Accesses the database to get all users that have been blocked.
     * This finds users with the actual "user", "manager", and "worker"
     * distinctions.
     * @return an observable list of all the blocked users
     */
    public ObservableList<User> getUnblockedUsers() {
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT username, password, fullname, ban, attempt, type, " +
                    "emailaddress, homeaddress, company, jobtitle, department FROM USER";
            ResultSet rs = stmt.executeQuery(sql);
            List<User> reportList = new ArrayList<>();
            while (rs.next()) {
                String responseType = rs.getString("type");
                String adminType = UserTypeEnum.ADMIN.toString();
                if (!adminType.equals(responseType) && (rs.getInt("attempt") < 3)) {
                    User temp = new User(rs.getString("username"),
                            rs.getString("fullname"), rs.getInt("ban"), rs.getString("type"),
                            rs.getString("emailaddress"), rs.getString("homeaddress"), rs.getString("company"),
                            rs.getString("jobtitle"), rs.getString("department"));
                    reportList.add(temp);
                }

            }
            return FXCollections.observableList(reportList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Accesses the database to get all users that have not been blocked.
     * This finds users with the actual "user", "manager", and "worker" distinctions.
     * @return an observable list of all the unblocked users
     */
    public ObservableList<User> getBlockedUsers() {
        Connection conn;
        Statement stmt;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bitsplease", "bitsplease", "bitsplease");
            stmt = conn.createStatement();
            String sql = "SELECT username, password, fullname, ban, attempt, type, emailaddress, homeaddress, " +
                    "company, jobtitle, department FROM USER";
            ResultSet rs = stmt.executeQuery(sql);
            List<User> reportList = new ArrayList<>();
            while (rs.next()) {
                String responseType = rs.getString("type");
                String adminType = UserTypeEnum.ADMIN.toString();
                if (!adminType.equals(responseType) && (rs.getInt("attempt") > 2)) {
                    User temp = new User(rs.getString("username"), rs.getString("fullname"), rs.getInt("ban"),
                            rs.getString("type"), rs.getString("emailaddress"), rs.getString("homeaddress"),
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
