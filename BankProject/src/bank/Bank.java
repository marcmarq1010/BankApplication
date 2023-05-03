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
	private Map<Integer, Account> accounts; // map to store all the bank accounts
    private static Map<String, Currency> exchangeRates = new HashMap<>(); // map to store exchange rates for different currencies

    // constructor to initialize the map of accounts
    public Bank() 
    {
        accounts = new HashMap<Integer, Account>();
        readCurrencyFile();
    }

    public CheckingAccount openCheckingAccount(Customer customer, double overdraftLimit, double initialDeposit, String currencyCode) 
    {
        // Check if exchange rates have been loaded successfully
        if (exchangeRates != null) 
        {
        	if(exchangeRates.containsKey(currencyCode.toUpperCase())) 
        	{
        		Currency exchangeRate = exchangeRates.get(currencyCode.toUpperCase());
                initialDeposit = initialDeposit * exchangeRate.getExchangeRate();
        	}
        	else
        	{
        		// Exchange rates not available, print error message and ask for currency to be re-entered
                System.out.println("Error: Currency not available. Please enter a different currency code.");
                Scanner scanner = new Scanner(System.in);
                currencyCode = scanner.nextLine();
                return openCheckingAccount(customer, overdraftLimit, initialDeposit, currencyCode); // recursive call to retry opening account
        	}
         
        } 
        else 
        {
            // Exchange rates not available, assume USD as currency
            currencyCode = Messages.CURRENCY_DEFAULT;
        }

        // create a new checking account object with the given parameters and currency code
        CheckingAccount checkingAccount = new CheckingAccount(customer, overdraftLimit, initialDeposit, currencyCode);
        // add the checking account to the map of accounts with its account number as the key
        accounts.put(checkingAccount.getAccountNumber(), checkingAccount);
        // return the new checking account object
        return checkingAccount;
    }

    public SavingAccount openSavingsAccount(Customer customer, double initialDeposit, String currencyCode)
    {
    	  // Check if exchange rates have been loaded successfully
        if (exchangeRates != null) 
        {
        	if(exchangeRates.containsKey(currencyCode.toUpperCase())) 
        	{
        		Currency exchangeRate = exchangeRates.get(currencyCode.toUpperCase());
                initialDeposit = initialDeposit * exchangeRate.getExchangeRate();
        	}
        	else
        	{
        		// Exchange rates not available, print error message and ask for currency to be re-entered
                System.out.println("Error: Currency not available. Please enter a different currency code.");
                Scanner scanner = new Scanner(System.in);
                currencyCode = scanner.nextLine();
                return openSavingsAccount(customer, initialDeposit, currencyCode); // recursive call to retry opening account
        	}
        }
        else 
        {
            // Exchange rates not available, assume USD as currency
            currencyCode = Messages.CURRENCY_DEFAULT;
        }

        // create a new savings account object with the given parameters and currency code
        SavingAccount savingsAccount = new SavingAccount(customer, initialDeposit, currencyCode);
        // add the savings account to the map of accounts with its account number as the key
        accounts.put(savingsAccount.getAccountNumber(), savingsAccount);
        // return the new savings account object
        return savingsAccount;
    }

    
    // method to read a file and store exchange rates
    public void readCurrencyFile()
    {
	    CurrencyExchangeReader reader = new CurrencyExchangeReader();

			try 
			{
				exchangeRates = reader.read("exchange-rate.csv");
			}
			catch (IOException e) 
			{
				// handle the exception here, e.g. print an error message
	            System.out.println(e.getMessage());
			}

    }
    
    public double getCurrency(String currency, double balance, String USD)
    {
    	if (currency.toUpperCase().equals(Messages.CURRENCY_DEFAULT)) 
    	{
    		return balance;
    	}
    	else
    	{ 
    		Currency exchangeRate = exchangeRates.get(currency.toUpperCase());
        
    		double convertedAmount = balance * exchangeRate.getExchangeRate();
    		
    		return convertedAmount;
    	}
    }
    
    public void convertCurrency(String selling, double amount, String buying) 
    {
        double convertedAmount = 0;
        
        if (selling.toUpperCase().equals(Messages.CURRENCY_DEFAULT)) 
        {
            if (!exchangeRates.containsKey(buying.toUpperCase())) 
            {
                System.out.println(Messages.CURRENCY_NOT_FOUND + buying.toUpperCase());
                return;
            }
            Currency exchangeRate = exchangeRates.get(buying.toUpperCase());
            
            convertedAmount = amount / exchangeRate.getExchangeRate();
        } 
        else if (buying.toUpperCase().equals(Messages.CURRENCY_DEFAULT)) 
        {
            if (!exchangeRates.containsKey(selling.toUpperCase())) 
            {
                System.out.println(Messages.CURRENCY_NOT_FOUND + buying.toUpperCase());
                return;
            }
            Currency exchangeRate = exchangeRates.get(selling.toUpperCase());
            convertedAmount = amount * exchangeRate.getExchangeRate();
        } 
        else 
        {
            if (!exchangeRates.containsKey(selling) || !exchangeRates.containsKey(buying)) 
            {
                System.out.println(Messages.CURRENCY_NOT_FOUND + selling + Messages.CURRENCY_NOT_FOUND + buying);
                // return;
            }
            Currency exchangeRate1 = exchangeRates.get(selling);
            Currency exchangeRate2 = exchangeRates.get(buying);
            convertedAmount = amount * exchangeRate1.getExchangeRate() / exchangeRate2.getExchangeRate();
        }
        System.out.println(Messages.CURRENCY_RATE_IS + exchangeRates.get(buying + Messages.CURRENCY_AMOUNT_GIVEN + buying + convertedAmount));
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
