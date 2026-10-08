package com.ll.iod.page;

import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.CommonUtils;
import com.ll.iod.utils.SeleniumUtils;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TenantsPage {
    public WebDriver driver;
    ReportGenerator reportGenerator;
    String tntName, tntDesc, searchTnt;
    @FindBy(xpath = "//span[contains(text(),'Add')]")
    private WebElement addTnts;
    @FindBy(xpath = "(//span[contains(text(),'Add')])[2]")
    private WebElement adtDes;
    @FindBy(xpath = "(//span[contains(text(),'Company')])")
    private WebElement tciDes;
    @FindBy(xpath = "(//span[contains(text(),'Id')])")
    private WebElement tntDes;
    @FindBy(xpath = "(//span[contains(text(),'Display')])")
    private WebElement disnDes;
    @FindBy(xpath = "(//span[contains(text(),'Admin')][1])")
    private WebElement adnUsrDes;
    @FindBy(xpath = "(//span[contains(text(),'Admin')][2])")
    private WebElement mailDes;
    @FindBy(xpath = "(//button[contains(text(),' Cancel ')])")
    private WebElement Cancel;
    @FindBy(xpath = "(//button[contains(text(),'Save ')])")
    private WebElement Save;
    @FindBy(xpath = "(//span[contains(text(),'com')])")
    private WebElement comDes;
    @FindBy(xpath = "(//div[@data-automation='tooltip'])")
    private WebElement errTipMsg;
    @FindBy(xpath = "//input[@data-placeholder='Search']")
    private WebElement search;
    @FindBy(xpath = "(//mat-select[@role= 'combobox'])[1]")
    private WebElement combobox;
    @FindBy(xpath = "//core-icon[@icon='more_vert']")
    private WebElement option;
    @FindBy(xpath = "//span[contains(text(),'Edit')]")
    private WebElement edit;
    @FindBy(xpath = "//span[contains(text(),'Delete')]")
    private WebElement delete;
    @FindBy(xpath = "(//input[@type='text'])[2]")
    private WebElement dispName;
    @FindBy(xpath = "//div[@data-automation='snack-bar-message']")
    private WebElement confirmMessage;
    @FindBy(xpath = "//button[@data-automation='snack-bar-close']")
    private WebElement dismissConfirmMessage;
    @FindBy(xpath = "//button[contains(text(),'Confirm')]")
    private WebElement confirm;

    public TenantsPage(WebDriver driver, ReportGenerator reportGenerator) {
        this.driver = driver;
        this.reportGenerator = reportGenerator;
        PageFactory.initElements(driver, this);
    }

    public boolean edtDel(String opt) {
        boolean res1, res2, res3, res;
        searchTnt = tntName + ": " + tntDesc;
        selectChild(tntName,searchTnt);
        CommonUtils.sleepForAWhile();
        SeleniumUtils.doClick(driver, option);
        if (opt.equals("edit")) {
            tntDesc = tntDesc + " edited";
            res1 = SeleniumUtils.doClick(driver, edit) && SeleniumUtils.isEnabled(driver, Save, false) && SeleniumUtils.isDisplayed(driver, Cancel, true);
            reportGenerator.logAndCaptureScreen("verifying post edit click", "edtDel", res1, driver);
            res2 = SeleniumUtils.sendKeys(driver, dispName, tntDesc) && SeleniumUtils.isEnabled(driver, Save, true) && SeleniumUtils.isDisplayed(driver, Cancel, true);
            reportGenerator.logAndCaptureScreen("filling display name", "edtDel", res2, driver);
            res3 = SeleniumUtils.doClick(driver, Save) &&
                    ((SeleniumUtils.verifyText(driver, confirmMessage, "Tenant " + tntName + " successfully edited")) &&
                            (SeleniumUtils.doClick(driver, dismissConfirmMessage)));
            reportGenerator.logAndCaptureScreen("clicking on save button", "edtDel", res3, driver);
            res = res1 && res2 && res3;
            System.out.println("edit result "+res1+res2+res3);
        } else {
            res1 = SeleniumUtils.doClick(driver, delete);
            reportGenerator.logAndCaptureScreen("verifying post delete click", "edtDel", res1, driver);
            res2 = SeleniumUtils.doClick(driver, confirm) &&
                    ((SeleniumUtils.verifyText(driver, confirmMessage, "Tenant " + tntName + " successfully deleted")) &&
                            (SeleniumUtils.doClick(driver, dismissConfirmMessage)));
            reportGenerator.logAndCaptureScreen("verifying pop up messag", "edtDel", res2, driver);
            System.out.println("delete result "+res1+res2);
            res = res1 && res2;
        }
        return res;
    }

    public void selectChild(String partialtext, String tnt) {
        WebElement filtertenant;
        SeleniumUtils.doClick(driver, combobox);
        SeleniumUtils.sendKeys(driver, search, partialtext);
        CommonUtils.sleepForAWhile(3000);
        filtertenant = driver.findElement(By.xpath("//span[contains(text(), '" + tnt + "')]"));
        SeleniumUtils.doubleClick(driver, filtertenant);

    }

    public void addTnts(String usrdetails, String errdataMsg, String adtTxt, String tciTxt, String tntTxt, String disTxt, String adnUsrTxt, String mailTxt, String comTxt) {
        SeleniumUtils.doClick(driver, addTnts);
        System.out.println("Loan");
        System.out.println("adtTxt header= " + SeleniumUtils.verifyText(driver, adtDes, adtTxt));
        System.out.println("tciTxt header= " + SeleniumUtils.verifyText(driver, tciDes, tciTxt));
        System.out.println("tntTxt header= " + SeleniumUtils.verifyText(driver, tntDes, tntTxt));
        System.out.println("disTxt header= " + SeleniumUtils.verifyText(driver, disnDes, disTxt));
        System.out.println("adnUsrTxt header= " + SeleniumUtils.verifyText(driver, adnUsrDes, adnUsrTxt));
        System.out.println("mailTxt header= " + SeleniumUtils.verifyText(driver, mailDes, mailTxt));
        System.out.println("comTxt header= " + SeleniumUtils.verifyText(driver, comDes, comTxt));
        System.out.println(mailDes.getText());
        System.out.println("RegisterEnable" + SeleniumUtils.isEnabled(driver, Save, false));
        System.out.println("close" + SeleniumUtils.isDisplayed(driver, Cancel, true));
        List<String> data = new ArrayList<String>(Arrays.asList(usrdetails.split(",")));
        List<String> errMsg = new ArrayList<String>(Arrays.asList(errdataMsg.split("/")));
        List<WebElement> listOfElements = driver.findElements(By.xpath("(//input)"));
        if (errMsg.get(0).equals("Mandatory field")) {
            for (int i = 0; i < listOfElements.size(); i++) {
                SeleniumUtils.doClick(driver, listOfElements.get(i));
            }
            SeleniumUtils.doClick(driver, listOfElements.get(0));
        } else if (data.size() > 1) {
            for (int i = 0; i < listOfElements.size(); i++) {
                SeleniumUtils.sendKeys(driver, listOfElements.get(i), data.get(i));
            }
        } else {
            tntName = "automation" + RandomString.make(5).toLowerCase();
            tntDesc = "created " + tntName;

            boolean res = SeleniumUtils.sendKeys(driver, listOfElements.get(0), tntName) &&
                    SeleniumUtils.sendKeys(driver, listOfElements.get(1), tntDesc) &&
                    SeleniumUtils.sendKeys(driver, listOfElements.get(2), "anandhan") &&
                    SeleniumUtils.sendKeys(driver, listOfElements.get(3), "anandhan.s@loanlogics.com") &&
                    SeleniumUtils.isEnabled(driver, Save, true) && SeleniumUtils.isDisplayed(driver, Cancel, true);
            reportGenerator.logAndCaptureScreen("enteredd all fields of creating child tenant", "addtnts", res, driver);
            SeleniumUtils.doClick(driver,Save);
            SeleniumUtils.verifyText(driver, confirmMessage, "Tenant " + tntName + " successfully created");
                    SeleniumUtils.doClick(driver, dismissConfirmMessage);
                    CommonUtils.sleepForAWhile(60000);
            edtDel("edit");
            driver.navigate().refresh();
            edtDel("delete");
        }
        List<WebElement> errorTips = driver.findElements(By.xpath("//core-icon[@icon='blocked-error']"));
        if (errorTips.size() > 0) {
            reportGenerator.logAndCaptureScreen("cancel enabled & save disabled", "addtnts", SeleniumUtils.isEnabled(driver, Save, false) && SeleniumUtils.isDisplayed(driver, Cancel, true), driver);
            for (int i = 0; i < errorTips.size(); i++) {
                SeleniumUtils.doHover(driver, errorTips.get(i));
                reportGenerator.logAndCaptureScreen("verifying tooltip msgs", "addTnts", SeleniumUtils.verifyText(driver, errTipMsg, errMsg.get(i)), driver);
            }
        }


    }
}
