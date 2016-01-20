package buildingAGame.objects;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public abstract class Pet extends AbstractCharacter implements Character {
    protected Human owner;

    public Pet(String name, Human owner) {
        this.name = name;
        this.owner = owner;
        this.owner.setPet(this);
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
        return String.format("{Pet: %s | Level %d | Owner: %s}", name, experienceLevel, getOwner());
    }

    protected abstract void cry();
}
