package com.ll.iod.page;

import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DataExtractionPage {
    WebDriver driver;
    ReportGenerator reportGenerator;
    public DataExtractionPage(WebDriver driver,ReportGenerator reportGenerator) {
        this.driver = driver;
        this.reportGenerator=reportGenerator;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[text()='Enable Auto Data Extraction']")
    private WebElement deDsr;
    @FindBy(xpath = "//div[@data-automation='data-extraction-title']")
    private WebElement deTle;
    @FindBy(xpath = "//core-switch[@data-automation='data-extraction-toggle']")
    private WebElement deToggle;
    @FindBy(xpath = "//core-switch[@data-automation='data-extraction-toggle']//span")
    private WebElement deOnOff;
    @FindBy(xpath = "//div[@data-automation='confirmation-dialog-title']")
    private WebElement confTle;
    @FindBy(xpath = "//core-icon[@data-automation='close-confirmation-modal']")
    private WebElement confClose;
    @FindBy(xpath = "//div[@data-automation='confirmation-dialog-message']")
    private WebElement confMsg;
    @FindBy(xpath = "//button[@data-automation='confirmation-modal-deny-button']")
    private WebElement confCancel;
    @FindBy(xpath = "//button[@data-automation='confirmation-modal-confirmation-button']")
    private WebElement confBtn;
    @FindBy(xpath = "//core-icon[@data-automation='snack-bar-icon']")
    private WebElement snkIcn;
    @FindBy(xpath = "//div[@data-automation='snack-bar-message']")
    private WebElement snkMsg;
    @FindBy(xpath = "//button[@data-automation='snack-bar-close']")
    private WebElement snkCls;
    @FindBy(xpath = "//core-icon[@data-automation='pipeline-clarifi-icon']")
    public WebElement pipeline;
public boolean vrfyDft(){
    return SeleniumUtils.verifyText(driver,deTle,"Data Extraction")&&
            SeleniumUtils.verifyText(driver,deDsr,"Enable Auto Data Extraction");
}
public boolean clkTgle(){
    return  SeleniumUtils.doClick(driver,deToggle)&&
            SeleniumUtils.verifyText(driver,confTle,"Auto DataExtraction Configuration Changed")&&
            SeleniumUtils.isDisplayed(driver,confClose,true)&&
            SeleniumUtils.verifyText(driver,confMsg,"Changes will be applied to the Auto data extraction configuration.\n" +
                    "Do you want to continue?")&&
            SeleniumUtils.isDisplayed(driver,confCancel,true)&&
            SeleniumUtils.isDisplayed(driver,confBtn,true)&&
            SeleniumUtils.doClick(driver,confBtn)&&
            SeleniumUtils.isDisplayed(driver,snkIcn,true)&&
            SeleniumUtils.verifyText(driver,snkMsg,"Auto data Extraction Configured")&&
            SeleniumUtils.doClick(driver,snkCls);
}
public boolean enableDE(String status){
    boolean flag;
   if(SeleniumUtils.getValue(driver,deOnOff).equalsIgnoreCase(status)){
      flag=true;
   }else{
      flag=clkTgle();
   }

   return flag;
}
    public HomePage clickPipeline(){
    reportGenerator.logAndCaptureScreen("navigating to IoD pipeline page","clickPipeline",SeleniumUtils.doClick(driver,pipeline),driver);
        return new HomePage(driver);
    }

}
