package buildingAGame.objects.game;

import buildingAGame.objects.characters.Character;
import buildingAGame.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

/**
 * Created by Lincoln W Daniel on 1/20/2016.
 * This class is used to create and set up a JavaFx GUI for our game.
 * We will use this to create a user interface for our game.
 *
 * Our game will use it to capture information from the user,
 * manipulate it, and display new information for the user to consume, view.
 *
 * Such information include the current state of the game, the characters in the game,
 * the current ongoing battle between characters, and the winner of the game.
 */
public class GameGUI {
    public static final String VIEW_DEFAULT_TEXT_ATTACKER_PANEL = "Attacker";
    public static final String VIEW_DEFAULT_TEXT_DEFENDER_PANEL = "Defender";
    public static final String VIEW_DEFAULT_TEXT_ATTACKER_AREA = "Attacker Area";
    public static final String VIEW_DEFAULT_TEXT_DEFENDER_AREA = "Defender Area";
    private static final String VIEW_DEFAULT_TEXT_MAX_BATTLES_PROMPT = "Enter number of max battles to run.";
    private Game game;//the game associated with this gui (user interface)

    /* Our root layout is a BorderPane and will hold all our other GUI views in our primary stage.
        A BorderPane allows out to add a single view in each of its cardinal positions: TOP, BOTTOM, RIGHT, LEFT
        We will use the BOTTOM, RIGHT, and LEFT positions */
    private BorderPane rootLayout;

    //the view that will display identification information about the attacker of a battle
    private TitledPane attackerPane;
    /* the attacker text area will display
    real time information about the state of a attacker in the current battle */
    private TextArea attackerTextArea;
    //the view that will display identification information about the defender of a battle
    private TitledPane defenderPane;
    /* the attacker text area will display
    real time information about the state of a defender in the current battle */
    private TextArea defenderTextArea;
    /* the players list view is a view that
        will show all of the characters playing in the current game */
    private ListView playersListView;

    //the game console will display information about the state of the game in real time
    private TextArea gameConsoleTextArea;

    //next battle button will let the user to start the next battle between characters
    private Button nextBattleButton;
    //next battle button will let the user to skip through the next 40 battles between characters
    private Button next40BattlesButton;
    //new game button allows the user to start a new game at any point
    private Button newGameButton;
    //max battles text field allow the user to choose the maximum number of battles to run in the new game
    private TextField maxBattlesInGameInput;

    /**
     * Constructs a new gui to use for a game.
     * It doesn't need any information, parameters, to be constructed
     * because a Game can call pass in its information when it calls our GameGUI's setup() instance method
     */
    public GameGUI() {}

    //create all getter methods for other parts of our application to access parts of our GUI
    public ListView getPlayersListView() {
        return playersListView;
    }

    public TextField getMaxBattlesInGameInput() { return maxBattlesInGameInput;}

    public TextArea getGameConsole() {
        return gameConsoleTextArea;
    }

    public TitledPane getAttackerPanel() {
        return attackerPane;
    }

    public TitledPane getDefenderPanel() {
        return defenderPane;
    }

    public Button getNextBattleButton() {
        return nextBattleButton;
    }

    public TextArea getAttackerTextArea() {
        return attackerTextArea;
    }

    public TextArea getDefenderTextArea() {
        return defenderTextArea;
    }

    public Button getNext40BattlesButton() {
        return next40BattlesButton;
    }

    public Button getNewGameButton() {
        return newGameButton;
    }

    /**
     * This method sets up the GUI for the provided game and returns whether or not setup was successful.
     * Set up will fail if no game has been provided.
     * <p>
     * This will set up all of the views of the user interface for the provided game
     *
     * @param game
     * @return
     */
    public boolean setup(Game game) {
        //make sure this GameGUI has a game to work with
        if (game == null) {
            return false;
        }
        //set the game associated to this gui as the provided game
        this.game = game;

        rootLayout = new BorderPane();
        rootLayout.setPadding(new Insets(20, 0, 20, 20));

        //setup views to put in the left position of our root layout
        TitledPane leftPane = setUpPlayersLeaderBoard();
        //set up the views to put in the right position of our root layout
        TitledPane rightPane = setupRightPane();
        //set up the views to put in the bottom position of our root layout
        TitledPane bottomPane = setupBottomPane();

        //make the panes auto size
        rightPane.autosize();
        leftPane.autosize();
        bottomPane.autosize();
        //put the right, left, and bottom, panes into place in the root layout
        rootLayout.setRight(rightPane);
        rootLayout.setLeft(leftPane);
        rootLayout.setBottom(bottomPane);

        //Add the our root layout to the scene
        Scene appScene = new Scene(rootLayout, 1400, 650);

        //Add the Scene to the Stage
        game.getPrimaryStage().setScene(appScene);
        game.getPrimaryStage().show();
        return true;
    }

