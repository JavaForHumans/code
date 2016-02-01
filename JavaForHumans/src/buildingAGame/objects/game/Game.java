package buildingAGame.objects.game;

import buildingAGame.GUIGameApplication;
import buildingAGame.objects.characters.Character;
import buildingAGame.objects.characters.human.Archer;
import buildingAGame.objects.characters.human.FireArcher;
import buildingAGame.objects.characters.human.Taoist;
import buildingAGame.objects.characters.human.Warrior;
import buildingAGame.objects.characters.pet.Dog;
import buildingAGame.objects.characters.pet.Lion;
import buildingAGame.utils.Utils;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by Lincoln W Daniel, the ModernNerd, on 1/17/2016
 * as an application of the concepts expressed in the "Java for Humans" book.
 *
 * This class has all the information and methods necessary to set up and play our game.
 * The application, GUIGameApplication.class, must instantiate this class to let the user play our game.
 * It will set up the game and the graphical user interface (GUI) necessary to display information to the user.
 *
 * The "final" keyword in the case of classes means that it cannot be extended by another class.
 */
public final class Game {
    //the default number of battles to run in a game
    private static final int DEFAULT_NUM_BATTLES_IN_GAME = 500;
    //the maximum number of times a user can play an instance of our Game.
    private static final int MAX_NUM_TIMES_USER_CAN_PLAY = 100;
    /*A fixed array to store the winner of each game.
    We fix it to only store 100 elements
    because we are assuming a user will not play more than 100 games in one sitting.*/
    private static final String[] historyOfWinners = new String[MAX_NUM_TIMES_USER_CAN_PLAY + 1];

    /* The primaryStage is the top-level container.
        It holds our display for our application.
        It should not be changed once it is set. */
    private final Stage primaryStage;

    private final String name;//name of the game associated with this gui
    //all characters in the game
    protected final LinkedList<Character> allCharacters = new LinkedList<>();
    //the total number of characters in the game
    protected int numCharactersInGame;
    //the number of characters still alive in the game
    protected int numCharactersLeftInGame;
    /*the maximum number of battles to play in a game.
    This will be provided by the user */
    protected int maxBattles;
    //the number of battles that have been completed in a game
    protected int numBattlesCompleted;
    //the attacking character in the current battle of a game
    private Character attacker;
    //the defending character in the current battle of a game
    private Character defender;

    /*instance of our GameGUI which we will use
    to display information from the game as the user plays*/
    private GameGUI gameGUI;

    /*****the below are simply local copies of the views in the game gui. Read about them in the GameGUI class*****/
    private TextArea gameConsole;
    private TitledPane attackerPanel;
    private TitledPane defenderPanel;
    private Button nextBattleButton;
    private TextArea attackerTextArea;
    private TextArea defenderTextArea;
    private Button next40BattlesButton;
    private Button newGameButton;
    /*****the above are simply local copies of the views in the game gui. Read about them in the GameGUI class*****/

    //holds whether or not the game has been started before.
    private boolean started;
    //holds whether or not this game has
    private boolean finishedSetup;
    //holds the user has played this game since starting it
    private int numTimesPlayed;

    /**
     * Constructs a new game with a name and a primaryStage from an Application instance.
     * Also, it sets up the game.
     * @param name the name of the game
     * @param primaryStage the primary stage of the JavaFx Application instance starting this game
     */
    public Game(String name, Stage primaryStage) {
        //set our application's name
        this.name = name;

        //set our application's primary stage
        this.primaryStage = primaryStage;

        //set the title of the application's primary stage
        primaryStage.setTitle(name);

        //set up the gui for this game
        setupGUI();
    }

    /**
     * Sets the provided characters as the players of this game.
     * If no characters are provided, we use our default list of characters.
     * @param allCharactersInGame
     */
    private void setPlayers(List<Character> allCharactersInGame) {
        //clear the list of all characters
        allCharacters.clear();
        //clear the list of all characters
        if(allCharactersInGame != null) {
            allCharacters.addAll(allCharactersInGame);
        } else {
            allCharacters.addAll(getDefaultPlayers());
        }
        //save the number of characters in the game to a local int variable
        numCharactersInGame = allCharacters.size();
        numCharactersLeftInGame = numCharactersInGame;
    }

    /**
     * Returns the primary stage of the application associated with this game
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @return name of this game
     */
    public String getName() {return name;}

    /**
     * returns
     * @return LinkedList of all characters in this game
     */
    public LinkedList<Character> getPlayers() {
        return allCharacters;
    }

