package buildingAGame;




//Imports are listed in full to show what's being used
// could just import javafx.*
import buildingAGame.objects.game.Game;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Random;

/**
 * Created by Lincoln W Daniel, the ModernNerd, on 1/17/2016
 * as an application of the concepts expressed in the "Java for Humans" book.
 *
 * This class is what starts the program.
 * When you run the main method of this class,
 * it will launch an application that will then start our instance of the Game class.
 *
 * The Game class will allow us to play a pseudo fighting game in a graphical user interface (GUI)
 */
public class GUIGameApplication extends Application {
    //a instance of the Random class to use across our application
    public final static Random sharedRandomGen = new Random();

    /* JavaFX application still use the main method.
       It should only ever contain the call to the launch method*/
    public static void main(String[] args) {
        /* starting point for the application
        this is where we put the code for the user interface
        the launch(args) method is provided by the Application class */
        launch(args);
    }

    /**
     *
     * @return The shared Random instance for generating random numbers, data throughout the application
     */
    public static Random getSharedRandom() {
        return sharedRandomGen;
    }

    /**
     * Starts this instance of the Application class.
     * When launch() is called, it calls this method to start the application.
     *
     * This is where we must create and start our game.
     * Although this method starts our entire game,
     * it doesn't have much code
     * because all of the functionality of our game is encapsulated in our Game and GameGUI classes
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        Game guiGame = new Game(null, primaryStage);
    }
}