package staticClassFieldsAndMethods;

import java.util.Scanner;

/**
 * Created by lwdthe1 on 1/6/2016.
 */
public class SocietyProgram {
    public static void main(String[] args) {
        System.out.println("# Starting Society Program ...");
        System.out.println("\n\n# Making humans ");

        Human human1 = new Human("Tyrone","male");
        Human human2 = new Human("Daniel","male");
        Human human3 = new Human("Tabitha","female");
        Human human4 = new Human("Luis","male");
        Human human5 = new Human("Carolyn","female");
        Human human6 = new Human("Imani","female");
        Human human7 = new Human("Julia","female");

        System.out.println("# All humans created.\nSociety is Thriving!");
    }
}
