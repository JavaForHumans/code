package methodsAndDotNotation;

/**
 * Created by lwdthe1 on 1/8/2016.
 */
public class Human {
    private String name;
    private CoolDog petDog;

    public Human(String name){
        this.name = name;
    }

    public void setPetDog(CoolDog petDog){
        this.petDog = petDog;
        petDog.setOwner(this);
    }

    public String getName(){ return name; }

    public void greet(){
        System.out.println("Hi, my name is " + name + ".");
        if(petDog != null ) {
            System.out.println("This is my pet dog. His name is " + petDog.getName()
                    + ". He is " + petDog.getHeight() + " inches tall and "
                    + petDog.getAge() + " years old.");
        }

    }

    public CoolDog getPetDog() {
        return petDog;
    }
}
