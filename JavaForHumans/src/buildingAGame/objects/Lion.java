package buildingAGame.objects;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public class Lion extends Pet implements Character {
    public Lion(String name, Human owner) {
        super(name, owner);
        roar();
    }

    public void roar() {
        speak("Meow Meow Meow!");
    }

    @Override
    public void attack(buildingAGame.objects.Character opponent) {
        if(!isSelf(opponent) && isAlive()) {
            speak("Scratching " + opponent.getName());
            if(opponent.doDamageToHealth(attackPower, experienceLevel)){
                gainExperience(ACTION_LANDED_ATTACK);
            }
        }
    }

    @Override
    protected void cry() {
        roar();
    }

    @Override
    public String toString(){
        return String.format("{Lion: %s | Level: %d | Owner: %s}", name, experienceLevel, getOwner().getName());
    }
}
