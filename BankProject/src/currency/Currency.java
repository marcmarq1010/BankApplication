
package currency;


import java.util.HashMap;
import java.util.Map;


public class Currency 
{
    // Declares private instance variables for currency code, name and exchange rate
    private String code;
    private String name;
    private double exchangeRate;
    
    // Constructor to initialize the currency code, name, and exchange rate instance variables
    public Currency(String code, String name, double exchangeRate)
    {
        this.code = code;
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    // Getter method to return the currency code
    public String getCode() 
    {
        return code;
    }

    // Getter method to return the currency name
    public String getName() 
    {
        return name;
    }

    // Getter method to return the exchange rate
    public double getExchangeRate() 
    {
        return exchangeRate;
    }
    
    // Override the toString() method to return the currency code as a string
    public String toString() 
    {
        return code;
    }
}
