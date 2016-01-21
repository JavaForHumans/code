package buildingAGame.objects.game;

import abstractClassesAndInterfaces.Human;
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

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public class Game {
    static final int DEFAULT_NUM_TURNS_TO_TAKE = 500;
    static final String[] history = new String[100];
    public final static Random sharedRandomGen = new Random();
    private int gamesPlayed;

    protected final LinkedList<Character> allCharacters = new LinkedList<>();
    private GameGUI gameGUI;
    protected int numCharactersInGame;
    protected int numCharactersLeftInGame;
    protected int numBattles;
    protected int battlesCompleted;
    private TextArea gameConsole;
    private TitledPane attackerPanel;
    private TitledPane defenderPanel;
    private Button nextBattleButton;
    private Character attacker;
    private Character defender;
    private TextArea attackerTextArea;
    private TextArea defenderTextArea;
    private boolean started;
    private Button next40BattlesButton;
    private Button resetGameButton;

    public Game(LinkedList<Character> allCharactersInGame, GameGUI gameGUI) {
        setPlayers(allCharactersInGame);
        //save the number of characters in the game to a local int variable
        numCharactersInGame = allCharacters.size();

        this.gameGUI = gameGUI;
        this.gameGUI.setGUIGame(this);
    }

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

    public void start() {
        numBattles = DEFAULT_NUM_TURNS_TO_TAKE;
        battlesCompleted = 0;

        gameConsole = gameGUI.getGameConsole();
        attackerPanel = gameGUI.getAttackerPanel();
        defenderPanel = gameGUI.getDefenderPanel();
        attackerTextArea = gameGUI.getAttackerTextArea();
        defenderTextArea = gameGUI.getDefenderTextArea();
        nextBattleButton = gameGUI.getNextBattleButton();
        next40BattlesButton = gameGUI.getNext40BattlesButton();
        resetGameButton = gameGUI.getResetGameButton();

        gameConsole.appendText(Utils.gameConsoleMessage(
                "There will be " + numBattles + " battles before the game ends.", false));

        started = true;
        nextBattleButton.setDisable(false);
        next40BattlesButton.setDisable(false);
        resetGameButton.setDisable(false);
    }

    public void restart(List<Character> allCharactersInGame) {
        //make sure the game was already set up and started at least once before
        if(started) {
            nextBattleButton.setDisable(true);
            next40BattlesButton.setDisable(true);
            setPlayers(allCharactersInGame);
            numBattles = DEFAULT_NUM_TURNS_TO_TAKE;
            battlesCompleted = 0;

            clearUpdateAreasFromLastBattle();
            gameConsole.setText(Utils.gameConsoleMessage(
                    "There will be " + numBattles + " battles before the game ends.", false));

            nextBattleButton.setDisable(false);
            next40BattlesButton.setDisable(false);
        }
    }

    protected boolean nextBattle() {
        gameConsole.appendText(Utils.gameConsoleMessage(
                "Battle " + battlesCompleted + "", false));
        if(numCharactersLeftInGame > 0) {
            gameGUI.getNextBattleButton().setDisable(true);
            if (battlesCompleted < numBattles) {
                clearUpdateAreasFromLastBattle();
                int randomAttackerIndex = sharedRandomGen.nextInt(numCharactersLeftInGame);
                int randomDefenderIndex = getRandomDefenderIndex(randomAttackerIndex);
                attacker = allCharacters.get(randomAttackerIndex);
                defender = allCharacters.get(randomDefenderIndex);

                attackerPanel.setText("Attacker \t\t" + attacker.getName()
                        + "\t\tL" + attacker.getExperienceLevel()
                        + "\t" + attacker.getExperience() + "xp");
                defenderPanel.setText("Defender \t\t" + defender.getName()
                        + "\t\tL" + defender.getExperienceLevel()
                        + "\t" + defender.getExperience() + "xp");

                //make sure both are alive
                if(attacker.isAlive() && defender.isAlive()) {
                    attacker.setUpdatesArea(attackerTextArea);
                    defender.setUpdatesArea(defenderTextArea);

                    //have the defender heal itself before being attacked
                    int healAmount = sharedRandomGen.nextInt(20) + 1;
                    defender.healSelf(healAmount);

                    //have the attacker attack the defender
                    attacker.attack(defender);

                    //check if the defender died from the attack
                    if(!defender.isAlive()) {
                        gameConsole.appendText(Utils.gameConsoleMessage(
                                defender + " has died.\n\tRemoving from game.", false));
                        gameConsole.setStyle("-fx-background-color: #e74c3c");

                        //if the defender is dead, take it out of the list of characters in the game
                        allCharacters.remove(defender);
                        //update the local value of how many characters are left in the game
                        numCharactersLeftInGame--;

                        if(numCharactersLeftInGame < 2 && numCharactersLeftInGame > 0) {
                            //if there are no more characters left in the game,
                            //break out of the for loop to end the game

                            gameConsole.appendText(Utils.gameConsoleMessage(
                                    "Game Over!\n\t" + allCharacters.get(0)
                                            + " is last player standing ...", false));
                            doAfterBattle();
                            finishGame();
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

    private void doAfterBattle() {
        gameGUI.updateLeadersBoard();
        nextBattleButton.setDisable(false);
        battlesCompleted++;
    }

    /**
     * Retrieves a random int to use in picking a character to battle against an attacker.
     * This method ensures that the random defender's index is not the same as the attacker's index
     * @param randomAttackerIndex
     * @return
     */
    private int getRandomDefenderIndex(int randomAttackerIndex) {
        int randomDefenderIndex = sharedRandomGen.nextInt(numCharactersLeftInGame);

        /*
          make sure the attacker and defender are not the same.
          So long as the randomDefenderIndex is the same as the randomAttackerIndex,
          try to get a new random index for the defender
         */
        while(randomDefenderIndex == randomAttackerIndex) {
            randomDefenderIndex = sharedRandomGen.nextInt(numCharactersLeftInGame);
        }
        return randomDefenderIndex;
    }

    private void clearUpdateAreasFromLastBattle() {
        gameGUI.getAttackerPanel().setText("Attacker");
        gameGUI.getDefenderPanel().setText("Defender");
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

    private void finishGame() {
        nextBattleButton.setDisable(true);
        next40BattlesButton.setDisable(true);

        gameConsole.setStyle("-fx-background-color: #f1c40f");
        //clear the game's console
        gameConsole.setText("");
        showGamingHistory();
        if(numCharactersLeftInGame == 1) {
            //print the champion if there was one
            gameConsole.appendText(
                    String.format("*> Winner After %d of %d Turns:\n\t %s",
                            battlesCompleted, numBattles, (allCharacters.get(0))));

            history[gamesPlayed++] = "Winner> " + allCharacters.get(0);
        } else {
            //otherwise print how many characters survived
            gameConsole.setText(String.format(
                    "No winner after %d of %d turns:\n\t%d of %d characters survived.",
                    battlesCompleted, numBattles, numCharactersLeftInGame, numCharactersInGame));

            history[gamesPlayed++] = numCharactersLeftInGame + " survived";
        }
    }

    private void showGamingHistory() {
        if (gamesPlayed > 0) {
            //print the user's gaming history
            gameConsole.appendText("Gaming History:");
            for (int i = 0; i < gamesPlayed; i++) {
                gameConsole.appendText(String.format("\n\t\t%d)\t%s", i, history[i]));
            }
            gameConsole.appendText("\n\n______________________________________________\n");
        }
    }

    /**
     * List of all characters in our game.
     * It is final so that it cannot be erased by accident by setting it to null or changing its value.
     * We can still add, update, and remove elements freely, though.
     *
     * @return list of characters in to add to an instance of ConsoleGame
     */
    public static LinkedList<Character> getDefaultPlayers() {
        LinkedList<Character> defaultPlayers = new LinkedList<Character>();
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
        return defaultPlayers;
    }

    public Random getSharedRandomGen() {
        return sharedRandomGen;
    }

    public LinkedList<Character> getPlayers() {
        return allCharacters;
    }

    public int getNumCharactersLeft() {
        return numCharactersLeftInGame;
    }
}
