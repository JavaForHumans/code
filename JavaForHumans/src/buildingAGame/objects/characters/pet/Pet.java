package buildingAGame.objects.characters.pet;

import buildingAGame.objects.characters.*;
import buildingAGame.objects.characters.human.Human;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public abstract class Pet extends AbstractCharacter implements buildingAGame.objects.characters.Character {
    protected Human owner;

    public Pet(String name, Human owner) {
        this.name = name;
        this.owner = owner;
        this.owner.setPet(this);
        typeDisplay = getClass().getSimpleName();
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
                name, typeDisplay, experienceLevel, getOwner().getName(), attackPower, defensePower, health);
    }

    protected abstract void cry();
}
