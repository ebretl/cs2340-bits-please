package model;


import java.util.ArrayList;
import java.util.List;

public class UserNetwork {

    private List<User> users;

    public UserNetwork() {
        //todo implement hashCode() for Users to allow HashSet here
        users = new ArrayList<>();

        //add default user. Cause, ya know, security
        addUser(new User("user", "pass"));
    }

    public void addUser(User user) {
        //todo make sure user does not exist already
        users.add(user);
    }

    public boolean containsUser(User user) {
        return users.contains(user);
    }

}
