package com.inetBanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.LoginPage;
import com.inetBanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass {

	@Test(dataProvider = "LoginData") // The same name as below @DataProvider(name = "LoginData")
	public void loginDDT(String username123, String password123) throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username123);
		logger.info("Username entered");
		lp.setPassword(password123);
		logger.info("Password entered");
		lp.clickSubmit();
		logger.info("Clicked Submit");
		Thread.sleep(3000);

		if (isAlertPresent() == true) {
			driver.switchTo().alert().accept(); // Close the alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false); // Alert thrown there ==> Wrong Username and Password
			logger.warn("Login failed");
		} else {
			Assert.assertTrue(true);
			logger.info("Login passed");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept(); // Closes logout alert
			driver.switchTo().defaultContent();
		}
	}

	@DataProvider(name = "LoginData")
	String[][] getData() throws IOException {
		String path = System.getProperty("user.dir") + "/src/test/java/com/inetBanking/testData/LoginData.xlsx";
		int rowNumber = XLUtils.getRowCount(path, "Sheet1");
		int cocount = XLUtils.getCellCount(path, "Sheet1", 1);

		String loginData[][] = new String[rowNumber][cocount];

		for (int i = 1; i <= rowNumber; i++) {
			for (int j = 0; j < cocount; j++) {
				loginData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j); // 1 0 || Array vs Excel indexing
			}
		}
		return loginData;
	}

}