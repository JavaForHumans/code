package methodsAndDotNotation;

/**
 * Created by lwdthe1 on 1/8/2016.
 */
public class CoolDog {
    private static int numCoolDogs;

    private final int id;
    private String name;
    private int age;
    private int height;
    private Human owner;

    public CoolDog(String name, int age, int height) {
        this.name = name;
        this.age = age;
        this.height = height;

        id = ++numCoolDogs;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public int getHeight() { return height; }
    public Human getOwner(){ return owner; }

    public void setOwner(Human owner) { this.owner = owner; }

    private boolean hasOwner() {
        return owner != null;
    }

    public void jump(int howHigh, int howLong) {
        if(hasOwner()) {
            System.out.println("Jumping " + howHigh
                    + " inches high for " + howLong + " seconds.");
        } else { bark(); }
    }

    public void rollOver() {
        if(hasOwner()) {
            System.out.println("Rolling over for " + owner.getName() + ".");
        } else { bark(); }
    }

    public void bark() {
        System.out.println("Wolf! WOLF!!");
    }

    public static int getNumCoolDogsInWorld(){
        return numCoolDogs;
    }
}
