package buildingAGame.utils;

import buildingAGame.objects.*;
import buildingAGame.objects.Character;

import java.util.LinkedList;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public class Config {
    /*
    List of all characters in our game.
    It is final so that it cannot be erased by accident by setting it to null or changing its value.
    We can still add, update, and remove elements freely, though.
     */
    private final static LinkedList<Character> allCharactersInGame = new LinkedList<>();

    /**
     * returns the list of all the characters in our game
     * @return
     */
    public static LinkedList<Character> getAllCharactersInGame() {
        return allCharactersInGame;
    }
}
