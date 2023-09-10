package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	public WebDriver ldriver;	
	
	WaitHelper waitHelper;
	
	public SearchCustomerPage(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
		waitHelper = new WaitHelper(rdriver);
		
	}
	
	@FindBy(how = How.CSS, using = ".search-text")
	@CacheLookup
	WebElement searchIcon;
	
	@FindBy(how = How.CSS, using = "#SearchEmail")
	@CacheLookup
	WebElement searchInputEmail;
	
	@FindBy(how = How.CSS, using = "#SearchFirstName")
	@CacheLookup
	WebElement searchInputFirstName;
	
	@FindBy(how = How.CSS, using = "#SearchLastName")
	@CacheLookup
	WebElement searchInputLastName;
	
	@FindBy(how = How.CSS, using = "#SearchMonthOfBirth")
	@CacheLookup
	WebElement searchInputDateOfBirthMonth;
	
	@FindBy(how = How.CSS, using = "#SearchDayOfBirth")
	@CacheLookup
	WebElement searchInputDateOfBirthDay;
	
	@FindBy(how = How.CSS, using = "#SearchCompany")
	@CacheLookup
	WebElement searchInputCompanyName;
	
	@FindBy(how = How.CSS, using = ".dataTables_scroll")
	@CacheLookup
	WebElement tableSearchResults;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']")
	@CacheLookup
	WebElement table;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr")
	@CacheLookup
	List<WebElement> tableSearchResultsRow;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody/tr/td")
	@CacheLookup
	List<WebElement> tableSearchResultsColumns;
	
	@FindBy(how = How.CSS, using = "#search-customers")
	@CacheLookup
	WebElement searchButtonClick;
	
	public void searchIcon() {
		searchIcon.click();
	}
	
	public void setSearchEmail(String searchEmail) {
		waitHelper.WaitForElement(searchInputEmail, 30);		
		searchInputEmail.clear();
		searchInputEmail.sendKeys(searchEmail);
	}
	
	public void setSearchFirstName(String searchFirstName) {
		waitHelper.WaitForElement(searchInputFirstName, 30);		
		searchInputEmail.clear();
		searchInputEmail.sendKeys(searchFirstName);
	}
	
	public void setSearchLastName(String searchLastName) {
		waitHelper.WaitForElement(searchInputLastName, 30);
		
		searchInputLastName.clear();
		searchInputLastName.sendKeys(searchLastName);
	}
	
	public void clickSearch() {		
		searchButtonClick.click();
	}
	
	public int getNoOfRows() {
		return(tableSearchResultsRow.size());
	}
	
	public int getNoOfColumns() {
		return(tableSearchResultsColumns.size());
	}
	
	public boolean searchCustomerByEmail(String email)
	{
		boolean flag = false;
		
		for (int i = 1; i <= getNoOfRows(); i++) {
			
			String emailid = table.findElement(By.xpath("//table[@id='customers-grid']//tbody/tr["+ i +"]/td[2]")).getText();
			
			System.out.println(emailid);
			
			if (emailid.equals(email)) {
				
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
}
