package com.ll.iod.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ll.iod.utils.CalendarDateHandler;
import com.ll.iod.utils.CommonUtils;
import com.ll.iod.utils.ConfigPropertyLoader;
import com.ll.iod.utils.FileIOHandler;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

public class ReportGenerator {


	private ExtentSparkReporter htmlReporter = null;
	private ExtentReports extent = null;
	private ExtentTest test = null;
	public static final String BASE_REPORT_DIR = System.getProperty("user.dir") +  File.separator + ConfigPropertyLoader.getConfigValue("reportFolder");
	private String reportPath = null;
public static String path=null;

	public String getReportPath() {
		return reportPath;
	}

	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}
	public ReportGenerator(){

	}

	public ReportGenerator(String testDesc) {
		 path=BASE_REPORT_DIR+testDesc;
		FileIOHandler.deleteFiles(path);
		//Empty constructor to instantiate from test method 
	}
	
	public void setupExtendedReport(String testCaseNumber, String testMethodName) {
		// location of the extent report
		reportPath = BASE_REPORT_DIR +File.separator+testCaseNumber;
		System.out.println(reportPath);
		String htmlFilePath = reportPath + File.separator+ testMethodName + "_" + CalendarDateHandler.getFormatedDate("MMddyyyyhh_mm_ss") +".html";
		htmlReporter = new ExtentSparkReporter(htmlFilePath);
		extent = new ExtentReports(); // create object of ExtentReports
		extent.attachReporter(htmlReporter);

		htmlReporter.config().setDocumentTitle("IoD Test Automation Report"); // Title of Report
		htmlReporter.config().setReportName("IoD Test Automation Report"); // Name of the report
		htmlReporter.config().setTheme(Theme.STANDARD);// Default Theme of Report

		// General information related to application
		extent.setSystemInfo("Application Name", "IoD");
		extent.setSystemInfo("Author", "anandhan.s@loanlogics");
		extent.setSystemInfo("Envirnoment", ConfigPropertyLoader.getConfigValue("environment"));
		extent.setSystemInfo("Browser", ConfigPropertyLoader.getConfigValue("Browser"));
		//extent.setSystemInfo("URL", EnvironmentPropertyLoader.getPropertyByName("idea_wfs_console_base_url"));
		test = extent.createTest(testMethodName);
		
	}
	
	
	/** 
	 * To log the test message to capture in the report. 
	 * @param message string value to be printed in the report
	 * @param logLevel logLevel could be one of the following values such as 
	 * PASS, FAIL, FATAL,ERROR, WARNING, INFO, DEBUG, SKIP
	 */
	public void logMessage(String message, Status logLevel) {
		Status level = Status.INFO;
		if(logLevel != null) level = logLevel;
		test.log(level, message);
		Reporter.log("Issue in "+message); //routing the message to testng reporter
	}

	public void logAndCaptureScreen(String message, String methodName, boolean status, WebDriver webDriver) {
		CommonUtils.sleepForAWhile(1000);
		captureScreenShot(webDriver,methodName);
		if(status){
		logMessage(message, Status.PASS);}
		else{
			logMessage(message,Status.FAIL);}
	}
	/*


	public void logAndCaptureScreen(String message, String methodName, Status logLevel, WebDriver webDriver, WebElement ele) {
		((JavascriptExecutor) webDriver).executeScript(IodConstants.JS_ARG_SCROLL_VIEW, ele);
		logAndCaptureScreen(message, methodName, logLevel, webDriver);
	}*/

	private void captureScreenShot(WebDriver webDriver, String methodName) {
		String destinationFileName = reportPath + File.separator + CalendarDateHandler.getFormatedDate("MMddYYYYhhmmss")+"-"+methodName+ ".png";
		if (webDriver != null) {
			File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			File reportDirectory = new File(reportPath);
			try {
				if(!reportDirectory.exists()) {reportDirectory.mkdirs(); }
				File destinationFile =  new File(destinationFileName);
				FileUtils.moveFile(scrFile,	destinationFile);
			} catch (IOException e) {
				Reporter.log("Failed to capture screen shots due to " + e.getMessage());
			}
		}
	}
	public void captureScreenShot(WebDriver webDriver) {

		String destinationFileName = path +File.separator+CalendarDateHandler.getFormatedDate("MMddYYYYhhmmss")+ ".png";
		System.out.println(destinationFileName);
		if (webDriver != null) {
			File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			File reportDirectory = new File(path );
			try {
				if(!reportDirectory.exists()) {reportDirectory.mkdirs(); }
				File destinationFile =  new File(destinationFileName);
				FileUtils.moveFile(scrFile,	destinationFile);
			} catch (IOException e) {
				Reporter.log("Failed to capture screen shots due to " + e.getMessage());
			}
		}
	}
	public void endReport() {
		try {
			extent.flush();
		} catch (Exception e) {
			Reporter.log("Exception occured while pushing the test execution report due to " + e.getMessage());
		} finally {
			test = null;extent = null;htmlReporter = null;			
		}
	}
}
