package buildingAGame.objects.characters.human;

import buildingAGame.objects.characters.Character;

/**
 * Created by Lincoln W Daniel, the ModernNerd, on 1/17/2016
 * as an application of the concepts expressed in the "Java for Humans" book.
 *
 * This class creates a template for Warrior objects.
 * It will be used by the Game class to populate characters in a game.
 *
 * This is a subclass of Human.class
 */
public class Warrior extends Human {

    //warriors have a shield that adds to their defense
    private int shieldDefensePower = 100;

    public Warrior(String name) {
        super(name);
        speak(name + ": Ready for battle!", false);
    }

    @Override
    public void attack(Character opponent) {
        if(!isSelf(opponent) && isAlive()) {
            speak("Attacking " + opponent.getName() + " with my sword!", true);

            //try to do damage to the opponent's health
            if(opponent.doDamageToHealth(attackPower, experienceLevel)){
                //if attack was successful and damage was done to opponent's health
                speak("Successful Attack of " + attackPower, true);
                //gain experience for it
                gainExperience(ACTION_LANDED_ATTACK);
            }  else {
                speak("Failed Attack of " + attackPower, true);
            }
        }
        train();
    }

    @Override
    public int getDefensePower() {
        return super.getDefensePower() + shieldDefensePower;
    }

    @Override
    protected void afterLevelUpgrade() {

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
}
