package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.listeners.RetryAnalyzer;
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
		public void VerifyWetherButtonsOfThePageAreActive()
		{
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();;
			categoryPage=homePage.selectCategoryPage();
			boolean actualStatus=categoryPage.IsButtonInthePageAreVisible();
			Assert.assertTrue(actualStatus);
		}
		@Test
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
			
			categoryPage.insertNewCategory("lenavo laptop","lenavo");
			categoryPage=homePage.selectCategoryPage();
			boolean actualAlert=categoryPage.alertnewCategory("dell");
			Assert.assertTrue(actualAlert);
			
		}
		@Test (groups={"regression","regressiontest"})
		public void validateCategorySearchButtonFunctionality()
		{
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();
		categoryPage=homePage.selectCategoryPage();
		boolean actualAlert=categoryPage.searchCategory("laptop");
		Assert.assertTrue(actualAlert);
		}
		@Test (groups={"regressiontest","regression"},retryAnalyzer = RetryAnalyzer.class )
		public void validateCategoryUpdateButtonFunctionality()
		{                                                    
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			categoryPage.updateCategory("Dell Laptop","New  laptop","dell");
			
			String expectedAlert="× Alert! Category Updated Successfully";
			String actualAlert=categoryPage.updateAlert();
			categoryPage.performSearch("New  laptop");
			homePage.logOutFunctionality();
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			Assert.assertEquals(actualAlert, expectedAlert);
		}
		@Test (groups={"regressiontest"})
		public void validateDeleteCategoryButton()
		{
			loginPage=new LoginPage(driver);
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			String actualAlert=categoryPage.deleteCategory("Beetroot");
			String expectedAlert="× Alert! Category Deleted Successfully";
			homePage.logOutFunctionality();
			homePage=loginPage.logIn();
			categoryPage=homePage.selectCategoryPage();
			Assert.assertEquals(actualAlert, expectedAlert);
		}
}
