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

public class ConfidenceScorePage {
    private final ReportGenerator reportGenerator;
    WebDriver driver;
    @FindBy(xpath = "//div[@data-automation='confidence-score-section']//div[@tabindex='0']")
    private WebElement csHdr;
    @FindBy(xpath = "//div[contains(text(),'This section')]")
    private WebElement csDsc;
    @FindBy(xpath = "//div[contains(text(),'Global Confidence')]")
    private WebElement gcHdr;
    @FindBy(xpath = "//div[contains(text(),'Adjust')]")
    private WebElement gcDsc;
    @FindBy(xpath = "//div[contains(text(),'Set Confidence')]")
    private WebElement scHdr;
    @FindBy(xpath = "(//core-icon[@icon='Information'])[2]")
    private WebElement scHdrIcn;
    @FindBy(xpath = "(//core-icon[@icon='Information'])[1]")
    private WebElement gcHdrIcn;
    @FindBy(xpath = "(//div[text()='Document Indexing'])[1]")
    private WebElement diSldHdr;
    @FindBy(xpath = "(//div[text()='0'])[1]")
    private WebElement di0;
    @FindBy(xpath = "(//div[text()='20'])[1]")
    private WebElement di20;
    @FindBy(xpath = "(//div[text()='40'])[1]")
    private WebElement di40;
    @FindBy(xpath = "(//div[text()='60'])[1]")
    private WebElement di60;
    @FindBy(xpath = "(//div[text()='80'])[1]")
    private WebElement di80;
    @FindBy(xpath = "(//div[text()='100'])[1]")
    private WebElement di100;
    @FindBy(xpath = "(//div[text()='Always Fail'])[1]")
    private WebElement diAf;
    @FindBy(xpath = "(//input[@type='checkbox'])[1]")
    private WebElement diAfCb;
    @FindBy(xpath = "(//div[text()='Always Pass'])[1]")
    private WebElement diAp;
    @FindBy(xpath = "(//input[@type='checkbox'])[2]")
    private WebElement diApCb;
    @FindBy(xpath = "(//div[text()='Data Extraction'])[1]")
    private WebElement deSldHdr;
    @FindBy(xpath = "(//div[text()='0'])[2]")
    private WebElement de0;
    @FindBy(xpath = "(//div[text()='20'])[2]")
    private WebElement de20;
    @FindBy(xpath = "(//div[text()='40'])[2]")
    private WebElement de40;
    @FindBy(xpath = "(//div[text()='60'])[2]")
    private WebElement de60;
    @FindBy(xpath = "(//div[text()='80'])[2]")
    private WebElement de80;
    @FindBy(xpath = "(//div[text()='100'])[2]")
    private WebElement de100;
    @FindBy(xpath = "(//div[text()='Always Fail'])[2]")
    private WebElement deAf;
    @FindBy(xpath = "(//div[text()='Always Pass'])[2]")
    private WebElement deAp;
    @FindBy(xpath = "(//input[@type='checkbox'])[3]")
    private WebElement deAfCb;
    @FindBy(xpath = "(//input[@type='checkbox'])[4]")
    private WebElement deApCb;
    @FindBy(xpath = "//span[contains(text(),'Use Default Confidence Score')]")
    private WebElement udCs;
    @FindBy(xpath = "//div[@data-automation='tooltip']")
    private WebElement tooTipMessage;
    @FindBy(xpath = "//core-slider[@data-automation='global-confidence-score-indexing-core-slider']//span[@role='slider']")
    private WebElement diSlide;
    @FindBy(xpath = "//core-slider[@data-automation='global-confidence-score-extraction-core-slider']//span[@role='slider']")
    private WebElement deSlide;
    @FindBy(xpath = "//a[@data-automation='add-document-types-button']/span")
    private WebElement fstAdd;
    @FindBy(xpath = "(//span[contains(text(),'Add Document')])[2]")
    private WebElement scdAdd;
    @FindBy(xpath = "(//div[text()='Document Indexing'])[2]")
    private WebElement diDoc;
    @FindBy(xpath = "(//div[text()='Data Extraction'])[2]")
    private WebElement deDoc;
    @FindBy(xpath = "(//core-icon[@icon='add_circle'])[1]")
    private WebElement addIcnFst;
    @FindBy(xpath = "(//core-icon[@icon='add_circle'])[2]")
    private WebElement addIcnScd;
    @FindBy(xpath = "//div[contains(text(),'No documents')]")
    private WebElement addNoDoc;
    @FindBy(xpath = "//mat-select[@data-automation='global-confidence-score-mat-select']")
    private WebElement dropDown;
    @FindBy(xpath = "//span[text()='Manually Set Confidence Score']")
    private WebElement mnlySetCs;
    @FindBy(xpath = "//button[@data-automation='save-confidence-score-button']")
    private WebElement saveCs;
    @FindBy(xpath = "//button[@data-automation='reset-confidence-score-button']")
    private WebElement resetCs;
    @FindBy(xpath = "//div[@data-automation='snack-bar-message']")
    private WebElement confirmMessage;
    @FindBy(xpath = "//button[@data-automation='snack-bar-close']")
    private WebElement dismissConfirmMessage;

