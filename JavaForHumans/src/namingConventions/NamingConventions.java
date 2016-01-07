/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namingConventions;

import java.util.Scanner;

/**
 *
 * @author lwdthe1
 */
public class NamingConventions {
/**
  * @params args
  */
  public static void main(String[] args) {
     //Scanner to get user input from keyboard
     Scanner scanner = new Scanner(System.in);
     String name = "";
     String birthDay = "";
     String gender = "";
     
     System.out.println("Enter your name: ");
     name = scanner.nextLine();

     System.out.println("Enter your birthday: ");
     birthDay = scanner.nextLine();

     System.out.println("Enter your gender: ");
     gender = scanner.nextLine();
     
     Human human = new Human(name, birthDay, gender);
     human.sayHello();
  }
}