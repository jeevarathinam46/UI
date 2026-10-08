package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.management.TestNGTestRailUploader;
import com.ll.iod.page.*;
import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.CommonUtils;
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

public class SupportUserTestCase extends Base {
    LoginPage loginPage;
    ReportGenerator reportGenerator;
    HomePage homePage;
    SettingPage setPg;
    AccountPage accPg;
    ApplicationClientPage appClPg;
    WebDriver driver;
    SoftAssert softAssert;

    public SupportUserTestCase() {
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
    public void verifysupportuser(ITestContext iTestContext, HashMap<String, String> hashmap) {
        reportGenerator = new ReportGenerator();// new obj for rp to access the methos present in it
        reportGenerator.setupExtendedReport(hashmap.get("TestCaseNumber"), hashmap.get("TestCaseName"));
        //html path is set
        String browser = ConfigPropertyLoader.getConfigValue("Browser");
        String url = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("tenantType"));
        iTestContext.setAttribute("testDataMap", hashmap);
        String uli = CommonUtils.getUniqueText();
        String selectedTntUrl = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("selectedTntType"));
        String appCltName = "Automation" + RandomString.make(5);
        String env = ConfigPropertyLoader.getConfigValue("environment");
        driver = openBrowser(browser, selectedTntUrl);
        loginPage = new LoginPage(driver, reportGenerator);
        homePage = loginPage.clickOnLoginButton(hashmap.get("tntUserName"), hashmap.get("tntPassword"));
        homePage.createLoan("Sivani", "V", uli, System.getProperty("user.dir") + "\\src\\test\\java\\com\\ll\\iod\\sample\\loans\\BankStatements.pdf");
        CommonUtils.sleepForAWhile();
        driver.navigate().refresh();
        CommonUtils.sleepForAWhile();
        accPg = homePage.clickOnAccount();
        appClPg = accPg.clickAppCltPg();
        reportGenerator.logAndCaptureScreen("create an app client", "appClPg.createAppClnt", appClPg.createAppClnt(appCltName), driver);
        homePage.usrLogOut();
        driver.switchTo().newWindow(WindowType.TAB);
// Opens LambdaTest homepage in the newly opened tab
        driver.navigate().to(url);
        CommonUtils.sleepForAWhile();
        //   driver = openBrowser(browser, url);
        /*opens the login page ------->moves to pipeline/home page ---->create user page*/
        loginPage = new LoginPage(driver, reportGenerator);
        System.out.println(selectedTntUrl);
        homePage = loginPage.clickOnLoginButton(hashmap.get("userName"), hashmap.get("password"));
        homePage.selecttenants(hashmap.get("partialText" + env), hashmap.get("fullTntName" + env));
        System.out.println("Search and Verify Loan:" + homePage.searchAndVerifyUli(uli));
        CommonUtils.sleepForAWhile();
        setPg = homePage.clickOnSetting();
        CommonUtils.sleepForAWhile();
        //setPg.vrfySupportSettings(hashmap.get("settingsOpt"));
        System.out.println("Status of verify settings opt:" + setPg.vrfySupportSettings(hashmap.get("selectedTntType"), hashmap.get("settingsOpt")));
        accPg = setPg.stngToUsrPg();
        CommonUtils.sleepForAWhile();
        System.out.println("Status of verify user roles:" + accPg.vrfySupportUserRoles(hashmap.get("userRoles")));
        appClPg = accPg.clickAppCltPg();
        System.out.println("Status of verified appclnt in support:" + appClPg.vrfyCtdAppClntSupport(appCltName));
    }

}
