package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.dataprovider.DataProviderNewUser;
import com.sevenrmartsupermarket.listeners.RetryAnalyzer;
import com.sevenrmartsupermarket.pages.AdminUserPage;
import com.sevenrmartsupermarket.pages.CategoryPage;
import com.sevenrmartsupermarket.pages.HomePage;
import com.sevenrmartsupermarket.pages.LoginPage;

public class CategoryTest extends Base{
		HomePage homePage;
		LoginPage loginPage;
		CategoryPage categoryPage;
		@Test(groups={"regression"})
		public void verifyNavigationToCategoryPage()
		{
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();;
			categoryPage=homePage.selectCategoryPage();
			String currentURL=categoryPage.getURL();
			String expectedURL="https://groceryapp.uniqassosiates.com/admin/list-category";
			Assert.assertEquals(currentURL, expectedURL);
		}
		@Test 
		public void validateCategoryNewButtonFunctionality()
		{
			String Url="https://groceryapp.uniqassosiates.com/admin/list-category";
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			String actualAlert=categoryPage.addCategory("OfficeBags","OfficeBags");
			String expectedAlert="� Alert! Category Created Successfully";
			driver.get(Url);
			Assert.assertEquals(actualAlert, expectedAlert);
		}
		@Test 
		public void validateCategorySearchButtonFunctionality()
		{
		String Url="https://groceryapp.uniqassosiates.com/admin/list-category";
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();
		categoryPage=homePage.selectCategoryPage();
		boolean actualAlert=categoryPage.searchCategory("OfficeBags");
		Assert.assertEquals(actualAlert, true);
		}
		@Test 
		public void validateCategoryUpdateButtonFunctionality()
		{
			String Url="https://groceryapp.uniqassosiates.com/admin/list-category";
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			String actualAlert=categoryPage.UpdateCategory("HandBags","greenbag");
			String expectedAlert="× Alert! Category Updated Successfully";
			driver.get(Url);
			Assert.assertEquals(actualAlert, expectedAlert);
		}
}