    @FindBy(xpath = "//div[@data-automation='confirmation-dialog-title']")
    private WebElement resetTtl;

    @FindBy(xpath = "//core-icon[@data-automation='close-confirmation-modal']")
    private WebElement resetCls;

    @FindBy(xpath = "//div[@data-automation='confirmation-dialog-message']")
    private WebElement resetMsg;

    @FindBy(xpath = "//button[@data-automation='confirmation-modal-deny-button']")
    private WebElement resetDeny;

    @FindBy(xpath = "//button[@data-automation='confirmation-modal-confirmation-button']")
    private WebElement resetCnfrm;
    @FindBy(xpath = "//core-icon[@data-automation='pipeline-clarifi-icon']")
    public WebElement pipeline;
    @FindBy(xpath = "//button[@data-automation='confirmation-modal-confirmation-button']")
    private WebElement cnfrDelDocTyp;

     @FindBy(xpath = "//button[@data-automation='add-and-close-button']")
      private WebElement addAndClose;
    // @FindBy(xpath = "")
    //  private WebElement ;
    public ConfidenceScorePage(WebDriver driver, ReportGenerator reportGenerator) {
        this.driver = driver;
        this.reportGenerator=reportGenerator;
        PageFactory.initElements(driver, this);
    }

    public boolean setGcDiDeSldr(Integer di, Integer de) {

        boolean dataInd=SeleniumUtils.confScrSlider(driver,diSlide,"aria-valuenow",di);
       boolean dataExt=SeleniumUtils.confScrSlider(driver,deSlide,"aria-valuenow",de);
       return dataInd&&dataExt;
    }
public void delDocTypes() {

    try {
        List<WebElement> listOfElements = driver.findElements(By.xpath("//core-icon[@data-automation='document-delete-icon']"));
        for (WebElement docType : listOfElements) {
            SeleniumUtils.doClick(driver, docType);
            SeleniumUtils.doClick(driver, cnfrDelDocTyp);
        }
    } catch (Exception e) {
        reportGenerator.logAndCaptureScreen("error in deleting existing doc types in conf score page", "delDocTypes", false, driver);
    }
    reportGenerator.logAndCaptureScreen("deleting existing doc types in conf score page", "delDocTypes", true, driver);
}

    public boolean selectDocTypes(List<String> a) {
        SeleniumUtils.doClick(driver, addIcnFst);
        List<WebElement> listOfElements = driver.findElements(By.xpath("//div[@data-automation='item-name']"));
        //   System.out.println(listOfElements);
         boolean flag=SeleniumUtils.selectVisibleTxt(driver, listOfElements, a)&&
                SeleniumUtils.doClick(driver,addAndClose);
        return flag;
    }

    public boolean clkResetBtn(String rstConfScrHdr, String rstConfScrMsg, String diVal, String deVal, boolean dftGcsAfAp) {
        boolean vrfPopUp = SeleniumUtils.doClick(driver, resetCs) &&
                SeleniumUtils.verifyText(driver, resetTtl, rstConfScrHdr) &&
                SeleniumUtils.verifyText(driver, resetMsg, rstConfScrMsg) &&
                SeleniumUtils.isDisplayed(driver, resetCls, true) &&
                SeleniumUtils.isDisplayed(driver, resetDeny, true) &&
                SeleniumUtils.isEnabled(driver, resetCnfrm) &&
                SeleniumUtils.doClick(driver, resetCnfrm);
        CommonUtils.sleepForAWhile();
        boolean vfyElt=
        SeleniumUtils.verifyAttribute(driver, diSlide, "aria-valuenow", diVal) &&
                SeleniumUtils.verifyAttribute(driver, deSlide, "aria-valuenow", deVal) &&
                SeleniumUtils.isEnabled(driver, diAfCb, dftGcsAfAp) &&
                SeleniumUtils.isEnabled(driver, diApCb, dftGcsAfAp) &&
                SeleniumUtils.isEnabled(driver, deAfCb, dftGcsAfAp) &&
                SeleniumUtils.isEnabled(driver, deApCb, dftGcsAfAp) ;

        return vfyElt&&vrfPopUp;
    }

    public boolean clkSveConfScr(String svConfMsg) {

        boolean flag= SeleniumUtils.doClick(driver, saveCs)
                && SeleniumUtils.verifyText(driver, confirmMessage, svConfMsg)
                && SeleniumUtils.doClick(driver, dismissConfirmMessage);

        return flag;
    }

