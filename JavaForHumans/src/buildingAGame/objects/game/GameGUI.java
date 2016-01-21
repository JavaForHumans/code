package buildingAGame.objects.game;

import buildingAGame.objects.characters.Character;
import buildingAGame.utils.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public class GameGUI {
    private final Stage gamePrimaryStage;
    private final String name;
    private Game game;
    private TitledPane defenderPane;
    private TitledPane attackerPane;
    private BorderPane rootLayout;
    private ListView playersListView;
    private Button nextBattleButton;
    private Label winnerLabel;
    private TextArea gameConsoleTextArea;
    private TextArea attackerTextArea;
    private TextArea defenderTextArea;
    private Button next40BattlesButton;
    private Button resetGameButton;

    public GameGUI(String name, Stage primaryStage) {
        this.name = name;
        this.gamePrimaryStage = primaryStage;
    }

    public void setGUIGame(Game game) {
        this.game = game;
    }

    public ListView getPlayersListView() {
        return playersListView;
    }

    public boolean setup(){
        //make sure this GameGUI has a game to work with
        if(this.game == null) { return false; }

        //The primaryStage is the top-level container
        gamePrimaryStage.setTitle("ModernNerd Battles");
        //The BorderPane has the same areas laid out as the
        // BorderLayout layout manager
        rootLayout = new BorderPane();
        rootLayout.setPadding(new Insets(20, 0, 20, 20));

        playersListView = setUpPlayersLeaderBoard();

        BorderPane currentBattlePane = new BorderPane();
        attackerPane = new TitledPane();
        defenderPane = new TitledPane();
        attackerPane.setText("Attacker Zone");
        defenderPane.setText("Defender Zone");

        attackerTextArea = new TextArea("Attacker Area");
        defenderTextArea = new TextArea("Defender Area");
        attackerTextArea.setWrapText(true);
        defenderTextArea.setWrapText(true);

        attackerPane.setContent(attackerTextArea);
        defenderPane.setContent(defenderTextArea);
        ((TextArea)attackerPane.getContent()).setEditable(false);
        ((TextArea)defenderPane.getContent()).setEditable(false);

        attackerPane.autosize();
        defenderPane.autosize();
        attackerPane.setCollapsible(false);
        defenderPane.setCollapsible(false);
        currentBattlePane.setTop(attackerPane);
        currentBattlePane.setBottom(defenderPane);

        TitledPane rightPane = new TitledPane("Current Battle", currentBattlePane);
        rightPane.setCollapsible(false);

        TitledPane bottomPane = new TitledPane("Control Station", currentBattlePane);
        bottomPane.setCollapsible(false);
        BorderPane controlStationContainer = new BorderPane();

        //The button uses an inner class to handle the button click event
        nextBattleButton = new Button("Next Battle");
        nextBattleButton.setDisable(true);
        nextBattleButton.setOnAction(event -> {
            game.nextBattle();
        });

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
                        break;
                    }
                }

                next40BattlesButton.setDisable(false);
            }
        });

        resetGameButton = new Button("Reset Game");
        resetGameButton.setDisable(true);
        resetGameButton.setOnAction(event -> {
            game.restart(null);
            playersListView.setItems(FXCollections.observableArrayList(game.getPlayers()));
            updateLeadersBoard();
        });

        controlStationContainer.setLeft(resetGameButton);
        controlStationContainer.setRight(nextBattleButton);
        controlStationContainer.setCenter(next40BattlesButton);
        bottomPane.setContent(controlStationContainer);

        rootLayout.setRight(rightPane);
        rootLayout.setBottom(bottomPane);
        //Add the BorderPane to the Scene
        Scene appScene = new Scene(rootLayout, 1400, 900);
        //Add the Scene to the Stage
        gamePrimaryStage.setScene(appScene);
        gamePrimaryStage.show();
        return true;
    }

    public TextArea getGameConsole() {
        return gameConsoleTextArea;
    }

    private ListView setUpPlayersLeaderBoard() {
        final TitledPane playersLeaderBoardContainer = new TitledPane();
        playersLeaderBoardContainer.setText("Players Leader Board");
        final FlowPane playersLeaderBoardFlowPane = new FlowPane();
        playersLeaderBoardFlowPane.setHgap(500);
        playersLeaderBoardFlowPane.setVgap(10);
        playersLeaderBoardFlowPane.autosize();

        ListView playersListView = new ListView(FXCollections.observableArrayList(game.getPlayers()));
        playersListView.setMinWidth(600);

        TitledPane gameConsoleContainer = setUpGameConsole();

        playersLeaderBoardFlowPane.getChildren().add(playersListView);
        playersLeaderBoardFlowPane.getChildren().add(gameConsoleContainer);

        playersLeaderBoardFlowPane.setVisible(true);
        playersLeaderBoardContainer.setContent(playersLeaderBoardFlowPane);

        rootLayout.setLeft(playersLeaderBoardContainer);
        return playersListView;
    }

    private TitledPane setUpGameConsole() {
        TitledPane gameConsoleContainer = new TitledPane();
        gameConsoleContainer.setText("Game Console");
        gameConsoleContainer.setCollapsible(false);
        gameConsoleTextArea = new TextArea();
        gameConsoleTextArea.setMaxSize(600, 200);
        gameConsoleTextArea.setWrapText(true);
        gameConsoleTextArea.setEditable(false);
        gameConsoleContainer.setContent(gameConsoleTextArea);
        return gameConsoleContainer;
    }

    public void updateLeadersBoard() {
        //switch the visibility for each FlowPane
        ObservableList items = playersListView.getItems();
        /*this is a sort method that sorts the elements of a collection.
        it uses a lambda to do its job.
        Lambdas are new to Java, so be sure to research them.*/
        items.sort((currentElement, nextElement) ->
                ((Character) currentElement).compare((Character) nextElement));
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

    public Button getResetGameButton() {
        return resetGameButton;
    }
}
