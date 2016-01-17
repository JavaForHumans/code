package encapsulationAndAccessModifiers;

/**
 * Created by lwdthe1 on 1/7/2016.
 */
public class VendingMachine {
    public static int numVendingMachines = 0;
    private int id;
    private int numPenniesLeft = 100 * 50;//$50 in pennies
    private double cookieCost;
    private double gumCost;
    private double changeFromLastPurchase = 0;


    public VendingMachine (double cookieCost, double gumCost) {
        numVendingMachines++;
        this.id = numVendingMachines;
        this.cookieCost = cookieCost;
        this.gumCost = gumCost;
        System.out.println("Vending Machine #" + id + " created.");
    }

    public double getCookieCost(){ return cookieCost; }
    public double getGumCost(){ return gumCost; }

    public void buyItem(String whichItem, double payment){
        resetLastPurchaseChange();
        if(itemExists(whichItem)) {
            if(paymentIsEnough(whichItem, payment)) {
                dispenseItem(whichItem);
                calculateChange(whichItem, payment);
            } else { alertUser(whichItem, payment); }
        }
    }

    public double getChange() {
        return changeFromLastPurchase;
    }

    private void alertUser(String whichItem, double payment) {
        System.out.printf("Payment of %d is not enough for a %s.", payment, whichItem);
    }

    private boolean itemExists(String whichItem) {
        boolean itemExists = false;

        if(whichItem.equals("cookie")) {
            itemExists = true;
        } else if (whichItem.equals("gum")) {
            itemExists = true;
        }

        return itemExists;
    }

    private boolean paymentIsEnough(String whichItem, double payment) {
        if(whichItem.equals("cookie")) {
            return payment >= cookieCost;
        } else if (whichItem.equals("gum")) {
            return payment >= gumCost;
        } else { return false; }
    }

    private void calculateChange(String whichItem, double payment) {
        double change;
        if(whichItem.equals("cookie")) {
            change = payment - cookieCost;
        } else if (whichItem.equals("gum")) {
            change = payment - gumCost;
        } else { change = 0; }

        if(change >= 0) {
             changeFromLastPurchase = change;
        }
        else { changeFromLastPurchase = 0; }
    }

    private void dispenseItem(String whichItem) {
        System.out.println("\nHere's your " + whichItem);
    }

    private void resetLastPurchaseChange() {
        changeFromLastPurchase = 0;
    }
}
