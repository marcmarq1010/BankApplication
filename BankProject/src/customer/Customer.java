package customer;


public class Customer 
{
    // Three private instance variables to hold the customer's first name, last name, and social security number
    private String firstName;
    private String lastName;
    private String ssn;

    // Constructor that takes three arguments and initializes the corresponding instance variables
    public Customer(String firstName, String lastName, String ssn)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn; 
    }

    // Getter method to retrieve the customer's first name
    public String getFirstName() 
    {
        return firstName;
    }

    // Setter method to set the customer's first name
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    // Getter method to retrieve the customer's last name
    public String getLastName() 
    {
        return lastName;
    }

    // Setter method to set the customer's last name
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    // Getter method to retrieve the customer's social security number
    public String getSsn() 
    {
        return ssn;
    }

    // Setter method to set the customer's social security number
    public void setSsn(String ssn) 
    {
        this.ssn = ssn;
    }
}
