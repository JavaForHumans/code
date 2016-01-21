package buildingAGame.objects.characters.human;

import buildingAGame.objects.characters.AbstractCharacter;
import buildingAGame.objects.characters.pet.Pet;

import java.lang.*;
import java.util.LinkedList;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public abstract class Human extends AbstractCharacter {
    private Pet pet;
    private LinkedList<Human> friendsList = new LinkedList<>();

    public Human(String name) {
        this.name = name;
        typeDisplay = getClass().getSimpleName();
    }

    public void setPet(Pet pet) {
        this.pet = pet;
        System.out.printf("%s: I have a new pet. Hi %s!\n",
                name, pet.getName());
    }

    public Pet getPet() {return pet;}

    public LinkedList<Human> getFriendsList() {return friendsList;}

    @Override
    public String toString(){
        if(getPet() == null) {
            return String.format("%s: %s | Level %d "
                            + "\n\t| Attack Power: %d | Defense Power: %d | Health: %s",
                    name, typeDisplay, experienceLevel, attackPower, defensePower, health);
        } else {
            return String.format("%s: %s | Level %d | Pet: %s "
                            + "\n\t| Attack Power: %d | Defense Power: %d | Health: %s",
                    name, typeDisplay, experienceLevel, getPet().getName(), attackPower, defensePower, health);
        }
    }
}
