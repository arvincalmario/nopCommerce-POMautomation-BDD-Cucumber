package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;

public class Steps extends Base{
		
	//LOGIN STEP DEFINITIONS
	
	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser()
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		lp = new LoginPage(driver);
	}
	
	@When("User opens URL {string}")
	public void user_opens_URL(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@When("User enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password)
	{
		lp.setUserName(email);
		lp.setPassword(password);
	}
	
	@When("Click on Login")
	public void click_on_Login()
	{
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
	
	//CUSTOMER STEP DEFINITIONS
	
	@Then("User can view Dashboard")
	public void user_can_view_dashboard() {		
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getTitlePage());
	}

	@When("User click on customers Menu")
	public void user_click_on_customers_menu() throws InterruptedException {
		Thread.sleep(2000);
		addCust.clickCustomerMenu();
	}

	@When("click on customer Menu Item")
	public void click_on_customer_menu_item() {
		addCust.clickCustomerMenuItem();
	}

	@When("click on Add new button")
	public void click_on_add_new_button() throws InterruptedException {
		addCust.clickAddNewCustomer();
		Thread.sleep(2000);
	}

	@Then("User can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getTitlePage());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
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
		addCust.clickSave();
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
		
	}

}
