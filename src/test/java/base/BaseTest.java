package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.log4testng.Logger;

import Utilities.DBManager;
import Utilities.ExcelReader;

public class BaseTest {
	
	/*
	 * All initializations
	 * Excel
	 * logs
	 * Properties
	 * TestNG
	 * JavaMail
	 * ReportNG
	 * Database
	 * WebDriver
	 * Wait
	 * Keywords
	 * Screenshots
	 * Maven - build tool
	 * Jenkins
	 */
	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static Logger log =  Logger.getLogger(BaseTest.class);
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\Resources\\excel\\Test Excel.xlsx");
	public static WebDriverWait wait;
	

	@BeforeSuite
	public void setUp() {
		//if (driver.equals(null)) {
		if (true) {
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\Resources\\properties\\config.properties");
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("config property file loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\Resources\\properties\\OR.properties");
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR property file loaded");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "D:\\Ranu documents\\Selenium\\Applications\\geckodriver.exe");
				driver=new FirefoxDriver();
				log.info("Firefox launched");
				
			}else if (config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "D:\\Ranu documents\\Selenium\\Applications\\chromedriver.exe");
				driver=new ChromeDriver();
				log.info("Chrome launched");
			}
			
			driver.get(config.getProperty("testsiteurl"));
			log.info("navigated to url.");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.MILLISECONDS);
			
			try {
				DBManager.setDBConnection();
				log.info("DB connection established!!!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			WebDriverWait wait =  new WebDriverWait(driver, Integer.parseInt(config.getProperty("explicit.wait")));
		}	
		
	}
	public static void findElements(String locator) {
		
		List <WebElement> elementList = driver.findElements(By.xpath(locator));
		System.out.println("list size - " + elementList.size());
		System.out.println("list - " + elementList);
	}
	public static void click(String locator) {
		
		if (locator.contains("_xpath")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		}
		if (locator.contains("_css")) {
			String title = driver.getTitle();
			System.out.println("Title of the page = " + title);
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}
	}
	public static void type(String locator, String value) {
		if(locator.contains("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}
		if (locator.contains("_css")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}
	}
	public static void isDisplayed(String locator) {
		
		if (locator.contains("_xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).isDisplayed();
		}
		if (locator.contains("_css")) {
			String title = driver.getTitle();
			System.out.println("Title of the page = " + title);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(text(),'Add Customer')]")));
			boolean st = driver.findElement(By.cssSelector(OR.getProperty(locator))).isDisplayed();
			System.out.println(st);
			
		}
	}
/*	
	@AfterSuite
	public void tearDown() {
		driver.quit();
		log.info("execution completed !!!");
	}
*/
}
// Practice URL - http://www.way2automation.com/angularjs-protractor/banking/#/login