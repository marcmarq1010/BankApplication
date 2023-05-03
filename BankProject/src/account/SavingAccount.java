package account;

import customer.Customer;
import ui.Messages;

public class SavingAccount extends Account 
{
    private final double INTEREST_RATE = 0.004;	 					 // Constant interest rate for the account
    private String accountType = Messages.ACCOUNT_TYPE_SAVING;		 // The account type

    // Constructor that takes a customer and initial balance
    public SavingAccount(Customer customer, double initialBalance, String currencyCode) 
    {
    	// Calls the constructor of the Account superclass to initialize customer and balance and currency code
        super(customer, initialBalance, currencyCode);
    }

    // Method to add interest to the account balance
    public void addInterestToBalance() 
    {
        // Calculates the interest earned on the current balance
        double interest = getBalance() * INTEREST_RATE;
        // Deposits the interest amount to the account
        depositInterest(interest);
    }

    // Overrides the toString method to display account information
    @Override
    public String toString() 
    {
        return getAccountNumber() + "(" + accountType + ") : " + getCustomer().getFirstName() + " : " + getCustomer().getLastName() + " : " + getCustomer().getSsn() + " : " + getCurrencyCode() + " : " + getBalance() + " : " + Messages.CURRENCY_DEFAULT + " : " + getConvertedBalance() + " : " + getStatus();
    }
}