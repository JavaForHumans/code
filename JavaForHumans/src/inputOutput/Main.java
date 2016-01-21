package inputOutput;

import java.util.Scanner;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userInput = "startInConsole";
        while(!userInput.equals("exit")) {
            String userName = "";
            int userAge = 0;
            System.out.println("Enter your name to begin: ");
            if(scanner.hasNext()) {
                userName = scanner.next();
            }

            System.out.println("Enter your age: ");
            if(scanner.hasNextInt()) {
                userAge = scanner.nextInt();
            }

            if(userAge >= 13) {
                System.out.println("Hi " + userName );
                System.out.println("You are old enough to play our game.");
                //play fun game here

                System.out.println("GUIGameApplication over!");

                System.out.println("Enter YES to play again or EXIT to exit.");
                if(scanner.hasNext()) {
                    userInput = scanner.next().toLowerCase();
                }
                System.out.println("-----------------------\n");
            } else {
                System.out.println("You can't play this game.");
                break;
            }
        }
    }
}
