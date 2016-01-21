package dataStructures.hashmaps;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by lwdthe1 on 1/18/2016.
 */
public class StudyGuide {
    public static final String OUTPUT_SEPERATOR_LINE = "\n___________________________________________________\n";
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        String userInput;
        int userPoints = 0;
        int possiblePoints = 0;
        int rounds = 0;

        HashMap<String, Concept> studyGuide = new HashMap<String, Concept>();

        studyGuide.put("Variable", new Concept("Variable",
                "Used to store a single value for later use."));
        studyGuide.put("String", new Concept("String",
                "A class for representing character strings."));
        studyGuide.put("double", new Concept("double",
                "A primitive datatype for representing floating point numbers."));
        studyGuide.put("Double", new Concept("Double",
                "A class for wrapping a double in an Object with convenient methods."));
        studyGuide.put("zero", new Concept("zero",
                "The number zero. The first index in arrays and the first position on lists."));
        System.out.println("Concepts to study: " + studyGuide.size());
        possiblePoints = studyGuide.size();

        System.out.println("> Let's Study Java Concepts!");
        System.out.println("> Try to give the meaning to each concept.");
        System.out.println("\tIf you are right, you will get a point " +
                "and the concept will be marked as correct.");
        System.out.println("> The game will continue until you get each concept right.");
        System.out.println("> To quit, enter \"X\" when prompted.");
        System.out.printf("\n>Let's begin...\n%s\n", OUTPUT_SEPERATOR_LINE);

        userInput = "startInConsole";
        while(userPoints < possiblePoints && !quit(userInput)) {
            for (String key : studyGuide.keySet()) {
                Concept currentConcept = studyGuide.get(key);
                if(!currentConcept.userGotCorrect()) {
                    System.out.printf("\n\t\t" + OUTPUT_SEPERATOR_LINE + "\n\n> What is %s?\n\n\n", key);

                    if(userInputScanner.hasNextLine()) {
                        userInput = userInputScanner.nextLine();
                        //System.out.println(userInput);
                    }

                    if (!quit(userInput)) {
                        if (currentConcept.userInputIsCloseToCorrectMeaning(userInput)) {
                            currentConcept.markGotCorrect();
                            userPoints++;
                            System.out.printf("\n> CORRECT! %s means: %s",
                                    key, currentConcept.getMeaning());
                        } else {
                            System.out.printf("\n> WRONG! %s means: %s",
                                    key, currentConcept.getMeaning());
                        }
                    }
                }
            }

            System.out.println(OUTPUT_SEPERATOR_LINE);
            System.out.printf("\n> You have %d points at the end of round %d. ", userPoints, rounds);
            System.out.println("\n> Type anything to continue or \"X\" to quit?");
            if(userInputScanner.hasNextLine()) {
                userInput = userInputScanner.nextLine();
            }
        }
    }

    private static boolean quit(String userInput) {
        if(userInput.equalsIgnoreCase("x") || userInput.equalsIgnoreCase("quit")) {
            System.exit(100);//end the program now
            //return true. This line is not necessary because the program ends before its reached
        }
        return false;
    }
}
