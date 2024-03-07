package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//span[@class='ng-star-inserted'])[2]")
	WebElement SelectCountry;

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submit;
	By results = By.xpath("//section[@class='ta-results list-group ng-star-inserted']");
	By submitEle=By.xpath("//a[@class='btnn action__submit ng-star-inserted']");

	public void selectCountry(String CountryName) {
		Actions action = new Actions(driver);
		action.sendKeys(country, CountryName).build().perform();
		waitForElementToAppear(results);
		SelectCountry.click();
	}

	public ConfirmationPage sumbitOrder() {
		waitForElementToBeClickable(submitEle);
		submit.click();
		ConfirmationPage confirmationpage=new ConfirmationPage(driver);
		return confirmationpage;

	}

}
