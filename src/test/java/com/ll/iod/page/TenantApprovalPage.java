package com.ll.iod.page;

import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.CommonUtils;
import com.ll.iod.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class TenantApprovalPage {
    public WebDriver driver;
    ReportGenerator reportGenerator;
    public TenantApprovalPage(WebDriver driver,ReportGenerator reportGenerator) {
        this.reportGenerator=reportGenerator;
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//core-icon[@data-automation='filter-icon']")
    private WebElement clkFlt;

    @FindBy(xpath = "//input[@data-automation='filter-input']")
    private WebElement entTnt;

    @FindBy(xpath = "//core-icon[@icon='more_vert']")
    private WebElement clkOpt;

    @FindBy(xpath = "//button[text()[normalize-space() = 'Cancel']]")
    private WebElement cancelApp;
    @FindBy(xpath = "//button[text()[normalize-space() = 'Confirm']]")
    private WebElement confApp;
    @FindBy(xpath = "//button[text()[normalize-space() = 'CONFIRM']]")
    private WebElement confDeny;
    @FindBy(xpath = "//button[text()[normalize-space() = 'CANCEL']]")
    private WebElement cancelDny;
    @FindBy(xpath = "//textarea[@maxlength='500']")
    private WebElement denyText;


    @FindBy(xpath = "//button[text()[normalize-space() = 'CLOSE']]")
    private WebElement closeCommnt;
   @FindBy(xpath = "//core-icon[@data-automation='filter-close-icon']")
    private WebElement fltCls;
    public void sltOpt(String tnt,String opt){
        if(driver.findElement(By.xpath("//core-icon[@data-automation='filter-close-icon']")).isDisplayed()){
          SeleniumUtils.doClick(driver,fltCls);
       }
        SeleniumUtils.doClick(driver,clkFlt);
        SeleniumUtils.sendKeys(driver,entTnt,tnt);
        SeleniumUtils.doClick(driver,clkOpt);
        List<WebElement> e=driver.findElements(By.xpath("//mat-option/span"));
        for(WebElement ele:e) {
            if(ele.getText().equalsIgnoreCase(opt)) {
               reportGenerator.logAndCaptureScreen("selecting options","sltOpt",SeleniumUtils.doClick(driver,ele),driver);
                break;
            }
        }
    }
    public void tntApproveDny(String tnt){
        sltOpt(tnt,"approve");
        CommonUtils.sleepForAWhile();
        reportGenerator.logAndCaptureScreen("tnt approval cancel button","tntApproveConf",SeleniumUtils.doClick(driver,cancelApp),driver);
        CommonUtils.sleepForAWhile();
    }
    public void tntApproveConf(String tnt){
        sltOpt(tnt,"approve");
        CommonUtils.sleepForAWhile();
        reportGenerator.logAndCaptureScreen("tnt approval confirm button","tntApproveConf",SeleniumUtils.doClick(driver,confApp),driver);
        CommonUtils.sleepForAWhile();
    }
    public void tntDenyConf(String tnt){
        sltOpt(tnt,"deny");
        CommonUtils.sleepForAWhile();
        SeleniumUtils.sendKeys(driver,denyText,"data inadequate");
        reportGenerator.logAndCaptureScreen("tenant approval denied","tntDenyConf",SeleniumUtils.doClick(driver,confDeny),driver);
        CommonUtils.sleepForAWhile();
    }
    public void tntComment(String tnt){
        CommonUtils.sleepForAWhile();
        sltOpt(tnt,"comments");
        CommonUtils.sleepForAWhile();
        reportGenerator.logAndCaptureScreen("closing comments","tntComment",SeleniumUtils.doClick(driver,closeCommnt),driver);
    }

}
