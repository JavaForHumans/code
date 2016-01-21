package helloWorld.lessSimpleExample;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Lincoln W Daniel
 */
public class HelloWorld {

    /**
     * This main method is the method that runs the application.
     * It is the first thing called by the system when our application starts.
     * It will run the rest of our code. I will explain more later.
     * For now, just look at it and see if you can make out what's happening here.
     * Run and play around with it. Change some things, break some things. You'll learn how to fix them later :)
     * @param args
     */
    public static void main(String[] args) {
        //create scanner to get user input from the keyboard
        Scanner scanner = new Scanner(System.in);
        
        //say something to the user to tell them what to do
        System.out.println("Welcome. Enter Y to begin or N to stop.");
        //while the user wants to continue, run our application
        while (scanner.hasNext() && (scanner.nextLine().equalsIgnoreCase("y"))) {
            //create variables to hold the input we get from the user
            String firstName = "";//this will hold the user's first name as a String
            String middleName = "";//this will hold the user's middle name
            String lastName = "";
            String favoriteProgrammingLanguage = "";
            String monthStartedProgramming = "";
            int yearStartedProgramming = -1;//this will hold the year the user started programming as a number
        
            //ask for user's first name
            System.out.println("What's  first name");
            //get next line from the scanner. 
            //This will hold what the user typed in.
            //save the user's input as their firstName
            firstName = scanner.nextLine();
            //ask for user's middle name
            System.out.println("What's your middle name");
            //get and save the user's input as their middleName
            middleName = scanner.nextLine();
            System.out.println("What's your last name");
            lastName = scanner.nextLine();
            System.out.println("What's your favorite programming language. (Try Java)");
            favoriteProgrammingLanguage = scanner.nextLine();
            System.out.println("What year did you startInConsole programming?");
            //we hope the user enters a number.
            //if they don't the application will break.
            //So we try to get the 
            try {
                //get the user's next input and try to parse an integer from it.
                yearStartedProgramming = Integer.parseInt(scanner.nextLine());
            } catch (IllegalArgumentException e) {
                //if the user does not enter a number, we will enter this block.
                //the good thing is, our application won't break because we caught the error.
                
                //sadly, we will never know when the user started programming
                
                //proceed :)
            }
            System.out.println("which month did you startInConsole programming?");
            monthStartedProgramming = scanner.nextLine();
            
            //create an instance of a helloWorld.lessSimpleExample.ModernNerd with the firstName, middleName, and lastName the user provided.
            ModernNerd modernNerd = new ModernNerd(firstName, middleName, 
                    lastName);
            //call method to set the user's favorite language to what they entered
            modernNerd.setFavoriteCodingLanguage(favoriteProgrammingLanguage);
            modernNerd.setMonthStartedCoding(monthStartedProgramming);
            modernNerd.setYearStartedCoding(yearStartedProgramming);
            
            //call the helloWorld.lessSimpleExample.ModernNerd object's greet method
            //so it can say hi and tell us about itself
            modernNerd.greet();
            System.out.println("Enter Y to begin or N to stop.");
        }
    }

}

abstract class Human {
    protected String firstName = "";
    protected String middleName = "";
    protected String lastName = "";
    private String name = "";

    public abstract void greet();

    public String getName() {
        if (!middleName.isEmpty()) {
            name = firstName + " " + middleName + " " + lastName;
        } else if (middleName.isEmpty()) {
            name = firstName + " " + lastName;
        }

        return name;
    }
}

interface Programmer {
    public void setFavoriteCodingLanguage(String codingLanguage);
    public void setMonthStartedCoding(String month);
    public void setYearStartedCoding(int year);
    
    public String getFavoriteCodingLanguage();
    public String getMonthStartedCoding();
    public int getYearStartedCoding();
}

class ModernNerd extends Human implements Programmer {

    private String favoriteProgrammingLangauge;
    private String monthStartedProgramming;
    private int yearStartedProgramming;

    //these are some companies I've interviewed with
    List<String> companiesInterviewedWith;

    //these are some of the companies I received offers from     
    String[] companiesReceivedOffersFrom;
    String[] companiesWorkedAt = {"IBM Watson", "Medium"};

    ModernNerd(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

        initiateCompaniesInterviewedWith();
        initiateCompaniesReceivedOffersFrom();
    } 

    private void initiateCompaniesInterviewedWith() {
        companiesInterviewedWith = new ArrayList<>();
        companiesInterviewedWith.add("Comcast");
        companiesInterviewedWith.add("AirBnB.com");
        companiesInterviewedWith.add("Slack.com");
        companiesInterviewedWith.add("Box.com");
        companiesInterviewedWith.add("Pandora.com");
        companiesInterviewedWith.add("LinkedIn");
    }

    public final void initiateCompaniesReceivedOffersFrom() {
        companiesReceivedOffersFrom = new String[3];
        companiesReceivedOffersFrom[0] = "IBM Watson";
        companiesReceivedOffersFrom[1] = "NASA JPL";
        companiesReceivedOffersFrom[2] = "Medium.com";
    }

    @Override
    public void greet() {
        System.out.printf("\n\nHello, World! My name is %s.", getName());
        System.out.printf("\nMy favorite language is %s.",
                getFavoriteCodingLanguage());

        System.out.printf("\n\nI have been Programming since %s %s.",
                getMonthStartedCoding(),
                getYearStartedCoding());

        StringBuffer workedFor = new StringBuffer("\nI have worked for");
        for (int i = 0; i < companiesWorkedAt.length; i++) {
            if (i > 0 && i == companiesWorkedAt.length - 1) {
                workedFor.append(", and ").append(companiesWorkedAt[i]);
            } else if (i > 0 && i != companiesWorkedAt.length - 1) {
                workedFor.append(", ").append(companiesWorkedAt[i]);
            } else {
                workedFor.append(" ").append(companiesWorkedAt[i]);
            }
        }
        System.out.println(workedFor);
        System.out.println("I have interviewed with these companies: ");

        for (String company : companiesInterviewedWith) {
            System.out.println("\t" + company);
        }

        System.out.printf("\nThe first company I worked for was %s.", companiesWorkedAt[0]);
        System.out.println("\n");
    }

    @Override
    public String getFavoriteCodingLanguage() {
        return this.favoriteProgrammingLangauge;
    }

    @Override
    public String getMonthStartedCoding() {
        return monthStartedProgramming;
    }

    @Override
    public int getYearStartedCoding() {
        return this.yearStartedProgramming;
    }

    @Override
    public void setFavoriteCodingLanguage(String codingLanguage) {
        this.favoriteProgrammingLangauge = codingLanguage;
    }

    @Override
    public void setMonthStartedCoding(String month) {
        this.monthStartedProgramming = month;
    }

    @Override
    public void setYearStartedCoding(int year) {
        this.yearStartedProgramming = year;
    }
}
