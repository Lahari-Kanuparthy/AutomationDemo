package qaacademy.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import qaacademy.pageobjects.CartPage;
import qaacademy.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "*[routerlink*='cart']")
	WebElement goToCart;
	@FindBy(css = "*[routerlink*='myorders']")
	WebElement goToOrders;

	public void waitForElementToAppear(By findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToDisappear() throws InterruptedException {

		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

	public void waitForWebElementToAppear(WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public CartPage goToCartPage() {
		goToCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}

	public OrdersPage goToOrderPage() {

		goToOrders.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}

	public void windowScrolling() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(9000,9000)");
	}
}
