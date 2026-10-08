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

public class NotificationWbhkPage {
    public WebDriver driver;
    @FindBy(xpath = "//span[normalize-space()='Create Loan Notification Webhook']")
    private WebElement crtLnNotfWbk;
    @FindBy(xpath = "//span[normalize-space()='Create Export Notification Webhook']")
    private WebElement crtExpNotfWbk;
    @FindBy(xpath = "//span[normalize-space()='Create Income Notification Webhook']")
    private WebElement crtIncmNotfWbk;
    @FindBy(xpath = "//span[normalize-space()='Create Rules Notification Webhook']")
    private WebElement crtRlsNotfWbk;
    @FindBy(xpath = "//input[@type='text']")
    private WebElement entrWbhkUrl;
    @FindBy(css = "input[type='url']")
    private WebElement entrEdtWbhkUrl;
    // @FindBy(xpath = "//input[@type='url']")
    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement svEdtWbhkUrl;
    @FindBy(xpath = "//button[normalize-space()='Register']")
    private WebElement rgstr;
    @FindBy(xpath = "//button[normalize-space()='Close']")
    private WebElement cls;
    @FindBy(xpath = "//div[normalize-space()='Loan Notification']/preceding::div[1]")
    private WebElement rgtdLnURl;
    @FindBy(xpath = "//div[normalize-space()='Export Notification']/preceding::div[1]")
    private WebElement rgtdExpURl;
    @FindBy(xpath = "//div[normalize-space()='Income Notification']/preceding::div[1]")
    private WebElement rgtdIncURl;
    @FindBy(xpath = "//div[normalize-space()='Rules Notification']/preceding::div[1]")
    private WebElement rgtdRlsURl;
    @FindBy(xpath = "//div[normalize-space()='Loan Notification']/following::core-icon[@icon='edit'][1]")
    private WebElement edtLn;
    @FindBy(xpath = "//div[normalize-space()='Export Notification']/following::core-icon[@icon='edit'][1]")
    private WebElement edtExp;
    @FindBy(xpath = "//div[normalize-space()='Income Notification']/following::core-icon[@icon='edit'][1]")
    private WebElement edtInc;
    @FindBy(xpath = "//div[normalize-space()='Rules Notification']/following::core-icon[@icon='edit'][1]")
    private WebElement edtRls;
    @FindBy(xpath = "//div[normalize-space()='Loan Notification']/following::core-icon[@icon='trash'][1]")
    private WebElement delLn;
    @FindBy(xpath = "//div[normalize-space()='Export Notification']/following::core-icon[@icon='trash'][1]")
    private WebElement delExp;
    @FindBy(xpath = "//div[normalize-space()='Income Notification']/following::core-icon[@icon='trash'][1]")
    private WebElement delInc;
    @FindBy(xpath = "//div[normalize-space()='Rules Notification']/following::core-icon[@icon='trash'][1]")
    private WebElement delRls;
    @FindBy(xpath = "//button[contains(text(),'Confirm')]")
    private WebElement delCnfrm;
    @FindBy(xpath = "(//span[normalize-space()='Create Loan Notification Webhook'])[2]")
    private WebElement crtLnWbhkHdr;
    @FindBy(xpath = "(//span[normalize-space()='Create Export Notification Webhook'])[2]")
    private WebElement crtExpWbhkHdr;
    @FindBy(xpath = "(//span[normalize-space()='Create Income Notification Webhook'])[2]")
    private WebElement crtIncWbhkHdr;
    @FindBy(xpath = "(//span[normalize-space()='Create Rules Notification Webhook'])[2]")
    private WebElement crtRlsWbhkHdr;
    @FindBy(xpath = "//span[contains(text(),'Fill')]")
    private WebElement iodDes;
    @FindBy(xpath = "//span[contains(text(),'URL')]")
    private WebElement wbhUrl;
    @FindBy(xpath = "//div[contains(text(),'Example')]")
    private WebElement Example;
    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    private WebElement Cancel;
    @FindBy(xpath = "//button[contains(text(),'Register')]")
    private WebElement Register;
    @FindBy(xpath = "//core-icon[@icon='close']")
    private WebElement close;
    @FindBy(xpath = "//span[normalize-space()='Loan Notification Webhook Details']")
    private WebElement pstCrtLnHdr;
    @FindBy(xpath = "//span[normalize-space()='Export Notification Webhook Details']")
    private WebElement pstCrtExpHdr;
    @FindBy(xpath = "//span[normalize-space()='Income Notification Webhook Details']")
    private WebElement pstCrtIncHdr;
    @FindBy(xpath = "//span[normalize-space()='Rules Notification Webhook Details']")
    private WebElement pstCrtRlsHdr;
    @FindBy(xpath = "//div[contains(text(),'URL')]")
    private WebElement pstUrlHdr;

