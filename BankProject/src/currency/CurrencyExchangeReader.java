package currency;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ui.Messages;

public class CurrencyExchangeReader 
{
    // Method to read a CSV file containing currency exchange rates and return a map of currency codes and corresponding Currency objects
    public static Map<String, Currency> read(String fileName) throws FileNotFoundException, IOException 
    {
        // Create a new HashMap to store the currency exchange rates
        Map<String, Currency> exchangeRates = new HashMap<>();

        // Create a new BufferedReader to read the CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) 
        {
            // Loop through each line in the file
            String line;
            while ((line = br.readLine()) != null) 
            {
                // Split the line into an array of values using a comma as the delimiter
                String[] values = line.split(",");

                // Create a new Currency object with the values from the CSV file
                String code = values[0];
                String name = values[1];
                double exchangeRate = Double.parseDouble(values[2]);
                Currency currency = new Currency(code, name, exchangeRate);

                // Add the Currency object to the exchangeRates map using the currency code as the key
                exchangeRates.put(code, currency);
            }
        }
        catch (FileNotFoundException e)
        {
            throw new FileNotFoundException(Messages.FILE_NOT_FOUND_EXCEPTION);
        }
        catch (IOException e)
        {
            System.out.println(Messages.IO_EXCEPTION);
        }
        
        // Return the exchangeRates map
        return exchangeRates;
    }
}
