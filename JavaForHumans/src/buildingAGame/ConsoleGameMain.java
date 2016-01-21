package buildingAGame;

import buildingAGame.objects.game.ConsoleGame;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class ConsoleGameMain {
    public static void main(String[] args){
        ConsoleGame game = new ConsoleGame(ConsoleGame.getDefaultPlayers());
        game.start();
    }
}
