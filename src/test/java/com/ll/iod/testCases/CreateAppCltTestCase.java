package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.management.TestNGTestRailUploader;
import com.ll.iod.page.AccountPage;
import com.ll.iod.page.ApplicationClientPage;
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

public class CreateAppCltTestCase extends Base {
    WebDriver driver;
    ReportGenerator reportGenerator;
    LoginPage loginPage;
    HomePage homePage;
    AccountPage accountPage;
    ApplicationClientPage appCltPg;
    SoftAssert softAssert;

    public CreateAppCltTestCase() {
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

        if (driver != null) {
            driver.quit();
        }
    }

    @Test(dataProvider = "CsvMapDataProvider", dataProviderClass = AutomationTestDataProvider.class)
    public void createAppClt(ITestContext iTestContext, HashMap<String, String> hashmap) {
        boolean negativeScn = Boolean.parseBoolean(hashmap.get("negativeScn"));
        String appCltHdr = hashmap.get("appCltHdr");
        String accKyHdr = hashmap.get("accKyHdr");
        String crdDtHdr = hashmap.get("crdDtHdr");
        String lsMfdHdr = hashmap.get("lsMfdHdr");
        String actHdr = hashmap.get("actHdr");
        String vrfyCrtHdr = hashmap.get("vrfyCrtHdr");
        String vrfyCrtSubHdr = hashmap.get("vrfyCrtSubHdr");
        String vrfyCrtTxtBoxHdr = hashmap.get("vrfyCrtTxtBoxHdr");
        String appCltShortDes = hashmap.get("appCltShortDes");
        String edtAppCltName = null;
        String appCltName = hashmap.get("appCltName");
        if (appCltName.equalsIgnoreCase("blank")) {
            appCltName = "";
        } else if (appCltName.trim().isEmpty()) {
            appCltName = "Automation" + RandomString.make(5);
        } else {
            appCltName = hashmap.get("appCltName");
        }
        String errMsg = hashmap.get("errMsg");
        boolean isCloseIconEnabled = Boolean.parseBoolean(hashmap.get("isCloseIconEnabled"));
        boolean isCancelBtnEnabled = Boolean.parseBoolean(hashmap.get("isCancelBtnEnabled"));
        boolean isCreateBtnEnabled = Boolean.parseBoolean(hashmap.get("isCreateBtnEnabled"));
        boolean clkCreate = Boolean.parseBoolean(hashmap.get("clkCreate"));
        boolean vrfyCtdAppClnt = Boolean.parseBoolean(hashmap.get("vrfyCtdAppClnt"));
        boolean edtCtdAppClt = Boolean.parseBoolean(hashmap.get("edtCtdAppClt"));
        String appCltDtls = hashmap.get("appCltDtls");
        String ctdAppCltNmeHdr = hashmap.get("ctdAppCltNmeHdr");
        String ctdAssKyHdr = hashmap.get("ctdAssKyHdr");
        String ctdSctKyHdr = hashmap.get("ctdSctKyHdr");
        boolean dltCtdAppClt = Boolean.parseBoolean(hashmap.get("dltCtdAppClt"));
        reportGenerator = new ReportGenerator();// new obj for rp to access the methos present in it
        reportGenerator.setupExtendedReport(hashmap.get("TestCaseNumber"), hashmap.get("TestCaseName"));
        //html path is set
        String url = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("tenantType"));
        String browser= ConfigPropertyLoader.getConfigValue("Browser");
        iTestContext.setAttribute("testDataMap", hashmap);
        driver = openBrowser(browser, url);
        /*opens the login page ------->moves to pipeline/home page ---->create user page*/
        loginPage = new LoginPage(driver,reportGenerator);
        homePage = loginPage.clickOnLoginButton(hashmap.get("username"), hashmap.get("password"));
        accountPage = homePage.clickOnAccount();
        appCltPg = accountPage.clickAppCltPg();
       appCltPg.chkHdr(appCltHdr, accKyHdr, crdDtHdr, lsMfdHdr, actHdr);
        appCltPg.createNewAppClt();
        reportGenerator.logAndCaptureScreen("verify default icon","verifyDftIcon",appCltPg.verifyDftIcon(isCancelBtnEnabled, isCreateBtnEnabled, isCloseIconEnabled),driver);
        reportGenerator.logAndCaptureScreen("verify create app headers","chkCrtAppHdr",appCltPg.chkCrtAppHdr(vrfyCrtHdr, vrfyCrtSubHdr, vrfyCrtTxtBoxHdr, appCltShortDes),driver);
        reportGenerator.logAndCaptureScreen("search app client name","enterAppCltName",appCltPg.enterAppCltName(appCltName),driver);
        if (negativeScn) {
            reportGenerator.logAndCaptureScreen("verify app client error","verifyAppCltError",appCltPg.verifyAppCltError(errMsg),driver);
        }

        if (clkCreate) {
            reportGenerator.logAndCaptureScreen("click create app client","clkCrtAppClt",appCltPg.clkCrtAppClt(appCltName, appCltDtls, ctdAppCltNmeHdr, ctdAssKyHdr, ctdSctKyHdr),driver);
            if (vrfyCtdAppClnt) {
                reportGenerator.logAndCaptureScreen("verify created app client","vrfyCtdAppClnt",appCltPg.vrfyCtdAppClnt(appCltName),driver);
                if (edtCtdAppClt) {
                   edtAppCltName = "Automation" + RandomString.make(5);
                    reportGenerator.logAndCaptureScreen("edit created app client","edtCtdAppClt",appCltPg.edtCtdAppClt(appCltName, edtAppCltName),driver);
                }
                if (dltCtdAppClt) {
                    reportGenerator.logAndCaptureScreen("delete created app client","dltCtdAppClt",appCltPg.dltCtdAppClt(edtAppCltName),driver);
  }
            }
        }
    }
}