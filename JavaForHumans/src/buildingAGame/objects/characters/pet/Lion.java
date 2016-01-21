package buildingAGame.objects.characters.pet;

import buildingAGame.objects.characters.*;
import buildingAGame.objects.characters.Character;
import buildingAGame.objects.characters.human.Human;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public class Lion extends Pet implements buildingAGame.objects.characters.Character {
    public Lion(String name, Human owner) {
        super(name, owner);
        roar();
    }

    public void roar() {
        speak("Meow Meow Meow!");
    }

    @Override
    public void attack(Character opponent) {
        if(!isSelf(opponent) && isAlive()) {
            speak("Scratching " + opponent.getName());
            if(opponent.doDamageToHealth(attackPower, experienceLevel)){
                gainExperience(AbstractCharacter.ACTION_LANDED_ATTACK);
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
        roar();
    }
}
