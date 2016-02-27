package abstractClassesAndInterfaces;

public class Main {
    public static void main(String[] args){
        Archer niceArcher = new Archer("Tom");
        Human modernArcher = new Archer("Stacy");

        Dog modernDog = new Dog("Hunter", modernArcher);
        Pet petDog = new Dog("Buddy", niceArcher);

        System.out.println("\nStart Game\n");
        modernArcher.attack(niceArcher);
        niceArcher.getPet().attack(modernArcher);
        modernArcher.attack(niceArcher.getPet());
        modernDog.attack(petDog.getOwner());
        petDog.attack(modernArcher.getPet());
    }
    /*Prints:
        Tom: Found 6 arrows ...
        Stacy: Found 4 arrows ...
        Stacy: I have a new pet. Hi Hunter!
        Hunter: Wolf Wolf!
        Tom: I have a new pet. Hi Buddy!
        Buddy: Wolf Wolf!
        
        Start Game
        
        Stacy: Attacking Tom with my arrows!
        Tom: I've been hit. My health now = 90.0
        Buddy: Biting Stacy
        Stacy: I've been hit. My health now = 99.0
        Stacy: Attacking Buddy with my arrows!
        Buddy: Wolf Wolf!
        Buddy: Health now = 90.0
        Hunter: Biting Tom
        Tom: I've been hit. My health now = 89.0
        Buddy: Biting Hunter
        Hunter: Wolf Wolf!
        Hunter: Health now = 99.0
     */
}
