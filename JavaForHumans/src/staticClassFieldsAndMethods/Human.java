package staticClassFieldsAndMethods;

/**
 * Created by lwdthe1 on 1/6/2016.
 */
public class Human {
    //static fields
    private static int numHumans = 0;
    private static int numMales = 0;
    private static int numFemales = 0;

    private String name;
    private String sex;

    public Human(String name, String sex){
        this.name = name;
        this.sex = sex;

        incrementNumberOfHumansAndPrint();
        if(sex.equalsIgnoreCase("male")){
            System.out.println("> There are now "
                    + incrementNumMales() + " males in Society.");
        } else if(sex.equalsIgnoreCase("female")){
            System.out.println("> There are now "
                    + incrementNumFemales() + " females in Society.");
        } else {
            System.out.println("This human instance has an invalid sex.");
        }
        System.out.println("\n");
    }

    private static int incrementNumFemales() {
        return ++numFemales;
    }

    private static int incrementNumMales() {
        return ++numMales;
    }

    private static int incrementNumberOfHumans(){
        return numHumans += 1;
    }

    private static void incrementNumberOfHumansAndPrint(){
        System.out.println("There are now "
                + incrementNumberOfHumans() + " humans in Society.");
    }

    public static int getNumHumans(){
        return numHumans;
    }

    public static int getNumMales(){
        return numMales;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }
}
