package buildingAGame.objects.characters.human;

import buildingAGame.objects.characters.Character;


/**
 * Created by Lincoln W Daniel, the ModernNerd, on 1/17/2016
 * as an application of the concepts expressed in the "Java for Humans" book.
 *
 * This class creates a template for Archer objects.
 * It will be used by the Game class to populate characters in a game.
 *
 * This is a subclass of Human.class
 */
public class Archer extends Human {
    //the number of arrows this archer has at any given time
    protected int numArrows = 0;
    //this is the maximum amount of arrows this archer can attack with.
    protected int maxAttackArrows = 30;

    public Archer(String name) {
        super(name);
        findArrows();
    }

    public void findArrows() {
        if(isAlive()) {
            int numArrowsFound = randomGenerator.nextInt(100) + 10;
            speak("Found " + numArrowsFound + " arrows!", true);
            numArrows += numArrowsFound;
            speak("I now have " + numArrows + " arrows.", true);

            gainExperience(ACTION_ARCHER_FOUND_ARROWS);
        }
    }

    @Override
    public void attack(Character opponent) {
        if(!isSelf(opponent) && isAlive()) {
            if(numArrows > 0) {
                int numArrowsToAttackWith = getNumArrowsToAttackWith();

                speak(" Attacking " + opponent.getName()
                        + " with " + numArrowsToAttackWith + " arrows!", true);

                //subtract the number of arrows you're using for this attack
                // from the number of arrows you have
                numArrows -= numArrowsToAttackWith;

                //calculate the attack power of this arrow with the number of arrows it will attack with
                int attackPowerWithArrows = calculateAttackPowerWithArrows(numArrowsToAttackWith);
                //try to do damage to the opponent's health
                if(opponent.doDamageToHealth(attackPowerWithArrows, experienceLevel)){
                    //if attack was successful and damage was done to opponent's health
                    speak("Successful Attack of " + attackPowerWithArrows, true);
                    //gain experience for it
                    gainExperience(ACTION_LANDED_ATTACK);
                } else {
                    speak("Failed Attack of " + attackPowerWithArrows, true);
                }

                //find more arrows to replace the arrows you just used
                findArrows();
            } else {
                speak("No arrows. Can't attack " + opponent.getName() + " ...", true);
                //find more arrows for next attack
                findArrows();
            }
        }
        train();
    }

    protected int getNumArrowsToAttackWith() {
        int numArrowsToAttackWith = randomGenerator.nextInt(numArrows) + 1;

        //make sure the numArrowsToAttackWith does not exceed the max allowed for this archer
        numArrowsToAttackWith = Math.min(numArrowsToAttackWith, maxAttackArrows);
        return numArrowsToAttackWith;
    }

    /**
     * Allows this character to try to train its attack power.
     */
    @Override
    public void train() {
        //archers always train their attack power by at least one
        int trainedAmount = randomGenerator.nextInt(getMaxTrainingCapability()) + 1;
        attackPower += trainedAmount;
        if(trainedAmount > 0) {
            speak("Trained. Added " + trainedAmount + " attack power.", true);
            gainExperience(ACTION_TRAINED);
        } else {
            speak("Training failed ...", true);
        }
    }

    public int calculateAttackPowerWithArrows(int numArrowsToAttackWith) {
        if(numArrowsToAttackWith == -1) {
            numArrowsToAttackWith = Math.min(numArrows, maxAttackArrows);
        }
        return attackPower + numArrowsToAttackWith;
    }

    public void increaseMaxAttackArrows() {
        maxAttackArrows += 10;
    }

    public int getMaxAttackArrows() {
        return maxAttackArrows;
    }

    @Override
    protected void afterLevelUpgrade() {
        increaseMaxAttackArrows();
        speak("New Max Arrows: " + maxAttackArrows, true);
        //archers also get to increase their maximum attack arrows
        findArrows();
    }

    @Override
    public int getAttackPower(){
        return calculateAttackPowerWithArrows(-1);
    }
}
