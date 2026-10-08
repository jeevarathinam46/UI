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

public class AccountPage {
    WebDriver driver;
    ReportGenerator reportGenerator=null;
    @FindBy(xpath = "//a[text()='Application Clients']")
    private WebElement appClient;
    @FindBy(xpath = "//div[text()=' First Name ']")
    private WebElement fstNameHeader;
    @FindBy(xpath = "//div[text()=' Last Name ']")
    private WebElement lstNameHeader;
    @FindBy(xpath = "//div[text()=' Username ']")
    private WebElement usrNameHeader;
    @FindBy(xpath = "//div[text()=' Contact Email ']")
    private WebElement cntEmailHeader;
    @FindBy(xpath = "//div[text()=' Created ']")
    private WebElement createdHeader;
    @FindBy(xpath = "//div[text()=' Last Modified ']")
    private WebElement lstMfdHeader;
    @FindBy(xpath = "//mat-header-cell[text()=' Active ']")
    private WebElement activeHeader;
    @FindBy(xpath = "//mat-header-cell[text()=' Action ']")
    private WebElement actionHeader;
    @FindBy(xpath = "//button[@data-automation='create-user-button']")
    private WebElement createUser;
    @FindBy(xpath = "//h3[text()='Add New User']")
    private WebElement title;
    @FindBy(xpath = "//p[contains(.,'Fill')]")
    private WebElement userTitle;
    @FindBy(xpath = "//h3[text()='Add New User']/following-sibling::core-icon[@icon='close']")
    private WebElement close;
    @FindBy(xpath = "//h3[text()='Edit User']/following-sibling::core-icon[@icon='close']")
    private WebElement edtClose;


