package rahulshettyacademy.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponent.BaseTest;

public class ErrorValidations extends BaseTest {
	@Test(groups={"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException {

		page.loginApplication("aniket.r.pisal@gmail.com", "Avengers@1234");
		Assert.assertEquals("Incorrect email or password.", page.getErrorMessage());
	}
}
