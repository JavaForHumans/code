package buildingAGame.objects;

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
            return String.format("{Human: %s | Level %d}", name, experienceLevel);
        } else {
            return String.format("{Human: %s | Level %d | Pet: %s}", name, experienceLevel, getPet());
        }
    }
}
