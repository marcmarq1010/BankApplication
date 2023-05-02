package bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Transaction.Transaction;
import account.Account;
import account.CheckingAccount;
import account.SavingAccount;
import customer.Customer;

public class Bank 
{
	private List<Account> accounts; // list to store all the bank accounts

    // constructor to initialize the list of accounts
    public Bank() 
    {
        accounts = new ArrayList<Account>();
    }

    // method to open a new checking account for a customer
    public CheckingAccount openCheckingAccount(Customer customer, double overdraftLimit,  double initialDeposit) 
    {
        // create a new checking account object with the given parameters
        CheckingAccount checkingAccount = new CheckingAccount(customer, overdraftLimit, initialDeposit);
        // add the checking account to the list of accounts
        accounts.add(checkingAccount);
        // return the new checking account object
        return checkingAccount;
    }

    // method to open a new savings account for a customer
    public SavingAccount openSavingsAccount(Customer customer, double initialDeposit)
    {
        // create a new savings account object with the given parameters
        SavingAccount savingsAccount = new SavingAccount(customer, initialDeposit);
        // add the savings account to the list of accounts
        accounts.add(savingsAccount);
        // return the new savings account object
    	return savingsAccount;
    }

    // method to get all accounts in the bank
    public List<Account> getAllAccounts() 
    {
    	// return the list of accounts
    	return accounts;
    }

    // method to find an account by its account number
    public Account findAccount(int accountNumber)
    {
        // loop through all accounts in the list
        for (Account account : accounts) 
        {
            // if the account number matches, return the account
            if (account.getAccountNumber() == accountNumber)
            {
                return account;
            }
        }
        // if account not found, return null
        return null;
    }
}