    /**
     *
     * @return number of characters that are still alive in this game
     */
    public int getNumCharactersLeft() {
        return numCharactersLeftInGame;
    }

    /**
     * Sets up the gui for this game
     */
    private void setupGUI() {
        gameGUI = new GameGUI();
        if(gameGUI.setup(this)) {
            //store local references to the views in the game's GameGUI instance
            gameConsole = gameGUI.getGameConsole();
            attackerPanel = gameGUI.getAttackerPanel();
            defenderPanel = gameGUI.getDefenderPanel();
            attackerTextArea = gameGUI.getAttackerTextArea();
            defenderTextArea = gameGUI.getDefenderTextArea();
            nextBattleButton = gameGUI.getNextBattleButton();
            next40BattlesButton = gameGUI.getNext40BattlesButton();
            newGameButton = gameGUI.getNewGameButton();

            //let the user start a new game
            gameConsole.appendText(Utils.gameConsoleMessage("Make sure this window is in full screen mode.", false));
            gameConsole.appendText(Utils.gameConsoleMessage("Enter max number of battles "
                    + "and push \"Start New Game\" below ...", false));
            newGameButton.setDisable(false);
            //set finishedSetup to true so the game knows it is done setting up
            finishedSetup = true;
        }
    }

    /**
     * Starts the current game for the first time if it has finished setting up and has not been started before.
     * If it was started once before, it calls the restart() method
     * @param allCharactersInGame
     */
    public void start(LinkedList<Character> allCharactersInGame, int maxBattles) {
        //check if the user has played 100 times. If so, don't let them play anymore
        if(numTimesPlayed <= MAX_NUM_TIMES_USER_CAN_PLAY) {
            setPlayers(allCharactersInGame);
            setMaxBattles(maxBattles);
            numBattlesCompleted = 0;
            //make sure the game has finished setting up and has not been started yet
            if (finishedSetup && !started) {
                gameConsole.appendText(Utils.gameConsoleMessage(
                        "There will be " + this.maxBattles + " battles before the game ends.", false));

                started = true;
                nextBattleButton.setDisable(false);
                next40BattlesButton.setDisable(false);
            } else {
                //if its been started once before, restart it
                restart(allCharactersInGame);
            }
            nextBattle();
        } else {
            //Let the user know they have played the max number of times
            gameConsole.setText("");
            gameConsole.appendText(Utils.gameConsoleMessage(
                    "#### Sorry, you can only play " + MAX_NUM_TIMES_USER_CAN_PLAY + " times."
                            + "\n\tPlease restart the application to play more.", false));

        }
    }

    /**
     * Sets the maximum number of battles to run in this game
     * @param maxBattles
     */
    private void setMaxBattles(int maxBattles) {
        if(maxBattles > 1) {
            this.maxBattles = maxBattles;
        } else { this.maxBattles = DEFAULT_NUM_BATTLES_IN_GAME; }
    }

    /**
     * Starts a new game only if the game was started once before
     * @param allCharactersInGame all the characters that will battle in this game
     */
    private boolean restart(List<Character> allCharactersInGame) {
        //make sure the game was already set up and started at least once before
        if (finishedSetup && started) {
            //disable the action buttons
            newGameButton.setDisable(true);
            nextBattleButton.setDisable(true);
            next40BattlesButton.setDisable(true);

            setPlayers(allCharactersInGame);

            resetUpdateAreasFromLastBattle();
            gameConsole.setText(Utils.gameConsoleMessage(
                    "There will be at most " + maxBattles + " battles in this game.", false));

            nextBattleButton.setDisable(false);
            next40BattlesButton.setDisable(false);
            return true;
        }

        //this will only be reached if the game has not been set up yet
        return false;
    }

