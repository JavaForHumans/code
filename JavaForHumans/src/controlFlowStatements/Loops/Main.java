package controlFlowStatements.Loops;

import java.util.Random;

/**
 * Created by lwdthe1 on 1/19/2016.
 */
public class Main {
    public static void main(String[] args) {
        for(int count = 0; count < 10; count++) {
            System.out.print(count + " | ");
        }

        System.out.println();

        int number = 0;
        while(number < 10) {
            System.out.println("Current number = " + number);
            number++;
        }
        /*Prints:
            Current number = 0
            Current number = 1
            Current number = 2
            Current number = 3
            Current number = 4
            Current number = 5
            Current number = 6
            Current number = 7
            Current number = 8
            Current number = 9
         */

        int numCookiesEaten = 0;
        int cookiesNeededToBeFull = 10;
        int numCookiesLeftInCookieJar = 8;
        
        Random randomCookiesGenerator = new Random();
        int numCookiesToEatThisTime;
        
        int numCompletedIterations = 0;
        while(numCookiesEaten < cookiesNeededToBeFull) {
            numCookiesToEatThisTime = randomCookiesGenerator.nextInt(numCookiesLeftInCookieJar) + 1;
        
            //make sure we aren't trying to eat more cookies than exists in the cookie jar
            if(numCookiesToEatThisTime > numCookiesLeftInCookieJar) {
                numCookiesToEatThisTime = numCookiesLeftInCookieJar;
            }
        
            System.out.println("< Eating " + numCookiesToEatThisTime + " cookies this time.");
        
            numCookiesEaten += numCookiesToEatThisTime;
            numCookiesLeftInCookieJar -= numCookiesToEatThisTime;
        
            if(numCookiesLeftInCookieJar < 1) {
                System.out.println("\n#No cookies left to eat :(");
                break;
            } else {
                System.out.println("> " + numCookiesLeftInCookieJar + " cookies left in jar");
            }
        
            //increment number of times we've looped
            numCompletedIterations++;
        }
        /*Prints:
            < Eating 4 cookies this time.
            > 4 cookies left in jar
            < Eating 2 cookies this time.
            > 2 cookies left in jar
            < Eating 2 cookies this time.
            
            #No cookies left to eat :(
         */
        
        System.out.println("Completed " + numCompletedIterations + " iterations completed.");
        //Completed 1 iterations completed.

    }
}
