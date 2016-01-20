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

        //make an instance of a person for yourself.
        Person you = new Person("you");

        /*make it true that you are happy
        and you know it*/
        //change this value to see how it affects the decision below
        you.areHappyAndYouKnowIt = true;

        System.out.println("Is " + you.name + " happy?: " + you.areHappyAndYouKnowIt);
        /*Make a decision: check check if its true that
        you are happy and you know it*/
        if(you.areHappyAndYouKnowIt){
            //it is true
            //so call a method to clap your hand
            you.clapYourHands();
        }

        //sit down in your seat.
        you.sitDown();


        /*******If-Then-Else Statements*********/
        /*******If-Then-Else Statements*********/
        /*******If-Then-Else Statements*********/
        System.out.println("\n/*******If-Then-Else Statements*********/");

        /*make it true that you are happy
        and you know it*/
        //change this value to see how it affects the decision below
        you.areHappyAndYouKnowIt = false;

        System.out.println("Is " + you.name + " happy?: " + you.areHappyAndYouKnowIt);
        /*Make a decision: check check if its true that
        you are happy and you know it*/
        if(you.areHappyAndYouKnowIt){
            //Block A: it is true

            //so call a method to clap your hand
            you.clapYourHands();
            you.continueStanding();
        } else {
            //Block B: it is false

            /*don't clap, just sit down*/
            you.sitDown();
            //meditate until you are happy
            you.meditate();
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
