package model;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UserNetwork {

    private HashSet<User> users;

    public UserNetwork() {
        users = new HashSet();

        //add default user. Cause, ya know, security
        User def = new User("user", "pass");
        addUser(def);
    }

    public void addUser(User user) {
        if(!(this.containsUser(user))) {
            users.add(user);
        } //todo make this throw an alert if it is a repeat username
    }

    public boolean containsUser(User user) {
        return users.contains(user);
    }
}
