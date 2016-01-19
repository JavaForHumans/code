package dataStructures.hashmaps;

/**
 * Created by lwdthe1 on 1/19/2016.
 */
public class Concept {
    /*
       if a user's guess at the meaning matches
       at least 65% of the actual meaning of a Concept, the user is correct.
     */
    private static final double CORRECTNESS_THRESHOLD_PERCENTAGE = .65;

    private String title;
    private String meaning;
    //whether or not you guessed its meaning correctly
    private boolean gotCorrect;
    private String[] meaningTokens;


    public Concept(String title, String meaning) {
        this.title = title;
        this.meaning = meaning;
        tokenizeMeaning();
    }

    /**
     * Splits the meaning of this concept into tokens.
     * The tokens are each word in the string.
     */
    private void tokenizeMeaning() {
        String meaningWithoutPunctuations = removeAllPunctuation(meaning);
        this.meaningTokens = meaningWithoutPunctuations.split(" ");
    }

    public void setGotCorrect(boolean gotCorrect) {
        this.gotCorrect = gotCorrect;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean userGotCorrect() {
        return gotCorrect;
    }

    public String getMeaning() {
        return meaning;
    }

    /**
     * This is a naive lexical analyzer
     * that counts how many words match
     * between the user input and the actual meaning of this concept.
     *
     * There are many ways to make this better. Try some.
     * @param userInput the user's guess at what this concept means
     * @return
     */
    public boolean userInputIsCloseToCorrectMeaning(String userInput) {
        int numMatchedTokens = 0;
        double matchPercentage = 0.0;
        userInput = removeAllPunctuation(userInput);
        String[] userInputTokens = userInput.split(" ");

        //total tokens. The greater of the two
        int totalTokens = Math.max(userInputTokens.length, meaningTokens.length);

        if(!userInput.isEmpty()) {
            numMatchedTokens = getNumMatchedTokens(userInputTokens);
        }

        matchPercentage = ((double)numMatchedTokens / totalTokens);//cast to a double to get percentage
        //System.out.println("matchPercentage = " + matchPercentage);
        return matchPercentage >= CORRECTNESS_THRESHOLD_PERCENTAGE;
    }

    private int getNumMatchedTokens(String[] userInputTokens) {
        int numMatchedTokens = 0;

        for (int i = 0; i < meaningTokens.length; i++) {
            String currentMeaningToken = meaningTokens[i];
            for(int j = 0; j < userInputTokens.length; j++) {
                String currentUserInputToken = userInputTokens[j];
                /*
                  check if the current token from the user's input tokens
                  matches the current token from the meaning's tokens
                 */
                if(currentMeaningToken.equalsIgnoreCase(currentUserInputToken)) {
                    numMatchedTokens++;
                    //System.out.print(numMatchedTokens + " ");
                    /*
                        break out of the userTokens loop
                        because we've found a match for the currentMeaningToken
                     */
                    break;
                } else {
                    /*System.out.printf("\n %s does not equal %s",
                    currentMeaningToken, currentUserInputToken);*/
                }
            }
        }
        return numMatchedTokens;
    }

    /**
     * Removes punctuation from a string to make comparisons better.
     * Replaces each punctuation with an empty space.
     * @param string
     * @return
     */
    private String removeAllPunctuation(String string) {
        string = string.replace(".", " ");// remove all periods
        string = string.replace("!", " ");// remove all exclamation points
        string = string.replace("?", " ");// remove all question marks
        string = string.replace(",", " ");// remove all commas
        return string;
    }

    public void markGotCorrect() {
        gotCorrect = true;
    }
}
