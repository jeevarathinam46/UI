package com.ll.iod.testCases;

import com.aventstack.extentreports.Status;
import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.management.TestNGTestRailUploader;
import com.ll.iod.page.HomePage;
import com.ll.iod.page.LoginPage;
import com.ll.iod.page.NotificationWbhkPage;
import com.ll.iod.page.SettingPage;
import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.ConfigPropertyLoader;
import com.ll.iod.utils.EnvironmentPropertyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class NotificationWbhkTestCase extends Base {
        WebDriver driver;
        ReportGenerator reportGenerator;

        LoginPage loginPage;
        HomePage homePage;
        SettingPage settingPage;
        NotificationWbhkPage ntfWbkPg;

        SoftAssert softAssert;
        public NotificationWbhkTestCase() {
                super();
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
        public void notfWbhk(ITestContext iTestContext, HashMap<String, String> hashmap) {
                String WbhkUrl,edtWbhkUrl,crtLnHdr,iodDes,wbhUrl,pstCrtLnHdr,sgnKyHdr,sgnKyDsc,wbhkType,HvrURLValdMsg,HvrURLValdData,edtHvrURLValdMsg,edtHvrURLValdData;
                boolean dftCs;
                WbhkUrl=hashmap.get("wbhkUrl");
                edtWbhkUrl=hashmap.get("edtWbhkUrl");
                crtLnHdr=hashmap.get("crtLnHdr");
                iodDes=hashmap.get("iodDes");
                wbhUrl=hashmap.get("wbhUrl");

                pstCrtLnHdr=hashmap.get("pstCrtLnHdr");
                sgnKyHdr=hashmap.get("sgnKyHdr");
                sgnKyDsc=hashmap.get("sgnKyDsc");
                wbhkType=hashmap.get("wbhkType");
                HvrURLValdMsg=hashmap.get("HvrURLValdMsg");
                HvrURLValdData=hashmap.get("HvrURLValdData");
                edtHvrURLValdMsg=hashmap.get("edtHvrURLValdMsg");
                edtHvrURLValdData=hashmap.get("edtHvrURLValdData");
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
                settingPage = homePage.clickOnSetting();
                ntfWbkPg=settingPage.clkNtfWbhkPg();
              ntfWbkPg.dftPg();
              reportGenerator.logMessage("Clearing all notification webhook", Status.PASS);
              reportGenerator.logAndCaptureScreen("create notification webhook","ntfWbkPg.crtWbhk",ntfWbkPg.crtWbhk(wbhkType,WbhkUrl,crtLnHdr,iodDes,wbhUrl,pstCrtLnHdr,sgnKyHdr,sgnKyDsc,HvrURLValdData,HvrURLValdMsg),driver);
              reportGenerator.logAndCaptureScreen("edit notification webhook","ntfWbkPg.edtWbhk",ntfWbkPg.edtWbhk(wbhkType,edtWbhkUrl,WbhkUrl,edtHvrURLValdData,edtHvrURLValdMsg),driver);
              reportGenerator.logAndCaptureScreen("delete notification webhook","ntfWbkPg.delWbhk",ntfWbkPg.delWbhk(wbhkType),driver);
              }

        }
