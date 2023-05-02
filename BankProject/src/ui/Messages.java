package ui;

public class Messages 
{	
    // Menu messages
    public static final String MENU_TITLE = "Welcome to the Banking App!";
    public static final String MENU_CHOICES = "Please enter the number of your choice:";
    
    // Bank menu header
	public static final String MENU_HEADER = "Banking App Menu";
	
	// Menu options
	public static final String MENU_OPTION_1 = "1. Open a Checking account";
	public static final String MENU_OPTION_2 = "2. Open a Saving account";
	public static final String MENU_OPTION_3 = "3. List Accounts";
	public static final String MENU_OPTION_4 = "4. Account Statement";
	public static final String MENU_OPTION_5 = "5. Deposit funds";
	public static final String MENU_OPTION_6 = "6. Withdraw funds";
	public static final String MENU_OPTION_7 = "7. Close an account";
	public static final String MENU_OPTION_8 = "8. Exit";
	
	// Input prompts
    public static final String ENTER_MENU_CHOICE = "Enter your choice: ";
    public static final String MENU_INVALID_CHOICE =  "Invalid choice. Please enter a number between 1 and 8.";
    public static final String ENTER_ACCOUNT_NUMBER = "Enter account number: ";
    public static final String ENTER_DEPOSIT_AMOUNT =  "Enter the amount to deposit: ";
    public static final String ENTER_WITHDRAW_AMOUNT =  "Enter the withdrawal amount: ";
    
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
    
    // Input prompts open account
    public static final String ENTER_FIRST_NAME = "Enter first name: ";
    public static final String ENTER_LAST_NAME = "Enter last name: ";
    public static final String ENTER_SSN = "Enter social security number: ";
    public static final String ENTER_OVERDRAFT_LIMIT =  "Enter overdraft limit: ";
    public static final String ENTER_INITAL_BALANCE = "Enter initial balance: ";
    
    // Account information messages
    public static final String ACCOUNT_DETAILS = "Account details:";
    public static final String ACCOUNT_TYPE = "Account type: ";
    public static final String ACCOUNT_NUMBER = "Account number: ";
    public static final String ACCOUNT_BALANCE = "Account balance: ";
}
