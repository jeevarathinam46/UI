package com.ll.iod.page;

import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.CommonUtils;
import com.ll.iod.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


	@FindBy(xpath = "(//input[@id='signInFormUsername'])[2]")
	public WebElement userName;
	@FindBy(xpath = "(//input[@id='signInFormPassword'])[2]")
	public WebElement password;
	@FindBy(xpath = "(//input[@name='signInSubmitButton'])[2]")
	public WebElement submit;
	@FindBy(xpath = "(//p[@id='loginErrorMessage'])[2]")
	public WebElement loginError;
	@FindBy(xpath = "(//a[@href])[2]")
	public WebElement forgotPassword;
	private  WebDriver driver = null;
	private ReportGenerator reportGenerator = null;
	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	public LoginPage(WebDriver driver,ReportGenerator reportGenerator) {

		this.driver = driver;
		this.reportGenerator = reportGenerator;
		PageFactory.initElements(driver, this);

	}

	public String invalidCredLogin(String emailText, String passwordText) {
		boolean res=SeleniumUtils.sendKeys(driver, userName, emailText)&&SeleniumUtils.sendKeys(driver, password, passwordText);
		reportGenerator.logAndCaptureScreen("Entered UserName and password","invalidCredLogin",res,driver);
		reportGenerator.logAndCaptureScreen("Clicking on submit button","invalidCredLogin",SeleniumUtils.doClick(driver, submit),driver);
		if (emailText == "")
			return userName.getAttribute("validationMessage");
		else if (passwordText == "")
			return password.getAttribute("validationMessage");
		else
			return loginError.getText();

	}
	public ForgotPasswordPage moveToForgotPassword() {
		SeleniumUtils.doClick(driver, forgotPassword);
		return new ForgotPasswordPage(driver);
	}


	public HomePage clickOnLoginButton(String emailText,String passwordText) {
		boolean res=SeleniumUtils.sendKeys(driver, userName, emailText)&&SeleniumUtils.sendKeys(driver, password, passwordText);
		reportGenerator.logAndCaptureScreen("Filling username and password details", "clickOnLoginButton",res, driver);;
		//reportGenerator.
		//reportGenerator.captureScreenShot(driver);
		//SeleniumUtils.doClick(driver, submit);
		reportGenerator.logAndCaptureScreen("Post submitting login details", "clickOnLoginButton",
				SeleniumUtils.doClick(driver, submit), driver);
		CommonUtils.sleepForAWhile();
		return new HomePage(driver,reportGenerator);

	}

}
