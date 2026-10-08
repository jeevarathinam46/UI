package com.ll.iod.base;

import com.ll.iod.constants.IodConstants;
import com.ll.iod.testrail.APIClient;
import com.ll.iod.testrail.APIException;
import com.ll.iod.utils.ConfigPropertyLoader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Base {

    public Properties prop;
    public String downloadPath = System.getProperty("user.dir") + File.separator + ConfigPropertyLoader.getConfigValue("reportFolder") + File.separator;
    protected WebDriver driver = null;
    protected APIClient a;
    protected String sTestCaseNumber = null;
    protected String testSuiteName = null;
    protected String sTestCaseName = null;
    protected String sReportDirectory = null;
    protected File folder;

    public Base() {
        prop = new Properties();
        File file = new File("src\\main\\resources\\config.properties");
        try {
            FileInputStream fis = new FileInputStream(file);
            prop.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        a = new APIClient(prop.getProperty("TESTRAIL_BASE_URL"));
        a.setUser(prop.getProperty("TESTRAIL_USERNAME"));
        a.setPassword(prop.getProperty("TESTRAIL_PASSWORD"));

    }

    public void uploadRes(String path) {
        try {
            a.sendPost("add_attachment_to_run/22516658", path);
        } catch (APIException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    public String getsTestCaseNumber() {
        return this.sTestCaseNumber;
    }

    public void setsTestCaseNumber(String sTestCaseNumber) {
        this.sTestCaseNumber = sTestCaseNumber;
    }

    public String getsTestCaseName() {
        return this.sTestCaseName;
    }

    public void setsTestCaseName(String sTestCaseName) {
        this.sTestCaseName = sTestCaseName;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public String getsReportDirectory() {
        return sReportDirectory;
    }

    public void setsReportDirectory() {
        if (this.getsTestCaseNumber() != null) {
            this.sReportDirectory = System.getProperty(IodConstants.USER_DIR) + File.separator + ConfigPropertyLoader.getConfigValue("reportFolder") + File.separator + this.getsTestCaseNumber();
        } else {
            this.sReportDirectory = System.getProperty(IodConstants.USER_DIR) + File.separator + ConfigPropertyLoader.getConfigValue("reportFolder");
        }
    }

    public WebDriver openBrowser(String browserName, String url) {
        if (browserName.equalsIgnoreCase("chrome")) {
            //folder=new File(UUID.randomUUID().toString());
            //folder.mkdir();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors",
                    "--ignore-ssl-errors=yes");
            chromeOptions.setBrowserVersion("115");
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", downloadPath);
            //prefs.put("download.default_directory",folder.getAbsolutePath());
            chromeOptions.setExperimentalOption("prefs", prefs);
            driver = new ChromeDriver(chromeOptions);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.download.folderList", 2);
            profile.setPreference("browser.download.dir", downloadPath);
            profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/msword, application/json, application/ris, participant_id/csv, image/png, application/pdf, participant_id/html, participant_id/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
            FirefoxOptions options = new FirefoxOptions();
            //options.addArguments("--remote-allow-origins=*", "ignore-certificate-errors",
            //		"--ignore-ssl-errors=yes");
            options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            options.setProfile(profile);
            driver = new FirefoxDriver(options);
        } else if (browserName.equalsIgnoreCase("edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors",
                    "--ignore-ssl-errors=yes");
            edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", downloadPath);
            edgeOptions.setExperimentalOption("prefs", prefs);
            driver = new EdgeDriver(edgeOptions);

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get(url);
        return driver;
    }

}
