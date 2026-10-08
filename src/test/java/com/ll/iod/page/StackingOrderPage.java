package com.ll.iod.page;

import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.CommonUtils;
import com.ll.iod.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StackingOrderPage {
    public WebDriver driver;
    @FindBy(xpath = "//div[@data-automation='stacking-order-title']")
    private WebElement stkOdrTtl;
    @FindBy(xpath = "//div[@data-automation='stacking-order-title']/following::p")
    private WebElement stkOrdDsc;
    @FindBy(xpath = "//a[@data-automation='add-doc-types-link']")
    private WebElement addDoc;
    //core-icon[@data-automation='back-stacking-order-list-icon']
    //div[@data-automation='stacking-order-management-title']
    //div[@data-automation='stacking-order-management-container']//following::p

    //mat-option[@data-automation='bookmark-pdf-option']
    //mat-option[@data-automation='individual-pdf-option']
    //mat-option[@data-automation='both-bookmark-individual-option']
    @FindBy(xpath = "//a[@data-automation='add-stacking-order-link']")
    private WebElement addStkOdr;
    @FindBy(xpath = "//input[@data-automation='stacking-order-name-input']")
    private WebElement StckOrdNm;
    @FindBy(xpath = "//button[@data-automation='add-button']")
    private WebElement add;
    @FindBy(xpath = "//button[@data-automation='add-and-close-button']")
    private WebElement addClose;
    @FindBy(xpath = "//div[@data-automation='select-all-items']")
    private WebElement selectAll;
    @FindBy(xpath = "//core-icon[@data-automation='filter-icon']")
    private WebElement stkFlt;
    @FindBy(xpath = "//input[@data-automation='filter-input']")
    private WebElement stkFltInp;
    @FindBy(xpath = "//core-icon[@data-automation='filter-close-icon']")
    private WebElement stkFltInpCls;
    @FindBy(xpath = "(//div[@id='DOCTYPE_LIST']/div)[1]")
    private WebElement selectFstDoc;
    @FindBy(xpath = "//button[@data-automation='cancel-button']")
    private WebElement dscrd;
    @FindBy(xpath = "//button[@data-automation='save-button']")
    private WebElement save;
    @FindBy(xpath = "//button[@data-automation='confirmation-modal-confirmation-button']")
    private WebElement dscrdConf;
    @FindBy(xpath = "//button[@data-automation='confirmation-modal-deny-button']")
    private WebElement dscrdDeny;
    @FindBy(xpath = "//mat-select[@data-automation='bookmark-delivery-type-select']")
    private WebElement selectPdfType;
    @FindBy(xpath = "(//div[@data-automation='stacking-order-name'])[1]")
    private WebElement hoverFltStkOrd;
    @FindBy(xpath = "(//div[@data-automation='stacking-order-name'])[1]/following::a[@data-automation='edit-stacking-order'][1]")
    private WebElement edtCtdStkOrd;
    @FindBy(xpath = "(//div[@data-automation='stacking-order-name'])[1]/following::a[@data-automation='copy-stacking-order'][1]")
    private WebElement cpyCtdStkOrd;
    @FindBy(xpath = "(//div[@data-automation='stacking-order-name'])[1]/following::div[@data-automation='delete-stacking-order'][1]")
    private WebElement dltCtdStkOrd;
    ReportGenerator reportGenerator;
    public StackingOrderPage(WebDriver driver,ReportGenerator reportGenerator) {
        this.driver = driver;
        this.reportGenerator=reportGenerator;
        PageFactory.initElements(driver, this);
    }

    public void addStkOrd(String stkName, String pdfType) {

        SeleniumUtils.doubleClick(driver, addStkOdr);
        SeleniumUtils.sendKeys(driver, StckOrdNm, stkName);
        SeleniumUtils.doClick(driver, selectPdfType);
        List<WebElement> allOptions = driver.findElements(By.xpath("//mat-option[@role='option']"));
        for (WebElement e : allOptions) {
            if ((e.getAttribute("value")).equalsIgnoreCase(pdfType)) {
                SeleniumUtils.doClick(driver, e);
                break;
            }
        }
        SeleniumUtils.doClick(driver, addDoc);
    }

    public void add() {
        SeleniumUtils.doClick(driver, add);
    }

    public void addAndClose() {
        SeleniumUtils.doClick(driver, addClose);
    }

    public void discardSaveChange(String msg) {
        SeleniumUtils.doClick(driver, dscrd);
        if (msg.equalsIgnoreCase("confirm"))
            SeleniumUtils.doClick(driver, dscrdConf);
        else
            SeleniumUtils.doClick(driver, dscrdDeny);
    }

    public void selectAllStkOrd(String stkName, String pdfType) {
        addStkOrd(stkName, pdfType);
        SeleniumUtils.doClick(driver, selectAll);
        addAndClose();
        discardSaveChange("confirm");
    }
    public void selectStkOrdByNme(String stkName, String pdfType, String docTypes) {
        addStkOrd(stkName, pdfType);
        List<String> docData = new ArrayList<String>(Arrays.asList(docTypes.split(",")));
        SeleniumUtils.doClick(driver, stkFlt);
        for(int i = 0; i < docData.size(); i++) {
            SeleniumUtils.sendKeys(driver, stkFltInp, docData.get(i));
            CommonUtils.sleepForAWhile(1000);
            SeleniumUtils.doClick(driver, selectFstDoc);
            add();
            SeleniumUtils.doClick(driver, stkFltInpCls);
        }
        discardSaveChange("confirm");
        CommonUtils.sleepForAWhile(1000);
    }

    public void hoverFltdStkOrd(String stkName) {
        SeleniumUtils.doClick(driver, stkFlt);
        SeleniumUtils.sendKeys(driver, stkFltInp, stkName);
        CommonUtils.sleepForAWhile(1000);
        SeleniumUtils.doHover(driver, hoverFltStkOrd);
    }

    public void edtStkOrd(String stkName, String pdfType, String edtStkName) {
        hoverFltdStkOrd(stkName);
        SeleniumUtils.doClick(driver, edtCtdStkOrd);
        selectAllStkOrd(edtStkName, pdfType);
    }

    public void dltStkOrd(String stkName) {
        hoverFltdStkOrd(stkName);
        SeleniumUtils.doClick(driver, dltCtdStkOrd);
        SeleniumUtils.doClick(driver, dscrdConf);
        SeleniumUtils.doClick(driver, stkFltInpCls);
        SeleniumUtils.doClick(driver, stkFltInpCls);
    }

    public void cpyStkOrd(String stkName) {
        hoverFltdStkOrd(stkName);
        SeleniumUtils.doClick(driver, cpyCtdStkOrd);
        discardSaveChange("confirm");
    }

}
