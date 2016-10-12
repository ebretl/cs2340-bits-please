package model;


public enum WaterConditionEnum {
    WASTE("Waste"), TREATABLEC("Treatable-Clear"),TREATABLEM("Treatable-Muddy"), POTABLE("Potable");

    private String stringRep;
    private WaterConditionEnum(String stringRep) {
        this.stringRep = stringRep;
    }

    @Override
    public String toString() {
        return stringRep;
    }
}
