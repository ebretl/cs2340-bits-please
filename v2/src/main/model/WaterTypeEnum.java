package main.model;


public enum WaterTypeEnum {
    BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring"), OTHER("Other");
    private String stringRep;

    private WaterTypeEnum(String stringRep) {
        this.stringRep = stringRep;
    }

    @Override
    public String toString() {
        return stringRep;
    }
}
