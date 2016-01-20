package buildingAGame.utils;

import java.util.Random;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public class Utils {
    private static final String SYSTEM_MESSAGE_PREFIX = "\n\t#> ";
    public static final String HUMAN_MESSAGE_PREFIX = "\n\tH> ";
    public static final String PET_MESSAGE_PREFIX = "\n\tP> ";
    public static final String UNKNOWN_TYPE_MESSAGE_PREFIX = "\n\tUT>";

    //an instance of the Random class to share across our program.
    //We will use it to generate random numbers
    private static Random sharedRandomNumberGenerator = new Random();

    public static Random getSharedRandomGen() { return sharedRandomNumberGenerator; }

    public static void printSystemMessage(String message) {
        System.out.println(SYSTEM_MESSAGE_PREFIX + ": " + message + "\n");
    }
}
