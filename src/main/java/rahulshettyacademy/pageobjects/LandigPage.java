package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandigPage extends AbstractComponent {

	WebDriver driver;

	public LandigPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement username;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement Submit;

	@FindBy(css = "[class*='FlyInOut']")
	WebElement ErrorMessage;

	public ProductCatalog loginApplication(String email, String password) {
		username.sendKeys(email);
		passwordEle.sendKeys(password);
		Submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}

	public String getErrorMessage() {
		waitForWebElementToAppear(ErrorMessage);
		return ErrorMessage.getText();

	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
