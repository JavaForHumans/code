package classInheritance;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class FireArcher extends Archer {
    int firePower = 1;
    public FireArcher(String name) {
        super(name);
    }

    @Override
    public void attack(Human opponent) {
        System.out.println("Attacking " + opponent.getName() + " with fire arrows!");
        opponent.decreaseHealth(attackPower + firePower);
    }
}
