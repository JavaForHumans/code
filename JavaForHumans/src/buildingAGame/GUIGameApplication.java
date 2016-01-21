package buildingAGame;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
/*contains a Label and ChoiceBox, the second FlowPane a Label and a ListView. The Button switches the visibility of each FlowPane.*/

//Imports are listed in full to show what's being used
// could just import javafx.*

import buildingAGame.objects.game.ConsoleGame;
import buildingAGame.objects.game.GUIGame;
import buildingAGame.objects.game.GameGUI;
import javafx.application.Application;
import javafx.stage.Stage;

public class GUIGameApplication extends Application {
    //JavaFX applicatoin still use the main method.
    // It should only ever contain the call to the launch method
    public static void main(String[] args) {
        //starting point for the application
        // this is where we put the code for the user interface
        //the launch(args) method is provided by the Application class
        launch(args);
    }

    /**
     * Starts this instance of the Application class.
     * When launch() is called, startInConsole() is called to startInConsole the application
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        GameGUI gameGUI = new GameGUI("ModernNerd Battles", primaryStage);

        GUIGame guiGame = new GUIGame(null, gameGUI);
        gameGUI.setup();
        guiGame.start();
    }


}