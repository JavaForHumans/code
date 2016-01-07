package classesAndObjects;

import java.util.Scanner;

/**
 * This class has the main method to run the test program.
 * Run this class to run the demo
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Starting Human Demo ...");
        System.out.println("\nWhat's your name?: ");
        String name = scanner.nextLine();//get a string from user's keyboard
        System.out.println("\nHow old are you?: ");
        int age = scanner.nextInt();//get a number from user
        System.out.println("\nHow tall are you?: ");
        int height = scanner.nextInt();//get a number from the user

        //create an object, a new instance of a Human
        Human human = new Human(name, age, height);

        human.speak();

        System.out.println("\nDo you want to change your name?: ");
        String decision = scanner.nextLine();
        if(decision.equalsIgnoreCase("yes")) {
            System.out.println("\nWhat do you want your new name to be?: ");
            String newName = scanner.nextLine();//get the new name from user
            human.setName(newName);
        }

        //see if the human is old enough to drive
        if(human.getAge() >= 16) {
            System.out.println("Hey, you are old enough to drive.");
        }

        System.out.println("I have to go, but can you remind me of your name and age?");
        //make the human
        human.speak();
    }
}
