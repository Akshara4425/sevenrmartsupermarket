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
		
		@Test (groups={"regression"})
		public void validateCategoryNewButtonFunctionality()
		{
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			
			categoryPage.insertNewCategory("Papper Bag","bag1");
			categoryPage=homePage.selectCategoryPage();
			boolean actualAlert=categoryPage.alertnewCategory("Papper Bag");
			Assert.assertTrue(actualAlert);
			
		}
		@Test (groups={"regression"})
		public void validateCategorySearchButtonFunctionality()
		{
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();
		categoryPage=homePage.selectCategoryPage();
		boolean actualAlert=categoryPage.searchCategory("Bag");
		Assert.assertTrue(actualAlert);
		}
		@Test (groups={"regression"},retryAnalyzer = RetryAnalyzer.class )
		public void validateCategoryUpdateButtonFunctionality()
		{                                                    
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			categoryPage.updateCategory("Lap Bag","Mini Handbag","bag1");
			
			String expectedAlert="× Alert! Category Updated Successfully";
			String actualAlert=categoryPage.updateAlert();
			categoryPage.performSearch("Mini Handbag");
			homePage.logOutFunctionality();
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			Assert.assertEquals(actualAlert, expectedAlert);
		}
		@Test (groups={"regression"})
		public void validateDeleteCategoryButton()
		{
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			String actualAlert=categoryPage.deleteCategory("Beverages");
			String expectedAlert="× Alert! Category Deleted Successfully";
			homePage.logOutFunctionality();
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			Assert.assertEquals(actualAlert, expectedAlert);
		}
}
