package buildingAGame.objects;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Archer extends Human implements Character {
    int numArrows = 0;

    public Archer(String name) {
        super(name);
        findArrows();
    }

    public void findArrows() {
        if(isAlive()) {
            int numArrowsFound = randomGenerator.nextInt(10) + 1;
            speak("Found " + numArrowsFound + " arrows!");
            numArrows += numArrowsFound;
            speak("I now have " + numArrows + " arrows.");

            gainExperience(ACTION_ARCHER_FOUND_ARROWS);
        }
    }

    @Override
    public void attack(Character opponent) {
        if(!isSelf(opponent) && isAlive()) {
            if(numArrows > 0) {
                int numArrowsToAttackWith = randomGenerator.nextInt(numArrows) + 1;

                //the maximum arrows an archer can attack with at once is 10
                //make sure the numArrowsToAttackWith does not exceed 10
                numArrowsToAttackWith = Math.min(numArrowsToAttackWith, 10);

                speak(" Attacking " + opponent.getName()
                        + " with " + numArrowsToAttackWith + " arrows!");

                //subtract the number of arrows you're using for this attack
                // from the number of arrows you have
                numArrows -= numArrowsToAttackWith;

                //calculate the attack power of this arrow with the number of arrows it will attack with
                int attackPowerWithArrows = calculateAttackPowerWithArrows(numArrowsToAttackWith);
                //try to do damage to the opponent's health
                if(opponent.doDamageToHealth(attackPowerWithArrows, experienceLevel)){
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
    }

    public int calculateAttackPowerWithArrows(int numArrowsToAttackWith) {
        return getAttackPower() + numArrowsToAttackWith;
    }

    @Override
    public String toString(){
        if(getPet() == null) {
            return String.format("{Archer: %s | Level %d}", name, experienceLevel);
        } else {
            return String.format("{Archer: %s | Level %d | Pet: %s}", name, experienceLevel, getPet().getName());
        }
    }
}
