package qaacademy.tests;

import java.io.IOException;
import qaacademy.testcomponents.Retry;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import qaacademy.pageobjects.CartPage;
import qaacademy.pageobjects.CheckOutPage;
import qaacademy.pageobjects.ConfirmationPage;
import qaacademy.pageobjects.OrdersPage;
import qaacademy.pageobjects.ProductCatalogue;
import qaacademy.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productname = "ZARA COAT 3";
	String countryName = "india";

	@Test(dataProvider = "getData", groups = { "Purchase" }, retryAnalyzer=Retry.class)
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		cartPage.windowScrolling();
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry(countryName);
		ConfirmationPage cnfrmPage = checkOutPage.placeOrder();
		cnfrmPage.windowScrolling();
		String cnfrmMsg = cnfrmPage.confirmationMessage();
		Assert.assertTrue(cnfrmMsg.equalsIgnoreCase("Thankyou for the order."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistory() {

		ProductCatalogue productCatalogue = landingPage.loginApplication("laharik@gmail.com", "Lahari@1996");
		OrdersPage ordersPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productname));

	}
	

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\qaacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}

//HashMap<String, String> map = new HashMap<String, String>();
//map.put("email", "laharik@gmail.com");
//map.put("password", "Lahari@1996");
//map.put("productName", "ADIDAS ORIGINAL");
//
//HashMap<String, String> map1 = new HashMap<String, String>();
//map1.put("email", "vamsik@gmail.com");
//map1.put("password", "Vamsi@1996");
//map1.put("productName", "ZARA COAT 3");
