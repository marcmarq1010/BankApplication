package ui;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Transaction.Transaction;
import account.Account;
import account.CheckingAccount;
import account.SavingAccount;
import bank.Bank;
import currency.Currency;
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
                    showAccountInformation(); // Call method to print account information
                    break;
                case 6:
                    depositFunds(); // Call method to deposit funds into a specific account
                    break;
                case 7:
                    withdrawFunds(); // Call method to withdraw funds from a specific account
                    break;
                case 8:
                    closeAccount(); // Call method to close a specific account
                    break;
                case 9:
                    saveTransactions(); // Call method to save transactions to a txt file
                    break;
                case 10:
                    currencyConversion(); // Call method to convert currency
                    break;
                case 11:
                    // Display message that the program is exiting
                    break;
                default:
                   System.out.println(Messages.MENU_INVALID_CHOICE); // Display message for an invalid choice
            }
        } 
        while (choice != 11); // Continue the loop until user chooses to exit
    }
    
    private void displayMenu() 
    {
        System.out.println("");
    	System.out.println(Messages.MENU_TITLE); 	 // Display the menu title
        System.out.println(Messages.MENU_OPTION_1);  // Display option to create a new checking account
        System.out.println(Messages.MENU_OPTION_2);  // Display option to create a new saving account
        System.out.println(Messages.MENU_OPTION_3);  // Display option to list all the created accounts
        System.out.println(Messages.MENU_OPTION_4);  // Display option to print statement for a specific account
        System.out.println(Messages.MENU_OPTION_5);  // Display option to print account information 
        System.out.println(Messages.MENU_OPTION_6);  // Display option to deposit funds into a specific account
        System.out.println(Messages.MENU_OPTION_7);  // Display option to withdraw funds from a specific account
        System.out.println(Messages.MENU_OPTION_8);  // Display option to close a specific account
        System.out.println(Messages.MENU_OPTION_9);  // Display option to save the transactions to a txt file
        System.out.println(Messages.MENU_OPTION_10); // Display option to convert currency
        System.out.println(Messages.MENU_OPTION_11); // Display option to exit the program
        System.out.println("");
        System.out.println(Messages.ENTER_MENU_CHOICE); // Prompt user to enter their choice
    }
	
 // Method to open a checking account
    private void openCheckingAccount()
    {
        // Create a new Customer object with first name, last name and SSN
        Customer c = new Customer(IU.getString(Messages.ENTER_FIRST_NAME), IU.getString(Messages.ENTER_LAST_NAME), IU.getString(Messages.ENTER_SSN));
        
        // Open a checking account for the customer with specified overdraft limit and initial balance
        CheckingAccount ca = bank.openCheckingAccount(c, IU.getDouble(Messages.ENTER_OVERDRAFT_LIMIT), IU.getDouble(Messages.ENTER_INITAL_BALANCE), IU.getString(Messages.ENTER_ACCOUNT_CURRENCY));
        
        // Print the message indicating the account number of the newly created checking account
        System.out.println(Messages.ACCOUNT_CREATED + ca.getAccountNumber());
    }

    // Method to open a savings account
    private void openSavingAccount()
    {
        // Create a new Customer object with first name, last name and SSN
        Customer c = new Customer(IU.getString(Messages.ENTER_FIRST_NAME), IU.getString(Messages.ENTER_LAST_NAME), IU.getString(Messages.ENTER_SSN));
        
        // Open a savings account for the customer with specified initial balance
        SavingAccount sa = bank.openSavingsAccount(c, IU.getDouble(Messages.ENTER_INITAL_BALANCE), IU.getString(Messages.ENTER_ACCOUNT_CURRENCY));
        
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
        	// handle the exception here, e.g. print an error message
            System.out.println(e.getMessage());
        }
    }

    private void showAccountInformation()
    {
    	  try 
          {
          	  // Find the account by its account number
              Account ac = bank.findAccount(IU.getInt(Messages.ENTER_ACCOUNT_NUMBER));
            
              System.out.println(Messages.ACCOUNT_NUMBER + ac.getAccountNumber());
              System.out.println(Messages.ACCOUNT_INFO_NAME + ac.getCustomer().getFirstName() + " " +ac.getCustomer().getLastName());
              System.out.println(Messages.ACCOUNT_INFO_CURRENCY + ac.getCurrencyCode());
              System.out.println(Messages.ACCOUNT_INFO_CURRENCY_BALANCE + ac.getBalance());
              System.out.println(Messages.ACCOUNT_INFO_CURRENCY_BALANCE_USD + ac.getConvertedBalance());
          } 
          catch (NoSuchAccountException e) 
          {
              // handle the exception here, e.g. print an error message
              System.out.println(e.getMessage());
          } 
    }
    
    // Method to deposit funds into an account
    private void depositFunds()
    {   
        try 
        {
        	 // Find the account by its account number
            Account ac = bank.findAccount(IU.getInt(Messages.ENTER_ACCOUNT_NUMBER));
            // Deposit the specified amount into the account
            ac.deposit(IU.getDouble(Messages.ENTER_DEPOSIT_AMOUNT), ac);
        } 
        catch (NoSuchAccountException e) 
        {
            // handle the exception here, e.g. print an error message
            System.out.println(e.getMessage());
        } 
        catch (AccountClosedException e) 
        {
            // handle the exception here, e.g. print an error message
            System.out.println(e.getMessage());
        }
    }

    // Method to withdraw funds from an account
    private void withdrawFunds()
    {
        try 
        {
        	 // Find the account by its account number
            Account ac = bank.findAccount(IU.getInt(Messages.ENTER_ACCOUNT_NUMBER));
            // Withdraw the specified amount from the account
            ac.withdraw(IU.getDouble(Messages.ENTER_DEPOSIT_AMOUNT), ac);
        } 
        catch (NoSuchAccountException e) 
        {
            // handle the exception here, e.g. print an error message
            System.out.println(e.getMessage());
        } 
        catch (AccountClosedException e) 
        {
            // handle the exception here, e.g. print an error message
            System.out.println(e.getMessage());
        } 
        catch (InsufficientBalanceException e)
        {
        	// handle the exception here, e.g. print an error message
            System.out.println(e.getMessage());
		}
    }

    // Method to close an account
    private void closeAccount()
    {
		try 
		{
			// Find the account by its account number
	        Account ac = bank.findAccount(IU.getInt(Messages.ENTER_ACCOUNT_NUMBER));
			// Set the account to be closed
	        ac.setAccountClose(false);
		} 
		catch (NoSuchAccountException e) 
		{
			// handle the exception here, e.g. print an error message
            System.out.println(e.getMessage());
		}       
    }
    
    // Method to save transactions to a file
    private void saveTransactions() 
    {
        try 
        {
            // Find the account by its account number
            Account ac = bank.findAccount(IU.getInt(Messages.ENTER_ACCOUNT_NUMBER));

            // Get all transactions for the account
            transactions = ac.getAllTransactions();

            // Create the file name based on the account number
            String fileName = ac.getAccountNumber() + Messages.FILE_TRANSACTIONS;

            // Create a new file with the given file name
            File file = new File(fileName);

            // Create a PrintWriter to write to the file
            PrintWriter writer = new PrintWriter(file);

            // Loop through each transaction and write its information to the file
            for (Transaction transaction : transactions) 
            {
                writer.println(transaction);
            }

            // Close the PrintWriter
            writer.close();

            // Print a success message
            System.out.println(Messages.FILE_SUCCESS + fileName);
        } 
        catch (NoSuchAccountException e) 
        {
            // handle the exception here, e.g. print an error message
            System.out.println(e.getMessage());
        } 
        catch (IOException e) 
        {
            // handle the exception here, e.g. print an error message
            System.out.println(Messages.FILE_SAVING_ERROR_EXCEPTION);
        }
    }
    
    public void currencyConversion()
    {
    	bank.convertCurrency(IU.getString(Messages.CURRENCY_SELLING), IU.getDouble(Messages.CURRENCY_AMOUNT_TO_BE_SOLD), IU.getString(Messages.CURRENCY_BUYING));
    }

}   


