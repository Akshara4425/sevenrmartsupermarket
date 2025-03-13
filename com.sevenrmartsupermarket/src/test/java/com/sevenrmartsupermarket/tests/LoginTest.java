package com.sevenrmartsupermarket.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.HomePage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.utilities.ExcelReader;
public class LoginTest extends Base {
	LoginPage loginpage;
	HomePage homepage;
	ExcelReader excelreader = new ExcelReader();

	@Test(groups = "regression")
	public void verifyLogin() {
		loginpage = new LoginPage(driver);
		homepage = loginpage.logIn();
		String expectedProfileName = "Admin";
		String actualProfileName = homepage.getProfileName();
		Assert.assertEquals(actualProfileName, expectedProfileName);

	}

	@Test
	public void verifyInvalidLogin() {
		excelreader.setExcelFile("LoginData", "logInInvalidCredentials");
		String userName = excelreader.getCellData(2, 0);
		String password = excelreader.getCellData(2, 1);
		System.out.println(userName + password);
		loginpage = new LoginPage(driver);
		String expectedAlert = "× Alert! Invalid Username/Password";
		String actualAlert = loginpage.logInInvalidalert(userName, password).trim().replaceAll("\\s+", " ");
		Assert.assertEquals(actualAlert, expectedAlert);
	}

}
