package controlFlowStatements.Decisions;

/**
 * Created by lwdthe1 on 12/19/2015.
 */
public class DecisionStatements {
    public static void main(String[] args) {
        /*******If-Then Statements*********/
        /*******If-Then Statements*********/
        /*******If-Then Statements*********/
        System.out.println("/*******If-Then Statements*********/");

        Player player1 = new Player("Lucy");
        Player player2 = new Player("Sam");
        //the first player rolls the dice
        int movePositions = player1.rollDice();

        //player moves piece on game's board
        player1.moveForward(movePositions);

        if(player1.passedGo()) {
            player1.collect200Dollars();
        }

        //next player rolls dice
        movePositions = player2.rollDice();
        player2.moveForward(movePositions);

        //...
        //Add more code here to continue the game


        /*******If-Then-Else Statements*********/
        /*******If-Then-Else Statements*********/
        /*******If-Then-Else Statements*********/
        System.out.println("\n/*******If-Then-Else Statements*********/");
        boolean itWillRain = true;
        if (itWillRain) {
            //pack the umbrella for the trip
            System.out.println("Adding our umbrella in the bag.");
        } else {
            //put the umbrella back in the closet
            System.out.println("Putting umbrella back in the closet.");
        }



        /*******Nested if Statements*********/
        /*******Nested if Statements*********/
        /*******Nested if Statements*********/
        System.out.println("\n/*******Nested if Statements*********/");

        /*create variable to hold
        whether or not its raining*/
        boolean itsRaining = true;

        //says whether or not we are outside
        boolean weAreOutside = true;

        /*we will use this to set
        whether or not to use the umbrella*/
        boolean useUmbrella;

        //check if its raining
        if (itsRaining) {
            //check if we are outside
            if (weAreOutside) {
                //use the umbrella if its true that we are outside
                useUmbrella = true;
                System.out.println("It is raining and we ARE OUTSIDE. " +
                        "Should we use our umbrella?: " + useUmbrella);
            } else {
                /*otherwise, don't use the umbrella
                because we are not outside*/
                useUmbrella = false;
                System.out.println("It is raining and we ARE NOT OUTSIDE. " +
                        "Should we use our umbrella?: " + useUmbrella);
            }
        } else {
            /*otherwise, don't use the umbrella
            because we it is not raining*/
            useUmbrella = false;
            System.out.println("It is NOT RAINING. " +
                    "Should we use our umbrella?: " + useUmbrella);
        }


        /*******If-Then-Else If-Then-Else Statements*********/
        /*******If-Then-Else If-Then-Else Statements*********/
        /*******If-Then-Else If-Then-Else Statements*********/
        System.out.println("\n/*******If-Then-Else If-Then-Else Statements*********/");

        boolean haveHomework = false;
        boolean haveChores = false;
        boolean friendsAreFreeToPlay = false;
        boolean haveTV = true;
        String activityToDo = "nothing yet";

        if(haveHomework) {
            activityToDo = "do homework";
        } else if (haveChores) {
            activityToDo = "wash dishes.";
        } else if(friendsAreFreeToPlay) {
            activityToDo = "go play with friends.";
        } else {
            if(haveTV) {
                activityToDo = "watch favorite TV show.";
            } else {
                activityToDo = "do nothing";
            }
        }
        System.out.println("Activity to do: " + activityToDo);
    }
}
