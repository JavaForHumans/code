package dataStructures.hashmaps;

import java.util.HashMap;

/**
 * Created by lwdthe1 on 1/18/2016.
 */
public class Main {
    public static void main(String[] args) {
        /*********HashMap to hold Object keys and Object values************************/
        HashMap<Object, Object> dictionary = new HashMap<Object, Object>();
        dictionary.put("Variable", "Used to store a single value for later use.");
        dictionary.put("String", "A class for representing character strings.");
        dictionary.put("double", "A primitive datatype for representing floating point numbers.");
        dictionary.put("Double", "A class for wrapping a double in an Object with convenient methods.");
        dictionary.put(0, "The number zero. The first index in arrays and the first position on lists.");
        dictionary.put("zero", 0);
        System.out.println("Elements in map: " + dictionary.size());
        //prints "Elements in map: 6"


        System.out.println("double: " + dictionary.get("double"));
        Object meaningOfDouble = dictionary.get("Double");
        Object meaningOf0 = dictionary.get(0);
        System.out.println("Double: " + meaningOfDouble);
        System.out.println("0: " + meaningOf0);
        System.out.println("zero: " + dictionary.get("zero"));
        /*Prints
            Elements in map: 6
            double: A primitive datatype for representing floating point numbers.
            Double: A class for wrapping a double in an Object with convenient methods.
            0: The number zero. The first index in arrays and the first position on lists.
            zero: 0
         */

        /*********HashMap to hold String keys and String values************************/
        HashMap<String, String> stringDictionary = new HashMap<String, String>();
        stringDictionary.put("Class", "A template for creating objects.");
        stringDictionary.put("Object", "An instance of a class.");

        //dictionary.put("Class", "A template that defines the attributes and behavior that objects constructed from it can exhibit.");
        stringDictionary.replace("Class", "A template that defines the attributes and behavior that objects constructed from it can exhibit.");

        //a better design pattern for updating or adding a pair
        if(stringDictionary.containsKey("Class")) {
            stringDictionary.replace("Class", "A better definition.");
        } else {
            stringDictionary.put("Class", "A descriptive definition");
        }

        String meaningOfClass = stringDictionary.get("Class");
        System.out.println("Class: " + meaningOfClass);

        String valueOfRemovedPair = stringDictionary.remove("Class");
        System.out.println("Value of removed pair with key \"Class\": " + valueOfRemovedPair);

        stringDictionary.clear();
        System.out.println("Pairs in our string dictionary: " + stringDictionary.size());
        //prints "Pairs in our string dictionary: 0"

        stringDictionary.put("Variable", "Used to store a single value for later use.");
        stringDictionary.put("String", "A class for representing character strings.");
        stringDictionary.put("double", "A primitive datatype for representing floating point numbers.");
        stringDictionary.put("Double", "A class for wrapping a double in an Object with convenient methods.");
        stringDictionary.put("zero", "The number zero. The first index in arrays and the first position on lists.");
        System.out.println("Elements in map: " + stringDictionary.size());

        for(String key: stringDictionary.keySet()) {

        }
    }
}
