package polymorphism;

import java.util.Random;

/**
 * Created by lwdthe1 on 1/17/2016.
 */
public interface Character {
    String getName();
    Random randomGenerator = new Random();

    double getHealth();
    long getExperience();

    void defend();
    void attack(Character opponent);
    void jump();
    int heal();
    int getAttackPower();
    double decreaseHealth(int opponentAttackPower);
    long gainExperience(long experience);
}