    //span[normalize-space()='Loan Notification Webhook Details']//following::core-icon[2]
    @FindBy(xpath = "//div[contains(text(),'URL')]//following::span[1]")
    private WebElement pstRgtdUrl;
    @FindBy(xpath = "//div[contains(text(),'Key')]")
    private WebElement sgnKyHdr;
    @FindBy(xpath = "//div[contains(text(),'Key')]//following::div[1]")
    private WebElement sgnKyDsc;
    @FindBy(xpath = "//div[contains(text(),'Key')]//following::span[1]")
    private WebElement sgnKy;
    @FindBy(xpath = "//core-icon[@icon='copy-text']")
    private WebElement cpySgnKy;
    @FindBy(xpath = "//mat-tooltip-component[@aria-hidden='true']/div")
    private WebElement vldMsg;
    @FindBy(xpath = "//div[normalize-space()='Loan Notification']/following::core-icon[@icon='visibility'][1]")
    private WebElement ntfPgLnEye;
    @FindBy(xpath = "//div[normalize-space()='Export Notification']/following::core-icon[@icon='visibility'][1]")
    private WebElement ntfPgExpEye;
    @FindBy(xpath = "//div[normalize-space()='Income Notification']/following::core-icon[@icon='visibility'][1]")
    private WebElement ntfPgIncEye;
    @FindBy(xpath = "//div[normalize-space()='Rules Notification']/following::core-icon[@icon='visibility'][1]")
    private WebElement ntfPgRlsEye;
    @FindBy(xpath = "//div[normalize-space()='Loan Notification']/following::a[@routerlink='new'][1]")
    private WebElement ntfPgLnSgn;
    @FindBy(xpath = "//div[normalize-space()='Export Notification']/following::a[@routerlink='new'][1]")
    private WebElement ntfPgExpSgn;
    @FindBy(xpath = "//div[normalize-space()='Income Notification']/following::a[@routerlink='new'][1]")
    private WebElement ntfPgIncSgn;
    @FindBy(xpath = "//div[normalize-space()='Rules Notification']/following::a[@routerlink='new'][1]")
    private WebElement ntfPgRlsSgn;
    ReportGenerator reportGenerator;
    public NotificationWbhkPage(WebDriver driver,ReportGenerator reportGenerator) {
        this.driver = driver;
        this.reportGenerator=reportGenerator;
        PageFactory.initElements(driver, this);
    }

