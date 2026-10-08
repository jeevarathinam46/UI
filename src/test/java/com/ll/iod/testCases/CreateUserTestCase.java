package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.management.TestNGTestRailUploader;
import com.ll.iod.page.AccountPage;
import com.ll.iod.page.HomePage;
import com.ll.iod.page.LoginPage;
import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.ConfigPropertyLoader;
import com.ll.iod.utils.EnvironmentPropertyLoader;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;


public class CreateUserTestCase extends Base {
    WebDriver driver;
    ReportGenerator reportGenerator;
    LoginPage loginPage;
    HomePage homePage;
    AccountPage accountPage;
    SoftAssert softAssert;
    String userName = "automation";

    public CreateUserTestCase() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
    }
    @AfterMethod
    public void tearDown(ITestResult iTestResult) {
        reportGenerator.endReport();
        TestNGTestRailUploader.uploadTestResultsToTestRail(iTestResult);

        //reportGenerator.endReport();
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "CsvMapDataProvider", dataProviderClass = AutomationTestDataProvider.class)
    public void createUser(ITestContext iTestContext, HashMap<String, String> hashmap) {
        {
            reportGenerator = new ReportGenerator();// new obj for rp to access the methos present in it
            reportGenerator.setupExtendedReport(hashmap.get("TestCaseNumber"), hashmap.get("TestCaseName"));
            //html path is set
            String url = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("tenantType"));
            String browser= ConfigPropertyLoader.getConfigValue("Browser");
            iTestContext.setAttribute("testDataMap", hashmap);
            driver = openBrowser(browser, url);
            loginPage = new LoginPage(driver,reportGenerator);
            /*opens the login page ------->moves to pipeline/home page ---->create user page*/
            homePage = loginPage.clickOnLoginButton(hashmap.get("username"), hashmap.get("password"));
            accountPage = homePage.clickOnAccount();
            /* Verifying the header of the users page with header column names such as 1stName, last name, contact email etc..*/
         /*   if (Boolean.parseBoolean(hashmap.get("verifyHeader"))) {
                reportGenerator.logAndCaptureScreen("verifying headers for user page","accountPage.verifyHeader",accountPage.verifyHeader(hashmap.get("fstNmHdr"), hashmap.get("lstNmHdr"), hashmap.get("usrNmHdr"), hashmap.get("cntMailHdr"), hashmap.get("ctdHdr"), hashmap.get("lstMfdHdr"), hashmap.get("acvHdr"), hashmap.get("actHdr")),driver);
            }*/
            /*click on create new user button*/
            reportGenerator.logAndCaptureScreen("clicking create new user buttn","accountPage.openCreateUser",accountPage.openCreateUser(),driver);
            /* verifying the text such as first name ,last name, email & username that is displayed before text-box */
           reportGenerator.logAndCaptureScreen("verifying title, usr title, 1st name,last name,username, email title","accountPage.verifyUsrTitleTxt",accountPage.verifyUsrTitleTxt(hashmap.get("title"), hashmap.get("userTitle"), hashmap.get("firstNameTxt"), hashmap.get("lastNameTxt"), hashmap.get("userNameTxt"), hashmap.get("emailTxt")),driver);
            /* Entering first name ,last name, email & username from CSV file, if username column is empty it will generate a unique username with prefix "Automation"*/
            if (hashmap.get("userName").equalsIgnoreCase("blank")) {
                userName = "";
            } else if (hashmap.get("userName").trim().isEmpty()) {
                userName = "Automation" + RandomString.make(5);
            } else {
                userName = hashmap.get("userName");
            }
            reportGenerator.logAndCaptureScreen("filling create new user fields","accountPage.fillUsrDetails",accountPage.fillUsrDetails(hashmap.get("firstName"), hashmap.get("lastName"), userName, hashmap.get("email")),driver);
            /* verifying whether cancel , save and close button has been enabled or disabled based on the data from CSV */
            reportGenerator.logAndCaptureScreen("Post filling validating save button enabled status","accountPage.verifySaveBttn",accountPage.verifySaveBttn(Boolean.parseBoolean(hashmap.get("isSaveEnabled")), Boolean.parseBoolean(hashmap.get("isCloseEnabled")), Boolean.parseBoolean(hashmap.get("isCancelEnabled"))),driver);
            /* Selecting roles such as doc-indexing, data-extraction,export , purge, IFRAME etc. from CSV*/
            reportGenerator.logAndCaptureScreen("selecting user roles based on data in csv file","accountPage.selectRoles",accountPage.selectRoles(hashmap.get("tenantType"), Boolean.parseBoolean(hashmap.get("docInd")), (Boolean.parseBoolean(hashmap.get("dataExt"))), (Boolean.parseBoolean(hashmap.get("expLoan"))), (Boolean.parseBoolean(hashmap.get("iodMgt"))), (Boolean.parseBoolean(hashmap.get("sysOpr"))), (Boolean.parseBoolean(hashmap.get("operateIfra"))), (Boolean.parseBoolean(hashmap.get("userMgt"))), (Boolean.parseBoolean(hashmap.get("sysAdmin"))), (Boolean.parseBoolean(hashmap.get("purgeLoan"))), (Boolean.parseBoolean(hashmap.get("autoRules"))), (Boolean.parseBoolean(hashmap.get("platAdmin"))), (Boolean.parseBoolean(hashmap.get("tntMgr"))), (Boolean.parseBoolean(hashmap.get("tntApprov")))),driver);
            if (Boolean.parseBoolean(hashmap.get("operateIfra"))) {
                reportGenerator.logAndCaptureScreen("validating iframe alert","accountPage.verifyIFrameAlert",accountPage.verifyIFrameAlert(hashmap.get("IframeAlert")),driver);
              }
            reportGenerator.logAndCaptureScreen("verifying close, cancel, save button in create new user page","accountPage.verifySaveBttn",accountPage.verifySaveBttn(Boolean.parseBoolean(hashmap.get("isSelectedSaveEnabled")), Boolean.parseBoolean(hashmap.get("isCloseEnabled")), Boolean.parseBoolean(hashmap.get("isCancelEnabled"))),driver);
            /* if the negative scenarios is true, it will verify the error-tool tip for the text boxes such as username, firstname, last name and email*/
            boolean negaScn_flag = Boolean.parseBoolean(hashmap.get("negativeScenario"));
            if (negaScn_flag) {
                accountPage.verifyErrToolTip(hashmap.get("firstNameError"), hashmap.get("lastNameError"), hashmap.get("userNameError"), hashmap.get("emailError"));
               // reportGenerator.logAndCaptureScreen("capturing tooltip message","accountPage.verifyErrToolTip",,driver);
              }

            /*clicking on cancel, save and close icon based on the condition provided from CSV, if we click on save is true, it will check for confirmation message, and it won't check for cancel and close icon */
            reportGenerator.logAndCaptureScreen("clicking save cancel or close button","accountPage.clickSaveClsCncl",accountPage.clickSaveClsCncl(Boolean.parseBoolean(hashmap.get("clickSave")), "User " + userName + " successfully added", Boolean.parseBoolean(hashmap.get("clickCloseIcon")), Boolean.parseBoolean(hashmap.get("clickCancel"))),driver);
  if (Boolean.parseBoolean(hashmap.get("verifyCreatedUser"))) {
                reportGenerator.logAndCaptureScreen("searching created user","accountPage.enterFltUsrNme",accountPage.enterFltUsrNme(userName),driver);
                reportGenerator.logAndCaptureScreen("verifying created user","accountPage.verifyCtdUser",accountPage.verifyCtdUser(hashmap.get("firstName"), hashmap.get("lastName"), userName, hashmap.get("email"), hashmap.get("usrAveStatus")),driver);
                reportGenerator.logAndCaptureScreen("click on edit icon of searched user","accountPage.clickEdt",accountPage.clickEdt(),driver);
                reportGenerator.logAndCaptureScreen("verifying edit title, usr title, 1st name,last name,username, email title","accountPage.verifyEdtUsrTitleTxt",accountPage.verifyEdtUsrTitleTxt(hashmap.get("editUsrTitle"), hashmap.get("userTitle"), hashmap.get("firstNameTxt"), hashmap.get("lastNameTxt"), hashmap.get("userNameTxt"), hashmap.get("emailTxt")),driver);
                reportGenerator.logAndCaptureScreen("verifying close, cancel, save button in edit user page","accountPage.verifyEdtSaveBttn",accountPage.verifyEdtSaveBttn(Boolean.parseBoolean(hashmap.get("isEditSaveEnabled")), Boolean.parseBoolean(hashmap.get("isCloseEnabled")), Boolean.parseBoolean(hashmap.get("isCancelEnabled"))),driver);
                reportGenerator.logAndCaptureScreen("selecting user roles in edit user page","accountPage.verifySelectedRoles",accountPage.verifySelectedRoles(hashmap.get("tenantType"), Boolean.parseBoolean(hashmap.get("docInd")), (Boolean.parseBoolean(hashmap.get("dataExt"))), (Boolean.parseBoolean(hashmap.get("expLoan"))), (Boolean.parseBoolean(hashmap.get("iodMgt"))), (Boolean.parseBoolean(hashmap.get("sysOpr"))), (Boolean.parseBoolean(hashmap.get("operateIfra"))), (Boolean.parseBoolean(hashmap.get("userMgt"))), (Boolean.parseBoolean(hashmap.get("sysAdmin"))), (Boolean.parseBoolean(hashmap.get("purgeLoan"))), (Boolean.parseBoolean(hashmap.get("autoRules"))), (Boolean.parseBoolean(hashmap.get("platAdmin"))), (Boolean.parseBoolean(hashmap.get("tntMgr"))), (Boolean.parseBoolean(hashmap.get("tntApprov")))),driver);
                reportGenerator.logAndCaptureScreen("clicking cancel button","accountPage.cancel",accountPage.cancel(),driver);

                if (Boolean.parseBoolean(hashmap.get("editUsrRole"))) {
                    reportGenerator.logAndCaptureScreen("cofirmation pop-up for deactivating user","accountPage.confPopUpDeActUsr",accountPage.confPopUpDeActUsr("User " + userName + " successfully deactivated"),driver);
                    reportGenerator.logAndCaptureScreen("cofirmation pop-up for activating user","accountPage.confPopUpActUsr",accountPage.confPopUpActUsr("User " + userName + " successfully activated"),driver);
                    reportGenerator.logAndCaptureScreen("click on edit button","accountPage.clickEdt",accountPage.clickEdt(),driver);
                    reportGenerator.logAndCaptureScreen("select role alert","accountPage.selectRoleAlert",accountPage.selectRoleAlert(hashmap.get("selectRoleAlert")),driver);
                    reportGenerator.logAndCaptureScreen("verifying post edited user's title, usr title, 1st name,last name,username, email title","accountPage.verifyEdtUsrTitleTxt",accountPage.verifyEdtUsrTitleTxt(hashmap.get("editUsrTitle"), hashmap.get("userTitle"), hashmap.get("firstNameTxt"), hashmap.get("lastNameTxt"), hashmap.get("userNameTxt"), hashmap.get("emailTxt")),driver);
                    reportGenerator.logAndCaptureScreen("verifying post edited  close, cancel, save button in edit user page","accountPage.verifyEdtSaveBttn",accountPage.verifyEdtSaveBttn(Boolean.parseBoolean(hashmap.get("isEditedSaveEnabled")), Boolean.parseBoolean(hashmap.get("isCloseEnabled")), Boolean.parseBoolean(hashmap.get("isCancelEnabled"))),driver);
                    reportGenerator.logAndCaptureScreen("selecting user roles post edited user","accountPage.selectRoles",accountPage.selectRoles(hashmap.get("tenantType"), Boolean.parseBoolean(hashmap.get("docInd")), (Boolean.parseBoolean(hashmap.get("dataExt"))), (Boolean.parseBoolean(hashmap.get("expLoan"))), (Boolean.parseBoolean(hashmap.get("iodMgt"))), (Boolean.parseBoolean(hashmap.get("sysOpr"))), (Boolean.parseBoolean(hashmap.get("operateIfra"))), (Boolean.parseBoolean(hashmap.get("userMgt"))), (Boolean.parseBoolean(hashmap.get("sysAdmin"))), (Boolean.parseBoolean(hashmap.get("purgeLoan"))), (Boolean.parseBoolean(hashmap.get("autoRules"))), (Boolean.parseBoolean(hashmap.get("platAdmin"))), (Boolean.parseBoolean(hashmap.get("tntMgr"))), (Boolean.parseBoolean(hashmap.get("tntApprov")))),driver);
                    reportGenerator.logAndCaptureScreen("verifying post edited  close, cancel, save button in edit user page","",accountPage.verifyEdtSaveBttn(Boolean.parseBoolean(hashmap.get("isEditSaveEnabled")), Boolean.parseBoolean(hashmap.get("isCloseEnabled")), Boolean.parseBoolean(hashmap.get("isCancelEnabled"))),driver);
                    reportGenerator.logAndCaptureScreen(" post edited saving user role","accountPage.confPopUpSaveUsr",accountPage.confPopUpSaveUsr("User " + userName + " successfully edited"),driver);
                 }
            }
        }

    }
}
