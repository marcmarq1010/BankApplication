package account;

import Transaction.Transaction;
import customer.Customer;
import ui.Messages;

public class CheckingAccount extends Account 
{
    private double overdraftLimit;				 // field to hold overdraft limit
    private String accountType = "Checking";	 // field to hold account type

    // constructor that takes customer, initial balance, and overdraft limit
    public CheckingAccount(Customer customer, double overdraftLimit, double initialBalance) 
    {
    	// call the constructor of the Account superclass
        super(customer, initialBalance);	
        // set the overdraft limit for this checking account
        this.overdraftLimit = overdraftLimit;	
    }

    // getter method for overdraft limit
    public double getOverdraftLimit() 
    {
        return overdraftLimit;
    }

    // setter method for overdraft limit
    public void setOverdraftLimit(double overdraftLimit) 
    {
        this.overdraftLimit = overdraftLimit;
    }

    // override the withdraw method from the Account superclass
    @Override
    public void withdraw(double amount, Account ac) 
    {
        // check if the withdrawal amount exceeds the account balance plus the overdraft limit
        if (amount > getBalance() + overdraftLimit || amount <= 0) 
        {
            System.out.println(Messages.WITHDRAWAL_FAILED + getBalance());
        } 
        else 
        {
            // withdraw the amount from the account balance
            setBalance(getBalance() - amount);
            transactions.add(new Transaction(ac, amount, "Withdrawal"));
        }
    }
    
    // override the toString method to display account information
    @Override
    public String toString() 
    {
        return getAccountNumber() + "(" + accountType + ") : " + getCustomer().getFirstName() + " : " + getCustomer().getLastName() + " : " + getCustomer().getSsn() + " : " + getBalance() + " : " + getStatus();
    }
}