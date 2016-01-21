package buildingAGame.objects.characters;

import buildingAGame.objects.characters.human.*;
import buildingAGame.objects.characters.pet.Pet;
import buildingAGame.utils.Utils;

import java.lang.*;

/**
 * Created by lwdthe1 on 1/20/2016.
 */
public abstract class AbstractCharacter  implements Character {
    private final String DEAD_INACTION_MESSAGE = "Dead. Cannot perform that action.";
    public static final String ACTION_LANDED_ATTACK = "action_landed_attack";
    public static final String ACTION_BLOCKED_ATTACK = "action_blocked_attack";
    public static final String ACTION_TRAINED = "action_blocked_attack";
    protected static final String ACTION_ARCHER_FOUND_ARROWS = "action_archer_found_arrows";

    private final double MAX_HEALTH = 100.0;
    private final int MAX_EXPERIENCE = 100000;

    /*
            this is a static block.
            It is executed the first time this class is accessed
            Everything in it will happen without an instance of the class
            */
    {
        Character.actionExperiences.put(ACTION_LANDED_ATTACK, 350);
        Character.actionExperiences.put(ACTION_BLOCKED_ATTACK, 250);
        Character.actionExperiences.put(ACTION_TRAINED, 150);
        Character.actionExperiences.put(ACTION_ARCHER_FOUND_ARROWS, 25);
    }

    protected String name;
    protected double health = MAX_HEALTH;
    protected int experience = 1;
    protected int experienceLevel = 1;
    protected int attackPower = 85;
    protected int defensePower = 300;
    protected String typeDisplay;

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
            health = Math.min(health, 100.0);
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
                Utils.printSystemMessage(speak("Blocked attack. Health = " + health));
                gainExperience(ACTION_BLOCKED_ATTACK);
                return false;
            } else {
                double damageToHealth = calculateHealthToLose(opponentAttackPower, opponentExperienceLevel);
                health -= damageToHealth;
                speak("I've been hit. My health is now = " + health);
                return true;
            }
        } else {
            Utils.printSystemMessage(speak("Dead. Cannot be attacked."));
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
            attackPower += experienceLevel * 10;
            defensePower += experienceLevel * 10;

            if(this instanceof Warrior) {
                defensePower += 20;//warrior gets most defense boost
            } else if (this instanceof Archer) {
                attackPower += 10; //archer gets more attack for arrows
            } else if (this instanceof Taoist) {
                attackPower += 20; // taoist gets most attack boost
            }
            speak("Upgraded. I'm now experienceLevel " + experienceLevel
            + "\n\tNew attack power = " + getAttackPower()
                    + "\n\tNew defence power = " + getDefensePower());
        }

        return experienceLevel;
    }

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
    public int getExperienceLevel() {
        return experienceLevel;
    }

    /**
     * Simulates a character speaking. It prints the message to the console.
     *
     * If this character is an instance of the Human class, add the human prefix.
     * If this character is an instance of the Pet class, add the pet prefix.
     * Otherwise, add the unknown type prefix
     * @param message the message to print
     */
    protected String speak(String message) {
        String returnMessage = "";
        if(isAlive()) {
            if (this instanceof Human) {
                returnMessage = Utils.HUMAN_MESSAGE_PREFIX + name + ": " + message;
            } else if (this instanceof Pet) {
                returnMessage = Utils.PET_MESSAGE_PREFIX + name + ": " + message;
            } else {
                returnMessage = Utils.UNKNOWN_TYPE_MESSAGE_PREFIX + name + ": " + message;
            }
            System.out.print(returnMessage + "\n");
        } else {
            returnMessage = DEAD_INACTION_MESSAGE;
        }
        return returnMessage;
    }
}
