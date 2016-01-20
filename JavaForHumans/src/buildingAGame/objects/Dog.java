package buildingAGame.objects;


/**
 * Created by lwdthe1 on 1/18/2016.
 */
public class Dog extends Pet implements Character {

    public Dog(String name, Human owner) {
        super(name, owner);
        bark();
    }

    public void bark() {
        speak("Wolf Wolf!");
    }

    @Override
    public void attack(buildingAGame.objects.Character opponent) {
        if(!isSelf(opponent) && isAlive()) {
            speak("Biting " + opponent.getName());
            if(opponent.doDamageToHealth(attackPower, experienceLevel)){
                gainExperience(ACTION_LANDED_ATTACK);
            }
        }
    }

    @Override
    protected void cry() {
        bark();
    }

    @Override
    public String toString(){
        return String.format("{Dog: %s | Level %d | Owner: %s}", name, experienceLevel, getOwner());
    }
}
