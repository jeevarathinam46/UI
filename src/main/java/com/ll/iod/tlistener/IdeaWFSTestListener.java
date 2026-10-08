package com.ll.iod.tlistener;


import com.ll.iod.utils.EnvironmentPropertyLoader;
import com.ll.iod.utils.FileIOHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;


public class IdeaWFSTestListener implements ITestListener {

	//ITestListener implementation
	
		@Override
	    public void onFinish(ITestContext arg0) {					
			Reporter.log("Finished test execution " + arg0.getSuite());
	    }	
	
	    @Override		
	    public void onStart(ITestContext iTestContext) {
	    	String baseReportDir = EnvironmentPropertyLoader.getPropertyByName("reportFolder");
	    	FileIOHandler.deleteFiles(baseReportDir);
		    System.out.println("started");
	    }		

	    @Override		
	    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {					
	    	//Do implement any logic upon failure with certain percentage of success if any
	    }		

	    @Override		
	    public void onTestFailure(ITestResult arg0) {					
	    	//Do implement if there is any logic
	    }		

	    @Override		
	    public void onTestSkipped(ITestResult arg0) {					
	    	//Do implement if there is any logic
	    }		

	    @Override		
	    public void onTestStart(ITestResult arg0) {	
	    	//Do implement if there is any logic
	    }		

	    @Override		
	    public void onTestSuccess(ITestResult arg0) {					
	    	//Do implement if there is any logic
	    }		

}
