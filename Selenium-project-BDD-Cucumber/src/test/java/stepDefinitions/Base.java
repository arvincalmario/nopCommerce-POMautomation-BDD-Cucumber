package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Base {
	
	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	public SearchCustomerPage searchCust;
	public static Logger logger;
	public Properties configProperties;
	
	//Generating random string for uniqueEmailID
	public static String randomString() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return(generatedString1);
	}

}
