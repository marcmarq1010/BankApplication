package ui;

public class Messages 
{	
    // Menu messages
    public static final String MENU_TITLE = "Welcome to the Banking App!";
    public static final String MENU_CHOICES = "Please enter the number of your choice:";
    
    // Bank menu header
	public static final String MENU_HEADER = "Banking App Menu";
	
	// Menu options
	public static final String MENU_OPTION_1 = "1.  Open a Checking account";
	public static final String MENU_OPTION_2 = "2.  Open a Saving account";
	public static final String MENU_OPTION_3 = "3.  List Accounts";
	public static final String MENU_OPTION_4 = "4.  Account Statement";
	public static final String MENU_OPTION_5 = "5.  Show Account Information";
	public static final String MENU_OPTION_6 = "6.  Deposit funds";
	public static final String MENU_OPTION_7 = "7.  Withdraw funds";
	public static final String MENU_OPTION_8 = "8.  Close an account";
	public static final String MENU_OPTION_9 = "9.  Save Transactions";
	public static final String MENU_OPTION_10 = "10. Currency Conversion";
	public static final String MENU_OPTION_11 = "11. Exit";
	
	// Input prompts
    public static final String ENTER_MENU_CHOICE = "Enter your choice: ";
    public static final String MENU_INVALID_CHOICE =  "Invalid choice. Please enter a number between 1 and 8.";
    public static final String ENTER_ACCOUNT_NUMBER = "Enter account number: ";
    public static final String ENTER_DEPOSIT_AMOUNT =  "Enter the amount to deposit: ";
    public static final String ENTER_WITHDRAW_AMOUNT =  "Enter the withdrawal amount: ";
    public static final String ENTER_ACCOUNT_CURRENCY = "Account Currency:";
 
    
    // Menu commands
    public static final String ENTER_PROGRAM_EXIT = "Exiting program...";
    
	// Account related messages
    public static final String ACCOUNT_CREATED = "Thank you, the account number is ";
    public static final String ACCOUNT_CLOSED = "Account closed successfully! Deposits are no longer avaialble. The current balance is ";
    public static final String ACCOUNT_NOT_FOUND = "Account not found";
    public static final String INSUFFICIENT_FUNDS = "Insufficient funds in account!";
    public static final String OVERDRAFT_LIMIT_EXCEEDED = "Withdrawal denied: Overdraft limit exceeded.";
    public static final String ACCOUNT_CLOSED_ALREADY = "Account is already closed. Can not close a closed account";
    
    // Deposit and withdrawal messages
    public static final String DEPOSIT_SUCCESSFUL = "Deposit successful!";
    public static final String WITHDRAWAL_SUCCESSFUL = "Withdrawal successful!";
    public static final String DEPOSIT_FAILED = "Deposit failed!";
    public static final String WITHDRAWAL_FAILED = "Withdrawal failed! The new balance is ";
    public static final String IS_DEPOSIT = "Depsosit";
    public static final String IS_WITHDRAWAL = "Withdrawal";
    
    // Input prompts open account
    public static final String ENTER_FIRST_NAME = "Enter first name: ";
    public static final String ENTER_LAST_NAME = "Enter last name: ";
    public static final String ENTER_SSN = "Enter social security number: ";
    public static final String ENTER_OVERDRAFT_LIMIT =  "Enter overdraft limit: ";
    public static final String ENTER_INITAL_BALANCE = "Enter initial balance: ";
    
    // Account information messages
    public static final String ACCOUNT_DETAILS = "Account details:";
    public static final String ACCOUNT_TYPE = "Account type: ";
    public static final String ACCOUNT_TYPE_CHECKING = "Checking";
    public static final String ACCOUNT_TYPE_SAVING = "Saving";
    public static final String ACCOUNT_NUMBER = "Account number: ";
    public static final String ACCOUNT_BALANCE = "Account balance: ";
    public static final String ACCOUNT_OPEN = "Open";
    public static final String ACCOUNT_CLOSE = "Closed";
    
    // Exception messages
    public static final String ACCOUNT_CLOSED_EXCEPTION = "Account is closed. Can not deposit into a closed account with a zero balance or positive balance";
    public static final String INSUFFICIENT_BALANCE_EXCEPTION = "There are not enough funds to make a withdrawal.";
    public static final String NO_SUCH_ACCOUNT_EXCEPTION = "Account does not exist";
    public static final String FILE_SAVING_ERROR_EXCEPTION = "Error saving transactions to file.";
    public static final String FILE_NOT_FOUND_EXCEPTION = "Currency file not found. Currency conversion service and Foreign Currency accounts are not available.";
    public static final String IO_EXCEPTION = "Error reading currency file. Currency conversion service and Foreign Currency accounts are not available.";
    
    // File messages
    public static final String FILE_TRANSACTIONS = " Transactions.txt";
    public static final String FILE_SUCCESS = "Transactions saved to file: ";
    
    // Currency statements
    public static final String CURRENCY_SELLING = "The currency you are selling : ";
    public static final String CURRENCY_AMOUNT_TO_BE_SOLD = "The amount you are selling : ";
    public static final String CURRENCY_BUYING = "The currency you are buying : ";
    public static final String CURRENCY_RATE_IS = "The exchange rate is ";
    public static final String CURRENCY_AMOUNT_GIVEN = "and you will get " ;
    public static final String CURRENCY_NOT_FOUND = "Exchange rate not found for ";
    public static final String CURRENCY_DEFAULT = "USD";
    public static final String CURRENCY_NOT_AVAILABLE = "Error: Currency not available. Please enter a different currency code.";
}

