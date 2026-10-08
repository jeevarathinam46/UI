package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.management.TestNGTestRailUploader;
import com.ll.iod.page.AccountPage;
import com.ll.iod.page.HomePage;
import com.ll.iod.page.LoginPage;
import com.ll.iod.page.SettingPage;
import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.ConfigPropertyLoader;
import com.ll.iod.utils.EnvironmentPropertyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.HashMap;


public class LoginTestCase extends Base {
    ReportGenerator reportGenerator = null;
String outputPath;
    LoginPage loginPage;
    HomePage homePage;
    SettingPage settingPage;
    boolean res;
    AccountPage accountPage;
    WebDriver driver;
    SoftAssert softAssert;

    // ReportGenerator reportGenerator;
    public LoginTestCase() {
        super();
    }

    @BeforeMethod
    public void setUp() {
        //reportGenerator = new ReportGenerator();
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
@BeforeTest
public void doBeforeTest(){
    //FileIOHandler.deleteFiles(baseReportDir);;
}
//uploadRes(zipFile);


   @Test(dataProvider = "CsvMapDataProvider", dataProviderClass = AutomationTestDataProvider.class)
    public void invalidTestLogin(ITestContext iTestContext, HashMap<String,String> hashmap, Method method) {
       reportGenerator = new ReportGenerator();// new obj for rp to access the methos present in it
       reportGenerator.setupExtendedReport(hashmap.get("TestCaseNumber"), hashmap.get("TestCaseName"));
       //html path is set

        String url = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("tenantType"));
        String browser= ConfigPropertyLoader.getConfigValue("Browser");

        iTestContext.setAttribute("testDataMap", hashmap);
        driver = openBrowser(browser, url);
        loginPage = new LoginPage(driver,reportGenerator);

        String errorMessage = hashmap.get("ErrorMessage");
        if (!(errorMessage.trim().isEmpty())) {

            if ((hashmap.get("username")).equalsIgnoreCase("whitespace")) {
                res=(loginPage.invalidCredLogin(" ", hashmap.get("password")).equals(errorMessage));
                reportGenerator.logAndCaptureScreen("Login Page Error message for username with whitespace","invalidCredLogin",res,driver);

            } else if ((hashmap.get("password")).equalsIgnoreCase("whitespace")) {
               res= (loginPage.invalidCredLogin(hashmap.get("username"), " ").equals(errorMessage));
                reportGenerator.logAndCaptureScreen("Login Page Error message for password with whitespace","invalidCredLogin",res,driver);
            } else {
                res=(loginPage.invalidCredLogin(hashmap.get("username"),hashmap.get("password")).equals(errorMessage));
                reportGenerator.logAndCaptureScreen("Login Page Error message for incorrect username and password","invalidCredLogin",res,driver);
            }
        } else {
            homePage = loginPage.clickOnLoginButton(hashmap.get("username"), hashmap.get("password"));

            boolean setting_flag = Boolean.parseBoolean(hashmap.get("settingPage"));
            boolean user_flag = Boolean.parseBoolean(hashmap.get("usersPage"));
            boolean theme_flag = Boolean.parseBoolean(hashmap.get("theme"));
            boolean noti_flag = Boolean.parseBoolean(hashmap.get("notification"));
            boolean loan_flag = Boolean.parseBoolean(hashmap.get("createNew"));
            res=homePage.chkHomePgIcons(setting_flag, user_flag, theme_flag, noti_flag, loan_flag);
            reportGenerator.logAndCaptureScreen("verified home page icons setting_flag, user_flag, theme_flag, noti_flag, loan_flag","chkHomePgIcons",res,driver);

            if (setting_flag) {
                settingPage = homePage.clickOnSetting();
                reportGenerator.logAndCaptureScreen("verified setting option based on tenant "+hashmap.get("tenantType")+hashmap.get("settingsOpts"),"vrfystngopt",settingPage.vrfystngsopt(hashmap.get("settingsOpts")),driver);
            }
            if (user_flag) {
                accountPage = homePage.clickOnAccount();
                reportGenerator.logAndCaptureScreen("verified user option based on tenant "+hashmap.get("tenantType")+hashmap.get("settingsOpts"),"vrfyusrs",accountPage.vrfyusrs(hashmap.get("usrOpts")),driver);
            }
        }

       if(!res){
           TestNGTestRailUploader.uploadTestResultsToTestRail("Failed", hashmap.get("TestCaseNumber"));

       }
    }
}
