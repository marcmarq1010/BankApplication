package account;

import java.util.ArrayList;
import java.util.List;

import Transaction.Transaction;
import customer.Customer;
import ui.Messages;

public class Account 
{
    private Customer customer;						// The customer who owns the account
    private int accountNumber;						// The unique account number
    private double balance;							// The current balance of the account   
    private static int nextAccountNumber = 1000; 	// The next available account number to be assigned
    private boolean isOpen;							// The status of the account
    protected ArrayList<Transaction> transactions;	// List of transactions for the account

    // Constructor to create a new account with the given customer and initial balance
    public Account(Customer customer, double initialBalance) 
    {
        this.customer = customer;
        this.accountNumber = nextAccountNumber;
        nextAccountNumber++;
        this.setBalance(initialBalance);
        this.isOpen = true;
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

    // Deposits the given interest amount to the account balance
    public void depositInterest(double interest) 
    {
    	setBalance(balance + interest);
    }

    // Deposits the given amount to the account balance
    public void deposit(double amount, Account fromAccount) 
    {
        // check if the amount is greater than zero and the fromAccount is open
        if(amount > 0 && fromAccount.isOpen())
        {
            // add the deposited amount to the current balance
        	setBalance(balance + amount);
        	// create a new transaction with details of the fromAccount, deposited amount, and "Deposit" as the transaction type
        	transactions.add(new Transaction(fromAccount, amount, "Deposit"));
        }
        else
        {
            // display a "DEPOSIT_FAILED" message if the amount is not greater than zero or the fromAccount is not open
        	System.out.println(Messages.DEPOSIT_FAILED);
        }
    }


    // Withdraws the given amount from the account balance
    public void withdraw(double amount, Account toAccount) 
    {
        // Subtract the amount from the account balance
        setBalance(balance - amount);
        // Add the transaction to the list of transactions
        transactions.add(new Transaction(toAccount, amount, "Withdrawal"));
    }

    // Sets the current balance of the account
    public void setBalance(double balance) 
    {
    	this.balance = balance;
    }

    // Returns whether the account is open or not
    public boolean isOpen() 
    {
        return isOpen;
    }

    // Sets the account to open
    public void setAccountOpen(boolean open) 
    {
        isOpen = open;
    }

    // Sets the account to closed
    public void setAccountClose(boolean close) 
    {
    	if(isOpen == false)
    	{
    		System.out.println(Messages.ACCOUNT_CLOSED_ALREADY);
    	}
    	else
    	{
        	isOpen = close;
        	System.out.println(Messages.ACCOUNT_CLOSED + getBalance());
    	}
    }

    // Returns the status of the account
    public String getStatus()
    {
    	if(isOpen)
    	{
    		return "Open";
    	}
    	else
    	{
    		return "Closed";
    	}
    }

    // Returns a list of all transactions for the account
    public List<Transaction> getAllTransactions()
    {
    	return transactions;
    }
}