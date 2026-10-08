package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.management.TestNGTestRailUploader;
import com.ll.iod.page.HomePage;
import com.ll.iod.page.LoginPage;
import com.ll.iod.page.SettingPage;
import com.ll.iod.page.TenantsPage;
import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.CommonUtils;
import com.ll.iod.utils.ConfigPropertyLoader;
import com.ll.iod.utils.EnvironmentPropertyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class TenantsTestCase extends Base {
    WebDriver driver;
    ReportGenerator reportGenerator;
    LoginPage loginPage;
    HomePage homePage;
    SettingPage settingPage;
    TenantsPage tntPg;
    SoftAssert softAssert;

    public TenantsTestCase() {
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
    public void tnts(ITestContext iTestContext, HashMap<String, String> hashmap) {
        reportGenerator = new ReportGenerator();// new obj for rp to access the methos present in it
        reportGenerator.setupExtendedReport(hashmap.get("TestCaseNumber"), hashmap.get("TestCaseName"));
        downloadPath += hashmap.get("TestCaseNumber");
        iTestContext.setAttribute("testDataMap", hashmap);
        String usrdetails, errdataMsg, adtDes, tciDes, tntDes, disnDes, adnUsrDes, mailDes, comDes;
        adtDes = hashmap.get("adtDes");
        tciDes = hashmap.get("tciDes");
        tntDes = hashmap.get("tntDes");
        disnDes = hashmap.get("disnDes");
        adnUsrDes = hashmap.get("adnUsrDes");
        usrdetails = hashmap.get("usrdetails");
        mailDes = hashmap.get("mailDes");
        comDes = hashmap.get("comDes");
        errdataMsg = hashmap.get("errdataMsg");
        String browser = ConfigPropertyLoader.getConfigValue("Browser");
        String url = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("tenantType"));
        driver = openBrowser(browser, url);
        /*opens the login page ------->moves to pipeline/home page ---->create user page*/
        loginPage = new LoginPage(driver, reportGenerator);
        homePage = loginPage.clickOnLoginButton(hashmap.get("username"), hashmap.get("password"));
        settingPage = homePage.clickOnSetting();
        tntPg = settingPage.clktnts();
        tntPg.addTnts(usrdetails, errdataMsg, adtDes, tciDes, tntDes, disnDes, adnUsrDes, mailDes, comDes);
        CommonUtils.sleepForAWhile();


    }
}

