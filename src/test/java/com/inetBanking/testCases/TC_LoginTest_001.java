package com.inetBanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws IOException {		
		//driver.get(baseUrl);		
		logger.info("URL is opened"); // Log4j		
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Username is entered");
		lp.setPassword(password);
		logger.info("Password is entered");
		lp.clickSubmit();
		
		if (driver.getTitle().equals("Guru99 Bank Manager HomePage1234")) {
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else {
			captureScreen(driver, "logintest");	// Note -> To get screenshot in Screenshots folder
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}
		
	}

}