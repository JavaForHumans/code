package abstractClassesAndInterfaces;

/**
 * Created by Lincoln W Daniel on 1/17/2016.
 */
public class Main {
    public static void main(String[] args){
        Archer niceArcher = new Archer("Tom");
        Human meanArcher = new Archer("Stacy");
        Character modernArcher = new Archer("Tabitha");

        Dog wildDog = new Dog("Hunter", meanArcher);
        Pet petDog = new Dog("Buddy", niceArcher);
        Character modernDog = new Dog("Java", meanArcher);

        System.out.println("\nStart Game\n");
        meanArcher.attack(niceArcher);
        niceArcher.getPet().attack(meanArcher);
        meanArcher.attack(niceArcher.getPet());
    }
    /*Prints:
        Tom: Found 5 arrows ...
        Stacy: Found 3 arrows ...
        Tabitha: Found 6 arrows ...
        Stacy: I have a new pet. Hi Hunter!
        Hunter: Wolf Wolf!
        Tom: I have a new pet. Hi Buddy!
        Buddy: Wolf Wolf!
        Stacy: I have a new pet. Hi Java!
        Java: Wolf Wolf!

        Start Game

        Stacy: Attacking Tom with my arrows!
        Tom: I've been hit. My health now = 90.0
        Buddy: Biting Stacy
        Stacy: I've been hit. My health now = 99.0
        Stacy: Attacking Buddy with my arrows!
        Buddy: Wolf Wolf!
        Buddy: Health now = 90.0
     */
}
