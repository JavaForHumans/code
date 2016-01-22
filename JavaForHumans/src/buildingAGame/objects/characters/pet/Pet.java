package buildingAGame.objects.characters.pet;

import buildingAGame.objects.characters.*;
import buildingAGame.objects.characters.human.Human;

import java.util.HashMap;

/**
 * Created by Lincoln W Daniel, the ModernNerd, on 1/17/2016
 * as an application of the concepts expressed in the "Java for Humans" book.
 *
 * This class creates a template for Pet objects.
 * It will be used by the Game class to populate characters in a game.
 */
public abstract class Pet extends AbstractCharacter implements buildingAGame.objects.characters.Character {
    protected Human owner;

    public Pet(String name, Human owner) {
        this.name = name;
        this.owner = owner;
        this.owner.setPet(this);
        //have the pet train for battle
        train();
    }

    public Human getOwner() {
        return owner;
    }

    @Override
    public boolean doDamageToHealth(int opponentAttackPower, int opponentExperienceLevel) {
        cry();
        return super.doDamageToHealth(opponentAttackPower, opponentExperienceLevel);
    }

    @Override
    public String toString(){
        return String.format("%s: %s | Level %d | Owner: %s "
                + "\n\t| Attack Power: %d | Defense Power: %d | Health: %s",
                name, getTypeDisplay(), experienceLevel, getOwner().getName(), getAttackPower(), getDefensePower(), health);
    }

    @Override
    protected void afterLevelUpgrade() {
        //all pets train after upgrade
        train();
    }

    protected abstract void cry();
}
