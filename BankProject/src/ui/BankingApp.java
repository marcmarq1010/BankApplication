package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Transaction.Transaction;
import account.Account;
import account.CheckingAccount;
import account.SavingAccount;
import bank.Bank;
import customer.Customer;
import exceptions.AccountClosedException;
import exceptions.InsufficientBalanceException;
import exceptions.NoSuchAccountException;

public class BankingApp 
{
    private Scanner scanner; // Scanner object to read user input
    Bank bank = new Bank();
    InputHelper IU = new InputHelper();
    
    List<Account> accounts;
    List<Transaction> transactions;

    public BankingApp() 
    {
        scanner = new Scanner(System.in); // Initialize the Scanner object to read user input
    }
    
    public void start()
    {
        int choice;
        do 
        {
            displayMenu(); // Display the main menu
            choice = scanner.nextInt(); // Read user input for their choice
            scanner.nextLine(); // Consume the newline character left by scanner.nextInt()

            switch (choice) 
            {
                case 1:
                    openCheckingAccount(); // Call method to create a new checking account
                    break;
                case 2:
                    openSavingAccount(); // Call method to create a new saving account
                    break;
                case 3:
                    listAccounts(); // Call method to list all the created accounts
                    break;
                case 4:
                    accountStatement(); // Call method to print statement for a specific account
                    break;
                case 5:
                    depositFunds(); // Call method to deposit funds into a specific account
                    break;
                case 6:
                    withdrawFunds(); // Call method to withdraw funds from a specific account
                    break;
                case 7:
                    closeAccount(); // Call method to close a specific account
                    break;
                case 8:
                    // Display message that the program is exiting
                    break;
                default:
                   System.out.println(Messages.MENU_INVALID_CHOICE); // Display message for an invalid choice
            }
        } 
        while (choice != 8); // Continue the loop until user chooses to exit
    }
    
    private void displayMenu() 
    {
        System.out.println("");
    	System.out.println(Messages.MENU_TITLE); // Display the menu title
        System.out.println(Messages.MENU_OPTION_1); // Display option to create a new checking account
        System.out.println(Messages.MENU_OPTION_2); // Display option to create a new saving account
        System.out.println(Messages.MENU_OPTION_3); // Display option to list all the created accounts
        System.out.println(Messages.MENU_OPTION_4); // Display option to print statement for a specific account
        System.out.println(Messages.MENU_OPTION_5); // Display option to deposit funds into a specific account
        System.out.println(Messages.MENU_OPTION_6); // Display option to withdraw funds from a specific account
        System.out.println(Messages.MENU_OPTION_7); // Display option to close a specific account
        System.out.println(Messages.MENU_OPTION_8); // Display option to exit the program
        System.out.println("");
        System.out.println(Messages.ENTER_MENU_CHOICE); // Prompt user to enter their choice
    }
	
 // Method to open a checking account
    private void openCheckingAccount()
    {
        // Create a new Customer object with first name, last name and SSN
        Customer c = new Customer(IU.getString(Messages.ENTER_FIRST_NAME), IU.getString(Messages.ENTER_LAST_NAME), IU.getString(Messages.ENTER_SSN));
        
        // Open a checking account for the customer with specified overdraft limit and initial balance
        CheckingAccount ca = bank.openCheckingAccount(c, IU.getDouble(Messages.ENTER_OVERDRAFT_LIMIT), IU.getDouble(Messages.ENTER_INITAL_BALANCE));
        
        // Print the message indicating the account number of the newly created checking account
        System.out.println(Messages.ACCOUNT_CREATED + ca.getAccountNumber());
    }

    // Method to open a savings account
    private void openSavingAccount()
    {
        // Create a new Customer object with first name, last name and SSN
        Customer c = new Customer(IU.getString(Messages.ENTER_FIRST_NAME), IU.getString(Messages.ENTER_LAST_NAME), IU.getString(Messages.ENTER_SSN));
        
        // Open a savings account for the customer with specified initial balance
        SavingAccount sa = bank.openSavingsAccount(c, IU.getDouble(Messages.ENTER_INITAL_BALANCE));
        
        // Print the message indicating the account number of the newly created savings account
        System.out.println(Messages.ACCOUNT_CREATED + sa.getAccountNumber());
    }

    // Method to list all accounts
    private void listAccounts() 
    {
        // Get all accounts from the bank
        accounts = bank.getAllAccounts();
        
        // Loop through each account and print its information
        for (Account account : accounts) 
        {
            System.out.println(account);
        }
    }

    // Method to generate an account statement
    private void accountStatement()
    {
        // Find the account by its account number
        try 
        {
            Account ac = bank.findAccount(IU.getInt(Messages.ENTER_ACCOUNT_NUMBER));
            // Get all transactions for the account
            transactions = ac.getAllTransactions();

            // Loop through each transaction and print its information
            for (Transaction transaction : transactions) 
            {
                System.out.println(transaction);
            }
        } 
        catch (NoSuchAccountException e) 
        {
            System.out.println("Account not found: " + e.getMessage());
        }

    }

    // Method to deposit funds into an account
    private void depositFunds()
    {
        // Find the account by its account number
        Account ac = bank.findAccount(IU.getInt(Messages.ENTER_ACCOUNT_NUMBER));
        
        // Check if the account exists
        if(ac == null)
        {
            System.out.println(Messages.ACCOUNT_NOT_FOUND);
        }
        
        // Deposit the specified amount into the account
        ac.deposit(IU.getDouble(Messages.ENTER_DEPOSIT_AMOUNT), ac);
    }

    // Method to withdraw funds from an account
    private void withdrawFunds()
    {
        // Find the account by its account number
        Account ac = bank.findAccount(IU.getInt(Messages.ENTER_ACCOUNT_NUMBER));
        
        // Check if the account exists
        if(ac == null)
        {
            System.out.println(Messages.ACCOUNT_NOT_FOUND);
        }
        
        // Withdraw the specified amount from the account
        ac.withdraw(IU.getDouble(Messages.ENTER_DEPOSIT_AMOUNT), ac);
    }

    // Method to close an account
    private void closeAccount()
    {
        // Find the account by its account number
        Account ac = bank.findAccount(IU.getInt(Messages.ENTER_ACCOUNT_NUMBER));
        
        // Check if the account exists
        if(ac == null)
        {
            System.out.println(Messages.ACCOUNT_NOT_FOUND);
        }
        
        // Set the account to be closed
        ac.setAccountClose(false);
    }
}   