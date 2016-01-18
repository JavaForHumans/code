package stringManipulation;

/**
 * Created by lwdthe1 on 1/16/2016.
 */
public class StringManipulator {
    public static void main(String[] args){
        String usernameInDatabase = "modernNerd";
        String passwordInDatabase = "mN123Java";

        String providedUsername = "modernnerd";
        String providedPassword = "mN123Java";

        if(providedUsername.equalsIgnoreCase(usernameInDatabase)){
            //successful username comparison
            //check the provided password

            //this will fail
            //if("mn123java".equals(passwordInDatabase)){}

            //this will pass
            if(providedPassword.equals(passwordInDatabase)){
                System.out.println("Success! You are logged in.");
            } else {
                System.out.println("Wrong password. Try again.");
            }
        } else {
            //user provided wrong username
            System.out.println("Wrong username. Try again.");
        }

        String message = "Hello, world! My name is Lincoln";
        int messageLength = message.length();

        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int indexOfLetterD = alphabet.indexOf("D");
        String halfOfAlphabet = alphabet.substring(alphabet.length() / 2);

        String fullAddress = "123 JavaStreet JavaCity, JavaState 19081 United States";
        String citySubstring = fullAddress.substring(0, fullAddress.indexOf(","));

        System.out.println(citySubstring);
        System.out.println(alphabet.indexOf("XYZ"));

        String name = "Lincoln Daniel Daniel";
        String lowercaseName = name.toLowerCase();
        String uppercaseName = name.toUpperCase();
        System.out.println("Lowercase name: " + lowercaseName);

        System.out.println("Uppercase name: " + uppercaseName);

        String newName = name.replace("Lincoln", "Modern");
        System.out.println(newName);

        String essay = "The ModernNerd coded everyday to build something great.";
        String[] allWordsInEssay = essay.split(" ");
        int numWordsInEssay = allWordsInEssay.length;
        System.out.println("Word Count: " + numWordsInEssay);
    }
}
