package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.management.TestNGTestRailUploader;
import com.ll.iod.page.*;
import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.ConfigPropertyLoader;
import com.ll.iod.utils.EnvironmentPropertyLoader;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class TenantApprovalTestCase extends Base {
    WebDriver driver;
    LoginPage loginPage;
    ReportGenerator reportGenerator;
    HomePage homePage;
    SettingPage settingPage;
    TenantApprovalPage tntApvPg;
    TenantRegsPage tntRegtPg;

    public TenantApprovalTestCase() {
        super();

    }

    SoftAssert softAssert;

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
    public void tntAprl(ITestContext iTestContext, HashMap<String, String> hashmap) {
        reportGenerator = new ReportGenerator();// new obj for rp to access the methos present in it
        reportGenerator.setupExtendedReport(hashmap.get("TestCaseNumber"), hashmap.get("TestCaseName"));
        //html path is set
        String url1 = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("tntRegtURL"));
        String browser = ConfigPropertyLoader.getConfigValue("Browser");
        iTestContext.setAttribute("testDataMap", hashmap);
        driver = openBrowser(browser, url1);
        String cmpnyNm, strtAdd, cty, zpCd, state, phnNo, cmmnts, fstNm, lstNm, phnNum, email, action;
        cmpnyNm = "Automation" + RandomString.make(4);
        strtAdd = hashmap.get("strtAdd");
        cty = hashmap.get("cty");
        zpCd = hashmap.get("zpCd");
        state = hashmap.get("state");
        phnNo = hashmap.get("phnNo");
        cmmnts = hashmap.get("cmmnts");
        fstNm = hashmap.get("fstNm");
        lstNm = hashmap.get("lstNm");
        phnNum = hashmap.get("phnNum");
        email = hashmap.get("email");
        action = hashmap.get("action");
        System.out.println("action " + action);
        tntRegtPg = new TenantRegsPage(driver, reportGenerator);
        tntRegtPg.enterDtlls(cmpnyNm, strtAdd, cty, zpCd, state, phnNo, cmmnts, fstNm, lstNm, phnNum, email);
        if (!(action.isEmpty())) {
        String url = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("tenantType"));
        driver.switchTo().newWindow(WindowType.TAB);
// Opens LambdaTest homepage in the newly opened tab
        driver.navigate().to(url);
        /*opens the login page ------->moves to pipeline/home page ---->create user page*/
        loginPage = new LoginPage(driver, reportGenerator);
        homePage = loginPage.clickOnLoginButton(hashmap.get("username"), hashmap.get("password"));
        settingPage = homePage.clickOnSetting();
        tntApvPg = settingPage.clktntAprl();
            tntApvPg.tntComment(cmpnyNm);
            if (action.equals("deny")) {
            tntApvPg.tntDenyConf(cmpnyNm);
        } else if (action.equals("approve")) {
            tntApvPg.tntApproveDny(cmpnyNm);
            tntApvPg.tntApproveConf(cmpnyNm);
        }
       }
    }
}
