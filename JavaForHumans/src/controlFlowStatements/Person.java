package controlFlowStatements;

/**
 * Created by lwdthe1 on 12/19/2015.
 */
public class Person {
    //variable (attribute) to hold this person's name
    public final String name;
    //variable to hold whether or not this person is happy
    public boolean areHappyAndYouKnowIt;

    public Person(String name) {
        this.name = name;
        System.out.println("Created new Person by name of: " + this.name);
    }

    public void clapYourHands() {
        System.out.println(name + " is clapping hands.");
    }

    public void sitDown() {
        System.out.println(name + " sat down");
    }

    public void meditate() {
        System.out.println(name + " is meditating");
    }

    public void continueStanding() {
        System.out.println(name + " is continuing to stand");
    }
}
