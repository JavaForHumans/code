package buildingAGame.objects.characters.human;

import buildingAGame.objects.characters.AbstractCharacter;
import buildingAGame.objects.characters.pet.Dog;
import buildingAGame.objects.characters.pet.Lion;
import buildingAGame.objects.characters.pet.Pet;

import java.lang.*;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Lincoln W Daniel, the ModernNerd, on 1/17/2016
 * as an application of the concepts expressed in the "Java for Humans" book.
 *
 * This class creates a template for Human objects.
 * It will be used by the Game class to populate characters in a game.
 *
 */
public abstract class Human extends AbstractCharacter {
    private Pet pet;
    final private LinkedList<Human> friendsList = new LinkedList<>();

    public Human(String name) {
        this.name = name;
        //have the human train before battle
        train();
    }

    public void addFriend(Human newFriend) {
        friendsList.add(newFriend);
    }

    public void setPet(Pet pet) {
        this.pet = pet;
        System.out.printf("%s: I have a new pet. Hi %s!\n",
                name, pet.getName());
    }

    public Pet getPet() {return pet;}

    public boolean hasPet() { return pet != null; }

    public LinkedList<Human> getFriendsList() {return friendsList;}

    @Override
    public String toString(){
        if(getPet() == null) {
            return String.format("%s: %s | Level %d "
                            + "\n\t| Attack Power: %d | Defense Power: %d | Health: %s",
                    name, getTypeDisplay(), experienceLevel, attackPower, defensePower, health);
        } else {
            return String.format("%s: %s | Level %d | Pet: %s "
                            + "\n\t| Attack Power: %d | Defense Power: %d | Health: %s",
                    name, getTypeDisplay(), experienceLevel, getPet().getName(), getAttackPower(), getDefensePower(), health);
        }
    }
}
