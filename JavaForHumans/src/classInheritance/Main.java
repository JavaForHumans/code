package classInheritance;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Main {
    public static void main(String[] args){
        Human human = new Human("Lisa");
        Human archer = new Archer("Samuel");
        FireArcher fireArcher = new FireArcher("Emmanuel");
        Warrior warrior = new Warrior("Carolyn");

        human.attack(fireArcher);
        archer.attack(warrior);
        warrior.attack(archer);
        fireArcher.attack(human);
    }
}
