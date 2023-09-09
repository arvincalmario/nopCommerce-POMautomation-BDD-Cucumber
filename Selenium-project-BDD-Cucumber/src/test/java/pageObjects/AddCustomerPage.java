package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {
	
	public WebDriver ldriver;
	
	public AddCustomerPage(WebDriver rdriver) {
		
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	By lnkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By lnkCustomers_menuItem = By.cssSelector("a[href='/Admin/Customer/List']");
	
	By btnAddNewCustomer = By.cssSelector(".btn.btn-primary[href='/Admin/Customer/Create']");
	By inputEmail = By.xpath("//input[@id='Email']");
	By inputPassword = By.xpath("//input[@id='Password']");
	By inputFirstName = By.xpath("//input[@id='FirstName']");
	By inputLastName = By.xpath("//input[@id='LastName']");
	
	By clickGenderMale = By.xpath("//input[@id='Gender_Male']");
	By clickGenderFemale = By.xpath("//input[@id='Gender_Female']");
	
	By dateOfBirth = By.xpath("//input[@id='DateOfBirth']");
	By inputCompanyName = By.xpath("//input[@id='Company']");
	By clickTaxExempt = By.xpath("//input[@id='IsTaxExempt']");
	
	By clickNewsLetter = By.xpath("//div[@class='input-group-append']//div[@role='listbox']");
	By newsLetterStore1 = By.xpath("//li[contains(text(),'Your store name')]");
	By newsLetterStore2 = By.xpath("//li[contains(text(),'Test store 2')]");
	
	By clickCustomerRoles = By.xpath("//div[@class='input-group-append input-group-required']//div[@role='listbox']");
	By customerRoleListAdministrator = By.xpath("//li[contains(text(),'Administrators')]");
	By customerRoleListRegistered = By.xpath("//li[contains(text(),'Registered')]");
	By customerRoleListGuests = By.xpath("//li[contains(text(),'Guests')]");
	By customerRoleListVendors = By.xpath("//li[contains(text(),'Vendors')]");
	By customerRoleListForumModerators = By.xpath("//li[contains(text(),'Forum Moderators')]");
	
	By clickManagerOfVendor = By.cssSelector("#VendorId");
	By managerOfVendorVendor1 = By.cssSelector("select[id='VendorId'] option[value='1']");
	By managerOfVendorVendor2 = By.cssSelector("select[id='VendorId'] option[value='2']");
	
	
	By clickActive = By.xpath("//input[@id='Active']");
	By inputAdminComment = By.cssSelector("#AdminComment");
	
	By clickSave = By.cssSelector("button[name='save']");
	By clickSaveAndContinueEdit = By.cssSelector("button[name='save-continue']");
	
	
	
	//ACTION METHODS
	
	public String getTitlePage() {		
		return ldriver.getTitle();
	}
	
	public void clickCustomerMenu() {		
		ldriver.findElement(lnkCustomers_menu).click();
	}
	
	public void clickCustomerMenuItem() {		
		ldriver.findElement(lnkCustomers_menuItem).click();
	}
	
	public void clickAddNewCustomer() {		
		ldriver.findElement(btnAddNewCustomer).click();
	}
	
	public void setEmail(String email) {		
		ldriver.findElement(inputEmail).sendKeys(email);
	}
	
	public void setPassword(String password) {		
		ldriver.findElement(inputPassword).sendKeys(password);
	}
	
	public void setFirstName(String firstName) {		
		ldriver.findElement(inputFirstName).sendKeys(firstName);
	}
	
	public void setLastName(String LastName) {		
		ldriver.findElement(inputLastName).sendKeys(LastName);
	}
	
	public void setCustomerRoles(String role) throws InterruptedException {
		
		if(!role.equals("Vendors")) {
			ldriver.findElement(By.xpath("(//span[@title='delete'])[1]")).click();
		}
		
		ldriver.findElement(clickCustomerRoles).click();
		WebElement listitem;
		Thread.sleep(3000);
		
		if (role.equals("Administrators")) {
			listitem = ldriver.findElement(customerRoleListAdministrator);
		} 
		else if (role.equals("Registered")){
			listitem = ldriver.findElement(customerRoleListRegistered);
		}
		else if (role.equals("Guests")){
			listitem = ldriver.findElement(customerRoleListGuests);
		}
		else if (role.equals("Vendors")){
			listitem = ldriver.findElement(customerRoleListVendors);
		}
		else {
			listitem = ldriver.findElement(customerRoleListForumModerators);
		}		
		
		//listitem.click();	
		
		JavascriptExecutor js = (JavascriptExecutor)ldriver;
		js.executeScript("arguments[0].click();", listitem);
	}
	
	public void setManagerOfVendor(String value) {		
		Select drp = new Select(ldriver.findElement(clickManagerOfVendor));
		drp.selectByVisibleText(value);
	}
	
	public void setGender(String gender) {		
		if (gender.equals("Male")) {
			ldriver.findElement(clickGenderMale).click();
		}
		else if (gender.equals("Female")) {
			ldriver.findElement(clickGenderFemale).click();
		}
		else {
			ldriver.findElement(clickGenderMale); //Default
		}
	}
	
	public void setDateOfBirth(String dob) {
		ldriver.findElement(dateOfBirth).sendKeys(dob);
	}
	
	public void setCompanyName(String companyName) {
		ldriver.findElement(inputCompanyName).sendKeys(companyName);
	}
	
	public void setAdminComment(String adminComment) {
		ldriver.findElement(inputAdminComment).sendKeys(adminComment);
	}
	
	public void clickSave() {
		ldriver.findElement(clickSave).click();
	}
	
	

	
}
