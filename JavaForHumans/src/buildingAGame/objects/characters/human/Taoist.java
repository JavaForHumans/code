package buildingAGame.objects.characters.human;

import buildingAGame.objects.characters.Character;
/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Taoist extends Human {
    public Taoist(String name) {
        super(name);
        System.out.println(name + ": Let's work some magic!");
    }

    @Override
    public void attack(Character opponent) {
        if(!isSelf(opponent) && isAlive()) {
            System.out.println("Attacking " + opponent.getName() + " with magical wand!");

            //try to do damage to the opponent's health
            if(opponent.doDamageToHealth(attackPower, experienceLevel)){
                //if attack was successful and damage was done to opponent's health
                //gain experience for it
                gainExperience(ACTION_LANDED_ATTACK);
            }
        }
        train();
    }

    @Override
    public void train() {
        int trainedAmount = randomGenerator.nextInt(10);
        attackPower += trainedAmount;
        if(trainedAmount > 0) {
            gainExperience(ACTION_TRAINED);
        }
    }
}
