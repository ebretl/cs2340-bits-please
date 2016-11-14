package test.java.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.User;
import model.UserManager;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by evankaplan on 11/13/16.
 */
public class UserManagerTest {
    @Test
    public void getUsersReturnsCorrectOutput() {
        UserManager manager = new UserManager();

        User user1 = new User();

        user1.set_username("Evan");
        user1.set_fullname("Evan Kaplan");
        user1.set_ban(0);
        user1.set_type("user");
        user1.set_emailaddress("ekaplan6@gatech.edu");
        user1.set_homeaddress("Atlanta");
        user1.set_company("Georgia Tech");
        user1.set_jobtitle("Software Engineer");
        user1.set_department("CS 2340");

        ObservableList<User> list1_actual = manager.getUsers(user1);
        List<User> user_list_expexted = new ArrayList<>();

        User user_expected = new User("Evan", "Evan Kaplan", 0, "user",
                "ekaplan6@gatech.edu", "Atlanta", "Georgia Tech",
                "Software Engineer", "CS 2330");

        user_list_expexted.add(user_expected);

        ObservableList<User> list1_expected = FXCollections.observableList(user_list_expexted);

        //assertEquals("getUser() returns wrong List", list1_expected.get(0).get_username(), list1_actual.get(0).get_username());
        //TODO: see if the rest is equal




    }

}