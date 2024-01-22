package qaacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qaacademy.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutElement;
	
	public boolean verifyProductDisplay(String productName) {
		
		boolean match = cartItems.stream().anyMatch(addedProduct -> addedProduct.getText().equals(productName));
		return match;
	}
	
	public CheckOutPage goToCheckOut() throws InterruptedException {
		
		waitForElementToDisappear();
		checkOutElement.click();
		return new CheckOutPage(driver);
	}
	
	
	
	
	
	

}
