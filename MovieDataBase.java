/**
 * MovieDataBase.java
 * @author Uriel Garcia
 * @author Andrew Nowinski
 * CIS 22C, Lab 7
 */

import java.awt.*;
import java.io.*;
import java.util.Scanner;

/* NOTE:
* This MovieDataBase.java was not the final product
* There may be some bugs in this .java file
*/


public class MovieDataBase {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(System.in);

        Hash<Movie> t = new Hash<Movie>(26);

        File text = new File("movies.txt");

        Scanner readIn = new Scanner(text);

        String exit = "";

        String title, director;

        int year;

        double gross;

        Movie bond = new Movie("default title", "default director", 0, 0.0);

        while(readIn.hasNextLine()) {

            title = readIn.nextLine();

            director = readIn.nextLine();

            year = readIn.nextInt();

            gross = readIn.nextDouble();

            bond = new Movie(title, director, year, gross);

            t.insert(bond);

            if(readIn.hasNextLine()) {

                readIn.nextLine();

                readIn.nextLine(); // clear

            }
        }









        String choice;

        System.out.println("Welcome to the Bond Movie Database!\n");

        System.out.println("Please select from one of the following options:\n");

        System.out.println("A. Add a movie");
        System.out.println("D. Display all movies");
        System.out.println("R. Remove a movie");
        System.out.println("S. Search for a movie");
        System.out.println("X. Exit");

        System.out.print("\nEnter your choice: ");
        choice = input.nextLine();

        while(!exit.equalsIgnoreCase("X")) {



            if(choice.equalsIgnoreCase("A")) {


                System.out.println("\nAdding a movie!\n");

                System.out.print("Enter the title: ");
                title = input.nextLine();

                System.out.println("");// new line

                System.out.print("Enter the director: ");
                director = input.nextLine();

                System.out.println("");// new line

                System.out.print("Enter the year: ");
                year = input.nextInt();

                System.out.println("");// new line

                System.out.print("Enter the gross in millions: $");
                gross = input.nextDouble();


                bond = new Movie(title, director, year, gross);

                t.insert(bond);

                System.out.println("\n" + title + " was added!");

                input.nextLine(); //clear




            } else if(choice.equalsIgnoreCase("D")) {

                System.out.println("Displaying Movie Database:\n");

                for(int i = 0; i < 26; i++) {

                    t.printBucket(i);

                }

                System.out.println("\nEnd of database!");


            } else if(choice.equalsIgnoreCase("R")) {

                System.out.println("\nRemoving a movie!\n");

                System.out.print("Enter the title: ");

                title = input.nextLine();

                System.out.print("Enter the director: ");

                director = input.nextLine();

                bond = new Movie(title, director, 0, 0.0);

                if(t.search(bond)) {

                    t.remove(bond);

                    System.out.println("\n" + title + " was removed!");

                } else {

                    System.out.println("I cannot find " + title + " in the database");

                }

                input.nextLine(); //clear


            } else if(choice.equalsIgnoreCase("S")) {

                System.out.println("\nSearching for a movie!\n");

                System.out.print("Enter the title: ");

                title = input.nextLine();

                System.out.print("Enter the director: ");

                director = input.nextLine();

                bond = new Movie(title, director, 0, 0);

                if(t.search(bond)) {

                    System.out.println(title + " is in the database!");

                } else {

                    System.out.println(title + " is not in the database.");
                }

            } else {

                System.out.println("That's not the answer that I am looking for");

            }

            System.out.println("");

            System.out.println("Please select from one of the following options:\n");

            System.out.println("A. Add a movie");
            System.out.println("D. Display all movies");
            System.out.println("R. Remove a movie");
            System.out.println("S. Search for a movie");
            System.out.println("X. Exit");

            System.out.print("\nEnter your choice: ");

            choice = input.nextLine();


        }

        System.out.println("\nGoodbye!");

    }
}
