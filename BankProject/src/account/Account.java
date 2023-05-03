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
    private String currency;
    protected ArrayList<Transaction> transactions;  // List of transactions for the account

    // Constructor to create a new account with the given customer and initial balance
    public Account(Customer customer, double initialBalance, String Currency) 
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
    public void deposit(double amount, Account fromAccount) throws AccountClosedException
    {
        try 
        {
            // check if the amount is greater than zero and the fromAccount is open
            if(amount > 0 && fromAccount.isOpen())
            {
                // add the deposited amount to the current balance
                setBalance(balance + amount);
                // create a new transaction with details of the fromAccount, deposited amount, and "Deposit" as the transaction type
                transactions.add(new Transaction(fromAccount, amount, Messages.IS_DEPOSIT));
            }
            else if(!fromAccount.isOpen())
            {
                // throw an AccountClosedException if the fromAccount is closed
                throw new AccountClosedException(Messages.ACCOUNT_CLOSED_EXCEPTION);
            }
            else
            {
                // display a "DEPOSIT_FAILED" message if the amount is not greater than zero
                System.out.println(Messages.DEPOSIT_FAILED);
            }
        } 
        catch (AccountClosedException e) 
        {
            // Catch and re-throw an AccountClosedException if it occurs
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
    		return Messages.ACCOUNT_OPEN;
    	}
    	else
    	{
    		return Messages.ACCOUNT_CLOSE;
    	}
    }

    public String getCurrencyCode() 
    {
		return currency;
	}

	public void setCurrency(String currency) 
	{
		this.currency = currency;
	}
	
	public double getConvertedBalance()
	{
		Bank bank = new Bank();
		return bank.getCurrency(currency, getBalance(),  Messages.CURRENCY_DEFAULT);
	}

	// Returns a list of all transactions for the account
    public List<Transaction> getAllTransactions()
    {
    	return transactions;
    }
}