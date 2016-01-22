package buildingAGame.objects.characters;

import buildingAGame.objects.characters.human.*;
import buildingAGame.objects.characters.pet.Dog;
import buildingAGame.objects.characters.pet.Lion;
import buildingAGame.objects.characters.pet.Pet;
import buildingAGame.utils.Utils;
import javafx.scene.control.TextArea;

import java.lang.*;
import java.util.HashMap;

/**
 * Created by Lincoln W Daniel, the ModernNerd, on 1/17/2016
 * as an application of the concepts expressed in the "Java for Humans" book.
 *
 * This class implements much of the Character interface
 * and must be extended by any class that intends to create a template for a Character object.
 * All implementing classes must include the below public methods and have access to the final static fields
 *
 */
public abstract class AbstractCharacter  implements Character {
    private final String DEAD_INACTION_MESSAGE = "Dead. Cannot perform that action.";
    public static final String ACTION_LANDED_ATTACK = "action_landed_attack";
    public static final String ACTION_BLOCKED_ATTACK = "action_blocked_attack";
    public static final String ACTION_TRAINED = "action_blocked_attack";
    protected static final String ACTION_ARCHER_FOUND_ARROWS = "action_archer_found_arrows";

    protected final static double MAX_HEALTH = 100.0;
    private final static int MAX_EXPERIENCE = 100000;
    private final static int BASE_ATTACK_POWER = 100;
    private final static int BASE_DEFENSE_POWER = 200;
    private final static HashMap<Class, Integer> maxTrainingCapability;

    /*
    this is a static block.
    It is executed the first time this class is accessed
    Everything in it will happen without an instance of the class
    */
    static {
        //add all possible ways for a character to gain experience
        //use a little math to make everything dynamic.
        //This way, if we change MAX_EXPERIENCE, everything will adjust accordingly. That's dynamic
        actionExperiences.put(ACTION_LANDED_ATTACK, MAX_EXPERIENCE / 285);
        actionExperiences.put(ACTION_BLOCKED_ATTACK, MAX_EXPERIENCE / 400);
        actionExperiences.put(ACTION_TRAINED, MAX_EXPERIENCE / 670);
        actionExperiences.put(ACTION_ARCHER_FOUND_ARROWS, MAX_EXPERIENCE / 1000);
    }

    static {
        maxTrainingCapability = new HashMap<Class, Integer>();
        //add Human training capabilities
        maxTrainingCapability.put(Archer.class, BASE_ATTACK_POWER / 30);
        maxTrainingCapability.put(FireArcher.class, BASE_ATTACK_POWER / 25);
        maxTrainingCapability.put(Taoist.class, BASE_ATTACK_POWER / 7);
        maxTrainingCapability.put(Warrior.class, BASE_ATTACK_POWER / 15);

        //add Pet training capabilities
        maxTrainingCapability.put(Dog.class, BASE_ATTACK_POWER / 8);
        maxTrainingCapability.put(Lion.class, BASE_ATTACK_POWER / 10);
    }

    protected String name;
    protected double health = MAX_HEALTH;
    protected int experience = 1;
    protected int experienceLevel = 1;
    protected int attackPower = BASE_ATTACK_POWER;
    protected int defensePower = BASE_DEFENSE_POWER;
    private TextArea updatesArea;

    /**
     * Allows this character to heal itself if it is alive.
     * The maximum amount of health is 100.0 or (100 percent)
     *
     * @param additionalHealth how much health to add to the character's current health.
     * @return
     */
    @Override
    public double healSelf(double additionalHealth) {
        //a character can only heal if it is alive
        if(isAlive()) {
            health += additionalHealth;
            //make sure the health of this character does not go above 100.0.
            //If the health is more than 100.0, it will be changed to 100.0
            health = Math.min(health, MAX_HEALTH);
        }
        return health;
    }

    public String getName() {
        return name;
    }

    public double getHealth(){
        return health;
    }

