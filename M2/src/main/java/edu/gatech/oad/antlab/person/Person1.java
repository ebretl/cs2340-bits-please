package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 1
 *  returns their name and a
 *  modified string 
 *  
 *  @author Evan Bretl
 *  @version 1.2
 */

public class Person1 {
    /** Holds the persons real name */
    private String name;

    /**
     * The constructor, takes in the persons
     * name
     * @param pname the person's real name
     */
    public Person1(String pname) {
        name = pname;
    }

    /**
     * This method should take the string
     * input and return its characters rotated
     * 2 positions.
     * given "gtg123b" it should return
     * "g123bgt".
     *
     * @param input the string to be modified
     * @return the modified string
     */
    private String calc(String input) {
        if(input.length() > 2) {
            return input.substring(2,input.length()) 
                 + input.substring(0,2);
        } else {
            return input;
        }
    }
    
    /**
     * Return a string rep of this object
     * that varies with an input string
     *
     * @param input the varying string
     * @return the string representing the 
     *         object
     */
    public String toString(String input) {
      return name + " " + calc(input);
    }

    /**
     * Test the functionality of this class 
     * in a static method. Should print:
     * Evan Bretl a
     * Evan Bretl ab
     * Evan Bretl cab
     * Evan Bretl cdefg123ab
     */
    public static void main(String[] args) {
        Person1 evan = new Person1("Evan Bretl");
        System.out.println(evan.toString("a"));
        System.out.println(evan.toString("ab"));
        System.out.println(evan.toString("abc"));
        System.out.println(evan.toString("abcdefg123"));
    }

}
