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

public class TaxonomyPage {
    public WebDriver driver;
    @FindBy(xpath = "//input[@type='text']")
    private WebElement srchBar;
    @FindBy(xpath = "//button[@data-automation='add-button']")
    private WebElement addIcon;
    @FindBy(xpath = "(//div[@data-automation='typeahead-option'])[1]")
    private WebElement selDoc;
    @FindBy(xpath = "(//core-icon[@icon='add_circle'])[1]")
    private WebElement plusIcon;
    @FindBy(xpath = "(//button[@data-automation='save-taxonomy-button'])")
    private WebElement save;
    @FindBy(xpath = "(//button[@data-automation='confirmation-modal-confirmation-button'])")
    private WebElement saveChanges;
    @FindBy(xpath = "(//button[@data-automation='confirmation-modal-deny-button'])")
    private WebElement discardChanges;


    @FindBy(xpath = "//button[@data-automation='save-taxonomy-button']")
    private WebElement saveTx;
    @FindBy(xpath = "//button[@data-automation='confirmation-modal-confirmation-button']")
    private WebElement delCnfrm;
    @FindBy(xpath = "(//input[@data-automation='export-name-input'])[2]")
    private WebElement expNme;
    @FindBy(xpath = "(//core-icon[@icon='cancel'])")
    private WebElement cancel;
    @FindBy(xpath = "//div[@data-automation='stacking-order-submenu-item']")
    private WebElement stckOrd;
    @FindBy(xpath = "//div[@data-automation='taxonomy-submenu-item']")
    private WebElement txnmy;
    ReportGenerator reportGenerator;

    public TaxonomyPage(WebDriver driver,ReportGenerator reportGenerator) {
        this.driver = driver;
        this.reportGenerator=reportGenerator;
        PageFactory.initElements(driver, this);


    }

    public void dftPg() {

        List<WebElement> listOfElements = driver.findElements(By.xpath("//core-icon[@icon='cancel']"));
        for (int i = listOfElements.size() - 1; i >= 0; i--) {
            try {
                if (i == 0) {
                    driver.findElement(By.xpath("(//core-icon[@icon='cancel'])")).click();
                    SeleniumUtils.doClick(driver, delCnfrm);
                    SeleniumUtils.doClick(driver, saveTx);
                } else {
                    String path = "(//core-icon[@icon='cancel'])" + "[" + (i) + "]";
                    driver.findElement(By.xpath(path)).click();
                    SeleniumUtils.doClick(driver, delCnfrm);
                    CommonUtils.sleepForAWhile(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void addDoc(String docType, String docTypeNms) {
        List<String> docData = new ArrayList<String>(Arrays.asList(docType.split(",")));
        List<String> docDataNms = new ArrayList<String>(Arrays.asList(docTypeNms.split(",")));
        for (int i = 0; i < docData.size(); i++) {
            String txtPath = "//div[@data-automation='export-row-" + docData.get(i) + "']//input[@type='text']";
            SeleniumUtils.doClick(driver, srchBar);
            SeleniumUtils.sendKeys(driver, srchBar, docData.get(i));
            SeleniumUtils.doClick(driver, selDoc);
            SeleniumUtils.doClick(driver, addIcon);
            for (int j = i; j < docDataNms.size(); j++) {
                driver.findElement(By.xpath(txtPath)).sendKeys(docDataNms.get(j));
                break;
            }
        }
    }

    public void discardChange(String docType, String docTypeNms) {
        addDoc(docType, docTypeNms);
        SeleniumUtils.doClick(driver, plusIcon);
        SeleniumUtils.doClick(driver, stckOrd);
        SeleniumUtils.doClick(driver, saveChanges);
        SeleniumUtils.doClick(driver, txnmy);
    }
    public void saveChanges(String docType, String docTypeNms) {
        addDoc(docType, docTypeNms);
        SeleniumUtils.doClick(driver, saveTx);
    }
    public void saveChanges(String docType, String docTypeNms, String expDocNmes) {
        addDoc(docType, docTypeNms);
        SeleniumUtils.doClick(driver, plusIcon);
        SeleniumUtils.sendKeys(driver, expNme, expDocNmes);
        SeleniumUtils.doClick(driver, stckOrd);
        SeleniumUtils.doClick(driver, saveChanges);
        SeleniumUtils.doClick(driver, txnmy);
    }
}

