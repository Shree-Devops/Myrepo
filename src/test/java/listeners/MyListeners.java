package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import Utilities.TestUtil;

public class MyListeners extends TestUtil implements ITestListener{

	public void onTestStart(ITestResult result) {
	}
	public void onTestSuccess(ITestResult result) {
	}
	public void onTestFailure(ITestResult result) {
	
		Reporter.log("failed");
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.captureScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// to add link in report which opens in a new tab
		Reporter.log("<a target=\"_blank\" href="+ TestUtil.filename + ">Link new tab</a>");
		// to add new line
		Reporter.log("<br>");
		// to add a picture in report
		Reporter.log("<a target=\"_blank\" href=" + TestUtil.filename + "><img src = " + TestUtil.filename + " height=200 width= 200></a>");	
	
		
	}
	public void onTestSkipped(ITestResult result) {
		
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}
	public void onStart(ITestContext context) {
		System.out.println("before suite starts i.e. in statring of each test (not each @Test) in testing.xml )");
	}
	public void onFinish(ITestContext context) {
		System.out.println("after suite finishes i.e. in ending of each test (not each @Test) in testing.xml");
	}
}
