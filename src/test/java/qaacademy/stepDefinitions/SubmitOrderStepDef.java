package qaacademy.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qaacademy.pageobjects.CartPage;
import qaacademy.pageobjects.CheckOutPage;
import qaacademy.pageobjects.ConfirmationPage;
import qaacademy.pageobjects.LandingPage;
import qaacademy.pageobjects.ProductCatalogue;
import qaacademy.testcomponents.BaseTest;

public class SubmitOrderStepDef extends BaseTest{
	
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage cnfrmPage;
	
	@Given("Landed on ecommerce page")
	public void landed_on_ecommerce_page() throws IOException {
	
		landingpage = launchApplication();
		
	}

	@Given("^Logged in with (.+) and (.+)$")
	public void logged_in_with_username_and_password(String username, String password ) {
	   
		productCatalogue = landingPage.loginApplication(username, password);
		productCatalogue.getProductList();
	}

	@When("^add the (.+) to the cart$")
	public void add_the_product_to_cart(String productName) throws InterruptedException {
		
		productCatalogue.addProductToCart(productName);
		
	}

	@When("^verify the (.+) in the cart and click submit$")
	public void verify_the_productname_in_the_cart_click_submit(String productName) throws InterruptedException {
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartPage.windowScrolling();
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("india");
		cnfrmPage = checkOutPage.placeOrder();
		cnfrmPage.windowScrolling();
	}

	@Then("{string} message is displayed in confirmation page")
	public void message_is_displayed_in_confirmation_page(String string) {
	  
		String cnfrmMsg = cnfrmPage.confirmationMessage();
		Assert.assertTrue(cnfrmMsg.equalsIgnoreCase(string));
		driver.close();
	}

}
