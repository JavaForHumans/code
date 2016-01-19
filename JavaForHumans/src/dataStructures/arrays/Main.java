package dataStructures.arrays;

/**
 * Created by lwdthe1 on 1/18/2016.
 */
public class Main {
    public static void main(String[] args) {
        String[] friends = new String[5];
        friends[0] = "Imani";
        friends[1] = "Andres";
        friends[2] = "Keith";
        friends[3] = "Sasha";
        friends[4] = "Tabitha";

        String firstFriend = friends[0];
        System.out.println("First friend is " + firstFriend);
        //prints "First friend is Imani"
        System.out.println("Last friend is " + friends[friends.length - 1]);
        //prints "Last friend is Tabitha"

        friends[0] = null;
        friends[1] = "Alejandro";
        System.out.println("First friend is now " + friends[0]);
        System.out.println("Second friend changed name to " + friends[1]);

        int[] friendsPhoneNumbers = new int[friends.length];
        friendsPhoneNumbers[0] = 555_8000;
        friendsPhoneNumbers[1] = 5554448;
        friendsPhoneNumbers[2] = 555_4311;
        friendsPhoneNumbers[3] = 555_7898;
        friendsPhoneNumbers[4] = 555_6710;

        for(int index = 0; index < friends.length; index++) {
            String friend = friends[index];
            if(friend != null) {
                System.out.printf("Index %d: %s's phone number is %d\n",
                        index, friend, friendsPhoneNumbers[index]);
            } else {
                System.out.println("#Alert: No friend at index " + index);
            }
        }
        /*Prints:
            #Alert: No friend at index 0
            Index 1: Alejandro's phone number is 5554448
            Index 2: Keith's phone number is 5558000
            Index 3: Sasha's phone number is 5557898
            Index 4: Tabitha's phone number is 5556710
         */

        Friend[] phoneBook = new Friend[5];
        phoneBook[0] = new Friend("Imani", 215, 555_8000);
        phoneBook[1] = new Friend("Andres", 484, 5554448);
        phoneBook[2] = new Friend("Keith", 319, 555_4311);
        phoneBook[3] = new Friend("Sasha", 212, 555_7898);
        phoneBook[4] = new Friend("Tabitha", 212, 555_6710);

        for(int index = 0; index < friends.length; index++) {
            Friend friend = phoneBook[index];
            if(friend != null) {
                System.out.println("Friend " + (index + 1) + ": " +friend);
            }
        }
        /*Prints:
            Friend 1: Name: Imani | Phone Number: (215)-5558000
            Friend 2: Name: Andres | Phone Number: (484)-5554448
            Friend 3: Name: Keith | Phone Number: (319)-5554311
            Friend 4: Name: Sasha | Phone Number: (212)-5557898
            Friend 5: Name: Tabitha | Phone Number: (212)-5556710
         */

        Friend friend2 = phoneBook[1];
        friend2.changeName("Alejandro");
        System.out.println(friend2);
        //prints "Name: Alejandro | Phone Number: (484)-5554448"

        phoneBook[0] = null;
        System.out.println(phoneBook[0]);
        //prints "null"
    }
}
