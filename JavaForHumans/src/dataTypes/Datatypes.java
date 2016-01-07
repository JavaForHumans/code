package dataTypes;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lwdthe1
 */
public class Datatypes {

    /**
     * @params args
     */
    public static void main(String[] args) {
        /*Declare and instantiate nine different variables to store our account's nine different data points*/

        /*declare an int variable to store our account identifier*/
        int id = 1234567828;

        /*declare a double variable to store our account balance*/
        double balance = 1301.41;

        /*declare a int variable to store pass code*/
        int passCode = 9889;

        /*declare a boolean variable to store our account's active status*/
        boolean isActive = true;

        /*declare a String variable to store our account owner's name*/
        String ownerName = "Lincoln W Daniel";

        /*declare a String variable to store our account owner's address*/
        String ownerAddress = "325 JavaPlace, UnitedJavas 19081";

        /*declare an int variable to store our account owner's birth year*/
        int ownerBirthYear = 1993;

        /*declare a String variable to store our account owner's birth month*/
        String ownerBirthMonth = "May";

        /*declare an int variable to store our account owner's birth day*/
        int ownerBirthDay = 26;
        
        /*print out our bank account's balance*/
        System.out.println("Our bank account's unique identifier (id): " + id);

        /*print out our bank account's balance*/
        System.out.println("Our bank account's balance: " + balance);

        /*add 600 dollars and 50 cents to our bank account*/
        balance = balance + 600.50;

        System.out.println("");
        /*print out our new bank account balance*/
        System.out.println("Our account's new balance: $" + balance);
    }
}
