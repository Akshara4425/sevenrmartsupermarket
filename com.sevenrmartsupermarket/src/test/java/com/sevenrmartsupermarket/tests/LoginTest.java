package com.sevenrmartsupermarket.tests;

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

	@Test(groups = "regression",description = "Verify login with valid credentials")
	public void verifyLogin() {
		loginpage = new LoginPage(driver);
		homepage = loginpage.logIn();
		String expectedProfileName = "Admin";
		String actualProfileName = homepage.getProfileName();
		Assert.assertEquals(actualProfileName, expectedProfileName,"Profile name doesn't match.");

	}

	@Test(groups = "regression",description = "Verify login with invalid credentials")
	public void verifyInvalidLogin() {
		loginpage = new LoginPage(driver);
		excelreader.setExcelFile("LoginData", "logInInvalidCredentials");
		String userName = excelreader.getCellData(2, 0);
		String password = excelreader.getCellData(2, 1);
		String expectedAlert = "× Alert! Invalid Username/Password";
		String actualAlert = loginpage.logInInvalidalert(userName, password);
		Assert.assertEquals(actualAlert, expectedAlert);
	}
	@Test(groups = "regression",description = "Verify whether after login page is redirected to Home ")
	public void verifyAfterLoginNavigation()
	{
		loginpage = new LoginPage(driver);
		homepage = loginpage.logIn();
		
		String  expectedValue="https://groceryapp.uniqassosiates.com/admin";
		Assert.assertEquals(homepage.getCurrentURL(), expectedValue);
		
	}

}
