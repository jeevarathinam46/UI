package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.page.AtmtdRlsPage;
import com.ll.iod.page.HomePage;
import com.ll.iod.page.LoginPage;
import com.ll.iod.page.SettingPage;
import com.ll.iod.tlistener.ScreenshotListener;
import com.ll.iod.utils.ConfigPropertyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
@Listeners({ScreenshotListener.class})
public class AtmdRlsTestCase extends Base {
    WebDriver driver;
    SoftAssert softAssert;
    LoginPage loginPg;
    HomePage homePg;
    SettingPage setPg;
    AtmtdRlsPage arPg;

    @BeforeMethod
    public void setUp() {
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() {
        //  FileIOHandler.deleteFiles(folder.getAbsolutePath());
        //  folder.delete();
        if (driver != null) {
            driver.quit();
        }

    }

    @Test(dataProvider = "CsvMapDataProvider", dataProviderClass = AutomationTestDataProvider.class)
    public void atmRls(ITestContext iTestContext, HashMap<String, String> hashmap) {
        iTestContext.setAttribute("testDataMap", hashmap);
        String url = ConfigPropertyLoader.getConfigValue(hashmap.get("tenantType"));
        driver = openBrowser("chrome", url);
        loginPg= new LoginPage(driver);
        homePg=loginPg.clickOnLoginButton(hashmap.get("username"),hashmap.get("password"));
        setPg=homePg.clickOnSetting();
        arPg=setPg.clkAtmdRls();
    }

    }
