package encapsulationAndAccessModifiers;

/**
 * Created by lwdthe1 on 1/7/2016.
 */
public class StoreProgram {
    public static void main(String[] args) {
        System.out.println("Starting Store Program");

        System.out.println("Owner creates vending machine.");
        VendingMachine vendingMachine = new VendingMachine(0.75, 0.50);

        System.out.println("Customer starts using machine.");
        double costOfCookie = vendingMachine.getCookieCost();
        double costOfGum = vendingMachine.getGumCost();

        System.out.println("\nWow cookies cost " + costOfCookie);
        System.out.println("and gum cost " + costOfGum);

        System.out.println("\nI'm going to buy a cookie!");
        vendingMachine.buyItem("cookie", 1.00);

        double change = vendingMachine.getChange();
        System.out.println("\nGot my change: " + change);
        int apple = 1;
        int orange = 2;
    }
}
