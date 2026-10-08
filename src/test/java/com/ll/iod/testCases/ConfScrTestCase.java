package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.management.TestNGTestRailUploader;
import com.ll.iod.page.*;
import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.CommonUtils;
import com.ll.iod.utils.ConfigPropertyLoader;
import com.ll.iod.utils.EnvironmentPropertyLoader;
import com.ll.iod.utils.FileIOHandler;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static com.ll.iod.report.ReportGenerator.BASE_REPORT_DIR;


public class ConfScrTestCase extends Base {
    WebDriver driver;
    ReportGenerator reportGenerator = null;
    LoginPage loginPage;
    HomePage homePage;
    SettingPage settingPage;
    ConfidenceScorePage confScrPg;
    DataExtractionPage dtExtPg;
    SoftAssert softAssert;
String outputPath;
    public ConfScrTestCase() {
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
    @BeforeTest
    public void doBeforeTest(){
        FileIOHandler.deleteFiles(BASE_REPORT_DIR);
    }

    @Test(dataProvider = "CsvMapDataProvider", dataProviderClass = AutomationTestDataProvider.class)
    public void confScr(ITestContext iTestContext,HashMap<String, String> hashmap,Method method) {
        reportGenerator = new ReportGenerator();// new obj for rp to access the methos present in it
        reportGenerator.setupExtendedReport(hashmap.get("TestCaseNumber"),hashmap.get("TestCaseName"));
        //html path is set
        String browser= ConfigPropertyLoader.getConfigValue("Browser");
        String url = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("tenantType"));
        iTestContext.setAttribute("testDataMap", hashmap);
        String lnSts, diSts, deSts, diExp, deExp, pdfName, uli, csHdr, csDsc, gcHdr, gcDsc, scHdr, inf, addDoc, noDoc, dftDiVl, dftDeVl, docTypes, attribute, rstConfMsg, rstConfHdr, svConfMsg;
        boolean dftCs, mnlCs, dftGcsAfAp, diAf, diAp, deAf, deAp, setGcDiDeVal, save, reset, addDocTypes;
        Integer di, de;
        driver = openBrowser(browser, url);
        /*opens the login page ------->moves to pipeline/home page ---->create user page*/
        loginPage = new LoginPage(driver,reportGenerator);
        homePage = loginPage.clickOnLoginButton(hashmap.get("username"), hashmap.get("password"));
        settingPage = homePage.clickOnSetting();
        confScrPg = settingPage.clkConfScr();
        csHdr = hashmap.get("csHdr");
        csDsc = hashmap.get("csDsc");
        gcHdr = hashmap.get("gcHdr");
        gcDsc = hashmap.get("gcDsc");
        scHdr = hashmap.get("scHdr");
        inf = hashmap.get("inf");
        addDoc = hashmap.get("addDoc");
        noDoc = hashmap.get("noDoc");
        dftDiVl = hashmap.get("dftDiVl");
        dftDeVl = hashmap.get("dftDeVl");
        docTypes = hashmap.get("docTypes");
        dftCs = Boolean.parseBoolean(hashmap.get("dftConfScr"));
        mnlCs = Boolean.parseBoolean(hashmap.get("manualConfScr"));
        dftGcsAfAp = Boolean.parseBoolean(hashmap.get("dftGcsAfAp"));
        svConfMsg = hashmap.get("svConfMsg");
        diAf = Boolean.parseBoolean(hashmap.get("diAf"));
        diAp = Boolean.parseBoolean(hashmap.get("diAp"));
        deAf = Boolean.parseBoolean(hashmap.get("deAf"));
        deAp = Boolean.parseBoolean(hashmap.get("deAp"));
        setGcDiDeVal = Boolean.parseBoolean(hashmap.get("setGcDiDeVal"));
        save = Boolean.parseBoolean(hashmap.get("save"));
        reset = Boolean.parseBoolean(hashmap.get("reset"));
        rstConfHdr = hashmap.get("rstConfHdr");
        rstConfMsg = hashmap.get("rstConfMsg");
        lnSts = hashmap.get("lnSts");
        diSts = hashmap.get("diSts");
        deSts = hashmap.get("deSts");
        diExp = hashmap.get("diExp");
        deExp = hashmap.get("deExp");
        addDocTypes = Boolean.parseBoolean(hashmap.get("addDocTypes"));

        attribute = hashmap.get("attribute");

        /*
        if the conf scr page is other than default format,
        then the default confidence score page format is set.
         */
        confScrPg.delDocTypes();
        reportGenerator.logAndCaptureScreen("setting conf score page to default format","dftOrMnl", confScrPg.dftOrMnl(true, false, svConfMsg),driver);

        /*
        verifying Global Confidence Score page icons
        a) use default conf score page elements such as always fail and pass check box , slider icon with values disabled
         b) Manually set conf score page elements such as always fail and pass check box , slider icon with values were enabled
         */
        reportGenerator.logAndCaptureScreen("verifying Global Confidence Score page icons","selectGlblConfScTyp",confScrPg.selectGlblConfScTyp(dftCs, mnlCs, csHdr, csDsc, gcHdr, gcDsc, scHdr, inf, dftDiVl, dftDeVl, svConfMsg, dftGcsAfAp, addDoc),driver);
      /*
     verifying Manually set conf score page functionality such as
     a) DI & DE - always pass & always fail check box.
     b) sliding the DI & DE slider with the values provided in the excel.
      */
        if (mnlCs) {
            reportGenerator.logAndCaptureScreen("selecting global conf score check boxes","slctGcCb",confScrPg.slctGcCb(diAf, diAp, deAf, deAp),driver);

            //  System.out.println(confScrPg.selectDocTypes(strList(docTypes)));
            if (setGcDiDeVal) {
                di = Integer.parseInt(hashmap.get("setDiValue"));
                de = Integer.parseInt(hashmap.get("setDeValue"));
                reportGenerator.logAndCaptureScreen("setting di de slider","setGcDiDeSldr",confScrPg.setGcDiDeSldr(di, de),driver);
            }
            if (addDocTypes) {
                reportGenerator.logAndCaptureScreen("selecting doc types","selectDocTypes",confScrPg.selectDocTypes(strList(docTypes)),driver);
                CommonUtils.sleepForAWhile(10000);
            }

            if (save && (diAf || diAp || deAf || deAp || setGcDiDeVal)) {
                reportGenerator.logAndCaptureScreen("saving the changes","clkSveConfScr",confScrPg.clkSveConfScr(svConfMsg),driver);
                dtExtPg = settingPage.clkDePg();
                reportGenerator.logAndCaptureScreen("setting automatic data ext on","enableDE",dtExtPg.enableDE("on"),driver);
                dtExtPg.clickPipeline();
                uli = CommonUtils.getUniqueText();
                pdfName = hashmap.get("pdfName");
                reportGenerator.logAndCaptureScreen("creating loan","createLoan",homePage.createLoan("anandhan", "s", uli, System.getProperty("user.dir") + "\\src\\test\\java\\com\\ll\\iod\\sample\\loans\\" + pdfName + ".pdf"),driver);
                CommonUtils.sleepForAWhile(15000);
                reportGenerator.logAndCaptureScreen("verifying status of di de after uploading pdf","verifyDiDeStatus",homePage.verifyDiDeStatus(uli, lnSts, diSts, deSts, diExp, deExp),driver);
              } else if (reset && (diAf || diAp || deAf || deAp || setGcDiDeVal))
                  reportGenerator.logAndCaptureScreen("click reset button","clkResetBtn",confScrPg.clkResetBtn(rstConfHdr, rstConfMsg, dftDiVl, dftDeVl, dftGcsAfAp),driver);
             }
    }

    private List<String> strList(String docTypes) {
        String[] strSplit = docTypes.split(",");

        // Now convert string into ArrayList
        List<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
        return strList;
    }
}
