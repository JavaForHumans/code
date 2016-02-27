package abstractClassesAndInterfaces;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public abstract class Human implements Character{
    private String name;
    private double health = 100.0;
    private long experience = 0;
    private int attackPower = 1;
    private Pet pet;

    public Human(String name) {
        this.name = name;
        gainExperience(1);
    }

    public void setPet(Pet pet) {
        this.pet = pet;
        System.out.printf("%s: I have a new pet. Hi %s!\n",
                name, pet.getName());
    }

    public abstract void attack(Character opponent);
    public abstract void defend();
    public abstract void jump();
    public abstract int heal();

    public String getName() {
        return name;
    }

    public double getHealth(){
        return health;
    }

    public long getExperience(){
        return experience;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public Pet getPet() {return pet;}

    public long gainExperience(long experience){
        this.experience += experience;
        return experience;
    }

    public double heal(double additionalHealth) {
        health += additionalHealth;
        return health;
    }

    public double decreaseHealth(int opponentAttackPower) {
        health -= opponentAttackPower;
        System.out.println(name + ": I've been hit. My health now = " + health);
        return health;
    }

    public boolean hasPet() {
        return pet != null;
    }
}
