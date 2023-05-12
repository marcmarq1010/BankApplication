package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationReader 
{

    public Config readConfig(String fileName) throws IOException 
    {
    	Config config = new Config();
    	
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = reader.readLine()) != null) 
        {
            String[] parts = line.split("=");
            if (parts.length != 2) 
            {
                throw new IllegalArgumentException("Invalid config line: " + line);
            }
            String key = parts[0].trim();
            String value = parts[1].trim();
            switch (key) 
            {
                case "support.currencies":
                	config.setSupportCurrencies(Boolean.parseBoolean(value));
                    break;
                case "currencies.source":
                	config.setCurrenciesSource(value);
                    break;
                case "webservice.url":
                	config.setWebserviceUrl(value);
                    break;
                case "currency.file":
                	config.setCurrencyFile(value);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown config key: " + key);
            }
        }
        reader.close();
        return config;
    }
}
