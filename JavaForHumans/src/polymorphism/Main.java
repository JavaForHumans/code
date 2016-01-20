package polymorphism;

import java.util.LinkedList;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public class Main {
    public static void main(String[] args){
        //the Human character representing the current user
        Human currentUser = new Archer("ModernNerd");

        //other Human characters playing the game
        Archer human2 = new Archer("Tom");
        Human human3 = new FireArcher("Christina");
        Warrior human4 = new Warrior("Tyrone");
        Taoist human5 = new Taoist("Jerome");

        //get current user's friend list and store a reference to it in a local variable
        LinkedList<Human> currentUserFriendsList = currentUser.getFriendsList();
        currentUserFriendsList.add(human2);
        currentUserFriendsList.add(human3);
        currentUserFriendsList.add(human4);
        currentUserFriendsList.add(human5);
        System.out.printf("\nCurrent user has %d friends.\n", currentUser.getFriendsList().size());

        Human ourFriendTomTheHuman = currentUserFriendsList.get(0);
        if(!(ourFriendTomTheHuman instanceof Archer)) {
            System.out.println("\nOur friend Tom is an instance of the Archer class");
            Archer ourFriendTomTheArcher = (Archer) ourFriendTomTheHuman;
            ourFriendTomTheArcher.findArrows();
            /*Prints:
                Our friend Tom is an instance of the Archer class
                Tom: Found 6 arrows!
                Tom: I now have 7 arrows.
             */
        } else {
            System.out.println("\nOur friend Tom is NOT an instance of the Archer class");
            //ourFriendTom.findArrows(); doesn't work
            System.out.println("Tom the human cannot go find arrows because he is not an Archer");
            ourFriendTomTheHuman.attack(human3);
            /*Prints:
                Our friend Tom is NOT an instance of the Archer class
                Tom the human cannot go find arrows because he is not an Archer
                Tom: Attacking Christina with my arrows!
                Christina: I've been hit. My health now = 99.0
             */
        }

    }
}
