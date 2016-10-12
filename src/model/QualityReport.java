package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class QualityReport {
    private SimpleIntegerProperty _reportnumber = new SimpleIntegerProperty();
    private StringProperty _date = new SimpleStringProperty();
    private StringProperty _time = new SimpleStringProperty();
    private StringProperty _name = new SimpleStringProperty();
    private StringProperty _location = new SimpleStringProperty();
    private StringProperty _overallcondition = new SimpleStringProperty();
    private SimpleIntegerProperty _virusPPM = new SimpleIntegerProperty();
    private SimpleIntegerProperty _contaminantPPM = new SimpleIntegerProperty();

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

    public String get_overallcondition() {
        return _overallcondition.get();
    }

    public void set_overallcondition(String _overallcondition) {
        this._overallcondition.set(_overallcondition);
    }

    public int get_virusPPM() {
        return _virusPPM.get();
    }

    public void set_virusPPM(int _virusPPM) {
        this._virusPPM.set(_virusPPM);
    }

    public int get_contaminantPPM() {
        return _contaminantPPM.get();
    }

    public void set_contaminantPPM(int _contaminantPPM) {
        this._contaminantPPM.set(_contaminantPPM);
    }

    public QualityReport(int reportnumber, String date, String time, String name, String location, String overallcondition, int virusPPM, int contaminantPPM) {
        _reportnumber.set(reportnumber);
        _date.set(date);
        _time.set(time);
        _name.set(name);
        _location.set(location);
        _overallcondition.set(overallcondition);
        _virusPPM.set(virusPPM);
        _contaminantPPM.set(contaminantPPM);

    }
}
