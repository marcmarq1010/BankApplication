package ui;

import java.util.Scanner;

public class InputHelper 
{
    public static final String INVALID = "Invalid input.\n";
	
    private Scanner scanner;

    // Constructor to create a new instance of the Scanner object
    public InputHelper() 
    {
        scanner = new Scanner(System.in);
    }

    // Method to read an integer input from the user
    public int getInt(String prompt) 
    {
        // Print the prompt to the console
        System.out.print(prompt);

        // Keep asking the user for input until a valid integer is entered
        while (!scanner.hasNextInt()) 
        {
            System.out.print(INVALID + prompt);
            scanner.next(); // discard the invalid input
        }
        
        int value = scanner.nextInt(); // read the integer input from the user
        scanner.nextLine(); // consume the newline character left by nextInt()
        return value; // return the integer input
    }

    // Method to read a double input from the user
    public double getDouble(String prompt) 
    {
        // Print the prompt to the console
        System.out.print(prompt);

        // Keep asking the user for input until a valid double is entered
        while (!scanner.hasNextDouble()) 
        {
            System.out.print(INVALID + prompt);
            scanner.next(); // discard the invalid input
        }
        
        double value = scanner.nextDouble(); // read the double input from the user
        scanner.nextLine(); // consume the newline character left by nextDouble()
        return value; // return the double input
    }

    // Method to read a string input from the user
    public String getString(String prompt) 
    {
        // Print the prompt to the console
        System.out.print(prompt);

        // Read the string input from the user
        String value = scanner.nextLine();
        return value; // return the string input
    }

    // Add methods for other variable types as needed
}
