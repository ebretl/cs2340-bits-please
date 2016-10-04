package main.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Abhay Dalmia on 9/26/2016.
 */
public class WaterReport {
    private SimpleIntegerProperty _reportnumber = new SimpleIntegerProperty();
    private StringProperty _date = new SimpleStringProperty();
    private StringProperty _time = new SimpleStringProperty();
    private StringProperty _name = new SimpleStringProperty();
    private StringProperty _location = new SimpleStringProperty();
    private StringProperty _watertype = new SimpleStringProperty();
    private StringProperty _watercondition = new SimpleStringProperty();

    public int get_reportnumber() {
        return _reportnumber.get();
    }

    public void set_reportnumber(int _reportnumber) {
        this._reportnumber.set(_reportnumber);
    }

    public String get_date() {
        return _date.get();
    }

    public void set_date(String _date) {
        this._date.set(_date);
    }

    public String get_time() {
        return _time.get();
    }

    public void set_time(String _time) {
        this._time.set(_time);
    }

    public String get_name() {
        return _name.get();
    }

    public void set_name(String _name) {
        this._name.set(_name);
    }

    public String get_location() {
        return _location.get();
    }

    public void set_location(String _location) {
        this._location.set(_location);
    }

    public String get_watertype() {
        return _watertype.get();
    }

    public void set_watertype(String _watertype) {
        this._watertype.set(_watertype);
    }

    public String get_watercondition() {
        return _watercondition.get();
    }

    public void set_watercondition(String _watercondition) {
        this._watercondition.set(_watercondition);
    }

    public WaterReport(int reportnumber, String date, String time, String name, String location, String watertype, String waterconditon) {
        _reportnumber.set(reportnumber);
        _date.set(date);
        _time.set(time);
        _name.set(name);
        _location.set(location);
        _watertype.set(watertype);
        _watercondition.set(waterconditon);
    }
}
