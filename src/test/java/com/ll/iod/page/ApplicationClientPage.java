package com.ll.iod.page;

import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.CommonUtils;
import com.ll.iod.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApplicationClientPage {
    ReportGenerator reportGenerator;
    WebDriver driver;
    @FindBy(xpath = "//core-icon[@data-automation='filter-icon']")
    private WebElement fltr;
    @FindBy(xpath = "//input[@data-automation='filter-input']")
    private WebElement fltrTxt;
    @FindBy(xpath = "//core-icon[@data-automation='filter-close-icon']")
    private WebElement fltrClose;
    @FindBy(xpath = "//button[@data-automation='create-user-button']")
    private WebElement createAppClt;
    @FindBy(xpath = "//h3[contains(text(),'Create New Application Client')]")
    private WebElement createAppCltHdr;
    @FindBy(xpath = "//div[contains(text(),'Fill out the')]")
    private WebElement useAppCltDescrp;
    @FindBy(xpath = "//span[contains(text(),'Application Client Name')]")
    private WebElement createAppCltTxtBoxHdr;
    @FindBy(xpath = "//input[@type='text' and @tabindex='1']")
    private WebElement entAppCltName;
    @FindBy(xpath = "//div[contains(text(),'You are allowed')]")
    private WebElement appCltDescrp;
    @FindBy(xpath = "//button[contains(text(),'CANCEL')]")
    private WebElement cancelBtn;
    @FindBy(xpath = "//button[contains(text(),'CREATE')and@tabindex='3']")
    private WebElement createBtn;
    @FindBy(xpath = "(//core-icon[@icon='close'])[2]")
    private WebElement clsIcon;
    @FindBy(xpath = "//div[text()=' Application Client Name ']")
    private WebElement appCltHdr;
    @FindBy(xpath = "//mat-header-cell[text()=' Access Key ID ']")
    private WebElement accKyHdr;
    @FindBy(xpath = "//div[text()=' Created Date ']")
    private WebElement crdDtHdr;
    @FindBy(xpath = "//div[text()=' Last Modified ']")
    private WebElement lsMfdHdr;
    @FindBy(xpath = "//mat-header-cell[text()=' Action ']")
    private WebElement actHdr;
    @FindBy(xpath = "//core-icon[@icon='blocked-error']")
    private WebElement appCltError;
    @FindBy(xpath = "//div[@data-automation='tooltip']")
    private WebElement tooTipMessage;
    @FindBy(xpath = "//div[@data-automation='snack-bar-message']")
    private WebElement confirmMessage;
    @FindBy(xpath = "//button[@data-automation='snack-bar-close']")
    private WebElement dismissConfirmMessage;
    @FindBy(xpath = "//h3[contains(text(),'Application Client Details')]")
    private WebElement appCltDtls;
    @FindBy(xpath = "(//div[text()='Application Client Name'])[2]")
    private WebElement ctdAppCltNmeHdr;
    @FindBy(xpath = "(//div[text()='Application Client Name'])[2]//following::div[1]")
    private WebElement ctdAppCltNme;
    @FindBy(xpath = "(//div[text()='Access Key ID'])[2]")
    private WebElement ctdAssKyHdr;
    @FindBy(xpath = "//core-icon[@id='accessKey']//preceding::span[1]")
    private WebElement ctdAccKyId;
    @FindBy(xpath = "//core-icon[@id='accessKey']")
    private WebElement ctdAccKyIdCpy;
    @FindBy(xpath = "//div[text()='Secret Key']")
    private WebElement ctdSctKyHdr;
    @FindBy(xpath = "//div[contains(text(),'To ensure ')]")
    private WebElement ctdSctKyDsc;
    @FindBy(xpath = "//core-icon[@id='secretKey']//preceding::span[1]")
    private WebElement ctdSctKyId;
    @FindBy(xpath = "//core-icon[@id='secretKey']")
    private WebElement ctdSctKyIdCpy;
    @FindBy(xpath = "(//core-icon[@icon='close'])[2]")
    private WebElement ctdClsIcon;
    @FindBy(xpath = "//button[text()=' CLOSE ']")
    private WebElement ctdClsBtn;
    @FindBy(xpath = "//core-icon[@data-automation='filter-icon']")
    private WebElement fltrBtn;
    @FindBy(xpath = "//input[@data-automation='filter-input']")
    private WebElement fltrInp;
    @FindBy(xpath = "//core-icon[@data-automation='filter-close-icon']")
    private WebElement fltrCls;
    @FindBy(xpath = "(//mat-row[@role='row'])[1]//mat-cell[1]//div")
    private WebElement fltrAppNme;
    @FindBy(xpath = "(//div[@position='bottom'])[2]")
    private WebElement fltrAccKy;
    @FindBy(xpath = "//core-icon[@icon='copy-text']")
    private WebElement fltrAccKyCpy;
    @FindBy(xpath = "(//div[@position='bottom'])[3]")
    private WebElement fltrAppCtd;
    @FindBy(xpath = "(//div[@position='bottom'])[4]")
    private WebElement fltrAppMfd;
    @FindBy(xpath = "(//core-icon[@icon='edit'])[1]")
    private WebElement fltrEdt;
    @FindBy(xpath = "//core-icon[@icon='trash']")
    private WebElement fltrDlt;
    @FindBy(xpath = "//h3[text()=' Edit Application Client Name ']")
    private WebElement edtTtl;
    @FindBy(xpath = "(//core-icon[@icon='close'])[2]")
    private WebElement edtCls;
    @FindBy(xpath = "//span[text()='Application Client Name']")
    private WebElement edtTxtBxHdr;
    @FindBy(xpath = "//button[text()=' CANCEL ']")
    private WebElement edtCncl;
    @FindBy(xpath = "//button[text()=' SAVE ']")
    private WebElement edtSv;
    @FindBy(xpath = "//div[@data-automation='confirmation-dialog-title']")
    private WebElement dltTtl;
    @FindBy(xpath = "//div[@data-automation='confirmation-dialog-message']")
    private WebElement dltMsg;
    @FindBy(xpath = "//button[@data-automation='confirmation-modal-confirmation-button']")
    private WebElement cnfrmDlt;

    public ApplicationClientPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public ApplicationClientPage(WebDriver driver, ReportGenerator reportGenerator) {
        this.driver = driver;
        this.reportGenerator=reportGenerator;
        PageFactory.initElements(driver, this);
    }

    public boolean enterCtdAppClnt(String msg1) {
        boolean fl=(SeleniumUtils.doClick(driver, fltrBtn));
        CommonUtils.sleepForAWhile(10000);
        boolean entrApp = ( (SeleniumUtils.sendKeys(driver, fltrInp, msg1))&&(SeleniumUtils.isDisplayed(driver, fltrClose, true)));
        return (fl&&entrApp);
    }


    public boolean vrfyCtdAppClnt(String msg1) {
        driver.navigate().refresh();
        CommonUtils.sleepForAWhile();
        enterCtdAppClnt(msg1);
        CommonUtils.sleepForAWhile();
        // (SeleniumUtils.verifyCopiedText(driver,fltrAccKy,fltrAccKyCpy))&&
        boolean verifyApp = ((SeleniumUtils.verifyText(driver, fltrAppNme, msg1)) && ((SeleniumUtils.getValue(driver, fltrAppCtd)).equalsIgnoreCase(SeleniumUtils.getValue(driver, fltrAppMfd))) && (SeleniumUtils.isDisplayed(driver, fltrEdt, true)) && (SeleniumUtils.isDisplayed(driver, fltrDlt, true)));
        return (verifyApp);

    }

    public boolean verifyAppCltError(String message) {
        return SeleniumUtils.doHover(driver, appCltError)&&SeleniumUtils.verifyText(driver, tooTipMessage, message);
    }

    public boolean chkHdr(String appClt, String accKy, String crtdHdr, String lsmHd, String actHd) {
        return ((SeleniumUtils.verifyText(driver, appCltHdr, appClt)) && (SeleniumUtils.verifyText(driver, accKyHdr, accKy)) && (SeleniumUtils.verifyText(driver, crdDtHdr, crtdHdr)) && (SeleniumUtils.verifyText(driver, lsMfdHdr, lsmHd)) && (SeleniumUtils.verifyText(driver, actHdr, actHd)));
    }

    public boolean createNewAppClt() {
        return SeleniumUtils.doClick(driver, createAppClt);
    }

    public boolean chkCrtAppHdr(String hdr, String sHdr, String txtBoxHdr, String descr) {
        return ((SeleniumUtils.verifyText(driver, createAppCltHdr, hdr)) && (SeleniumUtils.verifyText(driver, useAppCltDescrp, sHdr)) && (SeleniumUtils.verifyText(driver, createAppCltTxtBoxHdr, txtBoxHdr)) && (SeleniumUtils.verifyText(driver, appCltDescrp, descr)));
    }

    public boolean verifyDftIcon(boolean cncl, boolean crt, boolean cls) {
        return ((SeleniumUtils.isEnabled(driver, cancelBtn, cncl)) && (SeleniumUtils.isDisplayed(driver, clsIcon, cls)) && (SeleniumUtils.isEnabled(driver, createBtn, crt)));
    }

    public boolean clkCrtAppClt(String msg1, String msg2, String msg3, String msg4, String msg5) {
        CommonUtils.sleepForAWhile();
        boolean flag = ((SeleniumUtils.doClick(driver, createBtn)) && ((SeleniumUtils.verifyText(driver, confirmMessage, "Application Client: " + msg1 + " successfully added")) && (SeleniumUtils.doClick(driver, dismissConfirmMessage))));
      System.out.println("added app "+flag);

        boolean dtl_flag1= ((SeleniumUtils.verifyText(driver, appCltDtls, msg2)) && (SeleniumUtils.verifyText(driver, ctdAppCltNmeHdr, msg3)) && (SeleniumUtils.verifyText(driver, ctdAppCltNme, msg1)) && (SeleniumUtils.verifyText(driver, ctdAssKyHdr, msg4))&& (SeleniumUtils.verifyText(driver, ctdSctKyHdr, msg5)) );

        boolean dtl_flag2 =SeleniumUtils.verifyCopiedText(driver, ctdSctKyId, ctdSctKyIdCpy);

        boolean dtl_flag3 = SeleniumUtils.verifyCopiedText(driver, ctdAccKyId, ctdAccKyIdCpy);

        boolean dtl_flag =SeleniumUtils.doClick(driver,ctdClsBtn);
        System.out.println("dft app "+(dtl_flag&&dtl_flag1&&dtl_flag2&&dtl_flag3));
        return (flag && (dtl_flag&&dtl_flag1&&dtl_flag2&&dtl_flag3));
    }

    public boolean enterAppCltName(String appCltName) {
        CommonUtils.sleepForAWhile();
        boolean entrApp = ((SeleniumUtils.sendKeys(driver, entAppCltName, appCltName)));
        CommonUtils.sleepForAWhile();
        return entrApp;
    }

    public boolean edtCtdAppClt(String appCltName, String edtAppCltName) {
        driver.navigate().refresh();
        enterCtdAppClnt(appCltName);
        CommonUtils.sleepForAWhile();
        boolean dft = ((SeleniumUtils.doClickIcon(driver, fltrEdt)) && (SeleniumUtils.verifyText(driver, edtTtl, "Edit Application Client Name")) && (SeleniumUtils.verifyText(driver, edtTxtBxHdr, "Application Client Name")) && (SeleniumUtils.isDisplayed(driver, edtCls, true)) && (SeleniumUtils.isDisplayed(driver, edtCncl, true)) && (SeleniumUtils.isEnabled(driver, edtSv, false)));
        entAppCltName.clear();
        boolean vfyMsg = ( (SeleniumUtils.sendKeys(driver, entAppCltName, edtAppCltName))&&
                ( SeleniumUtils.doClick(driver, edtSv))&&(SeleniumUtils.verifyText(driver, confirmMessage, "Application Client: " + edtAppCltName + " successfully edited")) && (SeleniumUtils.doClick(driver, dismissConfirmMessage)));
        return (dft && vfyMsg);
    }

    public boolean dltCtdAppClt(String appCltName) {
        driver.navigate().refresh();
        enterCtdAppClnt(appCltName);
        CommonUtils.sleepForAWhile();
        return ((SeleniumUtils.doClickIcon(driver, fltrDlt)) && (SeleniumUtils.verifyText(driver, dltTtl, "Delete Application Client")) && (SeleniumUtils.verifyText(driver, dltMsg, "The Application Client: " + appCltName + " is going to be deleted. Do you want to continue?")) && (SeleniumUtils.doClick(driver, cnfrmDlt)) && (SeleniumUtils.verifyText(driver, confirmMessage, "Application Client: " + appCltName + " successfully deleted")) && (SeleniumUtils.doClick(driver, dismissConfirmMessage)));
    }
    public boolean createAppClnt(String applClntNm){
        SeleniumUtils.doubleClick(driver, createAppClt);
        enterAppCltName(applClntNm);
        boolean flag = ((SeleniumUtils.doubleClick(driver, createBtn)) && ((SeleniumUtils.verifyText(driver, confirmMessage, "Application Client: " + applClntNm + " successfully added")) && (SeleniumUtils.doubleClick(driver, dismissConfirmMessage))));
        System.out.println("added app "+flag);
        boolean dtl_flag2 =SeleniumUtils.verifyCopiedText(driver, ctdSctKyId, ctdSctKyIdCpy);
        boolean dtl_flag3 = SeleniumUtils.verifyCopiedText(driver, ctdAccKyId, ctdAccKyIdCpy);
        boolean dtl_flag =SeleniumUtils.doubleClick(driver,ctdClsBtn);
        System.out.println("dft app "+(dtl_flag&&dtl_flag2&&dtl_flag3));
        return (flag && (dtl_flag&&dtl_flag2&&dtl_flag3));
    }
    public boolean vrfyCtdAppClntSupport(String msg1)
    {
        CommonUtils.sleepForAWhile();
        enterCtdAppClnt(msg1);
        CommonUtils.sleepForAWhile();
        // (SeleniumUtils.verifyCopiedText(driver,fltrAccKy,fltrAccKyCpy))&&
        boolean verifyApp = ((SeleniumUtils.verifyText(driver, fltrAppNme, msg1)) && ((SeleniumUtils.getValue(driver, fltrAppCtd)).equalsIgnoreCase(SeleniumUtils.getValue(driver, fltrAppMfd))) && (SeleniumUtils.isDisplayed(driver, fltrEdt, true)) && (SeleniumUtils.isDisplayed(driver, fltrDlt, true)));
        return (verifyApp);
    }

}
