package account;

import java.util.ArrayList;
import java.util.List;

import Transaction.Transaction;
import bank.Bank;
import currency.Currency;
import customer.Customer;
import exceptions.AccountClosedException;
import exceptions.InsufficientBalanceException;
import ui.Messages;

public class Account 
{
    private Customer customer;                      // The customer who owns the account
    private int accountNumber;                      // The unique account number
    private double balance;                         // The current balance of the account   
    private static int nextAccountNumber = 1000;    // The next available account number to be assigned
    private boolean isOpen;                         // The status of the account
    private String currency;						// The currency that the account is using
    protected ArrayList<Transaction> transactions;  // List of transactions for the account

    // Constructor to create a new account with the given customer and initial balance
    public Account(Customer customer, double initialBalance, String currency) 
    {
        this.customer = customer;
        this.accountNumber = nextAccountNumber;
        nextAccountNumber++;
        this.setBalance(initialBalance);
        this.isOpen = true;
        this.currency = currency;
        transactions = new ArrayList<Transaction>();
    }

    // Returns the customer who owns the account
    public Customer getCustomer() 
    {
        return customer;
    }

    // Returns the unique account number
    public int getAccountNumber() 
    {
        return accountNumber;
    }

    // Returns the current balance of the account
    public double getBalance() 
    {
        return balance;
    }

    // Sets the current balance of the account
    public void setBalance(double balance) 
    {
        this.balance = balance;
    }
    
    // Deposits the given interest amount to the account balance
    public void depositInterest(double interest) 
    {
        setBalance(balance + interest);
    }

    // Deposits the given amount to the account balance
    public void deposit(double amount, Account account) throws AccountClosedException
    {
        try 
        {
            // Checks if the amount is greater than zero and the account is open
            if(amount > 0 && account.isOpen())
            {
                // Adds the deposited amount to the current balance
                setBalance(balance + amount);
                
                // Add the transaction to the list of transactions
                transactions.add(new Transaction(account, amount, Messages.IS_DEPOSIT));
            }
            else if(!account.isOpen())
            {
                // Throws an AccountClosedException if the account is closed
                throw new AccountClosedException(Messages.ACCOUNT_CLOSED_EXCEPTION);
            }
            else
            {
                // Displays a "DEPOSIT_FAILED" message if the amount is not greater than zero
                System.out.println(Messages.DEPOSIT_FAILED);
            }
        } 
        catch (AccountClosedException e) 
        {
            // Catches and re-throws an AccountClosedException if it occurs
            throw e;
        }
    }

    // Withdraws the given amount from the account balance
    public void withdraw(double amount, Account toAccount) throws InsufficientBalanceException, AccountClosedException
    {
        try 
        {
            if(!isOpen)
            {
                // If the account is closed, throw an AccountClosedException with a message
                throw new AccountClosedException(Messages.ACCOUNT_CLOSED_EXCEPTION);
            }
            else if(amount > balance)
            {
                // If there are insufficient funds to make the withdrawal, throw an InsufficientBalanceException with a message
                throw new InsufficientBalanceException(Messages.INSUFFICIENT_BALANCE_EXCEPTION);
            }
            else
            {
                // Subtract the amount from the account balance
                setBalance(balance - amount);
                
                // Add the transaction to the list of transactions
                transactions.add(new Transaction(toAccount, amount, Messages.IS_WITHDRAWAL));
            }
        } 
        catch (AccountClosedException e) 
        {
            // If an AccountClosedException is caught, re-throw the exception
            throw e;
        } 
        catch (InsufficientBalanceException e) 
        {
            // If an InsufficientBalanceException is caught, re-throw the exception
            throw e;
        }
    }

    // Sets the account to open
    public void setAccountOpen(boolean open) 
    {
        isOpen = open;
    }

	// This method sets the account status to closed
    public void setAccountClose(boolean close)
    {
		 // Check if the account is already closed
		 if(isOpen == false)
		 {
			 // Print a message indicating that the account is already closed
			 System.out.println(Messages.ACCOUNT_CLOSED_ALREADY);
		 }
		 // If the account is not already closed
		 else 
		 {
			 // Set the account status to closed
			 isOpen = close;
			 // Print a message indicating that the account is now closed, along with its balance
			 System.out.println(Messages.ACCOUNT_CLOSED + getBalance());
		 }
    }

    // Returns whether the account is open or not
    public boolean isOpen() 
    {
        return isOpen;
    }
    
    // Returns the current status of the account
    public String getStatus()
    {
        // Check if the account is currently open
        if (isOpen) 
        {
            // If it is, return a message indicating that the account is open
            return Messages.ACCOUNT_OPEN;
        }
        else 
        {
            // If it is not, return a message indicating that the account is closed
            return Messages.ACCOUNT_CLOSE;
        }
    }

    //Return the currency being used by the account
    public String getCurrencyCode() 
    {
		return currency;
	}

    //Sets the currency to be used by the account
	public void setCurrency(String currency) 
	{
		this.currency = currency;
	}
	
	//Returns the account balance in the default currency
	public double getConvertedBalance() 
	{
		// Create a new Bank object
		Bank bank = new Bank();
		
		// Call the getCurrency method of the Bank object, passing in the account currency, balance, and a default currency
		// The getCurrency method returns the account balance converted to the default currency
		return bank.getCurrency(this.currency, getBalance(), Messages.CURRENCY_DEFAULT);
	}

	// Returns a list of all transactions for the account
    public List<Transaction> getAllTransactions()
    {
    	return transactions;
    }
}
