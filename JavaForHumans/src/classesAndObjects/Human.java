package classesAndObjects;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lwdthe1
 */
public class Human {
    //the fields of a Human
    String name;//holds the name of the human instance
    int age;//holds the age of the human instance
    int height;//holds the height of the human instance

    //the constructor is a method that is called to create a new instance of a Human
    public Human(String name,int age, int height){
        //set this object's name with the provided name
        this.name = name;
        //set this object's age with the provided age
        this.age = age;
        //set this object's height with the provided height
        this.height = height;

        System.out.println("Created a new Human instance.\n");
    }

    //every instance of a Human can speak
    public void speak(){
        System.out.println("Hi my name is " + name + ".\n" +
                "I am " + age + "years old and " + height + " feet tall.");
    }

    //set the age of the Human object
    public void setName(String newName) {
        this.name = newName;
    }

    //return the age of the Human object
    public int getAge() {return age;}
}