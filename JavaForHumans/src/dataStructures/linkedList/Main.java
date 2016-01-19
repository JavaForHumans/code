package dataStructures.linkedList;

import java.util.LinkedList;

/**
 * Created by lwdthe1 on 1/18/2016.
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<Thing> bucketList = new LinkedList<>();
        bucketList.add(new Thing("Bungee Jump"));
        Thing learnJava = new Thing("Learn Java");
        bucketList.add(learnJava);
        bucketList.add(new Thing("Build an Android Application"));
        bucketList.add(new Thing("Travel to the Bermuda Triangle"));

        System.out.println(bucketList.contains(learnJava));
        //prints "true"

        System.out.println(bucketList.contains(new Thing("Learn Java")));
        //prints "false"

        Thing bungeeJump = bucketList.get(0);
        bungeeJump.markDone();

        for(int position = 0; position < bucketList.size(); position++){
            Thing thingToDo = bucketList.get(position);
            System.out.println("Thing at position " + position + ": " +thingToDo);
        }
        /*Prints:
            Thing at position 0: Bungee Jump is done.
            Thing at position 1: Learn Java is not done
            Thing at position 2: Build an Android Application is not done
            Thing at position 3: Travel to the Bermuda Triangle is not done
         */

        bucketList.getLast().markDone();
        for(int position = 0; position < bucketList.size(); position++){
            Thing thingToDo = bucketList.get(position);
            if(thingToDo.isDone()) {
                bucketList.remove(thingToDo);
            }
        }
        System.out.println("Number of things left to do: " + bucketList.size());
        //prints "Number of things left to do: 2"

        bucketList.clear();
        System.out.println("Number of things left to do: " + bucketList.size());
        //prints "Number of things left to do: 0"
    }
}