    @FindBy(xpath = "//button[contains(text(),'CANCEL')]")
    private WebElement cancel;
    @FindBy(xpath = "//button[contains(text(),'SAVE')]")
    private WebElement save;
    @FindBy(xpath = "//span[text()='First Name']")
    private WebElement firstNameTxt;
    @FindBy(xpath = "//span[text()='Last Name']")
    private WebElement lastNameTxt;
    @FindBy(xpath = "//span[text()='Username']")
    private WebElement userNameTxt;
    @FindBy(xpath = "//span[text()='Email']")
    private WebElement emailTxt;
    @FindBy(xpath = "//div[@data-automation='snack-bar-message']")
    private WebElement confirmMessage;
    @FindBy(xpath = "//button[@data-automation='snack-bar-close']")
    private WebElement dismissConfirmMessage;
    @FindBy(xpath = "//input[@formcontrolname='firstName']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@formcontrolname='userName']")
    private WebElement userName;
    @FindBy(xpath = "//input[@formcontrolname='lastName']")
    private WebElement lastName;
    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement email;
    @FindBy(xpath = "//div[@data-automation='tooltip']")
    private WebElement tooTipMessage;
    @FindBy(xpath = "//input[@formcontrolname='firstName']//following::core-icon[1]")
    private WebElement firstNameError;
    @FindBy(xpath = "//input[@formcontrolname='userName']//following::core-icon[1]")
    private WebElement userNameError;
    @FindBy(xpath = "//input[@formcontrolname='lastName']//following::core-icon[1]")
    private WebElement lastNameError;
    @FindBy(xpath = "//input[@formcontrolname='email']//following::core-icon[1]")
    private WebElement emailError;
    @FindBy(xpath = "//div[text()='Doc Indexing']//preceding::input[1]")
    private WebElement docIndex;
    @FindBy(xpath = "//div[text()='Data Extraction']//preceding::input[1]")
    private WebElement dataExt;
    @FindBy(xpath = "//div[text()='Export Loan']//preceding::input[1]")
    private WebElement expLoan;
    @FindBy(xpath = "//div[text()='IOD Management']//preceding::input[1]")
    private WebElement iodMgt;
    @FindBy(xpath = "//div[text()='User Management']//preceding::input[1]")
    private WebElement userMgt;
    @FindBy(xpath = "//div[text()='System Administration']//preceding::input[1]")
    private WebElement sysAdmin;
    @FindBy(xpath = "//div[text()='Purge Loan']//preceding::input[1]")
    private WebElement purgeLoan;
    @FindBy(xpath = "//div[text()='Operate only via the Iframe']//preceding::input[1]")
    private WebElement iframe;
    @FindBy(xpath = "//span[contains(text(),'disabled from the standard UI')]")
    private WebElement IframeAlert;
    @FindBy(xpath = "//span[contains(text(),'Please select')]")
    private WebElement roleAlert;
    @FindBy(xpath = "//div[text()='Automated Rules']//preceding::input[1]")
    private WebElement ar;
    @FindBy(xpath = "//div[text()='Platform Administration']//preceding::input[1]")
    private WebElement platAdmin;
    @FindBy(xpath = "//div[text()='Tenant Approver']//preceding::input[1]")
    private WebElement tntApprover;
    @FindBy(xpath = "//div[text()='Tenant Manager']//preceding::input[1]")
    private WebElement tntMgr;
    @FindBy(xpath = "//div[text()='System Operator']//preceding::input[1]")
    private WebElement sysOpr;
    @FindBy(xpath = "//div[text()='Doc Indexing']//preceding::span[8]")
    private WebElement checkdocIndex;
    @FindBy(xpath = "//div[text()='Data Extraction']//preceding::span[8]")
    private WebElement checkdataExt;
    @FindBy(xpath = "//div[text()='Export Loan']//preceding::span[8]")
    private WebElement checkexpLoan;
    @FindBy(xpath = "//div[text()='IOD Management']//preceding::span[8]")
    private WebElement checkiodMgt;
    @FindBy(xpath = "//div[text()='User Management']//preceding::span[8]")
    private WebElement checkuserMgt;
    @FindBy(xpath = "//div[text()='System Administration']//preceding::span[8]")
    private WebElement checksysAdmin;
    @FindBy(xpath = "//div[text()='Purge Loan']//preceding::span[8]")
    private WebElement checkpurgeLoan;
    @FindBy(xpath = "//div[text()='Operate only via the Iframe']//preceding::span[8]")
    private WebElement checkiframe;
    @FindBy(xpath = "//div[text()='Automated Rules']//preceding::span[8]")
    private WebElement checkar;
    @FindBy(xpath = "//div[text()='Platform Administration']//preceding::span[8]")
    private WebElement checkplatAdmin;
    @FindBy(xpath = "//div[text()='Tenant Approver']//preceding::span[8]")
    private WebElement checktntApprover;
    @FindBy(xpath = "//div[text()='Tenant Manager']//preceding::span[8]")
    private WebElement checktntMgr;
    @FindBy(xpath = "//div[text()='System Operator']//preceding::span[8]")
    private WebElement checksysOpr;
    @FindBy(xpath = "//core-icon[@data-automation='filter-icon']")
    private WebElement fltIcon;
    @FindBy(xpath = "//input[@data-automation='filter-input']")
    private WebElement fltInp;
    @FindBy(xpath = "//core-icon[@data-automation='filter-close-icon']")
    private WebElement fltCls;
    @FindBy(xpath = "(//mat-row[@role='row'])[1]//mat-cell[1]//div")
    private WebElement chkEntFNme;
    @FindBy(xpath = "(//mat-row[@role='row'])[1]//mat-cell[2]//div")
    private WebElement chkEntLNme;
    @FindBy(xpath = "(//mat-row[@role='row'])[1]//mat-cell[3]//div")
    private WebElement chkEntUNme;
    @FindBy(xpath = "(//mat-row[@role='row'])[1]//mat-cell[4]//div")
    private WebElement chkEntEml;
    @FindBy(xpath = "(//mat-row[@role='row'])[1]//mat-cell[5]")
    private WebElement chkEntCtdDt;
    @FindBy(xpath = "(//mat-row[@role='row'])[1]//mat-cell[6]")
    private WebElement chkLstMfdDt;
    @FindBy(xpath = "(//mat-row[@role='row'])[1]//mat-cell[7]//div[1]//span")
    private WebElement chkAveStat;
    @FindBy(xpath = "(//mat-row[@role='row'])[1]//mat-cell[8]//core-icon")
    private WebElement chkEdtIcon;
    @FindBy(xpath = "//h3[text()='Edit User']")
    private WebElement chkEdtUsrHdr;

