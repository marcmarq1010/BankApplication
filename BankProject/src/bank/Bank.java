package bank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Transaction.Transaction;
import account.Account;
import account.CheckingAccount;
import account.SavingAccount;
import currency.Currency;
import currency.CurrencyExchangeReader;
import customer.Customer;
import exceptions.NoSuchAccountException;
import ui.Messages;

public class Bank 
{
	private Map<Integer, Account> accounts; 									// Stores all the bank accounts
    private static Map<String, Currency> exchangeRates = new HashMap<>(); 		// Stores exchange rates for different currencies

    // Constructor to initialize the map of accounts and read currency file
    public Bank() 
    {
        accounts = new HashMap<Integer, Account>();
        //config method will be called to read config file and return boolean values then pass boolean arguments to the readCurrency method
        //The config method will have a branching statement to decide whether to access currency or not
        // and where to access it from, URL or from the local file
        readCurrency();
    }
    
    // Method to open a checking account
    public CheckingAccount openCheckingAccount(Customer customer, double overdraftLimit, double initialDeposit, String currencyCode) 
    {
        // Checks if exchange rates have been loaded successfully
        if (exchangeRates != null) 
        {
        	// Checks if the currency code is valid
        	if(exchangeRates.containsKey(currencyCode.toUpperCase())) 
        	{
        		// Creates a new checking account object with the given parameters and currency code
                CheckingAccount checkingAccount = new CheckingAccount(customer, overdraftLimit, initialDeposit, currencyCode);
                
                // Adds the checking account to the map of accounts with its account number as the key
                accounts.put(checkingAccount.getAccountNumber(), checkingAccount);
                
                // Returns the new checking account object
                return checkingAccount;
        	}
        	else
        	{
        		// Exchange rates not available, print error message and ask for currency to be re-entered
                System.out.println(Messages.CURRENCY_NOT_AVAILABLE);
                Scanner scanner = new Scanner(System.in);
                currencyCode = scanner.nextLine();
                
                // Recursive call to retry opening account
                return openCheckingAccount(customer, overdraftLimit, initialDeposit, currencyCode); 
        	}
         
        } 
        else 
        {
            // Exchange rates not available, assume USD as currency
            currencyCode = Messages.CURRENCY_DEFAULT;
            
            // Creates a new checking account object with the given parameters and currency code
            CheckingAccount checkingAccount = new CheckingAccount(customer, overdraftLimit, initialDeposit, currencyCode);
            
            // Adds the checking account to the map of accounts with its account number as the key
            accounts.put(checkingAccount.getAccountNumber(), checkingAccount);
            
            // Returns the new checking account object
            return checkingAccount;
        }
    }

    // Method to open a savings account
    public SavingAccount openSavingsAccount(Customer customer, double initialDeposit, String currencyCode)
    {
    	// Checks if exchange rates have been loaded successfully
        if (exchangeRates != null) 
        {
        	// Checks if the currency code is valid
        	if(exchangeRates.containsKey(currencyCode.toUpperCase())) 
        	{
        		// Creates a new savings account object with the given parameters and currency code
                SavingAccount savingsAccount = new SavingAccount(customer, initialDeposit, currencyCode);
                
                // Adds the savings account to the map of accounts with its account number as the key
                accounts.put(savingsAccount.getAccountNumber(), savingsAccount);
                
                // Returns the new savings account object
                return savingsAccount;
        	}
        	else
        	{
        		// Exchange rates not available, print error message and ask for currency to be re-entered
                System.out.println(Messages.CURRENCY_NOT_AVAILABLE);
                Scanner scanner = new Scanner(System.in);
                currencyCode = scanner.nextLine();
                
                // Recursive call to retry opening account
                return openSavingsAccount(customer, initialDeposit, currencyCode); 
        	}
        }
        else 
        {
            // Exchange rates not available, assume USD as currency
            currencyCode = Messages.CURRENCY_DEFAULT;
            
            // Creates a new savings account object with the given parameters and currency code
            SavingAccount savingsAccount = new SavingAccount(customer, initialDeposit, currencyCode);
            
            // Adds the savings account to the map of accounts with its account number as the key
            accounts.put(savingsAccount.getAccountNumber(), savingsAccount);
            
            // Returns the new savings account object
            return savingsAccount;
        }
    }
    
    // Method to read a file and store exchange rates
    public void readCurrency()
    {
    	// Creates a new CurrencyExchangeReader object
	    CurrencyExchangeReader reader = new CurrencyExchangeReader();

			try 
			{
				// Calls the read method of the CurrencyExchangeReader object, passing in the filename of the exchange rate file
				// The read method returns a HashMap containing the exchange rates
				exchangeRates = reader.read("exchange-rate.csv");
			}
			catch (IOException e) 
			{
				// Handles the exception here, e.g. print an error message
	            System.out.println(e.getMessage());
			}

    }
    