    public boolean dftOrMnl(boolean dft, boolean mnl, String svConfMsg) {
        SeleniumUtils.doClick(driver, dropDown);
        boolean flag = false;
        if (mnl) {
            flag = SeleniumUtils.doClick(driver, mnlySetCs);
        } else if (dft) {
            flag = SeleniumUtils.doClick(driver, udCs);
        }
        if (SeleniumUtils.isEnabled(driver, saveCs)) {
            clkSveConfScr(svConfMsg);
        }

        return flag;
    }

    public boolean selectGlblConfScTyp(boolean dft, boolean mnl, String csH, String csD, String gcH, String gcD, String scH, String inf, String diVal, String deVal, String svConfMsg, boolean dftGcsAfAp, String addDocu) {
        boolean vfyElt = dftOrMnl(dft, mnl, svConfMsg) &&
                SeleniumUtils.verifyAttribute(driver, diSlide, "aria-valuenow", diVal) &&
                SeleniumUtils.verifyAttribute(driver, deSlide, "aria-valuenow", deVal) &&
                SeleniumUtils.verifyText(driver, csHdr, csH) &&
                SeleniumUtils.verifyText(driver, csDsc, csD) &&
                SeleniumUtils.verifyText(driver, gcHdr, gcH) &&
                SeleniumUtils.verifyText(driver, gcDsc, gcD) &&
                SeleniumUtils.verifyText(driver, diSldHdr, "Document Indexing") &&
                SeleniumUtils.verifyText(driver, di0, "0") &&
                SeleniumUtils.verifyText(driver, di20, "20") &&
                SeleniumUtils.verifyText(driver, di40, "40") &&
                SeleniumUtils.verifyText(driver, di60, "60") &&
                SeleniumUtils.verifyText(driver, di80, "80") &&
                SeleniumUtils.verifyText(driver, di100, "100") &&
                SeleniumUtils.verifyText(driver, diAf, "Always Fail") &&
                SeleniumUtils.verifyText(driver, diAp, "Always Pass") &&
                SeleniumUtils.isEnabled(driver, diAfCb, dftGcsAfAp) &&
                SeleniumUtils.isEnabled(driver, diApCb, dftGcsAfAp) &&
                SeleniumUtils.verifyText(driver, deSldHdr, "Data Extraction") &&
                SeleniumUtils.verifyText(driver, de0, "0") &&
                SeleniumUtils.verifyText(driver, de20, "20") &&
                SeleniumUtils.verifyText(driver, de40, "40") &&
                SeleniumUtils.verifyText(driver, de60, "60") &&
                SeleniumUtils.verifyText(driver, de80, "80") &&
                SeleniumUtils.verifyText(driver, de100, "100") &&
                SeleniumUtils.verifyText(driver, deAf, "Always Fail") &&
                SeleniumUtils.verifyText(driver, deAp, "Always Pass") &&
                SeleniumUtils.isEnabled(driver, deAfCb, dftGcsAfAp) &&
                SeleniumUtils.isEnabled(driver, deApCb, dftGcsAfAp) &&
                SeleniumUtils.verifyText(driver, scHdr, scH) &&
                SeleniumUtils.verifyText(driver, diDoc, "Document Indexing") &&
                SeleniumUtils.verifyText(driver, deDoc, "Data Extraction") &&
                SeleniumUtils.verifyText(driver, fstAdd, addDocu) &&
                SeleniumUtils.verifyText(driver, scdAdd, addDocu) &&
                SeleniumUtils.isDisplayed(driver, addIcnFst, true) &&
                SeleniumUtils.isDisplayed(driver, addIcnScd, true);

        return vfyElt;
        //  (SeleniumUtils.doHover(driver, gcHdrIcn)) &&
        //                (SeleniumUtils.verifyText(driver, tooTipMessage, inf)) &&
        //                 (SeleniumUtils.doHover(driver, scHdrIcn)) &&
        //                (SeleniumUtils.verifyText(driver, tooTipMessage, inf)) &&
    }
    public boolean slctGcCb(boolean diAf,boolean diAp,boolean deAf,boolean deAp){
        boolean di = false,de = false;
        if(diAf){
        di=SeleniumUtils.doClick(driver,diAfCb);}
        else if(diAp){
        di=SeleniumUtils.doClick(driver,diApCb);}
        if(deAf){
        de=SeleniumUtils.doClick(driver,deAfCb);}
        else if (deAp) {
            de=SeleniumUtils.doClick(driver,deApCb);
        }
        return (di&&de);
       }
    public HomePage clickPipeline(){
        SeleniumUtils.doClick(driver,pipeline);
        return new HomePage(driver);
    }

}
