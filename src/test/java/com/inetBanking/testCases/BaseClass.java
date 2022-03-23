package com.inetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.AfterClass;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();

	public String baseUrl = readConfig.getApplicationURL();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();
	public static WebDriver driver; // static
	public static Logger logger; // For Log4j (We need to use across multiple classes. Hence, outside.)

	@SuppressWarnings("deprecation")
	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser) {
		logger = Logger.getLogger("eBanking"); // Log4j
		PropertyConfigurator.configure("Log4j.properties"); // Log4j

		if (browser.equals("chrome")) {
			// System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
			// + "//Drivers//chromedriver.exe"); // Note: Forward slash
			System.setProperty("webdriver.chrome.driver", readConfig.getChromePath());
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readConfig.getFirefoxPath());
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", readConfig.getEdgePath());
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public boolean isAlertPresent() { // User defined method to check Alert is present or not
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}
	
	public String randomString() {
		return (RandomStringUtils.randomAlphabetic(8));		
	}
	
	public String randomNumber() {
		return (RandomStringUtils.randomNumeric(8));		
	}

}