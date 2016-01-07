package operators;

/**
 * Created by lwdthe1 on 12/18/2015.
 */
public class Operators {
    public static void main(String[] args) {
        //use = to set a variable equal to my name
        String myName = "[enter your name here]";
        System.out.print("My name is " + myName);
        System.out.println("\n");

        //use + symbol to add two numbers
        int numApples = 100;
        int numOranges = 10;
        int numFruits = numApples + numOranges;
        System.out.println("Number of fruits = " + numFruits);
        System.out.println("\n");

        /*use + symbol to connect, concatenate, strings*/
        String firstName = "Lincoln";
        String lastName = "Daniel";
        String fullName = firstName + " " + lastName;
        System.out.printf("My full name is %s ", fullName);
        System.out.println("\n");

        //use - symbol to subtract two numbers
        int four = 9 - 5;
        System.out.printf("%d - %d = %d", 9, 5, four);
        System.out.println("\n");

        //use * to multiply two numbers
        int oneHundred = 20 * 5;
        System.out.printf("%d * %d = %d", 20, 5, oneHundred);
        System.out.println("\n");

        //use / to divide two numbers
        int six = 12 / 2;
        System.out.printf("%d / %d = %d", 12, 2, six);

        int count = 0;

        /*Combining Additive Operators
        & the Assignment Operator*/

        count++;//count now equals (0 + 1) = 1
        count += 6;//count now equals (1 + 6) = 7
        count--;//count now equals (7 - 1) = 6
        count -= 2;//count now equals (6- 2) = 4
        System.out.printf("\n\nCount equals " + count);

       /*Coming Multiplicative Operators
       with the Assignment Operator*/

        count *= 5;//count now equals (4 * 5) = 20;
        count /= 10;//count now equals (20 / 10) = 2;
        System.out.printf("\nCount equals " + count);
    }
}
