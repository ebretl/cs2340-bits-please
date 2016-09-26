package model;

/**
 * Created by Abhay Dalmia on 9/26/2016.
 */
public enum WaterType {
    BOTTLED("Bottled"), WELL("Well"), STREAM("Stream"), LAKE("Lake"), SPRING("Spring"), OTHER("Other");
    private String stringRep;

    private WaterType(String stringRep) {
        this.stringRep = stringRep;
    }

    @Override
    public String toString() {
        return stringRep;
    }
}
