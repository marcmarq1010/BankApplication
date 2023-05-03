package account;

import customer.Customer;
import ui.Messages;

public class SavingAccount extends Account 
{
    private final double INTEREST_RATE = 0.004;	 // constant interest rate for the account
    private String accountType = Messages.ACCOUNT_TYPE_SAVING;		 // field to hold account type


    // constructor that takes a customer and initial balance
    public SavingAccount(Customer customer, double initialBalance, String currencyCode) 
    {
        // call the constructor of the Account class to initialize customer and balance
        super(customer, initialBalance, currencyCode);
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
        return getAccountNumber() + "(" + accountType + ") : " + getCustomer().getFirstName() + " : " + getCustomer().getLastName() + " : " + getCustomer().getSsn() + " : " + getCurrencyCode() + " : " + getBalance() + " : " + Messages.CURRENCY_DEFAULT + " : " + getConvertedBalance() + " : " + getStatus();
    }
}