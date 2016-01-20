package polymorphism;


/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Taoist extends Human {
    public Taoist(String name) {
        super(name);
        System.out.println(name + ": Let's work some magic!");
    }

    @Override
    public void attack(Character opponent) {
        System.out.println("Attacking " + opponent.getName() + " with magical wand!");
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
