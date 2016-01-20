package polymorphism;



/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class FireArcher extends Archer {
    int firePower = 1;
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
        System.out.println("Attacking " + opponent.getName() + " with fire arrows!");
        opponent.decreaseHealth(getAttackPower() + firePower);
    }
}
