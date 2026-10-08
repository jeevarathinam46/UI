package com.ll.iod.page;

import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TenantRegsPage {
    public WebDriver driver;
    public ReportGenerator reportGenerator;

    public TenantRegsPage(WebDriver driver,ReportGenerator reportGenerator) {
        this.driver = driver;
        this.reportGenerator=reportGenerator;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//input[@formcontrolname='companyName']")
    private WebElement cmpnyNm;

    @FindBy(xpath = "//input[@formcontrolname='webAddress']")
    private WebElement wbAdd;

    @FindBy(xpath = "//input[@formcontrolname='streetAddress']")
    private WebElement strtAdd;

    @FindBy(xpath = "//input[@formcontrolname='city']")
    private WebElement cty;

    @FindBy(xpath = "//input[@formcontrolname='zipCode']")
    private WebElement zpCd;
    @FindBy(xpath = "//mat-select[@role='combobox']")
    private WebElement state;
    @FindBy(xpath = "(//input[@formcontrolname='phoneNumber'])[1]")
    private WebElement phnNo;

    @FindBy(xpath = "//textarea[@formcontrolname='comments']")
    private WebElement cmmnts;

    @FindBy(xpath = "//input[@formcontrolname='firstName']")
    private WebElement fstNm;

    @FindBy(xpath = "//input[@formcontrolname='lastName']")
    private WebElement lstNm;

    @FindBy(xpath = "(//input[@formcontrolname='phoneNumber'])[2]")
    private WebElement phnNum;

    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement email;
    @FindBy(xpath = "//button[text()[normalize-space() = 'SUBMIT']]")
    private WebElement submit;

    public void enterDtlls(String cmpnyNme,String strtAddr,String city,String zipCd,String ste,String phneNo,String cmments,String fstNme,String lstNme,String phnNumr,String cEmail) {
        SeleniumUtils.sendKeys(driver, cmpnyNm, cmpnyNme);
        SeleniumUtils.sendKeys(driver, strtAdd, strtAddr);
        SeleniumUtils.sendKeys(driver, cty, city);
        SeleniumUtils.sendKeys(driver, zpCd, zipCd);
        SeleniumUtils.doClick(driver, state);
        if (ste != null) {
            List<WebElement> e = driver.findElements(By.xpath("//mat-option[@role='option']/span"));
            for (WebElement ele : e) {
                if ((ele.getText()).equalsIgnoreCase(ste)) {
                    SeleniumUtils.doClick(driver, ele);
                    break;
                }
            }
        }
        SeleniumUtils.sendKeys(driver, phnNo, phneNo);
        SeleniumUtils.sendKeys(driver, fstNm, fstNme);
        SeleniumUtils.sendKeys(driver, lstNm, lstNme);
        SeleniumUtils.sendKeys(driver, phnNum, phnNumr);
        SeleniumUtils.sendKeys(driver, email, cEmail);
        SeleniumUtils.sendKeys(driver, cmmnts, cmments);
        // System.out.println(SeleniumUtils.isEnabled(driver,submit));
        if (SeleniumUtils.isEnabled(driver, submit)) {
            reportGenerator.logAndCaptureScreen("submit button enabled", "fill details", SeleniumUtils.isEnabled(driver, submit), driver);
            reportGenerator.logAndCaptureScreen("post submission", "fill details", SeleniumUtils.doClick(driver, submit), driver);
        } else {
        List<WebElement> err = driver.findElements(By.xpath("//div[@class='tooltip']"));
        List<WebElement> errmsg = driver.findElements(By.xpath("//div[@class='tooltip']/div"));
        for (int i = 0; i < err.size(); i++) {

            for (int j = i; j < errmsg.size(); j++) {
                boolean flag = SeleniumUtils.doHover(driver, err.get(i)) && SeleniumUtils.verifyText(driver, errmsg.get(i), "Mandatory field");
                reportGenerator.logAndCaptureScreen("validating error pop ups", "enterDtlls", flag, driver);
                break;
            }
        }
    }
    }

}