    public AccountPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }
    public AccountPage(WebDriver driver,ReportGenerator reportGenerator) {

        this.driver = driver;
        this.reportGenerator = reportGenerator;
        PageFactory.initElements(driver, this);

    }
    public ApplicationClientPage clickAppCltPg(){
        CommonUtils.sleepForAWhile(2000);
        SeleniumUtils.doClick(driver,appClient);
   //  reportGenerator.logAndCaptureScreen("moving to app client page","appclient",SeleniumUtils.doClick(driver,appClient),driver);
      return new ApplicationClientPage(driver,reportGenerator);
    }

    public boolean verifyHeader(String fName, String lName, String usrNm, String cntMail, String ctd, String lstMfd, String ave, String act) {
      CommonUtils.sleepForAWhile(2000);
        return ((SeleniumUtils.verifyText(driver, fstNameHeader, fName)) && (SeleniumUtils.verifyText(driver, lstNameHeader, lName)) && (SeleniumUtils.verifyText(driver, usrNameHeader, usrNm)) && (SeleniumUtils.verifyText(driver, cntEmailHeader, cntMail)) && (SeleniumUtils.verifyText(driver, createdHeader, ctd)) && (SeleniumUtils.verifyText(driver, lstMfdHeader, lstMfd)) && (SeleniumUtils.verifyText(driver, activeHeader, ave)) && (SeleniumUtils.verifyText(driver, actionHeader, act)));
    }

    public boolean verifyEdtUsrTitleTxt(String ttle, String usrTitle, String fNmeTxt, String lNmeTxt, String uNmeTxt, String eMailTxt) {
        return ((SeleniumUtils.verifyText(driver, chkEdtUsrHdr, ttle)) && (SeleniumUtils.verifyText(driver, userTitle, usrTitle)) && (SeleniumUtils.verifyText(driver, firstNameTxt, fNmeTxt)) && (SeleniumUtils.verifyText(driver, lastNameTxt, lNmeTxt)) && (SeleniumUtils.verifyText(driver, userNameTxt, uNmeTxt)) && (SeleniumUtils.verifyText(driver, emailTxt, eMailTxt)));
    }

    public boolean verifyUsrTitleTxt(String ttle, String usrTitle, String fNmeTxt, String lNmeTxt, String uNmeTxt, String eMailTxt) {
        return ((SeleniumUtils.verifyText(driver, title, ttle)) && (SeleniumUtils.verifyText(driver, userTitle, usrTitle)) && (SeleniumUtils.verifyText(driver, firstNameTxt, fNmeTxt)) && (SeleniumUtils.verifyText(driver, lastNameTxt, lNmeTxt)) && (SeleniumUtils.verifyText(driver, userNameTxt, uNmeTxt)) && (SeleniumUtils.verifyText(driver, emailTxt, eMailTxt)));
    }

    public boolean verifyEdtSaveBttn(boolean sv, boolean cls, boolean cncl) {
        return ((SeleniumUtils.isEnabled(driver, cancel, cncl)) && (SeleniumUtils.isEnabled(driver, save, sv)) && (SeleniumUtils.isEnabled(driver, edtClose, cls)));
    }

    public boolean verifySaveBttn(boolean sv, boolean cls, boolean cncl) {
        return ((SeleniumUtils.isEnabled(driver, cancel, cncl)) && (SeleniumUtils.isEnabled(driver, save, sv)) && (SeleniumUtils.isEnabled(driver, close, cls)));
    }

    public boolean confPopUpActUsr(String msg) {
        CommonUtils.sleepForAWhile(2000);
        boolean flag = ((SeleniumUtils.doClick(driver, chkAveStat)) && ((SeleniumUtils.verifyText(driver, confirmMessage, msg)) && (SeleniumUtils.doClick(driver, dismissConfirmMessage))));
        return flag;
    }

    public boolean confPopUpDeActUsr(String msg) {
        CommonUtils.sleepForAWhile(2000);
        boolean flag = ((SeleniumUtils.doClick(driver, chkAveStat)) && ((SeleniumUtils.verifyText(driver, confirmMessage, msg)) && (SeleniumUtils.doClick(driver, dismissConfirmMessage))));
        return flag;
    }

    public boolean confPopUpSaveUsr(String msg) {
        CommonUtils.sleepForAWhile(2000);
        boolean flag = ((SeleniumUtils.doClick(driver, save)) && ((SeleniumUtils.verifyText(driver, confirmMessage, msg)) && (SeleniumUtils.doClick(driver, dismissConfirmMessage))));
        return flag;
    }

    public boolean clickSaveClsCncl(boolean sv, String msg, boolean cls, boolean cncl) {
        boolean flag = false;
        if (sv) {
            flag = confPopUpSaveUsr(msg);
        } else if (cls) {
            flag = close();
        } else if (cncl) {
            flag = cancel();
        }
        return flag;
    }

    public boolean close() {
        return SeleniumUtils.doClick(driver, close);
    }

    public boolean cancel() {
        return SeleniumUtils.doClick(driver, cancel);
    }

    public boolean openCreateUser() {
        return SeleniumUtils.doClick(driver, createUser);
    }

    public boolean fillUsrDetails(String fName, String lName, String uName, String eml) {
        return ((SeleniumUtils.sendKeys(driver, firstName, fName)) && (SeleniumUtils.sendKeys(driver, lastName, lName)) && (SeleniumUtils.sendKeys(driver, userName, uName)) && (SeleniumUtils.sendKeys(driver, email, eml)));
    }

    public void verifyErrToolTip(String fName, String lName, String uName, String eml) {
        reportGenerator.logAndCaptureScreen("verifyErrToolTip fname","verifyErrToolTip",(verifyFirstNameError(fName)),driver);
        reportGenerator.logAndCaptureScreen("verifyErrToolTip lname","verifyErrToolTip",(verifyLastNameError(lName)),driver);
        reportGenerator.logAndCaptureScreen("verifyErrToolTip uname","verifyErrToolTip",(verifyUserNameError(uName)),driver);
        reportGenerator.logAndCaptureScreen("verifyErrToolTip email","verifyErrToolTip",(verifyEmailError(eml)),driver);
        //return b;
    }

    public boolean verifyFirstNameError(String message) {
        SeleniumUtils.doHover(driver, firstNameError);
        return SeleniumUtils.verifyText(driver, tooTipMessage, message);
    }

    public boolean verifyLastNameError(String message) {
        SeleniumUtils.doHover(driver, lastNameError);
        return SeleniumUtils.verifyText(driver, tooTipMessage, message);
    }

    public boolean verifyUserNameError(String message) {
        SeleniumUtils.doHover(driver, userNameError);
        return SeleniumUtils.verifyText(driver, tooTipMessage, message);
    }

    public boolean verifyEmailError(String message) {
        SeleniumUtils.doHover(driver, emailError);
        return SeleniumUtils.verifyText(driver, tooTipMessage, message);
    }


    public boolean selectRoles(String tnt, boolean di, boolean de, boolean el, boolean im, boolean so, boolean ifr, boolean um, boolean sa, boolean pl, boolean arl, boolean pa, boolean tm, boolean ta) {
        boolean flag = false;
        boolean default_Role = ((SeleniumUtils.doClickCondn(driver, checkdocIndex, di)) && (SeleniumUtils.doClickCondn(driver, checkdataExt, de)) && (SeleniumUtils.doClickCondn(driver, checkexpLoan, el)) && (SeleniumUtils.doClickCondn(driver, checkiodMgt, im)) && (SeleniumUtils.doClickCondn(driver, checkar, arl)) && (SeleniumUtils.doClickCondn(driver, checkiframe, ifr)));
        if (tnt.equalsIgnoreCase("std")) {
            boolean common_Role = ((SeleniumUtils.doClickCondn(driver, checkuserMgt, um)) && (SeleniumUtils.doClickCondn(driver, checksysAdmin, sa)) && (SeleniumUtils.doClickCondn(driver, checkpurgeLoan, pl)));
            flag = ((default_Role) && (common_Role));
        } else if (tnt.equalsIgnoreCase("admin")) {
            boolean common_Role = ((SeleniumUtils.doClickCondn(driver, checkuserMgt, um)) && (SeleniumUtils.doClickCondn(driver, checksysAdmin, sa)) && (SeleniumUtils.doClickCondn(driver, checkpurgeLoan, pl)));
            flag = ((default_Role) && (common_Role) && (SeleniumUtils.doClickCondn(driver, checkplatAdmin, pa)) && (SeleniumUtils.doClickCondn(driver, checktntApprover, ta)));
        } else if (tnt.equalsIgnoreCase("parent")) {
            boolean common_Role = ((SeleniumUtils.doClickCondn(driver, checkuserMgt, um)) && (SeleniumUtils.doClickCondn(driver, checksysAdmin, sa)) && (SeleniumUtils.doClickCondn(driver, checkpurgeLoan, pl)));
            flag = ((default_Role) && (common_Role) && (SeleniumUtils.doClickCondn(driver, checktntMgr, tm)));
        } else if (tnt.equalsIgnoreCase("child")) {
            flag = ((default_Role) && (SeleniumUtils.doClickCondn(driver, checksysOpr, so)));
        }
        return flag;

    }


    public boolean verifyIFrameAlert(String act) {
        return (SeleniumUtils.verifyText(driver, IframeAlert, act));
    }

    public boolean selectRoleAlert(String act) {
        return (SeleniumUtils.verifyText(driver, roleAlert, act));
    }

    public boolean enterFltUsrNme(String nme) {
        return ((SeleniumUtils.doClick(driver, fltIcon)) && (SeleniumUtils.sendKeys(driver, fltInp, nme)));
    }

    public boolean verifyCtdUser(String fNme, String lNme, String uNme, String email, String on) {
        CommonUtils.sleepForAWhile(3000);
        return ((SeleniumUtils.verifyText(driver, chkEntFNme, fNme)) && (SeleniumUtils.verifyText(driver, chkEntLNme, lNme)) && (SeleniumUtils.verifyText(driver, chkEntUNme, uNme)) && (SeleniumUtils.verifyText(driver, chkEntEml, email)) && (SeleniumUtils.verifyText(driver, chkAveStat, on)) && (SeleniumUtils.isEnabled(driver, chkEdtIcon)) && ((SeleniumUtils.getValue(driver, chkEntCtdDt)).equals((SeleniumUtils.getValue(driver, chkLstMfdDt)))));
    }

    public boolean clickEdt() {
        CommonUtils.sleepForAWhile(1000);
        return SeleniumUtils.doClick(driver, chkEdtIcon);
    }

    public boolean verifySelectedRoles(String tnt, boolean di, boolean de, boolean el, boolean im, boolean so, boolean ifr, boolean um, boolean sa, boolean pl, boolean arl, boolean pa, boolean tm, boolean ta) {
        boolean flag = false;
        boolean default_Role = ((SeleniumUtils.isSelected(driver, docIndex, di)) && (SeleniumUtils.isSelected(driver, dataExt, de)) && (SeleniumUtils.isSelected(driver, expLoan, el)) && (SeleniumUtils.isSelected(driver, iodMgt, im)) && (SeleniumUtils.isSelected(driver, ar, arl)) && (SeleniumUtils.isSelected(driver, iframe, ifr)));
        boolean common_Role = ((SeleniumUtils.isSelected(driver, userMgt, um)) && (SeleniumUtils.isSelected(driver, sysAdmin, sa)) && (SeleniumUtils.isSelected(driver, purgeLoan, pl)));
        if (tnt.equalsIgnoreCase("std")) {
            flag = ((default_Role) && (common_Role));
        } else if (tnt.equalsIgnoreCase("admin")) {
            flag = ((default_Role) && (common_Role) && (SeleniumUtils.isSelected(driver, platAdmin, pa)) && (SeleniumUtils.isSelected(driver, tntApprover, ta)));
        } else if (tnt.equalsIgnoreCase("parent")) {
            flag = ((default_Role) && (common_Role) && (SeleniumUtils.isSelected(driver, tntMgr, tm)));
        } else if (tnt.equalsIgnoreCase("child")) {
            flag = ((default_Role)  && (SeleniumUtils.isSelected(driver, sysOpr, so)));
        }
        return flag;

    }
    public boolean vrfySupportUserRoles(String roles)
    {
        boolean flag= true;
        SeleniumUtils.doClick(driver,createUser);

        List<String> roleList = new ArrayList<String>(Arrays.asList(roles.split(",")));
        //Collections.sort(optList);
        WebElement UserRoles;
        List<WebElement> listOfElements = driver.findElements(By.xpath("//mat-checkbox//following-sibling::div[1]"));
        System.out.println(listOfElements);
        CommonUtils.sleepForAWhile();
        for (int i = listOfElements.size()-1; i >=0; i--)
        {
            try
            {
                System.out.println(listOfElements.get(i).getText()+" "+roleList.get(i));
                if(listOfElements.get(i).getText().equalsIgnoreCase(roleList.get(i)))
                {
                    //System.out.println(SettingsOption.getText()+" "+optList.get(i));
                    flag&= true;
                }
                else
                {
                    flag&= false;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cancel();
        return flag;
    }
    public boolean vrfyusrs(String usrrls)
    {
        boolean flag =true;
        List<String> usrrlslst = new ArrayList<String>(Arrays.asList(usrrls.split(",")));
        SeleniumUtils.doClick(driver,createUser);
        List<WebElement> usropt = driver.findElements(By.xpath("(//input[@tabindex='0'])//following::div[1]"));
        //Collections.sort(usrrlslst);
        WebElement Userroles;
        System.out.println(usropt.size());
        CommonUtils.sleepForAWhile();
        int j=0;
        for (int i = 2; i <= usropt.size(); i++)
        {
            String path = "(//input[@tabindex='0'])[" + i + "]//following::div[1]";
            Userroles= driver.findElement(By.xpath(path)) ;
            System.out.println(Userroles.getText()+" "+usrrlslst.get(j));
            if (usrrlslst.get(j).equals(Userroles.getText())) {
                flag &= true;
            } else {
                flag = false;
            }
            j++;

        }
        return flag;
    }
}
