package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.page.HomePage;
import com.ll.iod.page.LoginPage;
import com.ll.iod.page.SettingPage;
import com.ll.iod.page.StackingOrderPage;
import com.ll.iod.utils.ConfigPropertyLoader;
import net.bytebuddy.utility.RandomString;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class StackingOrderTestCase extends Base {
    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    SettingPage settingPage;
    StackingOrderPage stkOrdPg;
    SoftAssert softAssert;

    public StackingOrderTestCase() {
        super();
    }

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
    public void stckOrd(ITestContext iTestContext, HashMap<String, String> hashmap) {
        iTestContext.setAttribute("testDataMap", hashmap);
        String url = ConfigPropertyLoader.getConfigValue(hashmap.get("tenantType"));
        String stkName, pdfType, docTypes, edtPdfType, edtStkName;
        pdfType = hashmap.get("pdfType");
        docTypes = hashmap.get("docTypes");
        edtPdfType = hashmap.get("edtPdfType");
        stkName = "Automation" + RandomString.make(5);
        edtStkName = stkName + "e";
        driver = openBrowser("chrome", url);
        /*opens the login page ------->moves to pipeline/home page ---->create user page*/
        loginPage = new LoginPage(driver);
        homePage = loginPage.clickOnLoginButton(hashmap.get("username"), hashmap.get("password"));
        settingPage = homePage.clickOnSetting();
        stkOrdPg = settingPage.clkStkOrdPg();
        stkOrdPg.selectStkOrdByNme(stkName, pdfType, docTypes);
        stkOrdPg.edtStkOrd(stkName,edtPdfType, edtStkName);
        stkOrdPg.cpyStkOrd(edtStkName);
        stkOrdPg.dltStkOrd(edtStkName);

    }
}
