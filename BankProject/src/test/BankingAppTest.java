package test;

import exceptions.AccountClosedException;
import exceptions.InsufficientBalanceException;
import exceptions.NoSuchAccountException;
import ui.BankingApp;

public class BankingAppTest 
{
    public static void main(String[] args)
    {
    	// create an instance of BankingApp
        BankingApp bankingApp = new BankingApp();
        // start the application
        bankingApp.start();
    }
}