    public int getExperience(){
        return experience;
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public int getDefensePower() {
        return defensePower;
    }

    @Override
    public String getTypeDisplay() {
        return getClass().getSimpleName();
    }

    /**
     * Checks whether or not a character is strong enough
     * to block an attack from another character
     * @param opponentAttackPower
     * @return true if the character's defense power is greater than the opponent's attack power
     */
    @Override
    public boolean blockAttack(int opponentAttackPower) {
        return getDefensePower() > opponentAttackPower;
    }

    /**
     * When a character gets attacked,
     * it can try to block the attack,
     * but if it doesn't, damagae is done to the character's health.
     * @param opponentAttackPower
     * @param opponentExperienceLevel the experience experienceLevel of the opponent
     * @return True if the attack was successful and damage was done to this character's health.
     */
    @Override
    public boolean doDamageToHealth(int opponentAttackPower, int opponentExperienceLevel) {
        if(isAlive()) {
            if(blockAttack(opponentAttackPower)) {//try to block the attack
                speak("Blocked attack. Health = " + health, true);
                gainExperience(ACTION_BLOCKED_ATTACK);
                updatesArea.setStyle("-fx-background-color: #2ecc71");
                return false;
            } else {
                double damageToHealth = calculateHealthToLose(opponentAttackPower, opponentExperienceLevel);
                health -= damageToHealth;

                speak("I've been hit. My health is now = " + health, true);
                updatesArea.setStyle("-fx-background-color: #9b59b6");

                //check if this character died from being attacked
                if(!isAlive()) {
                    //indicate that this character has died
                    updatesArea.setStyle("-fx-background-color: #e74c3c");
                }
                return true;
            }
        } else {
            speak("Dead. Cannot be attacked.", true);
            return false;
        }
    }

    /**
     * Calculates how much damage an opponent does to this character's health.
     * Feel free to change the math to change how damage is applied.
     * @param opponentAttackPower the attack power of the opponent
     * @param opponentExperienceLevel the experience experienceLevel of the opponent
     * @return
     */
    private double calculateHealthToLose(int opponentAttackPower, int opponentExperienceLevel) {
        double healthToLose;
        healthToLose = opponentAttackPower - defensePower;

        //if this character has a higher experience experienceLevel than the opponent
        //decrease the amount of damage the opponent does to this character
        if(experienceLevel > opponentExperienceLevel){healthToLose -= experienceLevel * 2.5;}

        /*ensure that zero is the minimum health a character can lose.
        No negative numbers. If healthToLose is less than 0, healthToLose will be set to 0.
         */
        healthToLose = Math.max(healthToLose, 0.0);
        return healthToLose;
    }

    /**
     * Increases the character's experience depending on the action it completed
     * @param action the completed action to gain experience from
     * @return the updated value of the character's experience
     */
    public int gainExperience(String action){
        int experienceToAdd = actionExperiences.get(action);
        this.experience = Math.min(experience + experienceToAdd, MAX_EXPERIENCE);

        updateExperienceLevel();
        return experience;
    }


    /**
     * Updates the experience experienceLevel of the character
     * based on the amount of experience its accumulated.
     * The highest level is 9
     * @return the updated value of the character's experience experienceLevel
     */
    private int updateExperienceLevel() {
        //hold the user's current experienceLevel to compare at the end
        int currentLevel = experienceLevel;
        //you can write an if statement on a single line like thi to save space
        if(experience <= (MAX_EXPERIENCE / 1024)) { experienceLevel = 2; }
        else if (experience <= (MAX_EXPERIENCE / 512)) { experienceLevel = 3; }
        else if (experience <= (MAX_EXPERIENCE / 256)) { experienceLevel = 4; }
        else if (experience <= (MAX_EXPERIENCE / 128)) { experienceLevel = 5; }
        else if (experience <= (MAX_EXPERIENCE / 64)) { experienceLevel = 3; }
        else if (experience <= (MAX_EXPERIENCE / 32)) { experienceLevel = 4; }
        else if (experience <= MAX_EXPERIENCE / 16) { experienceLevel = 5; }
        else if (experience <= (MAX_EXPERIENCE / 8)) { experienceLevel = 6; }
        else if (experience <= (MAX_EXPERIENCE / 4)) { experienceLevel = 7; }
        else if (experience <= (MAX_EXPERIENCE / 2)) { experienceLevel = 8; }
        else if (experience <= MAX_EXPERIENCE) { experienceLevel = 9; }

        //check if the user's experienceLevel changed
        if(experienceLevel > currentLevel) {
            //add more attack power and train maximally
            attackPower += experienceLevel * 10 + getMaxTrainingCapability();
            //add more defensive power
            defensePower += experienceLevel * 10;

            if(this instanceof Warrior) {
                //give warrior an extra boost in defense for its shield
                int randomDividend = Utils.getSharedRandomGen().nextInt(6) + 5;
                defensePower *= 1.08 + (1/randomDividend);
            } else if (this instanceof Archer) {
                //archer gets more attack for arrows
                int randomDividend = Utils.getSharedRandomGen().nextInt(6) + 15;
                attackPower *= 1 + (1/randomDividend);
            } else if (this instanceof Taoist) {
                // taoist gets most attack boost
                int randomDividend = Utils.getSharedRandomGen().nextInt(6) + 10;
                attackPower *= 1 + (1/randomDividend);

                //archer also gets a little more defense
                defensePower *= 1.05 + (1/randomDividend);
            }
            speak("Upgraded."
                    + "\n\t\tExperience Level = " + experienceLevel
                    + "\n\t\tNew attack power = " + getAttackPower()
                    + "\n\t\tNew defence power = " + getDefensePower(), true);
            afterLevelUpgrade();

            if(updatesArea != null) {
                updatesArea.setStyle("-fx-background-color: #3498db");
            }
        }

        return experienceLevel;
    }

    protected abstract void afterLevelUpgrade();

    /**
     * Should be used to make sure this character does not perform adverse methods on itself.
     * @param otherCharacter the other character to compare this character to
     * @return returns true if this character is a reference of the other character
     */
    protected boolean isSelf(Character otherCharacter) {
        return this == otherCharacter;
    }

    /**
     * A character must be alive to interact with other characters.
     * This checks if the user has enough health to be alive.
     * @return true if a user has a health value above 0.0
     */
    @Override
    public boolean isAlive() {
        boolean isAlive = health > 0.0;
        return isAlive;
    }

    @Override
    public void setUpdatesArea(TextArea attackerTextArea) {
        this.updatesArea = attackerTextArea;
        attackerTextArea.setText(toString() + "\n");
    }

    @Override
    public void clearUpdatesArea() {
        if(updatesArea != null) {
            updatesArea.setText("");
            updatesArea = null;
        }
    }

    @Override
    public int getExperienceLevel() {
        return experienceLevel;
    }

    @Override
    public int getMaxTrainingCapability() {
        return maxTrainingCapability.get(this.getClass());
    }

    /**
     * Compares this character to another character.
     * It returns 0 if this character is greater than the other character and 1 otherwise.
     *
     * In some cases, a character with more experience may not mean it is stronger than others,so
     * which is greater is determined by the following criteria:
     * #1. which is alive
     * #2. which has more attack
     * #3. if tie in #3, which has more defensive power
     * #4. if tie in #4, which has more experience
     * @param otherCharacter
     * @return
     */
    public int compare(Character otherCharacter){
        if (isAlive() && !otherCharacter.isAlive()
                //if the first expression was not true, this one is evaluated
                || hasMoreAttack(otherCharacter)
                //if the second expression was not true, this one is evaluated
                || hasEqualAttackMoreDefense(otherCharacter)
                //if the third expression was not true, this one is evaluated
                || hasEqualAttackAndDefenceMoreExp(otherCharacter)
                ) {
            return 0;
        } else return 1;
    }

    private boolean hasMoreAttack(Character otherCharacter) {
        return getAttackPower() > otherCharacter.getAttackPower();
    }

    private boolean hasEqualAttackMoreDefense(Character otherCharacter) {
        return getExperience() == otherCharacter.getExperience()
                && getAttackPower() > otherCharacter.getAttackPower();
    }

    private boolean hasEqualAttackAndDefenceMoreExp(Character otherCharacter) {
        return getAttackPower() == otherCharacter.getAttackPower()
                &&  getDefensePower() == otherCharacter.getDefensePower()
        && getExperience() > otherCharacter.getExperience();
    }

    /**
     * Simulates a character speaking. It prints the message to the console.
     *
     * If this character is an instance of the Human class, add the human prefix.
     * If this character is an instance of the Pet class, add the pet prefix.
     * Otherwise, add the unknown type prefix
     * @param message the message to print
     * @param appendToUpdatesArea
     */
    @Override
    public String speak(String message, boolean appendToUpdatesArea) {
        String returnMessage = "";
        if(isAlive()) {
            if (this instanceof Human) {
                returnMessage = Utils.HUMAN_MESSAGE_PREFIX + name + ": " + message;
            } else if (this instanceof Pet) {
                returnMessage = Utils.PET_MESSAGE_PREFIX + name + ": " + message;
            } else {
                returnMessage = Utils.UNKNOWN_TYPE_MESSAGE_PREFIX + name + ": " + message;
            }
            if(appendToUpdatesArea && updatesArea != null) {
                updatesArea.appendText(returnMessage);
            } else {
                System.out.print(returnMessage + "\n");
            }
        } else {
            returnMessage = DEAD_INACTION_MESSAGE;
        }
        return returnMessage;
    }

    @Override
    public abstract String toString();
}
