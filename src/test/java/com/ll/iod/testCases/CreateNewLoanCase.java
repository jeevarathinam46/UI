package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.page.HomePage;
import com.ll.iod.page.LoginPage;
import com.ll.iod.utils.CommonUtils;
import com.ll.iod.utils.ConfigPropertyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateNewLoanCase extends Base {
	LoginPage loginPage;
	HomePage homePage;
	WebDriver driver;
	SoftAssert softAssert;
	public CreateNewLoanCase() {
		super();
	}
	@BeforeMethod
	public void setUp() {
		softAssert = new SoftAssert();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void createLoan() {
		String url = ConfigPropertyLoader.getConfigValue("std");
		driver = openBrowser("chrome", url);
		/*opens the login page ------->moves to pipeline/home page ---->create user page*/
		loginPage = new LoginPage(driver);
		//homePage = loginPage.clickOnLoginButton(hashmap.get("username"), hashmap.get("password"));

		//String path = ClassLoader.getSystemResource("./sample.loans/BankStatements.pdf").getPath();
		//System.out.println(path);
		homePage = loginPage.clickOnLoginButton("anandhan", "P@ssw0rd");
		homePage.createLoan("anandhan", "s", CommonUtils.getUniqueText(),System.getProperty("user.dir") +"\\src\\test\\java\\com\\ll\\iod\\sample\\loans\\BankStatements.pdf");
		CommonUtils.sleepForAWhile();
	}
}