    /**
     *
     * @return a TitledPane containing a view that displays information about the current battle in the game.
     */
    private TitledPane setupRightPane() {
        BorderPane currentBattlePane = setupCurrentBattleArea();
        //add the current battle pane to the right pane of the gui
        TitledPane rightPane = new TitledPane("Current Battle", currentBattlePane);
        //make sure the user can't collapse the right titled pane
        rightPane.setCollapsible(false);
        //return the right pane to be added to the right position of the root layout
        return rightPane;
    }

    /**
     *
     * @return a the bottom TitledPane to be added to the root layout of our gui.
     * It holds the control station of the game
     */
    private TitledPane setupBottomPane() {
        TitledPane bottomPane = new TitledPane("Control Station", null);
        BorderPane userControlStationContainer = setupControlStation();
        bottomPane.setContent(userControlStationContainer);
        bottomPane.setCollapsible(false);
        //return the bottom pane to be added to the bottom position of the root layout
        return bottomPane;
    }

    /**
     * Sets up a BorderPane to hold the user input text field
     * and the three buttons for the user to control the game
     * @return A BorderPane holding the user maxBattlesInGameInput text field
     * and the three buttons for the user to control the game
     */
    private BorderPane setupControlStation() {
        BorderPane controlStationContainer = new BorderPane();
        BorderPane newGamePane = setUpNewGamePane();
        setupNextBattleButton();
        setupNext40BattlesButton();

        controlStationContainer.setLeft(newGamePane);
        controlStationContainer.setRight(nextBattleButton);
        controlStationContainer.setCenter(next40BattlesButton);
        //return the control station to be added to the bottom pane
        return controlStationContainer;
    }

    /**
     * Creates a BorderPane to hold views that
     * display information about the attacker and defender of the current battle in the game in real time.
     * @return
     */
    private BorderPane setupCurrentBattleArea() {
        BorderPane currentBattlePane = new BorderPane();
        attackerPane = new TitledPane();
        attackerPane.setText("Attacker Zone");
        attackerTextArea = new TextArea("Attacker Area");
        attackerTextArea.setWrapText(true);
        attackerTextArea.setEditable(false);
        attackerPane.setContent(attackerTextArea);
        attackerPane.autosize();
        attackerPane.setCollapsible(false);

        defenderPane = new TitledPane();
        defenderPane.setText("Defender Zone");
        defenderTextArea = new TextArea("Defender Area");
        defenderTextArea.setWrapText(true);
        defenderTextArea.setEditable(false);
        defenderPane.setContent(defenderTextArea);
        defenderPane.autosize();
        defenderPane.setCollapsible(false);

        currentBattlePane.setTop(attackerPane);
        currentBattlePane.setBottom(defenderPane);
        return currentBattlePane;
    }

    /**
     * Initiates a button to let the user start the next battle in the game
     */
    private void setupNextBattleButton() {
        //The button uses an inner class to handle the button click event
        nextBattleButton = new Button("Next Battle");
        nextBattleButton.setDisable(true);
        nextBattleButton.setOnAction(event -> {
            game.nextBattle();
        });
    }

    /**
     * Initiates a button to let the user skip over the next 40 battle in the game
     */
    private void setupNext40BattlesButton() {
        next40BattlesButton = new Button("Next 40 Battles");
        next40BattlesButton.setDisable(true);
        next40BattlesButton.setOnAction(event -> {
            if (game.getNumCharactersLeft() > 1) {
                next40BattlesButton.setDisable(true);

                //loop from 1 to and including 40
                for (int i = 1; i <= 40; i++) {
                    //this will run the next battle and return the result
                    //if there are no more turns or characters left, it will return false
                    if (!game.nextBattle()) {
                        Utils.gameConsoleMessage("Game Over!", true);
                        //there are no more battles left
                        next40BattlesButton.setDisable(true);
                        break;
                    } else {
                        next40BattlesButton.setDisable(false);
                    }
                }
            }
        });
    }

