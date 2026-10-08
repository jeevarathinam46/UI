package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.page.HomePage;
import com.ll.iod.page.LoginPage;
import com.ll.iod.page.SettingPage;
import com.ll.iod.page.TaxonomyPage;
import com.ll.iod.utils.ConfigPropertyLoader;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class TaxonomyTestCase extends Base {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    SettingPage settingPage;
    TaxonomyPage txnmyPg;
    SoftAssert softAssert;

    public TaxonomyTestCase() {
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
    public void txnmy(ITestContext iTestContext, HashMap<String, String> hashmap) {
        iTestContext.setAttribute("testDataMap", hashmap);
        String docTypes, docTypeNms, addDocNme;
        docTypes = hashmap.get("docType");
        docTypeNms = hashmap.get("docTypeNms");
        addDocNme = hashmap.get("addDocNme");
        String url = ConfigPropertyLoader.getConfigValue(hashmap.get("tenantType"));
        driver = openBrowser("chrome", url);
        /*opens the login page ------->moves to pipeline/home page ---->create user page*/
        loginPage = new LoginPage(driver);
        homePage = loginPage.clickOnLoginButton(hashmap.get("username"), hashmap.get("password"));
        settingPage = homePage.clickOnSetting();
        txnmyPg = settingPage.clktxnmy();
        txnmyPg.dftPg();
        txnmyPg.saveChanges(docTypes, docTypeNms, addDocNme);
        txnmyPg.discardChange(docTypes, docTypeNms);
    }
}



