package com.inetBanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.inetBanking.pageObjects.AddCustomerPage;
import com.inetBanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Username is provided");
		lp.setPassword(password);
		logger.info("Passsword is provided");
		lp.clickSubmit();
		Thread.sleep(3000);

		AddCustomerPage addCust = new AddCustomerPage(driver);
		addCust.clickAddNewCustomer();
		logger.info("Providing Customer Details ....");

		addCust.custName("Pavan");
		addCust.custGender("male");
		addCust.custDob("10", "15", "1985");
		Thread.sleep(5000);
		addCust.custAddress("INDIA");
		addCust.custCity("HYD");
		addCust.custState("AP");
		addCust.custPinNo("5000074");
		addCust.custTelephoneNo("987890091");
		String email = randomString() + "@gmail.com";
		addCust.custEmailId(email);
		addCust.custPassword("abcdef");
		addCust.custSubmit();

		Thread.sleep(3000);

		logger.info("validation started....");
		
		boolean res = driver.getPageSource().contains("Customer Registered Successfully!!!");
		if (res == true) {
			Assert.assertTrue(true);
			captureScreen(driver,"addNewCustomer");
			logger.info("Test case passed....");
		}
		else {
			logger.info("Test case failed ....");
			Assert.assertTrue(false);
		}

	}
}