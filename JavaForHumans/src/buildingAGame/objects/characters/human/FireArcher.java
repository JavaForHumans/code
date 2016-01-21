package buildingAGame.objects.characters.human;

import buildingAGame.objects.characters.Character;
/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class FireArcher extends Archer {
    int firePower = 5;
    public FireArcher(String name) {
        super(name);
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
                int numArrowsToAttackWith = randomGenerator.nextInt(numArrows) + 1;

                //the maximum arrows an archer can attack with at once is 10
                //make sure the numArrowsToAttackWith does not exceed 10
                numArrowsToAttackWith = Math.min(numArrowsToAttackWith, 10);

                speak(" Attacking " + opponent.getName()
                        + " with " + numArrowsToAttackWith + " fire arrows!");

                //subtract the number of arrows you're using for this attack
                // from the number of arrows you have
                numArrows -= numArrowsToAttackWith;

                //calculate the attack power of this arrow with the number of arrows it will attack with
                int attackPowerWithArrows = calculateAttackPowerWithArrows(numArrowsToAttackWith);
                //try to do damage to the opponent's health
                if(opponent.doDamageToHealth(attackPowerWithArrows + firePower, experienceLevel)){
                    //if attack was successful and damage was done to opponent's health
                    //gain experience for it
                    gainExperience(ACTION_LANDED_ATTACK);
                }

                //find more arrows to replace the arrows you just used
                findArrows();
            } else {
                speak("No arrows. Can't attack " + opponent.getName() + " ...");
                //find more arrows for next attack
                findArrows();
            }
        }
        train();
    }
}
