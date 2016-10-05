package main.model;


public enum OverallConditionEnum {
    SAFE("Safe"), TREATABLE("Treatable"), UNSAFE("Unsafe");

    private String stringRep;

    private OverallConditionEnum(String stringRep) {
        this.stringRep  = stringRep;
    }

    @Override
    public String toString() {
        return stringRep;
    }
}
