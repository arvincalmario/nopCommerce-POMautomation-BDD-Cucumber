Feature: Customers

Background: Below are the common steps for each scenario
    Given User Launch Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login"
    And User enters email as "admin@yourstore.com" and password as "admin"
    And Click on Login
    Then User can view Dashboard

@sanity
Scenario: Add a new Customer
    When User click on customers menu
    And click on customer Menu Item
    And click on Add new button 
    Then User can view Add new customer page
    When User enter customer info
    And click on Save button
    Then User can view confirmation message "The new customer has been added successfully"
    And close browser

@regression    
Scenario: Search Customer by EmailID
	When User click on customers menu
	And click on customer Menu Item
	And Enter customer email
	When Click on search button
	Then User should found Email in the Search table
	And close browser

@regression 	
Scenario: Search Customer by Name
	When User click on customers menu
	And click on customer Menu Item
	And Enter customer First Name
	And Enter customer Last Name
	When Click on search button
	Then User should found Name in the Search table
	And close browser