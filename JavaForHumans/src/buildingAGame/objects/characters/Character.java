package buildingAGame.objects.characters;

import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Lincoln W Daniel, the ModernNerd, on 1/17/2016
 * as an application of the concepts expressed in the "Java for Humans" book.
 *
 * This file creates an interface for any character in the game.
 * All implementing classes must include the below public methods and have access to the final static fields
 *
 */
public interface Character {
    HashMap<String, Integer> actionExperiences = new HashMap<String, Integer>();
    Random randomGenerator = new Random();

    String getName();
    double getHealth();
    int getExperience();

    String speak(String message, boolean appendToUpdatesArea);
    double healSelf(double additionalHealth);
    void attack(Character opponent);
    int getAttackPower();
    int getDefensePower();
    boolean blockAttack(int opponentAttackPower);
    boolean doDamageToHealth(int opponentAttackPower, int opponentExperienceLevel);
    int gainExperience(String action);
    int getExperienceLevel();
    boolean isAlive();
    void train();
    int getMaxTrainingCapability();

    void setUpdatesArea(TextArea attackerTextArea);

    void clearUpdatesArea();

    int compare(Character otherCharacter);

    String getTypeDisplay();
}
