@tag
Feature: Purchasing an order from Ecommerce website

Background: 
	Given Landed on ecommerce page

  @tag2
  Scenario Outline: Submit the order and verifying confirmation message
    Given Logged in with <username> and <password>
    When add the <productName> to the cart
    And verify the <productName> in the cart and click submit
    Then "THANKYOU FOR THE ORDER." message is displayed in confirmation page

    Examples: 
      | username  				|  password 	 | productName  |
      | laharik@gmail.com |  Lahari@1996 | ZARA COAT 3 |
      