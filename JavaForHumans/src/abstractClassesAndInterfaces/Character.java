package abstractClassesAndInterfaces;

import java.util.Random;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public interface Character {
    Random randomGenerator = new Random();

    String getName();
    double getHealth();
    long getExperience();
    int getAttackPower();
    void setAttackPower(int attackPower);

    void defend();
    void jump();
    int heal();
    void attack(Character opponent);
    double decreaseHealth(int opponentAttackPower);
    long gainExperience(long experience);
}

