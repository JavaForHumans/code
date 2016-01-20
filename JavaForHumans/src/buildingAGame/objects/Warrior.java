package buildingAGame.objects;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Warrior extends Human {

    //warriors have a shield that adds to their defense
    private int shieldDefensePower = 10;

    public Warrior(String name) {
        super(name);
        System.out.println(name + ": Ready for battle!");
    }

    @Override
    public void attack(Character opponent) {
        if(!isSelf(opponent) && isAlive()) {
            System.out.println("Attacking " + opponent.getName() + " with my sword!");

            //try to do damage to the opponent's health
            if(opponent.doDamageToHealth(attackPower, experienceLevel)){
                //if attack was successful and damage was done to opponent's health
                //gain experience for it
                gainExperience(ACTION_LANDED_ATTACK);
            }
        }
    }

    @Override
    public int getDefensePower() {
        return super.getDefensePower() + shieldDefensePower;
    }

    @Override
    public String toString(){
        if(getPet() == null) {
            return String.format("{Warrior: %s | Level %d}", name, experienceLevel);
        } else {
            return String.format("{Warrior: %s | Level %d | Pet: %s}", name, experienceLevel, getPet().getName());
        }
    }
}
