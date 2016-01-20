package abstractClassesAndInterfaces;

/**
 * Created by lwdthe1 on 1/18/2016.
 */
public class Dog extends Pet implements Character {

    public Dog(String name, Human owner) {
        super(name, owner);
        bark();
    }

    public void bark() {
        System.out.println(getName() + ": Wolf Wolf!");
    }

    @Override
    public void attack(Character opponent) {
        System.out.println(getName() + ": Biting " + opponent.getName());
        opponent.decreaseHealth(getAttackPower());
    }
    @Override
    public void defend() {

    }
    @Override
    public void jump() {

    }
    @Override
    public int heal() {
        return 0;
    }

    @Override
    protected void cry() {
        bark();
    }
}
