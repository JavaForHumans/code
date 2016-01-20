package polymorphism;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Archer extends Human implements Character {
    private int numArrows = 0;

    public Archer(String name) {
        super(name);
        findArrows();
    }

    public void findArrows() {
        int numArrowsFound = randomGenerator.nextInt(10) + 1;
        System.out.println(getName() + ": Found "
                + numArrowsFound + " arrows!");
        numArrows += numArrowsFound;
        System.out.println(getName() + ": I now have " + numArrows + " arrows.");
    }

    @Override
    public void defend() {

    }

    @Override
    public void attack(Character opponent) {
        System.out.println(getName() + ": Attacking " + opponent.getName()
                + " with my arrows!");
        opponent.decreaseHealth(getAttackPower());
    }

    @Override
    public void jump() {

    }

    @Override
    public int heal() {
        return 0;
    }
}
