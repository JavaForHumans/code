package controlFlowStatements.Decisions;

/**
 * Created by lwdthe1 on 12/19/2015.
 */
public class Player {
    //variable (attribute) to hold this person's name
    public final String name;
    //
    public int positionOnBoard = 0;
    private int cash;

    public Player(String name) {
        this.name = name;
        System.out.println("Created new Player by name of: " + this.name);
        cash = 500;
    }

    public int rollDice() {
        System.out.println(name + " rolling dice");
        return 12;
    }

    public void moveForward(int positions) {
        positionOnBoard += positions;
    }

    public boolean passedGo() {
        return positionOnBoard > 11;
    }

    public void collect200Dollars() {
        cash += 200;
    }
}
