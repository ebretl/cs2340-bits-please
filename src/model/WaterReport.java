package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class WaterReport {
    private final SimpleIntegerProperty _reportnumber = new SimpleIntegerProperty();
    private final StringProperty _date = new SimpleStringProperty();
    private final StringProperty _time = new SimpleStringProperty();
    private final StringProperty _name = new SimpleStringProperty();
    private final StringProperty _location = new SimpleStringProperty();
    private final StringProperty _watertype = new SimpleStringProperty();
    private final StringProperty _watercondition = new SimpleStringProperty();

    /**
     * Gets the report number
     * @return the report number
     */
    public int get_reportnumber() {
        return _reportnumber.get();
    }

    /**
     * Sets all the paramters of the water report
     * @param reportnumber The report number of the water report
     * @param date date of the water report
     * @param time time of the report
     * @param name name of the user who submitted it
     * @param location location of the water
     * @param watertype the type of body of water
     * @param waterconditon the condition of the water
     */
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
