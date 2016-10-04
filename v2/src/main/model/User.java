package main.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class User {
    private StringProperty _username = new SimpleStringProperty();
    private StringProperty _fullname = new SimpleStringProperty();
    private SimpleIntegerProperty _ban = new SimpleIntegerProperty();
    private StringProperty _type = new SimpleStringProperty();
    private StringProperty _emailaddress = new SimpleStringProperty();
    private StringProperty _homeaddress = new SimpleStringProperty();
    private StringProperty _company = new SimpleStringProperty();
    private StringProperty _jobtitle = new SimpleStringProperty();
    private StringProperty _department = new SimpleStringProperty();

    public void User(String username, String fullname, int ban, String type, String emailaddress, String homeaddress, String company, String jobtitle, String department) {
        _username.set(username);
        _fullname.set(fullname);
        _ban.set(ban);
        _type.set(type);
        _emailaddress.set(emailaddress);
        _homeaddress.set(homeaddress);
        _company.set(company);
        _jobtitle.set(jobtitle);
        _department.set(department);
    }

    public String get_username() {
        return _username.get();
    }

    public void set_username(String _username) {
        this._username.set(_username);
    }

    public String get_fullname() {
        return _fullname.get();
    }

    public void set_fullname(String _fullname) {
        this._fullname.set(_fullname);
    }

    public int get_ban() {
        return _ban.get();
    }

    public void set_ban(int _ban) {
        this._ban.set(_ban);
    }

    public String get_type() {
        return _type.get();
    }

    public void set_type(String _type) {
        this._type.set(_type);
    }

    public String get_emailaddress() {
        return _emailaddress.get();
    }

    public void set_emailaddress(String _emailaddress) {
        this._emailaddress.set(_emailaddress);
    }

    public String get_homeaddress() {
        return _homeaddress.get();
    }

    public void set_homeaddress(String _homeaddress) {
        this._homeaddress.set(_homeaddress);
    }

    public String get_company() {
        return _company.get();
    }

    public void set_company(String _company) {
        this._company.set(_company);
    }

    public String get_jobtitle() {
        return _jobtitle.get();
    }

    public void set_jobtitle(String _jobtitle) {
        this._jobtitle.set(_jobtitle);
    }

    public String get_department() {
        return _department.get();
    }

    public void set_department(String _department) {
        this._department.set(_department);
    }







}
