/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namingConventions;

/**
* A class that holds information about 
* a human being and its functions
*/
public class Human {
    //variables that hold information about the human
    String name;
    String birthDay;
    String gender;

    public Human(String name, String birthDay, String gender){
        this.name = name;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    /**
    * A method that makes the human say hello with his/her name.
    */
    public void sayHello(){
        System.out.println("Hello, world!");
        System.out.print(" My name is " + name);
    }
}