    /**
     * runs the next battle in the current game.
     * This method will be called when the user clicks the "Next Battle" button.
     * It returns whether or not there are more characters or battles left in the game.
     * @return true if there are more battles left to played and more characters left to battle
     */
    protected boolean nextBattle() {
        gameConsole.appendText(Utils.gameConsoleMessage(
                "Battle " + numBattlesCompleted + "", false));
        if(numCharactersLeftInGame > 0) {
            gameGUI.getNextBattleButton().setDisable(true);
            if (numBattlesCompleted < maxBattles) {
                resetUpdateAreasFromLastBattle();
                int randomAttackerIndex = getSharedRandom().nextInt(numCharactersLeftInGame);
                int randomDefenderIndex = getRandomDefenderIndex(randomAttackerIndex);
                attacker = allCharacters.get(randomAttackerIndex);
                defender = allCharacters.get(randomDefenderIndex);

                attackerPanel.setText("Attacker: \t\t" + attacker.getName()
                        + "\t\t" + attacker.getTypeDisplay()
                        + "\t\tL" + attacker.getExperienceLevel()
                        + "\t" + attacker.getExperience() + "xp");
                defenderPanel.setText("Defender \t\t" + defender.getName()
                        + "\t\t" + attacker.getTypeDisplay()
                        + "\t\tL" + defender.getExperienceLevel()
                        + "\t" + defender.getExperience() + "xp");

                //make sure both are alive
                if(attacker.isAlive() && defender.isAlive()) {
                    attacker.setUpdatesArea(attackerTextArea);
                    defender.setUpdatesArea(defenderTextArea);

                    //have the defender heal itself before being attacked
                    int healAmount = getSharedRandom().nextInt(10) + 50;
                    defender.healSelf(healAmount);

                    //have the attacker attack the defender
                    attacker.attack(defender);

                    //check if the defender died from the attack
                    if(!defender.isAlive()) {
                        checkIfDefenderDied();

                        if (onlyOneCharacterLeft()) {
                            return false;
                        }
                    }
                }
                doAfterBattle();
                return true;
            } else {
                finishGame();
                return false;
            }
        } else {
            finishGame();
            return false;
        }
    }

    /**
     * Checks whether or not there's only one character left alive in the game.
     * If there is only one character left alive, the game is ended.
     * @return true if there's only one character left
     */
    private boolean onlyOneCharacterLeft() {
        if(numCharactersLeftInGame < 2 && numCharactersLeftInGame > 0) {
            //if there are no more characters left in the game,
            //break out of the for loop to end the game

            gameConsole.appendText(Utils.gameConsoleMessage(
                    "Game Over!\n\t" + allCharacters.get(0)
                            + " is last player standing ...", false));
            doAfterBattle();
            finishGame();
            return true;
        }
        return false;
    }

    private void checkIfDefenderDied() {
        gameConsole.appendText(Utils.gameConsoleMessage(
                defender + " has died.\n\tRemoving from game.", false));
        gameConsole.setStyle("-fx-background-color: #e74c3c");

        //if the defender is dead, take it out of the list of characters in the game
        allCharacters.remove(defender);

                        /* You could also remove the dead character from the game's gui players ListView
                         by doing the following: However, the user may want to see all of the characters at all times
                        gameGUI.getPlayersListView().getItems().remove(defender);
                         */

        //update the local value of how many characters are left in the game
        numCharactersLeftInGame--;
    }

    /**
     * Gets and returns the shared instance of the Random class from the GUIGameApplication
     * @return
     */
    private Random getSharedRandom() {
        return GUIGameApplication.getSharedRandom();
    }

    /**
     * This will be called at the end of each battle of this game
     */
    private void doAfterBattle() {
        //show the user the new leader board
        gameGUI.updateLeadersBoard();
        //enable the next battle button to let the user start the next battle
        nextBattleButton.setDisable(false);
        //increment the number of battles that have been completed
        numBattlesCompleted++;
    }

    /**
     * Retrieves a random int to use in picking a character to battle against an attacker.
     * This method ensures that the random defender's index is not the same as the attacker's index
     * @param randomAttackerIndex
     * @return
     */
    private int getRandomDefenderIndex(int randomAttackerIndex) {
        int randomDefenderIndex = getSharedRandom().nextInt(numCharactersLeftInGame);

        /*
          make sure the attacker and defender are not the same.
          So long as the randomDefenderIndex is the same as the randomAttackerIndex,
          try to get a new random index for the defender
         */
        while(randomDefenderIndex == randomAttackerIndex) {
            randomDefenderIndex = getSharedRandom().nextInt(numCharactersLeftInGame);
        }
        return randomDefenderIndex;
    }

    /**
     * Resets the update areas from the last battle.
     * This will allow the game to only show the user the most recent information from each battle
     */
    private void resetUpdateAreasFromLastBattle() {
        gameGUI.getAttackerPanel().setText(GameGUI.VIEW_DEFAULT_TEXT_ATTACKER_PANEL);
        gameGUI.getDefenderPanel().setText(GameGUI.VIEW_DEFAULT_TEXT_DEFENDER_PANEL);
        gameGUI.getAttackerTextArea().setText("");
        gameGUI.getDefenderTextArea().setText("");

        gameConsole.setStyle("-fx-background-color: #ffffff");
        attackerTextArea.setStyle("-fx-background-color: #ffffff");
        defenderTextArea.setStyle("-fx-background-color: #ffffff");
        if(attacker != null) {
            attacker.clearUpdatesArea();
        }
        if(defender != null) {
            defender.clearUpdatesArea();
        }
    }

