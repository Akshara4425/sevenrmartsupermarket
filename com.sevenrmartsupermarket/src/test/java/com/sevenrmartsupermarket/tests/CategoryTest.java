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
		public void VerifyWetherButtonsOfThePageAreActive()
		{
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();;
			categoryPage=homePage.selectCategoryPage();
			boolean actualStatus=categoryPage.IsButtonInthePageAreVisible();
			Assert.assertTrue(actualStatus);
		}
		@Test(groups={"regression"})
		public void VerifyWetherCategoriesAreDisplaied()
		{
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();;
			categoryPage=homePage.selectCategoryPage();
			boolean actualStatus=categoryPage.IsCategoriesAreDisplaiedInTable();
			Assert.assertTrue(actualStatus);
		}
		@Test(groups={"regression"},priority = 1)
		public void verifyNavigationToCategoryPage()
		{
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();;
			categoryPage=homePage.selectCategoryPage();
			String currentURL=categoryPage.getURL();
			String expectedURL="https://groceryapp.uniqassosiates.com/admin/list-category";
			Assert.assertEquals(currentURL, expectedURL);
		}
		@Test (groups={"regression"},priority = 2)
		public void validateCategoryNewButtonFunctionality()
		{
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			String actualAlert=categoryPage.addCategory("OfficeBags","OfficeBags");
			String expectedAlert="� Alert! Category Created Successfully";
			driver.navigate().back();
			Assert.assertEquals(actualAlert, expectedAlert);
		}
		@Test (groups={"regression"},priority = 3)
		public void validateCategorySearchButtonFunctionality()
		{
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();
		categoryPage=homePage.selectCategoryPage();
		boolean actualAlert=categoryPage.searchCategory("OfficeBags");
		Assert.assertTrue(actualAlert);
		}
		@Test (groups={"regression"},priority = 4)
		public void validateCategoryUpdateButtonFunctionality()
		{                                                    
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			String actualAlert=categoryPage.updateCategory("laptop","DellLaptop","dell");
			String expectedAlert="× Alert! Category Updated Successfully";
			Assert.assertEquals(actualAlert, expectedAlert);
		}
		@Test (groups={"regression"},priority = 4)
		public void validateDeleteCategoryButton()
		{
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			String actualAlert=categoryPage.deleteCategory("laptopDelllaptop");
			String expectedAlert="× Alert! Category Deleted Successfully";
			Assert.assertEquals(actualAlert, expectedAlert);
		}
}
