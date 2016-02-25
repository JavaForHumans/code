package generatingRandomNumbers;

import methodsAndDotNotation.CoolDog;
import methodsAndDotNotation.Human;

import java.util.Random;

/**
 * Created by lwdthe1 on 1/16/2016.
 */
public class RandomNumber {
    public static void main(String[] args){
        System.out.println("#Starting Program");
        Random randomNumberGenerator = new Random();

        //generate random int
        int randomInt = randomNumberGenerator.nextInt();

        //generate random long
        long randomLong = randomNumberGenerator.nextLong();

        //generate random double
        double randomDouble = randomNumberGenerator.nextDouble();

        //generate random boolean
        boolean randomBoolean = randomNumberGenerator.nextBoolean();

        System.out.println("Random whole number as int: " + randomInt);
        System.out.println("Random whole number as long: " + randomLong);
        System.out.println("Random floating point number as double: " + randomDouble);
        System.out.println("Random boolean: " + randomBoolean);

        System.out.println("Another random whole number as int: " + randomNumberGenerator.nextInt());

        int randomIntWithBound = randomNumberGenerator.nextInt(10);

        if(randomIntWithBound >= 0 && randomIntWithBound < 5) {
            //random int is between 0 (inclusive) and 5 (exclusive)
            System.out.println("Random int = " + randomIntWithBound + ". Going Out!");
        } else if(randomIntWithBound >= 5 && randomIntWithBound < 10){
            //random int is between 5 (inclusive) and 10 (exclusive)
            System.out.println("Random int = " + randomIntWithBound + ". Staying in!");
        }

        if(randomBoolean) {
            //random boolean is true
            System.out.println("I'm Going Out!");
        } else {
            //random boolean is false
            System.out.println("I'm Staying in.");
        }
    }
}
