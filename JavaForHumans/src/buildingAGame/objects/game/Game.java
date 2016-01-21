package buildingAGame.objects.game;


import buildingAGame.objects.characters.Character;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public interface Game {
    void start();
    void reset(List<Character> allCharacters);
    int getNumCharacters();
    int getNumTurns();
    void setNumTurns(int numTurnsToTake);
    LinkedList<Character> getPlayers();
}
