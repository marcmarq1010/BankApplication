package Transaction;

import account.Account; 

public class Transaction 
{
    private static int nextTransactionNumber = 1;	// A static variable to keep track of the next transaction number
    private final int transactionNumber;  			// A final instance variable to store the transaction number
    private final Account account;					// A final instance variable to store the account associated with the transaction
    private final double amount;					// A final instance variable to store the amount of the transaction
    private final String type;	  					// A final instance variable to store the type of the transaction (either DEPOSIT or WITHDRAW)

    // Constructor that initializes the instance variables
    public Transaction(Account account, double amount, String type) 
    {
        this.account = account;
        this.amount = amount;
        this.type = type;
        
        this.transactionNumber = nextTransactionNumber;
        nextTransactionNumber++; // Increment the static variable for the next transaction number
    }

    // Getter method for the transaction number
    public int getTransactionNumber() 
    {
        return transactionNumber;
    }

    // Getter method for the account associated with the transaction
    public Account getAccount() 
    {
        return account;
    }

    // Getter method for the amount of the transaction
    public double getAmount()
    {
        return amount;
    }

    // Getter method for the type of the transaction
    public String getType() 
    {
        return type;
    }

	@Override
	public String toString() 
	{
		return account.getAccountNumber() + ":" + transactionNumber + ":" + amount+ ":" + type;
	}
    
    
}
