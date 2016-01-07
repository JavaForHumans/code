package variables;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lwdthe1
 */
public class Variables {
    public static void main(String[] args) {
        /*This is a multi-line comment. It won't be compiled by the 
        compiler*/

        /*Declare and instantiate a single variable to store    
        information about our bank account*/
        String bankAccount = "identifier: 1234567828; "
        + "balance: 1301.78; passcode: 9889; active: true; " 
        + "owner's name: Lincoln Daniel; " 
        + "owner's address: 325 JavaPlace, UnitedJavas 19081; "
        + "owner's birthday: May 26, 1993";
        

        /*print out the data stored in the bankAccount String 
        variable.
        All the information in the bankAccount variable will be 
        printed.*/
        System.out.println("Our bank account: " + bankAccount);
    }
}
