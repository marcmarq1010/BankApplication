package bank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    // method to open a new checking account for a customer
    public CheckingAccount openCheckingAccount(Customer customer, double overdraftLimit,  double initialDeposit) 
    {
        // create a new checking account object with the given parameters
        CheckingAccount checkingAccount = new CheckingAccount(customer, overdraftLimit, initialDeposit);
        // add the checking account to the map of accounts with its account number as the key
        accounts.put(checkingAccount.getAccountNumber(), checkingAccount);
        // return the new checking account object
        return checkingAccount;
    }

    // method to open a new savings account for a customer
    public SavingAccount openSavingsAccount(Customer customer, double initialDeposit)
    {
        // create a new savings account object with the given parameters
        SavingAccount savingsAccount = new SavingAccount(customer, initialDeposit);
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
    
    public void convertCurrency(String selling, double amount, String buying)
    {
    	
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
