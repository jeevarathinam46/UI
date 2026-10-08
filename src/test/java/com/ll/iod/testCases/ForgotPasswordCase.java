package com.ll.iod.testCases;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ll.iod.base.Base;
import com.ll.iod.page.ForgotPasswordPage;
import com.ll.iod.page.LoginPage;

public class ForgotPasswordCase extends Base {
	LoginPage loginPage;
	ForgotPasswordPage forgotPage;
	WebDriver driver;

	public ForgotPasswordCase() {
		super();
	}

	@BeforeMethod
	public void setup() {
		driver = openBrowser("chrome","https://stdqaautomation.qa.loanhdsandbox.com/");
		loginPage = new LoginPage(driver);
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}

	}

	@Test
	public void InvalidForgotPass(ITestContext it) throws InterruptedException {
		forgotPage = loginPage.moveToForgotPassword();
		System.out.println(forgotPage.clickInvalidReset(" "));
		System.out.println(forgotPage.clickInvalidReset(""));
		it.getAttribute("hashmap");
	}

	@Test
	public void validForgotPass() throws InterruptedException {
		forgotPage = loginPage.moveToForgotPassword();
		forgotPage.clickValidReset("anandsa", "2fd", "Passw0rd", "Passw0rd");
	}

}
