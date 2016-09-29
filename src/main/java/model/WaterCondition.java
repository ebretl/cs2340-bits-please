package model;

/**
 * Created by Abhay Dalmia on 9/26/2016.
 */
public enum WaterCondition {
    WASTE("Waste"), TREATABLEC("Treatable-Clear"),TREATABLEM("Treatable-Muddy"), POTABLE("Potable");

    private String stringRep;
    private WaterCondition(String stringRep) {
        this.stringRep = stringRep;
    }

    @Override
    public String toString() {
        return stringRep;
    }
}