    /**
     * This is called at the game of each game to finish it up.
     * It shows the survivors/winner of this game and adds it to the historyOfWinners of winners.
     */
    private void finishGame() {
        nextBattleButton.setDisable(true);
        next40BattlesButton.setDisable(true);

        //reset the background color of the game's console
        gameConsole.setStyle("-fx-background-color: #f1c40f");
        //clear the game's console text
        gameConsole.setText("");
        //show the historyOfWinners of past winners
        showGamingHistory();
        //show and add the survivors/winner to the historyOfWinners array
        if(numCharactersLeftInGame == 1) { showWinnerAndAddToHistory(); }
        else { showSurvivorsAndAddToHistory(); }
        gameConsole.appendText("\n");
        newGameButton.setDisable(false);
    }

    /**
     * If there was no winner of this game,
     * show the amount of characters that survived and add it to the historyOfWinners array.
     */
    private void showSurvivorsAndAddToHistory() {
        //otherwise print how many characters survived
        gameConsole.setText(String.format(
                "No winner after %d of %d battles:\n\t%d of %d characters survived.",
                numBattlesCompleted, maxBattles, numCharactersLeftInGame, numCharactersInGame));

        /*add the amount of survivors of this game to the historyOfWinners of winners
          at the index corresponding to the current amount of games that have been played.
          At the same time, "numTimesPlayed++" increments the total amount of games that have been played.*/
        historyOfWinners[numTimesPlayed++] = numCharactersLeftInGame
                + " survived after " + numBattlesCompleted + " battles." ;
    }

    /**
     * If there was a winner, show it and add it to the historyOfWinners of winners.
     */
    private void showWinnerAndAddToHistory() {
        //print the champion if there was one
        gameConsole.appendText(
                String.format("*> Winner After %d of %d Turns:\n\t %s",
                        numBattlesCompleted, maxBattles, (allCharacters.get(0))));

        /*add the winner of this game to the historyOfWinners of winners
          at the index corresponding to the current amount of games that have been played.
          At the same time, "numTimesPlayed++" increments the total amount of games that have been played.*/
        historyOfWinners[numTimesPlayed++] = "Winner> " + allCharacters.get(0);
    }

    /**
     * Shows the historyOfWinners of winners/survivors from the historyOfWinners array.
     */
    private void showGamingHistory() {
        if (numTimesPlayed > 0) {
            //print the user's gaming historyOfWinners
            gameConsole.appendText("Gaming History:");
            for (int i = 0; i < numTimesPlayed; i++) {
                gameConsole.appendText(String.format("\n\t\t%d)\t%s", i, historyOfWinners[i]));
            }
            gameConsole.appendText("\n\n______________________________________________\n");
        }
    }

    /**
     * Return the default list of all characters for a game.
     *
     * @return list of characters in to add to an instance of ConsoleGame
     */
    public static LinkedList<Character> getDefaultPlayers() {
        //create a LinkedList of Character objects to store our default characters
        final LinkedList<Character> defaultPlayers = new LinkedList<Character>();

        //add a bunch of characters to the list to play with
        Taoist tabitha = new Taoist("Tabitha");
        defaultPlayers.add(tabitha);
        defaultPlayers.add(new Lion("Simba", tabitha));
        FireArcher modernNerd = new FireArcher("ModernNerd");
        defaultPlayers.add(modernNerd);
        defaultPlayers.add(new Dog("Buddy", modernNerd));
        defaultPlayers.add(new Taoist("Lincoln"));
        defaultPlayers.add(new Warrior("Sasha"));
        Archer eman = new Archer("Eman");
        defaultPlayers.add(eman);
        defaultPlayers.add(new Lion("Musafa", eman));
        FireArcher lucy = new FireArcher("Lucy");
        defaultPlayers.add(lucy);
        Warrior andres = new Warrior("Andres");
        defaultPlayers.add(andres);
        Taoist keith = new Taoist("Keith");
        defaultPlayers.add(keith);
        defaultPlayers.add(new Dog("Pup", keith));

        return defaultPlayers; }
}
