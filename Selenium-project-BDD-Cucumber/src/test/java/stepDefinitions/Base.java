package stepDefinitions;

import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;

public class Base {
	
	public WebDriver driver;
	public LoginPage lp;
	public AddCustomerPage addCust;
	
	//Generating random string for uniqueEmailID
	public static String randomString() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(5);
		return(generatedString1);
	}

}
