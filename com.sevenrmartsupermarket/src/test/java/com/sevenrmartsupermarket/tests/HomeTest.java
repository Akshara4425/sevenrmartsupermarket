package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.pages.HomePage;
import com.sevenrmartsupermarket.pages.LoginPage;

public class HomeTest extends Base {
	HomePage homePage;
	LoginPage loginpage;
	@Test(groups={"regression"})
	public void verifyPageTitle() {
		homePage = new HomePage(driver);
		String actualTiltle = homePage.getPageTiltle();
		String expectedTitle = "Login | 7rmart supermarket";
		Assert.assertEquals(actualTiltle, expectedTitle);
	}
	@Test(groups={"smoke","regression"})
	public void verifyMoreInfoLink() {
		loginpage = new LoginPage(driver);
		//homePage = new HomePage(driver);
		homePage=loginpage.logIn();
		int totalLinks = homePage.getMoreInfoLinks().size();
		String homePageUrl = "https://groceryapp.uniqassosiates.com/admin";
		System.out.println("Total 'More Info' links found: " + totalLinks);
		for (int i = 0; i < totalLinks; i++) {
			homePage.getMoreInfoLinks().get(i).click();
			String currentURL = homePage.getCurrentURL();
			System.out.println("Navigated to: " + currentURL);
			Assert.assertNotEquals(currentURL, homePageUrl, "Navigation failed for link index: " + i);
			//driver.navigate().back();
		}
	}
/*	@Test
	public void verifyLogOutFunctionality() {
		loginpage = new LoginPage(driver);
		//homePage = new HomePage(driver);
		homePage=loginpage.logIn();
		boolean actualLink = homePage.logOutFunctionality();
		boolean expectedLink = true;
		Assert.assertEquals(actualLink, expectedLink);
	}*/
}