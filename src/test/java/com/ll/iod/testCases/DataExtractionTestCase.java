package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.management.TestNGTestRailUploader;
import com.ll.iod.page.DataExtractionPage;
import com.ll.iod.page.HomePage;
import com.ll.iod.page.LoginPage;
import com.ll.iod.page.SettingPage;
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

public class DataExtractionTestCase extends Base {
    WebDriver driver;
    ReportGenerator reportGenerator;
    LoginPage loginPage;
    HomePage homePage;
    SettingPage settingPage;
    DataExtractionPage dtExtPg;
    SoftAssert softAssert;
    public DataExtractionTestCase() {
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
    public void dataExt(ITestContext iTestContext, HashMap<String, String> hashmap) {
        String lnSts, diSts, deSts, diExp, deExp,uli,pdfName,autoDE;
        lnSts = hashmap.get("lnSts");
        diSts = hashmap.get("diSts");
        deSts = hashmap.get("deSts");
        diExp = hashmap.get("diExp");
        deExp = hashmap.get("deExp");
        autoDE=hashmap.get("autoDE");
        reportGenerator = new ReportGenerator();// new obj for rp to access the methos present in it
        reportGenerator.setupExtendedReport(hashmap.get("TestCaseNumber"),hashmap.get("TestCaseName"));
        //html path is set
        String browser= ConfigPropertyLoader.getConfigValue("Browser");
        String url = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("tenantType"));
        iTestContext.setAttribute("testDataMap", hashmap);
        driver = openBrowser(browser, url);

        /*opens the login page ------->moves to pipeline/home page ---->create user page*/
        loginPage = new LoginPage(driver,reportGenerator);
        homePage = loginPage.clickOnLoginButton(hashmap.get("username"), hashmap.get("password"));
        settingPage = homePage.clickOnSetting();
        dtExtPg=settingPage.clkDePg();
        reportGenerator.logAndCaptureScreen("status of data extraction","dtExtPg.enableDE",dtExtPg.enableDE(autoDE),driver);
       dtExtPg.clickPipeline();
        uli = CommonUtils.getUniqueText();
        pdfName = hashmap.get("pdfName");
        reportGenerator.logAndCaptureScreen("creating loan","createLoan",homePage.createLoan("anandhan", "s", uli, System.getProperty("user.dir") + "\\src\\test\\java\\com\\ll\\iod\\sample\\loans\\" + pdfName + ".pdf"),driver);

        reportGenerator.logAndCaptureScreen("verifying status of di de after uploading pdf","verifyDiDeStatus",homePage.verifyDiDeStatus(uli, lnSts, diSts, deSts, diExp, deExp),driver);

    }
}
