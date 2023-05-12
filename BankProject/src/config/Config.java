package config;

public class Config 
{
    private boolean supportCurrencies;
    private String currenciesSource;
    private String webserviceUrl;
    private String currencyFile;

    public boolean isSupportCurrencies()
    {
        return supportCurrencies;
    }

    public void setSupportCurrencies(boolean supportCurrencies) 
    {
        this.supportCurrencies = supportCurrencies;
    }

    public String getCurrenciesSource() 
    {
        return currenciesSource;
    }

    public void setCurrenciesSource(String currenciesSource) 
    {
        this.currenciesSource = currenciesSource;
    }

    public String getWebserviceUrl()
    {
        return webserviceUrl;
    }

    public void setWebserviceUrl(String webserviceUrl) 
    {
        this.webserviceUrl = webserviceUrl;
    }

    public String getCurrencyFile()
    {
        return currencyFile;
    }

    public void setCurrencyFile(String currencyFile) 
    {
        this.currencyFile = currencyFile;
    }
}
