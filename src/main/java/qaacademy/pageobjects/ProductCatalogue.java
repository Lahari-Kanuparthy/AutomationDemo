package qaacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qaacademy.abstractcomponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productList = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector(".ng-tns-c31-0");
	By spinner = By.cssSelector(".ng-animating");

	public void getProductList() {
		waitForElementToAppear(productList);
	}

	public WebElement getProductName(String productName) {

		WebElement product = products.stream().filter(
		prod -> prod.findElement(By.cssSelector("div div h5 b")).getText().equalsIgnoreCase(productName))
				.findAny().orElse(null);
		System.out.println(product);
		return product;
	}

	public void addProductToCart(String productName) throws InterruptedException {

		WebElement product = getProductName(productName);
		product.findElement(addToCart).click();
		waitForElementToDisappear();

	}

}
