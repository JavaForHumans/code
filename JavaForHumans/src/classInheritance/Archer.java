package classInheritance;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Archer extends Human {
    private int numArrows = 0;

    public Archer(String name) {
        super(name);
        findArrows();
    }

    public void findArrows() {
        System.out.println("Looking for arrows");
    }
}
