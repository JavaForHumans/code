package methodsAndDotNotation;

/**
 * Created by lwdthe1 on 1/8/2016.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("#Starting Program");

        Human person = new Human("Lucy");
        CoolDog coolDog = new CoolDog("Buddy", 4, 30);
        person.setPetDog(coolDog);
        person.getPetDog().jump(10, 10);
    }
}
