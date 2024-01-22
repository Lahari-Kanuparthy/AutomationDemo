package qaacademy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import qaacademy.pageobjects.CartPage;
import qaacademy.pageobjects.ProductCatalogue;
import qaacademy.testcomponents.BaseTest;
import qaacademy.testcomponents.Retry;

public class ErrorValidation extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() {

		landingPage.loginApplication("lahari@gmail.com", "Lahari@1996");
		Assert.assertEquals("Incoorrect email or password.", landingPage.errorMessage());

	}
	
	@Test
	public void productErrorDisplay() throws InterruptedException {
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("laharik@gmail.com", "Lahari@1996");
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
	}

}