    public boolean crtWbhk(String wbhkType, String url, String crtWbhHdr, String iodDesc, String wbhUrll, String pstCrtLnHd, String sgnKyHd, String sgnKyDscr, String errData, String errMsg) {

        System.out.println(wbhkType);
        /*
        verifying the -ve scenarios by fetching the data and error message from arrayList and validating the messages
         */
        List<String> errList = new ArrayList<String>(Arrays.asList(errData.split(",")));
        List<String> msgList = new ArrayList<String>(Arrays.asList(errMsg.split(",")));

        for (int i = 0; i <= errList.size(); i++) {
            if (wbhkType.equalsIgnoreCase("loan")) {
                SeleniumUtils.doClick(driver, crtLnNotfWbk);
                System.out.println("Loan header= " + SeleniumUtils.verifyText(driver, crtLnWbhkHdr, crtWbhHdr));
            } else if (wbhkType.equalsIgnoreCase("export")) {
                SeleniumUtils.doClick(driver, crtExpNotfWbk);
                System.out.println("Export header= " + SeleniumUtils.verifyText(driver, crtExpWbhkHdr, crtWbhHdr));
            } else if (wbhkType.equalsIgnoreCase("income")) {
                SeleniumUtils.doClick(driver, crtIncmNotfWbk);
                System.out.println("Income header= " + SeleniumUtils.verifyText(driver, crtIncWbhkHdr, crtWbhHdr));
            } else {
                SeleniumUtils.doClick(driver, crtRlsNotfWbk);
                System.out.println("Rules header= " + SeleniumUtils.verifyText(driver, crtRlsWbhkHdr, crtWbhHdr));
            }
            for (int j = i; j < msgList.size(); j++) {
                SeleniumUtils.sendKeys(driver, entrWbhkUrl, errList.get(j));
                System.out.print(errList.get(j) + " " + msgList.get(j));
                boolean b=((SeleniumUtils.doHover(driver, entrWbhkUrl)) && (SeleniumUtils.verifyText(driver, vldMsg, msgList.get(j))));
                reportGenerator.logAndCaptureScreen("validating create webhook error pop-ups","crtWbhk",b,driver);
                SeleniumUtils.doClick(driver, close);
                break;
            }
        }
        /*
        Registering the notification webhook based on the type
        verifying the description and other icons in create webhook pop-up
         */

        System.out.println("iodDesc header= " + SeleniumUtils.verifyText(driver, iodDes, iodDesc));
        System.out.println("wbhurll header= " + SeleniumUtils.verifyText(driver, wbhUrl, wbhUrll));
        System.out.println("close" + SeleniumUtils.isDisplayed(driver, close, true));
        System.out.println("Cancel" + SeleniumUtils.isDisplayed(driver, Cancel, true));
        SeleniumUtils.sendKeys(driver, entrWbhkUrl, url);
        System.out.println("RegisterEnable" + SeleniumUtils.isEnabled(driver, Register, true));
        reportGenerator.logAndCaptureScreen("clicking on webhook register button","crtntfwbhk",SeleniumUtils.doClick(driver, rgstr),driver);
        ;
         /*
        verifying the description and other icons in post create webhook pop-up
         */
        if (wbhkType.equalsIgnoreCase("loan"))
            System.out.println("Loan header pst= " + SeleniumUtils.verifyText(driver, pstCrtLnHdr, pstCrtLnHd));
        else if (wbhkType.equalsIgnoreCase("export")) {
            System.out.println("Export headerpst= " + SeleniumUtils.verifyText(driver, pstCrtExpHdr, pstCrtLnHd));
        } else if (wbhkType.equalsIgnoreCase("income")) {
            System.out.println("Income headerpst= " + SeleniumUtils.verifyText(driver, pstCrtIncHdr, pstCrtLnHd));
        } else {
            System.out.println("Rules headerpst= " + SeleniumUtils.verifyText(driver, pstCrtRlsHdr, pstCrtLnHd));
        }
        System.out.println(" head url" + SeleniumUtils.verifyText(driver, pstUrlHdr, wbhUrll));
        System.out.println("regtd url " + SeleniumUtils.verifyText(driver, pstRgtdUrl, url));
        System.out.println("sgn hdr " + SeleniumUtils.verifyText(driver, sgnKyHdr, sgnKyHd));
        System.out.println(" sgn desc" + SeleniumUtils.verifyText(driver, sgnKyDsc, sgnKyDscr));
        CommonUtils.sleepForAWhile(2000);
        System.out.println(" cpy sgn" + SeleniumUtils.verifyCopiedText(driver, sgnKy, cpySgnKy));
        SeleniumUtils.doClick(driver, cls);
         /*
        verifying the created webhook and signature key in webhook page
         */
        if (wbhkType.equalsIgnoreCase("loan")) {
            return SeleniumUtils.doClick(driver, ntfPgLnEye) && SeleniumUtils.verifyCopiedText(driver, ntfPgLnSgn, cpySgnKy) && (SeleniumUtils.verifyText(driver, rgtdLnURl, url));
        } else if (wbhkType.equalsIgnoreCase("export")) {
            return SeleniumUtils.doClick(driver, ntfPgExpEye) && SeleniumUtils.verifyCopiedText(driver, ntfPgExpSgn, cpySgnKy) && (SeleniumUtils.verifyText(driver, rgtdExpURl, url));
        } else if (wbhkType.equalsIgnoreCase("income")) {
            return SeleniumUtils.doClick(driver, ntfPgIncEye) && SeleniumUtils.verifyCopiedText(driver, ntfPgIncSgn, cpySgnKy) && (SeleniumUtils.verifyText(driver, rgtdIncURl, url));
        } else {
            return SeleniumUtils.doClick(driver, ntfPgRlsEye) && SeleniumUtils.verifyCopiedText(driver, ntfPgRlsSgn, cpySgnKy) && (SeleniumUtils.verifyText(driver, rgtdRlsURl, url));
        }
    }

