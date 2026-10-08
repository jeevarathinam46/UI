package com.ll.iod.page;

import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ll.iod.utils.SeleniumUtils;

public class HomePage {
	public WebDriver driver;

	@FindBy(xpath = "//button[@data-automation='create-loan-button']")
	public WebElement createNew;
	@FindBy(id = "first")
	public WebElement firstName;
	@FindBy(css = "input[placeholder='Last Name']")
	public WebElement lastName;
	@FindBy(id = "uli")
	public WebElement ULI;
	@FindBy(xpath = "//input[@type='file']")
	public WebElement uploadPDF;
	@FindBy(css = "button[class='iod-button__primary ng-star-inserted']")
	public WebElement submit;
	@FindBy(css = "button.iod-button__secondary.ng-star-inserted")
	public WebElement cancel;
	@FindBy(xpath = "//core-icon[@data-automation='settings-core-icon']")
	public WebElement settingsPage;
	@FindBy(xpath = "//core-icon[@data-automation='user-management-icon']")
	public WebElement accountPage;
	@FindBy(xpath = "//core-icon[@data-automation='change-theme-icon']")
	public WebElement changeTheme;
	@FindBy(xpath = "//core-icon[@data-automation='notifications-icon']")
	public WebElement notifications;
	@FindBy(xpath = "//core-icon[@icon='arrow_drop_down']")
	public WebElement userAccount;
	@FindBy(xpath = "//div[text()='Logout']")
	public WebElement userLogOut;
	@FindBy(xpath = "//core-icon[@icon='pipeline']")
	public WebElement pipeline;
	@FindBy(xpath = "//core-icon[@data-automation='filter-icon']")
	public WebElement filter;
	@FindBy(xpath = "//input[@data-automation='filter-input']")
	public WebElement filterText;
	@FindBy(xpath = "(//div[@data-automation='data-grid-col-content'])[1]//core-icon")
	public WebElement loanStatus;
	@FindBy(xpath = "(//div[@data-automation='data-grid-col-content'])[2]//core-icon")
	public WebElement diStatus;
	@FindBy(xpath = "(//div[@data-automation='data-grid-col-content'])[3]")
	public WebElement deStatus;
	@FindBy(xpath = "//iod-error-link[@column='doc-indexing']//div")
	public WebElement diExcept;
	@FindBy(xpath = "//iod-error-link[@column='data-extraction']//div")
	public WebElement deExcept;
	@FindBy(xpath = "//core-icon[@data-automation='filter-close-icon']")
	public WebElement filterClose;
	@FindBy(xpath = "//div[contains(text(),'Loan Status')]")
	public WebElement loanStatusHdr;
	@FindBy(xpath = "//div[contains(text(),'Doc Indexing Status')]")
	public WebElement docIndStatusHdr;
	@FindBy(xpath = "//div[contains(text(),' Data Extraction Status ')]")
	public WebElement datExtStatusHdr;
	@FindBy(xpath = "//div[text()=' Doc ']")
	public WebElement docExcHdr;
	@FindBy(xpath = "//div[text()=' Data ']")
	public WebElement datExcHdr;
	@FindBy(xpath = "//div[contains(text(),'First Name')]")
	public WebElement fNameSortHdr;
	@FindBy(xpath = "//div[contains(text(),'Last Name')]")
	public WebElement lNamesortHdr;
	@FindBy(xpath = "//div[contains(text(),'Uploaded')]")
	public WebElement uploadSortHdr;
	@FindBy(xpath = "//div[text()=' Indexing ']")
	public WebElement indexSortHdr;
	@FindBy(xpath = "//div[text()=' Extraction ']")
	public WebElement extSortHdr;
	@FindBy(xpath = "//div[@data-automation='tooltip']")
	private WebElement tooTipMessage;
	@FindBy(xpath = "//div[@data-automation='data-grid-col-content'][4]/div")
	private WebElement uli;
	@FindBy(xpath = "//input[@data-placeholder='Search']")
	private WebElement search;
	@FindBy(xpath = "(//mat-select[@role= 'combobox'])[1]")
	private WebElement combobox;
	@FindBy(xpath = "//span[contains(text(),'Tenant: ')]/following::span[2]")
	private WebElement defaultTenant;

