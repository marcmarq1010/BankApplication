package account;

import Transaction.Transaction;
import currency.Currency;
import customer.Customer;
import ui.Messages;

public class CheckingAccount extends Account 
{
    private double overdraftLimit;				 					 // The overdraft limit
    private String accountType = Messages.ACCOUNT_TYPE_CHECKING;	 // The account type
    private double convertedBalance;								 // The convert balance from current currency to default currency
    
    // Constructor that takes customer, initial balance, and overdraft limit
    public CheckingAccount(Customer customer, double overdraftLimit, double initialBalance, String currencyCode) 
    {
    	// Calls the constructor of the Account superclass to initialize customer and balance and currency code
        super(customer, initialBalance, currencyCode);	
        
        // Sets the overdraft limit for this checking account
        this.overdraftLimit = overdraftLimit;	
    }

    // Getter method for overdraft limit
    public double getOverdraftLimit() 
    {
        return overdraftLimit;
    }

    // Setter method for overdraft limit
    public void setOverdraftLimit(double overdraftLimit) 
    {
        this.overdraftLimit = overdraftLimit;
    }
	
	// Overrides the withdraw method from the Account superclass
    @Override
    public void withdraw(double amount, Account ac) 
    {
        // Checks if the withdrawal amount exceeds the account balance plus the overdraft limit
        if (amount > getBalance() + overdraftLimit || amount <= 0) 
        {
        	// Displays a "WITHDRAWAL_FAILED" message if the amount is is greater than the balance plus the overdraft limit
            System.out.println(Messages.WITHDRAWAL_FAILED + getBalance());
        } 
        else 
        {
            // Withdraws the amount from the account balance
            setBalance(getBalance() - amount);
            //Add the transaction to the list of transactions
            transactions.add(new Transaction(ac, amount, Messages.IS_WITHDRAWAL));
        }
    }
    
    // override the toString method to display account information
    @Override
    public String toString() 
    {
        return getAccountNumber() + "(" + accountType + ") : " + getCustomer().getFirstName() + " : " + getCustomer().getLastName() + " : " + getCustomer().getSsn() + " : " + getCurrencyCode() + " : " + getBalance() + " : "  + Messages.CURRENCY_DEFAULT + " : " + getConvertedBalance() + " : " + getStatus();
    }
}