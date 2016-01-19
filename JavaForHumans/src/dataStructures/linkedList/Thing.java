package dataStructures.linkedList;

/**
 * Created by lwdthe1 on 1/18/2016.
 */
public class Thing {
    private final String name;
    private boolean done;

    public Thing(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void markDone(){
        done = true;
    }

    @Override
    public String toString() {
        if(done) { return name + " is done.";}
        else { return name + " is not done";}
    }

    public boolean isDone() {
        return done;
    }
}