	private ReportGenerator reportGenerator = null;
	public HomePage(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}
	public HomePage(WebDriver driver,ReportGenerator reportGenerator) {

		this.driver = driver;
		this.reportGenerator = reportGenerator;
		PageFactory.initElements(driver, this);

	}
public boolean usrLogOut(){
		SeleniumUtils.doClick(driver,userAccount);
		return SeleniumUtils.doClick(driver,userLogOut);
}
	public boolean createLoan(String fName, String lName, String uli, String fileName) {
		SeleniumUtils.doClick(driver, createNew);
		SeleniumUtils.sendKeys(driver, firstName, fName);
		SeleniumUtils.sendKeys(driver, lastName, lName);
		SeleniumUtils.sendKeys(driver, ULI, uli);
		CommonUtils.sleepForAWhile();
		uploadPDF.sendKeys(fileName);
		//SeleniumUtils.sendKeys(driver, uploadPDF, fileName);
		return SeleniumUtils.doClick(driver, submit);

	}
	public boolean chkHomePgIcons(boolean settPg,boolean accPg,boolean chgTh,boolean ntf,boolean crtLn){
		return ((SeleniumUtils.isDisplayed(driver, settingsPage, settPg))&&
				(SeleniumUtils.isDisplayed(driver, accountPage, accPg))&&
				(SeleniumUtils.isDisplayed(driver,changeTheme,chgTh))&&
		 		(SeleniumUtils.isDisplayed(driver,notifications,ntf))&&
				(SeleniumUtils.isDisplayed(driver, createNew, crtLn))
				);

	}
	public boolean searchULI(String uli){
		final boolean b = SeleniumUtils.doubleClick(driver, filter) &&
				SeleniumUtils.sendKeys(driver, filterText, uli);
		CommonUtils.sleepForAWhile(7000);
		return b;
	}
	//Pending, Not Started,Error
	public boolean verifyDiDeStatus(String uli,String lnSts,String diSts,String deSts,String diExp,String deExp){
		boolean b1;
		do{
			System.out.println("fltr Status"+searchULI(uli));

			  b1 = SeleniumUtils.doHover(driver, loanStatus) &&
					SeleniumUtils.verifyText(driver, tooTipMessage, lnSts);
			System.out.println("loan Status"+b1);
			driver.navigate().refresh();
		}while(!b1);
		searchULI(uli);
		final boolean b2 = SeleniumUtils.doHover(driver, diStatus) &&
				SeleniumUtils.verifyText(driver, tooTipMessage,diSts);

		System.out.println("di Status"+b2);
		final boolean b3 = SeleniumUtils.doHover(driver, deStatus) &&
				SeleniumUtils.verifyText(driver, tooTipMessage,deSts);
		System.out.println("de Status"+b3);
		final boolean b4 =SeleniumUtils.verifyText(driver,diExcept,diExp);
		final boolean b5=SeleniumUtils.verifyText(driver,deExcept,deExp);
		System.out.println(b4+" "+b5);
		//return(SeleniumUtils.verifyText(driver,diExcept,diExp)&&SeleniumUtils.verifyText(driver,deExcept,deExp));
		return b4;
	}

	public SettingPage clickOnSetting() {
		reportGenerator.logAndCaptureScreen("clicked on settings icon","clickOnSetting",SeleniumUtils.doClick(driver, settingsPage),driver);
		return new SettingPage(driver,reportGenerator);
	}
	public AccountPage clickOnAccount() {
		reportGenerator.logAndCaptureScreen("clicked on users icon","clickOnAccount",SeleniumUtils.doClick(driver, accountPage),driver);
		return new AccountPage(driver,reportGenerator);
	}
	public HomePage clickPipeline(){
		SeleniumUtils.doClick(driver,pipeline);
		return new HomePage(driver);
	}
	public void selecttenants(String partialtext, String tnt){
		if(SeleniumUtils.verifyText(driver,defaultTenant,tnt))
		{
		}
		else {

			WebElement filtertenant;
			SeleniumUtils.doClick(driver, combobox);
			SeleniumUtils.sendKeys(driver, search, partialtext);
			CommonUtils.sleepForAWhile(3000);
			filtertenant = driver.findElement(By.xpath("//span[contains(text(), '" + tnt + "')]"));
			SeleniumUtils.doubleClick(driver, filtertenant);
		}


	}
	public boolean searchAndVerifyUli(String uliref)
	{
		searchULI(uliref);
		return SeleniumUtils.verifyText(driver,uli,uliref);

	}

}