    public boolean edtWbhk(String wbhkType, String edtUrl, String WbhkUrl, String errData, String errMsg) {
        /*
        verifying the -ve scenarios by fetching the data and error message from arrayList and validating the messages
         */
        List<String> errList = new ArrayList<String>(Arrays.asList(errData.split(",")));
        List<String> msgList = new ArrayList<String>(Arrays.asList(errMsg.split(",")));
       for (int i = 0; i <= errList.size(); i++) {
            if(wbhkType.equalsIgnoreCase("loan"))
            System.out.println("Loan header edit= "+  SeleniumUtils.doClick(driver,edtLn));
        else if (wbhkType.equalsIgnoreCase("export")) {
            System.out.println("Export headeredit= "+ SeleniumUtils.doClick(driver,edtExp));
        } else if (wbhkType.equalsIgnoreCase("income")) {
            System.out.println("Income headeredit= "+  SeleniumUtils.doClick(driver,edtInc));
        } else {
            System.out.println("Rules headeredit= "+  SeleniumUtils.doClick(driver,edtRls));
        }
            for (int j = i; j < msgList.size(); j++) {
                SeleniumUtils.sendKeys(driver,entrEdtWbhkUrl,errList.get(j));
                System.out.println(errList.get(j) + " "+msgList.get(j));
reportGenerator.logAndCaptureScreen("validating edit error pop-up","edtWbhk",((SeleniumUtils.doHover(driver,entrEdtWbhkUrl))&&(SeleniumUtils.verifyText(driver, vldMsg, msgList.get(j)))),driver);

                SeleniumUtils.doClick(driver,Cancel);
                break;
            }
        }
        if (wbhkType.equalsIgnoreCase("loan"))
            System.out.println("Loan header edit= " + SeleniumUtils.doClick(driver, edtLn));
        else if (wbhkType.equalsIgnoreCase("export")) {
            System.out.println("Export headeredit= " + SeleniumUtils.doClick(driver, edtExp));
        } else if (wbhkType.equalsIgnoreCase("income")) {
            System.out.println("Income headeredit= " + SeleniumUtils.doClick(driver, edtInc));
        } else {
            System.out.println("Rules headeredit= " + SeleniumUtils.doClick(driver, edtRls));
        }
        SeleniumUtils.sendKeys(driver, entrEdtWbhkUrl, edtUrl);
        SeleniumUtils.doClick(driver, svEdtWbhkUrl);
        CommonUtils.sleepForAWhile(2000);
        if (wbhkType.equalsIgnoreCase("loan")) {
            return (SeleniumUtils.verifyText(driver, rgtdLnURl, edtUrl));
        } else if (wbhkType.equalsIgnoreCase("export")) {
            return (SeleniumUtils.verifyText(driver, rgtdExpURl, edtUrl));
        } else if (wbhkType.equalsIgnoreCase("income")) {
            return (SeleniumUtils.verifyText(driver, rgtdIncURl, edtUrl));
        } else {
            return (SeleniumUtils.verifyText(driver, rgtdRlsURl, edtUrl));
        }
    }

    public boolean delWbhk(String wbhkType) {
        System.out.println("delelte");
        if (wbhkType.equalsIgnoreCase("loan")) {
            System.out.println("Loan headerdel= " + SeleniumUtils.doClick(driver, delLn));
        } else if (wbhkType.equalsIgnoreCase("export")) {
            System.out.println("Export headerdel= " + SeleniumUtils.doClick(driver, delExp));
        } else if (wbhkType.equalsIgnoreCase("income")) {
            System.out.println("Income headerdel= " + SeleniumUtils.doClick(driver, delInc));
        } else {
            System.out.println("Rules headerdel= " + SeleniumUtils.doClick(driver, delRls));
        }
        return SeleniumUtils.doClick(driver, delCnfrm);
    }

    public void dftPg() {

        List<WebElement> listOfElements = driver.findElements(By.xpath("//core-icon[@icon='trash']"));
        for (int i = listOfElements.size() - 1; i >= 0; i--) {
            try {
                if (i == 0) {
                    driver.findElement(By.xpath("(//core-icon[@icon='trash'])")).click();
                    SeleniumUtils.doClick(driver, delCnfrm);
                } else {
                    String path = "(//core-icon[@icon='trash'])" + "[" + (i) + "]";
                    driver.findElement(By.xpath(path)).click();
                    SeleniumUtils.doClick(driver, delCnfrm);
                    CommonUtils.sleepForAWhile(2000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}


