package buildingAGame.objects.characters;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public interface Character {
    HashMap<String, Integer> actionExperiences = new HashMap<String, Integer>();

    String getName();
    Random randomGenerator = new Random();

    double getHealth();
    int getExperience();

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
}
