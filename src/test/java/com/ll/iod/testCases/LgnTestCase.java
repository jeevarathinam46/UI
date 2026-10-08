package com.ll.iod.testCases;

import com.ll.iod.base.Base;
import com.ll.iod.dataprovider.AutomationTestDataProvider;
import com.ll.iod.management.TestNGTestRailUploader;
import com.ll.iod.page.LoginPage;
import com.ll.iod.report.ReportGenerator;
import com.ll.iod.utils.EnvironmentPropertyLoader;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

public class LgnTestCase extends Base {
    ReportGenerator reportGenerator = null;
    LoginPage lp=null;
    public LgnTestCase(){
        super();
    }
    @BeforeSuite(alwaysRun = true, groups = { "Level1" })
    public void doSetUp(ITestContext iTestContext) throws Exception {
        /**
         * Create sub directory under <code>/testdata</code> for every suite to
         * segregate the test data CSV file, helps to manage with ease. For example,
         * <code>user</code> is a sub test data folder to manage all csv files related
         * to user module
         */
        this.setTestSuiteName("user");
       }
    @AfterMethod(alwaysRun = true, groups = { "Level1" })
    public void doAfterMethod(ITestResult result) {
        reportGenerator.endReport();
        TestNGTestRailUploader.uploadTestResultsToTestRail(result);
        if (driver != null) {
            driver.quit();
        }
    }
    @Test(dataProvider ="CsvMapDataProvider",dataProviderClass = AutomationTestDataProvider.class)
    public void TestCasesofLogin(HashMap<String, String> hashmap, Method method,ITestContext iTestContext){
        reportGenerator = new ReportGenerator();// new obj for rp to access the methos present in it
        reportGenerator.setupExtendedReport(hashmap.get("TestCaseNumber"), hashmap.get("TestCaseName"));
        //html path is set
        iTestContext.setAttribute("testDataMap", hashmap); // Test Data Map must be set to reporting purpose

     /*   reportGenerator = new ReportGenerator(method.getName()+ File.separator+data.get("password"));
System.out.println(path);*/
        String url = EnvironmentPropertyLoader.getPropertyByName(hashmap.get("tnt"));
        System.out.println(url);

        //data.get("tnt");
       driver=openBrowser("chrome", url);
         lp=new LoginPage(driver,reportGenerator);// create object for login page
         lp.clickOnLoginButton(hashmap.get("username"),hashmap.get("password"));
        }
}
