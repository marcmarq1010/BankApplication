package account;

import customer.Customer;

public class SavingAccount extends Account 
{
    private final double INTEREST_RATE = 0.004;	 // constant interest rate for the account
    private String accountType = "Saving";		// field to hold account type

    // constructor that takes a customer and initial balance
    public SavingAccount(Customer customer, double initialBalance) 
    {
        // call the constructor of the Account class to initialize customer and balance
        super(customer, initialBalance);
    }

    // method to add interest to the account balance
    public void addInterestToBalance() 
    {
        // calculate the interest earned on the current balance
        double interest = getBalance() * INTEREST_RATE;
        // deposit the interest amount to the account
        depositInterest(interest);
    }

    // override the toString method to display account information
    @Override
    public String toString() 
    {
        return getAccountNumber() + "(" + accountType + ") : " + getCustomer().getFirstName() + " : " + getCustomer().getLastName() + " : " + getCustomer().getSsn() + " : " + getBalance() + " : " + getStatus();
    }
}