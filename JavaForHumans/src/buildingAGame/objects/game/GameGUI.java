package buildingAGame.objects.game;

import buildingAGame.objects.characters.Character;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public class GameGUI {
    private final Stage gamePrimaryStage;
    private final String name;
    private GUIGame game;
    private TitledPane defenderPane;
    private TitledPane attackerPane;
    private BorderPane rootLayout;
    private ListView playersListView;
    private Button resetGameButton;
    private Label winnerLabel;
    private TextArea winnerTextArea;
    private TextArea attackerTextArea;
    private TextArea defenderTextArea;

    public GameGUI(String name, Stage primaryStage) {
        this.name = name;
        this.gamePrimaryStage = primaryStage;
    }

    public void setGUIGame(GUIGame game) {
        this.game = game;
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


        BorderPane battlePane = new BorderPane();
        attackerPane = new TitledPane();
        defenderPane = new TitledPane();
        attackerPane.setText("Attacker Zone");
        defenderPane.setText("Defender Zone");

        attackerTextArea = new TextArea("Attacker Area");
        defenderTextArea = new TextArea("Attacker Area");
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
        battlePane.setTop(attackerPane);
        battlePane.setBottom(defenderPane);

        TitledPane rightPane = new TitledPane("Current Battle", battlePane);
        rightPane.setCollapsible(false);

        //The button uses an inner class to handle the button click event
        resetGameButton = new Button("Next Battle");

        resetGameButton.setOnAction(event -> {
            game.nextBattle();
        });

        rootLayout.setRight(rightPane);
        rootLayout.setBottom(resetGameButton);
        //Add the BorderPane to the Scene
        Scene appScene = new Scene(rootLayout, 1200, 800);
        //Add the Scene to the Stage
        gamePrimaryStage.setScene(appScene);
        gamePrimaryStage.show();
        return true;
    }

    public TextArea getWinnerTextArea() {
        return winnerTextArea;
    }

    private ListView setUpPlayersLeaderBoard() {
        final TitledPane titledPane = new TitledPane();
        titledPane.setText("Players Leader Board");

        final FlowPane listFlowPane = new FlowPane();
        listFlowPane.setHgap(500);
        listFlowPane.setVgap(10);
        ListView playersList = new ListView(FXCollections.observableArrayList(game.getPlayers()));

        winnerTextArea = new TextArea();
        winnerTextArea.setMaxSize(600, 100);
        winnerTextArea.setWrapText(true);
        winnerTextArea.setEditable(false);

        listFlowPane.getChildren().add(playersList);
        listFlowPane.getChildren().add(new Label("Winner"));
        listFlowPane.getChildren().add(winnerTextArea);

        titledPane.setContent(listFlowPane);
        rootLayout.setLeft(titledPane);
        listFlowPane.autosize();
        listFlowPane.setVisible(true);

        return playersList;
    }

    public void updateLeadersBoard() {
        //switch the visibility for each FlowPane
        ObservableList items = playersListView.getItems();

        Character character = (Character) items.get(0);
        items.sort((o, t1) -> {
            if (((Character) o).getExperienceLevel() > ((Character) t1).getExperienceLevel()) {
                return 0;
            } else return 1;
        });
    }

    public TitledPane getAttackerPanel() {
        return attackerPane;
    }

    public TitledPane getDefenderPanel() {
        return defenderPane;
    }

    public Button getResetButton() {
        return resetGameButton;
    }

    public TextArea getAttackerTextArea() {
        return attackerTextArea;
    }

    public TextArea getDefenderTextArea() {
        return defenderTextArea;
    }
}
