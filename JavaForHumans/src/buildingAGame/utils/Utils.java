package buildingAGame.utils;

import java.util.Random;

/**
 * Created by Lincoln W Daniel, the ModernNerd, on 1/17/2016
 * as an application of the concepts expressed in the "Java for Humans" book.
 *
 * This class is a simple utility class to facilitate a few functions of our game.
 *
 * The "final" keyword in the case of classes means that it cannot be extended by another class.
 *
 */
public final class Utils {
    private static final String SYSTEM_MESSAGE_PREFIX = "\n\t#> ";
    public static final String HUMAN_MESSAGE_PREFIX = "\n\tH> ";
    public static final String PET_MESSAGE_PREFIX = "\n\tP> ";
    public static final String UNKNOWN_TYPE_MESSAGE_PREFIX = "\n\tUT>";

    //an instance of the Random class to share across our program.
    //We will use it to generate random numbers
    private static Random sharedRandomNumberGenerator = new Random();

    public static Random getSharedRandomGen() { return sharedRandomNumberGenerator; }

    public static String gameConsoleMessage(String message, boolean logToSystemConsole) {
        String returnMessage = SYSTEM_MESSAGE_PREFIX + ": " + message + "\n";
        if(logToSystemConsole){ System.out.println(returnMessage); }

        return  returnMessage;
    }
}
