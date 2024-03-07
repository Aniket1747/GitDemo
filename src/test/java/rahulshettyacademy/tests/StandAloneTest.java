package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponent.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandigPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalog;

public class StandAloneTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = "purchase")
	public void sudmitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub

		LandigPage page = launchApplication();

		ProductCatalog productCatalog = page.loginApplication(input.get("Email"), input.get("Password"));

		List<WebElement> products = productCatalog.getProductsList();
		System.out.println(products);
		productCatalog.addproductToCart(input.get("ProductName"));
		CartPage cart = productCatalog.goToCartPage();
		Boolean match = cart.verifyProductDisplay(input.get("ProductName"));
		Assert.assertTrue(match);

		CheckOutPage checkoutpage = cart.goToCheckOutPage();
		checkoutpage.selectCountry("india");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

		ConfirmationPage confirmationpage = checkoutpage.sumbitOrder();

		String message = confirmationpage.verifyConfirmationMessage();

		Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "sudmitOrder" })
	public void orderHistoryTest() {
		ProductCatalog productCatalog = page.loginApplication("aniket.r.pisal@gmail.com", "Avengers@123");
		OrderPage orderpage = productCatalog.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));
	}


	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJasonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) } };
	}
}
