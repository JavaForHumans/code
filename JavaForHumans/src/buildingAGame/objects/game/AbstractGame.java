package buildingAGame.objects.game;

import buildingAGame.objects.characters.Character;
import buildingAGame.objects.characters.human.Archer;
import buildingAGame.objects.characters.human.FireArcher;
import buildingAGame.objects.characters.human.Taoist;
import buildingAGame.objects.characters.human.Warrior;
import buildingAGame.objects.characters.pet.Dog;
import buildingAGame.objects.characters.pet.Lion;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public abstract class AbstractGame implements Game {
    static final int DEFAULT_NUM_TURNS_TO_TAKE = 1000;
    private final static Random sharedRandomGen = new Random();

    protected final LinkedList<Character> allCharacters = new LinkedList<>();
    protected int numCharactersInGame;
    protected int numCharactersLeftInGame;
    protected int numTurnsToTake;
    protected int turnsTaken;

    public AbstractGame(LinkedList<Character> allCharactersInGame) {
        setPlayers(allCharactersInGame);
        //save the number of characters in the game to a local int variable
        numCharactersInGame = allCharacters.size();
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
        numCharactersInGame = allCharacters.size();
        numCharactersLeftInGame = numCharactersInGame;
    }

    @Override
    public void reset(List<Character> newCharacters) {
        turnsTaken = 0;
        setPlayers(newCharacters);
    }

    @Override
    public int getNumCharacters() {
        return numCharactersInGame;
    }

    @Override
    public int getNumTurns() {
        return numTurnsToTake;
    }

    @Override
    public LinkedList<Character> getPlayers() {
        return allCharacters;
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
        defaultPlayers.add(new Lion("Musafa", tabitha));
        defaultPlayers.add(new FireArcher("ModernNerd"));
        defaultPlayers.add(new Taoist("Lincoln"));
        defaultPlayers.add(new Warrior("Sasha"));
        defaultPlayers.add(new Archer("Eman"));
        FireArcher lucy = new FireArcher("Lucy");
        defaultPlayers.add(lucy);
        defaultPlayers.add(new Dog("Buddy", lucy));
        Warrior andres = new Warrior("Andres");
        defaultPlayers.add(andres);
        defaultPlayers.add(new Lion("Simba", andres));
        defaultPlayers.add(new Taoist("Keith"));
        return defaultPlayers;
    }

    public Random getSharedRandomGen() {
        return sharedRandomGen;
    }
}