    // This method converts a balance from a specified currency to a default currency
    public double getCurrency(String currency, double balance, String USD)
    {
    	// Checks if the specified currency is the same as the default currency
    	if (currency.toUpperCase().equals(Messages.CURRENCY_DEFAULT)) 
    	{
    		// If true, returns the balance without conversion
    		return balance;
    	}
    	else
    	{ 
    		// If false, Creates an instance of the Currency class and gets the information for the specified currency from the exchangeRates HashMap
    		Currency exchangeRate = exchangeRates.get(currency.toUpperCase());
        
    		// Calculates the converted amount by multiplying the balance by the exchange rate of the currency 
    		double convertedAmount = balance * exchangeRate.getExchangeRate();
    		
    		// Returns the converted amount
    		return convertedAmount;
    	}
    }
    
    // This method converts an amount of money from one currency to another
    public void convertCurrency(String selling, double amount, String buying) 
    {
    	// Initializes a variable to hold the converted amount
        double convertedAmount = 0;
        
        // Checks if the selling currency is the same as the default currency
        if (selling.toUpperCase().equals(Messages.CURRENCY_DEFAULT)) 
        {
            // If true, checks if the buying currency is in the exchangeRates HashMap
            if (!exchangeRates.containsKey(buying.toUpperCase())) 
            {
                // If false, print an error message and return from the method
                System.out.println(Messages.CURRENCY_NOT_FOUND + buying.toUpperCase());
                return;
            }
            else
            {
                // If the buying currency is in the exchangeRates HashMap, gets the exchange rate
                Currency exchangeRate = exchangeRates.get(buying.toUpperCase());
                
                // Calculates the converted amount
                convertedAmount = amount / exchangeRate.getExchangeRate();
                
                // Print the exchange rate and converted amount
                System.out.println(Messages.CURRENCY_RATE_IS + exchangeRates.get(buying + Messages.CURRENCY_AMOUNT_GIVEN + buying + convertedAmount));
            }
        } 
        // If the buying currency is the same as the default currency, follow the same logic as above but in reverse
        else if (buying.toUpperCase().equals(Messages.CURRENCY_DEFAULT)) 
        {
        	// If true, checks if the selling currency is in the exchangeRates HashMap
            if (!exchangeRates.containsKey(selling.toUpperCase())) 
            {
            	// If false, print an error message and return from the method
                System.out.println(Messages.CURRENCY_NOT_FOUND + buying.toUpperCase());
                return;
            }
            else
            {
            	 // If the selling currency is in the exchangeRates HashMap, gets the exchange rate
                Currency exchangeRate = exchangeRates.get(selling.toUpperCase());
                
                // Calculates the converted amount
                convertedAmount = amount * exchangeRate.getExchangeRate();
                
                // Print the exchange rate and converted amount
                System.out.println(Messages.CURRENCY_RATE_IS + exchangeRates.get(buying + Messages.CURRENCY_AMOUNT_GIVEN + buying + convertedAmount));
            }
        } 
        // If neither currency is the default currency, check that both currencies are in the exchangeRates HashMap
        else 
        {
            // Checks that both currencies are in exchangeRates HashMap
            if (!exchangeRates.containsKey(selling) || !exchangeRates.containsKey(buying)) 
            {
                // Prints error message and return
                System.out.println(Messages.CURRENCY_NOT_FOUND + selling + Messages.CURRENCY_NOT_FOUND + buying);
                return;
            }
            // Checks that at least one of the currencies is the default currency
            else if((!buying.toUpperCase().equals(Messages.CURRENCY_DEFAULT) && !selling.toUpperCase().equals(Messages.CURRENCY_DEFAULT)))
            {
                // Prints error message and return
            	System.out.println(Messages.CURRENCIES_MUST_BE_USD);
                return;
            }
        }
    }
    
    // method to get all accounts in the bank
    public List<Account> getAllAccounts() 
    {
        // return the list of accounts
        return new ArrayList<Account>(accounts.values());
    }


    // method to find an account by its account number
    public Account findAccount(int accountNumber) throws NoSuchAccountException
    {
        // get the account from the map using its account number as the key
        Account account = accounts.get(accountNumber);
        // if account not found, throw NoSuchAccountException
        if (account == null)
        {
            throw new NoSuchAccountException(Messages.NO_SUCH_ACCOUNT_EXCEPTION);
        }
        // return the account
        return account;
    }
}
