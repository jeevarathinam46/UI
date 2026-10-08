package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.management.TestNGTestRailUploader;
import com.ll.iod.page.AuditTrailPage;
import com.ll.iod.page.HomePage;
import com.ll.iod.page.LoginPage;
import com.ll.iod.page.SettingPage;
import com.ll.iod.report.ReportGenerator;
import com.ll.iod.tlistener.ScreenshotListener;
import com.ll.iod.utils.CommonUtils;
import com.ll.iod.utils.ConfigPropertyLoader;
import com.ll.iod.utils.EnvironmentPropertyLoader;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

@Listeners({ScreenshotListener.class})
public class AuditTrailTestCase extends Base {
    WebDriver driver;
    ReportGenerator reportGenerator;
    LoginPage loginPage;
    HomePage homePage;
    SettingPage settingPage;
    AuditTrailPage adtTrlPg;
    SoftAssert softAssert;

    public AuditTrailTestCase() {
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
    public void auditTrail(ITestContext iTestContext, HashMap<String, String> hashmap) {
        reportGenerator = new ReportGenerator();// new obj for rp to access the methos present in it
        reportGenerator.setupExtendedReport(hashmap.get("TestCaseNumber"),hashmap.get("TestCaseName"));
        downloadPath+=hashmap.get("TestCaseNumber");
        String adtTrlHdr = hashmap.get("adtTrlHdr");
        String adtTrlRfr = hashmap.get("adtTrlRfr");
        String txtBox = hashmap.get("txtBox");
        String uli=hashmap.get("uli"),pdfName;
        String errMsg = hashmap.get("errMsg");
        boolean negativeScn = Boolean.parseBoolean(hashmap.get("negativeScn"));
        iTestContext.setAttribute("testDataMap", hashmap);
        String browser= ConfigPropertyLoader.getConfigValue("Browser");
        String url = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("tenantType"));
        driver = openBrowser(browser,url);
        /*opens the login page ------->moves to pipeline/home page ---->create user page*/
        loginPage = new LoginPage(driver,reportGenerator);
        homePage = loginPage.clickOnLoginButton(hashmap.get("username"), hashmap.get("password"));
        if(!negativeScn){
            uli = CommonUtils.getUniqueText();
            pdfName = hashmap.get("pdf");
            reportGenerator.logAndCaptureScreen("creating loan","createLoan",homePage.createLoan("anandhan", "s", uli, System.getProperty("user.dir") + "\\src\\test\\java\\com\\ll\\iod\\sample\\loans\\" + pdfName + ".pdf"),driver);
      driver.navigate().refresh();
        }
        settingPage = homePage.clickOnSetting();
        adtTrlPg = settingPage.clkAdtTrl();
        System.out.println(adtTrlPg.vrfyDftAdtPg(adtTrlHdr, adtTrlRfr, txtBox));
        System.out.println(uli);
        if ((negativeScn) && (uli.trim().isEmpty())) {
            uli = "Automation" + RandomString.make(5);
            reportGenerator.logAndCaptureScreen("invalid ULI check","verifyInvdRef",adtTrlPg.verifyInvdRef(uli, errMsg),driver);
         } else if (negativeScn) {
            reportGenerator.logAndCaptureScreen("invalid character check","verifyAdtrlError",adtTrlPg.verifyAdtrlError(uli, errMsg),driver);
            } else {
            // System.out.println(folder.getAbsolutePath());
            reportGenerator.logAndCaptureScreen("","",adtTrlPg.verifyVldRef(uli, errMsg),driver);
           }
    }
}