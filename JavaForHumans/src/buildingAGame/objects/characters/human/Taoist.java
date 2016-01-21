package buildingAGame.objects.characters.human;

import buildingAGame.objects.characters.Character;
/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Taoist extends Human {
    public Taoist(String name) {
        super(name);
        speak(name + ": Let's work some magic!", false);
    }

    @Override
    public void attack(Character opponent) {
        if(!isSelf(opponent) && isAlive()) {
            speak("Attacking " + opponent.getName() + " with magic!", true);

            //try to do damage to the opponent's health
            if(opponent.doDamageToHealth(attackPower, experienceLevel)){
                //if attack was successful and damage was done to opponent's health
                speak("Successful Attack of " + attackPower, true);
                //gain experience for it
                gainExperience(ACTION_LANDED_ATTACK);
            } else {
                speak("Failed Attack of " + attackPower, true);
            }
        }
        train();
    }

    /**
     * Allows this character to try to train its attack power.
     */
    @Override
    public void train() {
        int trainedAmount = randomGenerator.nextInt(getMaxTrainingCapability());
        attackPower += trainedAmount;
        if(trainedAmount > 0) {
            speak("Trained. Added " + trainedAmount + " attack power.", true);
            gainExperience(ACTION_TRAINED);
        } else {
            speak("Training failed ...", true);
        }
    }

    @Override
    protected void afterLevelUpgrade() {
        //taoist also get to heal itself when it levels
        healSelf(Math.min(health * .5,MAX_HEALTH / 3));
    }
}
