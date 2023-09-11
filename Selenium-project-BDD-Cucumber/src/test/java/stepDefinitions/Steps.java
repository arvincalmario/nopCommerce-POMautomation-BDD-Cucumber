package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends Base{
		
	@Before
	public void setup() throws IOException {
		
		logger = Logger.getLogger("nopCommerce"); //Added logger
		PropertyConfigurator.configure("log4j.properties"); //Added logger
		
		//Reading Properties
		configProperties = new Properties();
		FileInputStream configPropfile = new FileInputStream("config.properties");
		configProperties.load(configPropfile);
				
		String br = configProperties.getProperty("browser");
		
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configProperties.getProperty("chromepath"));
			driver = new ChromeDriver();
		}else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProperties.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		}else {
			System.setProperty("webdriver.edge.driver", configProperties.getProperty("edgepath"));
			driver = new EdgeDriver();
		}
			
		
		
		logger.info("******* Launching Browser *******");
		
	}
	
	//LOGIN STEP DEFINITIONS
	
	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser()
	{
		lp = new LoginPage(driver);
	}
	
	@When("User opens URL {string}")
	public void user_opens_URL(String url)
	{
		logger.info("******* Opening URL *******");
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@When("User enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password)
	{
		logger.info("******* Providing email and password *******");
		lp.setUserName(email);
		lp.setPassword(password);
	}
	
	@When("Click on Login")
	public void click_on_Login()
	{
		logger.info("******* Triggering Log in button *******");
		lp.clickLogin();
	}
	
	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title)
	{
		if(driver.getPageSource().contains("Login was unsuccessful")) {
			driver.close();
			Assert.assertTrue(false);
		} else {
			Assert.assertEquals(title, driver.getTitle());
		}
	}
	
	@When("User click on Log out link")
	public void user_click_on_Log_out_link() throws InterruptedException
	{
		lp.clickLogout();
		Thread.sleep(3000);
	}
	
	@Then("close browser")
	public void close_browser()
	{
		driver.quit();
	}
	
	//ADD CUSTOMER STEP DEFINITIONS
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {		
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getTitlePage());
	}

	@When("User click on customers menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(1000);
		addCust.clickCustomerMenu();
	}

	@When("click on customer Menu Item")
	public void click_on_customer_menu_item() {
		addCust.clickCustomerMenuItem();
	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		logger.info("******* Trigger Add customer button *******");
		addCust.clickAddNewCustomer();
		Thread.sleep(1000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getTitlePage());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		logger.info("******* Adding new customer information *******");
		String email = randomString() + "@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		addCust.setFirstName("Fname ACA");
		addCust.setLastName("Lname ACA");
		addCust.setGender("Female");
		addCust.setDateOfBirth("12/25/1991"); // MM/DD/YYYY
		addCust.setCompanyName("QA Automation");
		addCust.setCustomerRoles("Guests");
		addCust.setManagerOfVendor("Vendor 2");					
	}

	@When("click on Save button")
	public void click_on_save_button() {
		logger.info("******* Triggering save button *******");
		addCust.clickSave();
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
	}
	
	//SEARCH CUSTOMER EMAIL STEP DEFINITIONS
		
	@When("Enter customer email")
	public void enter_customer_email() {
		logger.info("******* Searching customer email *******");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setSearchEmail("victoria_victoria@nopCommerce.com");

	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
	    searchCust.clickSearch();
	    Thread.sleep(2000);
	}

	@Then("User should found Email in the Search table")
	public void user_should_found_email_in_the_search_table() {
	    
		boolean status = searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");		
		Assert.assertEquals(true, status);
	}
	
	//SEARCH CUSTOMER NAME STEP DEFINITIONS
	
	@When("Enter customer First Name")
	public void enter_customer_first_name() {
		logger.info("******* Searching customer first name *******");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setSearchFirstName("Victoria");

	}
	@When("Enter customer Last Name")
	public void enter_customer_last_name() {
		logger.info("******* Searching customer last name *******");
		searchCust.setSearchLastName("Terces");

	}
	@Then("User should found Name in the Search table")
	public void user_should_found_name_in_the_search_table() {
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);

	}





}
