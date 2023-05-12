package currency;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import config.Config;
import ui.Messages;

public class CurrencyExchangeReader 
{
    // Method to read a CSV file containing currency exchange rates and return a map of currency codes and corresponding Currency objects
    public static Map<String, Currency> read(Config config) throws FileNotFoundException, IOException 
    {
        // Create a new HashMap to store the currency exchange rates
        Map<String, Currency> exchangeRates = new HashMap<>();
        
        boolean URL = true;

	        if (config.isSupportCurrencies()) 
	        {
	        	if(config.getCurrenciesSource().equals("webservice"))
	        	{
	        	// Attempt to retrieve exchange rates from URL
	               try 
	               {
	                   HttpClient client = HttpClient.newHttpClient();
	                   HttpRequest request = HttpRequest.newBuilder()
	                           .uri(URI.create(config.getCurrenciesSource()))
	                           .build();
	                   HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	                   String body = response.body();
	
	                   BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body.getBytes())));
	                   String line;
	                   while ((line = br.readLine()) != null) 
	                   {
	                       String[] values = line.split(",");
	                       String code = values[0];
	                       String name = values[1];
	                       double exchangeRate = Double.parseDouble(values[2]);
	                       Currency currency = new Currency(code, name, exchangeRate);
	                       exchangeRates.put(code, currency);
	                   }
	                   return exchangeRates;
	               } 
	               catch (IOException | InterruptedException e) 
	               {
	                   // If the attempt to retrieve exchange rates from URL fails, log the exception and attempt to load from file
	                   System.out.println(Messages.CURRENCY_FILE_DOWNLOAD_FAILED);
	                   URL = false;
	               }
	           }
	           else if(config.getCurrenciesSource().equals("file") || !URL)         
	           {
	
	               // Create a new BufferedReader to read the CSV file
	               try (BufferedReader br = new BufferedReader(new FileReader(config.getCurrencyFile()))) 
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
	               catch (FileNotFoundException e1)
	               {
	                   throw new FileNotFoundException(Messages.FILE_NOT_FOUND_EXCEPTION);
	               }
	               catch (IOException e2)
	               {
	                   System.out.println(Messages.IO_EXCEPTION);
	               }
	
	               // Return the exchangeRates map
	               return exchangeRates;
	           }	
	        }
	        else
	        {
	        	System.out.println(Messages.CURRENCY_IS_FALSE);
	        	return exchangeRates;
	        }
			return exchangeRates;

    }
}
