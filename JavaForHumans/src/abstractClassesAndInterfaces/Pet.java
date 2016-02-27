package abstractClassesAndInterfaces;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public abstract class Pet implements Character{
    protected String name;
    protected double health = 100.0;
    protected long experience = 0;
    private int attackPower = 1;
    protected Human owner;

    public Pet(String name, Human owner) {
        this.name = name;
        this.owner = owner;
        this.owner.setPet(this);
        gainExperience(1);
    }

    public abstract void attack(Character character);
    public abstract void defend();
    public abstract void jump();
    public abstract int heal();

    public Human getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public double getHealth(){
        return health;
    }

    public long getExperience(){
        return experience;
    }

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
        cry();
        System.out.println(getName() + ": Health now = " + getHealth());
        return health;
    }

    protected abstract void cry();

    public int getAttackPower() {
        return attackPower;
    }
    
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }
}
