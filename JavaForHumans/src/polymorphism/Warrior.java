package polymorphism;



/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Warrior extends Human {
    public Warrior(String name) {
        super(name);
        System.out.println(name + ": Ready for battle!");
    }

    @Override
    public void attack(Character opponent) {
        System.out.println("Attacking " + opponent.getName() + " with my sword!");
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
}
