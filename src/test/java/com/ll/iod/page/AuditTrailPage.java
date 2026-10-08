package com.ll.iod.page;

import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.CommonUtils;
import com.ll.iod.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AuditTrailPage {
    WebDriver driver;
    ReportGenerator reportGenerator;

    public AuditTrailPage(WebDriver driver,ReportGenerator reportGenerator) {
        this.driver = driver;
        this.reportGenerator=reportGenerator;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//div[@data-automation='audit-trail-title']")
    private WebElement adtTtl;
    @FindBy(xpath="//label[@for='uli']")
    private WebElement ref;
    @FindBy(xpath ="//input[@data-automation='audit-trail-uli-input']" )
    private WebElement adtIp;
    @FindBy(xpath = "//button[@data-automation='audit-trail-generate-csv']")
    private WebElement gntCsv;
    @FindBy(xpath = "//core-icon[@icon='blocked-error']")
    private WebElement adtTrlError;
    @FindBy(xpath = "//div[@data-automation='tooltip']")
    private WebElement tooTipMessage;
    @FindBy(xpath = "//div[@data-automation='snack-bar-message']")
    private WebElement confirmMessage;
    @FindBy(xpath = "//button[@data-automation='snack-bar-close']")
    private WebElement dismissConfirmMessage;
    @FindBy(xpath = "//core-icon[@data-automation='pipeline-clarifi-icon']")
    public WebElement pipeline;
    public HomePage clickPipeline(){
        reportGenerator.logAndCaptureScreen("navigating to IoD pipeline page","clickPipeline",SeleniumUtils.doClick(driver,pipeline),driver);
        return new HomePage(driver);
    }


    public boolean vrfyDftAdtPg(String msg,String msg1,String msg2){
        return(
                (SeleniumUtils.verifyText(driver,adtTtl,msg))&&
                        (SeleniumUtils.verifyText(driver,ref,msg1))&&
                        (SeleniumUtils.verifyText(driver,adtIp,"placeholder",msg2))&&
                        (SeleniumUtils.isEnabled(driver,gntCsv,false))
                );
    }
    public boolean entUli(String uli){
        return SeleniumUtils.sendKeys(driver,adtIp,uli);
    }
    public boolean verifyAdtrlError(String uli,String message) {
        boolean res= ((SeleniumUtils.sendKeys(driver,adtIp,uli))&&
                (SeleniumUtils.doHover(driver, adtTrlError))&&(SeleniumUtils.verifyText(driver, tooTipMessage, message)));
        reportGenerator.logAndCaptureScreen("validating error message","hover error msg",res,driver);
                return (SeleniumUtils.isEnabled(driver,gntCsv,false));
    }
    public boolean verifyInvdRef(String uli,String msg){
        return((SeleniumUtils.sendKeys(driver,adtIp,uli))&&
                (SeleniumUtils.isEnabled(driver,gntCsv,true))&&
                (SeleniumUtils.doClick(driver,gntCsv))&&
                (SeleniumUtils.verifyText(driver, confirmMessage, msg))
                        && (SeleniumUtils.doClick(driver, dismissConfirmMessage))
                );
    }
    public boolean verifyVldRef(String uli,String msg){

        boolean pre=((SeleniumUtils.sendKeys(driver,adtIp,uli))&&
                (SeleniumUtils.isEnabled(driver,gntCsv,true))&&
                (SeleniumUtils.doClick(driver,gntCsv)));
        CommonUtils.sleepForAWhile();
        boolean post=((SeleniumUtils.doHover(driver, adtTrlError))&&(SeleniumUtils.verifyText(driver, tooTipMessage, msg)));

        return (pre&&post);
    }

}
