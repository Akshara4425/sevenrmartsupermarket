package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.HomePage;
import com.sevenrmartsupermarket.pages.LoginPage;

public class HomeTest extends Base {
	HomePage homePage;
	LoginPage loginpage;

	@Test(groups = { "regression" })
	public void verifyPageTitle() {
		homePage = new HomePage(driver);
		String actualTiltle = homePage.getPageTiltle();
		String expectedTitle = "Login | 7rmart supermarket";
		Assert.assertEquals(actualTiltle, expectedTitle);
	}
	@Test
	public void verifyProfileName()
	{
		loginpage = new LoginPage(driver);
		homePage = loginpage.logIn();
		String actualTiltle = homePage.getProfileName();
		String expectedTitle = "Admin";
		Assert.assertEquals(actualTiltle, expectedTitle);	
	}

	@Test(groups = { "smoke", "regression" })
	public void verifyMoreInfoLink() {
		loginpage = new LoginPage(driver);
		homePage = loginpage.logIn();
		boolean atualstatus=homePage.morelinkNavigation();
		Assert.assertTrue(atualstatus);
		
	}
 @Test (priority =21 )
	 public void verifyLogOutFunctionality() { 
	  loginpage = new LoginPage(driver); 
	  homePage=loginpage.logIn();
	 boolean actualLink =homePage.logOutFunctionality();
	  Assert.assertTrue(actualLink); }
	 
}