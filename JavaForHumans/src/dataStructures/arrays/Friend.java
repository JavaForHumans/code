package dataStructures.arrays;

/**
 * Created by lwdthe1 on 1/18/2016.
 */
public class Friend {
    private String name;
    private int phoneNumber;
    private int areaCode;

    Friend(String name, int areaCode, int phoneNumber) {
        this.name = name;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
    }

    public void changePhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void changeAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Phone Number: (%d)-%d",
                name, areaCode, phoneNumber);
    }
}
