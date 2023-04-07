package Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.BaseTest;

public class TestUtil extends BaseTest{
	
	public static String filename; //global
	
	public static void captureScreenshots() throws IOException {
		
		Date d = new Date();
		filename = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
	
		File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File( System.getProperty("user.dir") +  "//test-output//html//" + filename));
	}
	
}
