package buildingAGame.objects.characters.human;

import buildingAGame.objects.characters.Character;
import buildingAGame.utils.Utils;

/**
 * Created by Lincoln W Daniel, the ModernNerd, on 1/17/2016
 * as an application of the concepts expressed in the "Java for Humans" book.
 *
 * This class creates a template for FireArcher objects.
 * It will be used by the Game class to populate characters in a game.
 *
 * This is a subclass of Human.class
 */
public class FireArcher extends Archer {
    int firePower;
    public FireArcher(String name) {
        super(name);
        /*
        fire archers start off with less maxAttackArrows than regular archers
        because fire archers get more power from their fire
         */
        maxAttackArrows = 20;
        firePower = Utils.getSharedRandomGen().nextInt(50) + 40;
    }

    /**
     * The only thing the fire archer does differently
     * from other archers instances is its method of  attacking
     * @param opponent
     */
    @Override
    public void attack(Character opponent) {
        if(!isSelf(opponent) && isAlive()) {
            if(numArrows > 0) {
                int numArrowsToAttackWith = getNumArrowsToAttackWith();

                speak(" Attacking " + opponent.getName()
                        + " with " + numArrowsToAttackWith + " fire arrows!", true);

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

    @Override
    public int calculateAttackPowerWithArrows(int numArrowsToAttackWith) {
        if(numArrowsToAttackWith == -1) {
            numArrowsToAttackWith = Math.min(numArrows, maxAttackArrows);
        }
        return attackPower + firePower + numArrowsToAttackWith;
    }
}
