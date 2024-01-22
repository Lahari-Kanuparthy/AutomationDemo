package qaacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qaacademy.abstractcomponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css=".ta-item:nth-of-type(2)")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	
	public void selectCountry(String countryName) {
		
		Actions act = new Actions(driver);
		act.sendKeys(selectCountry, countryName).build().perform();
		country.click();
	}
	
	public ConfirmationPage placeOrder() {
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	

}
