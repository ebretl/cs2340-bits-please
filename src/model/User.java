package model;

public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        //todo restrict inputs
        this.username = username;
        this.password = password;
    }

    public boolean checkCredentials(String usr, String pass) {
        return username.equals(usr) && password.equals(pass);
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(null == other) return false;
        if(!(other instanceof User)) return false;
        return this.username.equals(((User) other).username);
    }

    public int hashCode() {
        int hash = username.hashCode();
        hash = hash * 31 + password.hashCode();
        return hash;
    }
    
    protected String getUsername() {
        return username;
    }

}
