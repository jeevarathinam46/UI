package com.ll.iod.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ll.iod.utils.SeleniumUtils;

public class ForgotPasswordPage {
	WebDriver driver;

	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@id='username']")
	private WebElement username;
	@FindBy(xpath="//button[@type='submit']")
	private WebElement reset;
	@FindBy(xpath="//p[@id='errorMessage']")
	private WebElement resetError;
	@FindBy(xpath="//input[@name='code']")
	private WebElement code;
	@FindBy(xpath="//input[@name='password']")
	private WebElement npassword;
	@FindBy(xpath="//input[@name='confirmPassword']")
	private WebElement cpassword;
	public String clickInvalidReset(String userName) {
		SeleniumUtils.sendKeys(driver, username, userName);
		SeleniumUtils.doClick(driver, reset);
		if (userName == "")
			return username.getAttribute("validationMessage");
		else
			return resetError.getText();

	}
	public void clickValidReset(String userName,String Code,String pass, String cpass) {
		SeleniumUtils.sendKeys(driver, username, userName);
		SeleniumUtils.doClick(driver, reset);
		SeleniumUtils.sendKeys(driver, code, Code);
		SeleniumUtils.sendKeys(driver, npassword, pass);
		SeleniumUtils.sendKeys(driver, cpassword, cpass);
	}
}
