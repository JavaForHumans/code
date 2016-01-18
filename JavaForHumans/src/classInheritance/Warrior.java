package classInheritance;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Warrior extends Human {
    public Warrior(String name) {
        super(name);
    }

    @Override
    public void attack(Human opponent) {
        System.out.println("Attacking " + opponent.getName() + " with my sword!");
        super.attack(opponent);
    }
}
