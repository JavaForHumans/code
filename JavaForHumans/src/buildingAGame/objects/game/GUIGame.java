package buildingAGame.objects.game;

import buildingAGame.objects.characters.Character;
import buildingAGame.utils.Utils;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public class GUIGame extends AbstractGame {
    //Create an instance of the Scanner class to get user input
    private Random sharedRandomGen = getSharedRandomGen();
    private final GameGUI gameGUI;

    private String userInput;
    private int numCharactersInGame;
    private int turnsInGame;

    public GUIGame(LinkedList<Character> allCharactersInGame, GameGUI gameGUI) {
        super(allCharactersInGame);
        this.gameGUI = gameGUI;
        this.gameGUI.setGUIGame(this);
    }

    @Override
    public void start() {
        //save the number of characters in the game to a local int variable
        numCharactersInGame = allCharacters.size();
        numCharactersLeftInGame = numCharactersInGame;
        turnsInGame = 500;
        turnsTaken = 0;
        Utils.printSystemMessage("Starting the game with.");
        Utils.printSystemMessage("There will be " + turnsInGame + " turns before the game ends.");

        Utils.printSystemMessage("These are all the characters in the game");
        for(Character character: allCharacters) {
            System.out.println(character);
        }
    }

    @Override
    public void setNumTurns(int numTurnsToTake) {

    }


    protected void nextBattle() {
        if(numCharactersLeftInGame > 0) {
            gameGUI.getResetButton().setDisable(true);
            if (turnsTaken < turnsInGame) {
                turnsTaken++;
                Character attacker = allCharacters.get(sharedRandomGen.nextInt(numCharactersLeftInGame));
                Character defender = allCharacters.get(sharedRandomGen.nextInt(numCharactersLeftInGame));

                //make sure both are alive
                if(attacker.isAlive() && defender.isAlive()) {
                    gameGUI.getAttackerPanel().setText(attacker.getName() + " L" + attacker.getExperienceLevel());
                    gameGUI.getDefenderPanel().setText(defender.getName() + " L" + defender.getExperienceLevel());
                    gameGUI.getAttackerTextArea().setText(attacker.toString());
                    gameGUI.getDefenderTextArea().setText(defender.toString());

                    //have the defender heal itself before being attacked
                    int healAmount = sharedRandomGen.nextInt(20) + 1;
                    defender.healSelf(healAmount);

                    //have the attacker attack the defender
                    attacker.attack(defender);

                    //check if the defender died from the attack
                    if(!defender.isAlive()) {
                        gameGUI.getDefenderTextArea()
                                .setText((defender + " has died.\nRemoving from game."));

                        //if the defender is dead, take it out of the list of characters in the game
                        allCharacters.remove(defender);
                        //update the local value of how many characters are left in the game
                        numCharactersLeftInGame--;

                        if(numCharactersLeftInGame < 2) {
                            //if there are no more characters left in the game,
                            //break out of the for loop to end the game
                            gameGUI.updateLeadersBoard();
                        }
                    }
                }
                gameGUI.getResetButton().setDisable(false);
            } else { finishGame(); }
        } else { finishGame(); }
    }

    private void finishGame() {
        if(allCharacters.size() == 1) {
            //print the champion if there was one
            gameGUI.getWinnerTextArea().setText(
                    String.format("After %d of %d turns:\n\t %s",
                            turnsTaken, turnsInGame, (allCharacters.get(0))));
        } else {
            //otherwise print how many characters survived
            gameGUI.getWinnerTextArea().setText(String.format(
                    "No winner after %d of %d turns:\n\t%d of %d characters survived.",
                    turnsTaken, turnsInGame, numCharactersLeftInGame, numCharactersInGame));
        }
        gameGUI.getResetButton().setDisable(false);
    }
}
