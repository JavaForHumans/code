package abstractClassesAndInterfaces;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Main {
    public static void main(String[] args){
        Human niceArcher = new Archer("Stacy");
        Pet pet = new Dog("Buddy", niceArcher);
        Archer meanArcher = new Archer("Tom");

        System.out.println();
        meanArcher.attack(niceArcher);
        System.out.println();
        niceArcher.getPet().attack(meanArcher);
        System.out.println();
        meanArcher.attack(niceArcher.getPet());
    }
}
