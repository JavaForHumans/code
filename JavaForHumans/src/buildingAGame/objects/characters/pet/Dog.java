package buildingAGame.objects.characters.pet;


import buildingAGame.objects.characters.Character;
import buildingAGame.objects.characters.human.Human;

/**
 * Created by lwdthe1 on 1/18/2016.
 */
public class Dog extends Pet implements buildingAGame.objects.characters.Character {

    public Dog(String name, Human owner) {
        super(name, owner);
        bark();
    }

    public void bark() {
        speak("Wolf Wolf!");
    }

    @Override
    public void attack(Character opponent) {
        if(!isSelf(opponent) && isAlive()) {
            speak("Biting " + opponent.getName());
            if(opponent.doDamageToHealth(attackPower, experienceLevel)){
                gainExperience(ACTION_LANDED_ATTACK);
            }
        }
        train();
    }

    @Override
    public void train() {
        int trainedAmount = randomGenerator.nextInt(5);
        attackPower += trainedAmount;
        if(trainedAmount > 0) {
            gainExperience(ACTION_TRAINED);
        }
    }

    @Override
    protected void cry() {
        bark();
    }
}