    /**
     * Sets up the new game pane to hold the text field for user input and the new game button
     * @return The new game pane holding the maxBattlesInGameInput text field and the startNewGame button.
     */
    private BorderPane setUpNewGamePane() {
        BorderPane newGamePane = new BorderPane();
        maxBattlesInGameInput = new TextField();
        maxBattlesInGameInput.autosize();
        maxBattlesInGameInput.setMinWidth(400);
        maxBattlesInGameInput.setPadding(new Insets(20,20, 20, 20));
        maxBattlesInGameInput.setPromptText(VIEW_DEFAULT_TEXT_MAX_BATTLES_PROMPT);

        newGameButton = new Button("Start New Game");
        newGameButton.setDisable(true);
        newGameButton.setOnAction(event -> {
            int maxBattles = -1;
            try {
                //try to get an integer from the max battles text field.
                maxBattles = Integer.parseInt(maxBattlesInGameInput.getText());

                //restart the game with default characters
                game.start(null, maxBattles);
                //reset the players list view with the new characters from the game
                playersListView.setItems(FXCollections.observableArrayList(game.getPlayers()));
                //update the leader board so the user can see the new characters' information
                updateLeadersBoard();
            } catch (NumberFormatException e) {
                //if the user has not entered an integer, don't start the game
                //tell the user to enter a number
                maxBattlesInGameInput.setPromptText("Enter a number to start ...");
                return;
            }


        });

        //add the max battles text field to the new game pane
        newGamePane.setLeft(maxBattlesInGameInput);
        //add the new game button to the new game pane
        newGamePane.setRight(newGameButton);
        //return the new game pane
        return newGamePane;
    }

    /**
     * Creates a titled pane to how a ListView of all the characters and their information.
     * The titled pane will also hole the game's console text area view
     * @return a TitledPane to hold a list of all the characters, players, in a game along with the game's console.
     */
    private TitledPane setUpPlayersLeaderBoard() {
        final TitledPane playersLeaderBoardContainer = new TitledPane();
        playersLeaderBoardContainer.setText("Players Leader Board");
        final FlowPane playersLeaderBoardFlowPane = new FlowPane();
        playersLeaderBoardFlowPane.setHgap(500);
        playersLeaderBoardFlowPane.setVgap(10);
        playersLeaderBoardFlowPane.autosize();

        /*initiate the players ListView as an observable array list so that we can observe its elements for updates
        this will allow us to update the display of how characters stack up to one another during the game
        as they battle one another*/
        playersListView = new ListView(FXCollections.observableArrayList(game.getPlayers()));
        playersListView.setMinWidth(600);
        playersListView.setMaxHeight(300);
        playersListView.autosize();

        TitledPane gameConsoleContainer = setupGameConsole();

        playersLeaderBoardFlowPane.getChildren().add(playersListView);
        playersLeaderBoardFlowPane.getChildren().add(gameConsoleContainer);

        playersLeaderBoardFlowPane.setVisible(true);
        playersLeaderBoardContainer.setContent(playersLeaderBoardFlowPane);

        return playersLeaderBoardContainer;
    }

    /**
     * Sets up a text area view to act as the game's console.
     * @return a TitledPane holding a text area to act as the game's console
     */
    private TitledPane setupGameConsole() {
        TitledPane gameConsoleContainer = new TitledPane();
        gameConsoleContainer.setText("Game Console");
        gameConsoleContainer.setCollapsible(false);
        gameConsoleTextArea = new TextArea();
        gameConsoleTextArea.setMaxHeight(150);
        gameConsoleTextArea.setWrapText(true);
        gameConsoleTextArea.setEditable(false);
        gameConsoleTextArea.autosize();
        gameConsoleContainer.setContent(gameConsoleTextArea);
        gameConsoleContainer.autosize();
        return gameConsoleContainer;
    }

    /**
     * Updates the players listview to show the most recent state of the characters in a game
     */
    public void updateLeadersBoard() {
        //switch the visibility for each FlowPane
        ObservableList items = playersListView.getItems();
        /*this is a sort method that sorts the elements of a collection.
        it uses a lambda to do its job.
        Lambdas are new to Java, so be sure to research them.*/
        items.sort((currentElement, nextElement) ->
                ((Character) currentElement).compare((Character) nextElement));
    }

    /**
     *
     * @return the game associated with this gui
     */
    public Game getGame() {
        return game;
    }
}
