package classInheritance;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Human {
    protected String name;
    protected double health = 100.0;
    protected long experience = 0;
    protected int attackPower = 1;

    public Human(String name) {
        this.name = name;
        gainExperience(1);
    }

    public double getHealth(){
        return health;
    }

    public String getName(){
        return name;
    }

    public long getExperience(){
        return experience;
    }

    public long gainExperience(long experience){
        this.experience += experience;
        return experience;
    }

    public long addAttackPower(long attackPower) {
        this.attackPower += attackPower;
        return attackPower;
    }

    public double heal(double additionalHealth) {
        health += additionalHealth;
        return health;
    }

    public void attack(Human opponent) {
        opponent.decreaseHealth(attackPower);
    }

    public double decreaseHealth(int opponentAttackPower) {
        health -= attackPower;
        return health;
    }

    public void jump() {
        System.out.println("Jumped up");
    }
}