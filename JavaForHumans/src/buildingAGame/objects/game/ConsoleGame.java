package buildingAGame.objects.game;

import buildingAGame.objects.characters.Character;
import buildingAGame.utils.Utils;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public class ConsoleGame extends AbstractGame {
    //Create an instance of the Scanner class to get user input
    private final Scanner userInputScanner = new Scanner(System.in);
    private Random sharedRandomGen = getSharedRandomGen();
    private String userInput;

    public ConsoleGame(LinkedList<Character> allCharactersInGame) {
        super(allCharactersInGame);
    }

    @Override
    public void start() {
        //save the number of characters in the game to a local int variable
        numCharactersInGame = allCharacters.size();

        /*store a local copy of the shared Random instance from the Utils class
         we will use this to decide who is attacking and who is defending on each turn of the game*/
        Random sharedRandomGen = Utils.getSharedRandomGen();
        Utils.printSystemMessage("How many turns to you want to give characters playing the game? \n\tEnter a number.");

        if(userInputScanner.hasNext()) {
            userInput = userInputScanner.next();
        }

        //use a try-catch block to catch any errors that occur with user input
        try {
            //try to parse the number from the user input string
            setNumTurns(Integer.parseInt(userInput));
        } catch (NumberFormatException e) {
            //this error block will execute if the user does not enter a number
            Utils.printSystemMessage("Error! You were supposed to enter a number ...");

            //show yourself the error so you can find and fix it during development
            e.printStackTrace();

            Utils.printSystemMessage("ConsoleGame will startInConsole with "
                    + DEFAULT_NUM_TURNS_TO_TAKE +" turns for the characters to interact.");
            //but, let the user continue with a default number of turns
            setNumTurns(1000);
        }

        play(numTurnsToTake);
    }

    @Override
    public void setNumTurns(int numTurnsToTake) {
        this.numTurnsToTake = numTurnsToTake;
    }

    private void play(int turnsInGame) {
        if(numTurnsToTake > 0) {
            Utils.printSystemMessage("Starting the game with.");
            Utils.printSystemMessage("There will be " + turnsInGame + " turns before the game ends.");

            Utils.printSystemMessage("These are all the characters in the game");
            for(Character character: allCharacters) {
                System.out.println(character);
            }

            System.out.println("\n\n_________________________Lets Start!_________________________\n\n");

            int numCharactersLeftInGame = numCharactersInGame;
            for (int turnsTaken = 0; turnsTaken < turnsInGame; turnsTaken++) {
                Character attacker = allCharacters.get(sharedRandomGen.nextInt(numCharactersLeftInGame));
                Character defender = allCharacters.get(sharedRandomGen.nextInt(numCharactersLeftInGame));

                //make sure both are alive
                if(attacker.isAlive() && defender.isAlive()) {
                    //have the defender heal itself before being attacked
                    int healAmount = sharedRandomGen.nextInt(10) + 1;
                    defender.healSelf(healAmount);

                    //have the attacker attack the defender
                    attacker.attack(defender);

                    //check if the defender died from the attack
                    if(!defender.isAlive()) {
                        Utils.printSystemMessage(defender + " has died. Removing from game.");
                        //if the defender is dead, take it out of the list of characters in the game
                        allCharacters.remove(defender);

                        //update the local value of how many characters are left in the game
                        numCharactersLeftInGame = allCharacters.size();

                        if(numCharactersLeftInGame < 2) {
                            //if there are no more characters left in the game,
                            //break out of the for loop to end the game
                            Utils.printSystemMessage("\n\n\n_____________________________________________________________\n");
                            Utils.printSystemMessage("###Only one character left. \n\tGAMEOVER!*****");
                            break;
                        }
                    }
                }
            }
            Utils.printSystemMessage("\n\n\n_____________________________________________________________\n");
            Utils.printSystemMessage(String.format("##All %d turns taken. GUIGameApplication Over!", turnsInGame));

            //print the champion if there was one
            if(allCharacters.size() == 1) {
                Utils.printSystemMessage("\n\n");
                Utils.printSystemMessage(allCharacters.get(0) + " is the CHAMPION!!!!");
            } else {
                //otherwise print how many characters survived
                Utils.printSystemMessage(String.format("%d of %d characters survived.", numCharactersLeftInGame, numCharactersInGame));
            }
        } else {
            Utils.printSystemMessage("No characters in the game to play with... \n\tGameOver!");
        }
    }
}
