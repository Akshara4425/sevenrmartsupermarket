package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.dataprovider.DataProviderNewUser;
import com.sevenrmartsupermarket.listeners.RetryAnalyzer;
import com.sevenrmartsupermarket.pages.AdminUserPage;
import com.sevenrmartsupermarket.pages.HomePage;
import com.sevenrmartsupermarket.pages.LoginPage;


public class AdminUserTest extends Base{
	HomePage homePage;
	LoginPage loginPage;
	AdminUserPage adminUserPage;
	DataProviderNewUser dataprovidernewuser=new DataProviderNewUser();
	@Test(groups={"smoke"})
	public void verifyNavigationToAdminUserPage()
	{
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();;
		adminUserPage=homePage.selectAdminUser();
		
		String expectedURL="https://groceryapp.uniqassosiates.com/admin/list-admin";
		Assert.assertEquals(adminUserPage.getURL(), expectedURL);
	}
	//@Test (retryAnalyzer = RetryAnalyzer.class )
	@Test (groups={"regression"},dataProvider = "newUser",dataProviderClass =DataProviderNewUser.class )
	public void validateNewUserCreation(String userName,String password,String userType)
	{
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();;
		adminUserPage=homePage.selectAdminUser();
		adminUserPage.addUserDetails(userName, password, userType);
		
		String actualAlert=adminUserPage.addNewUser(userName, password, userType);
		String expectedAlert="× Alert! User Created Successfully";
		Assert.assertEquals(actualAlert, expectedAlert);
	}
	@Test(groups = "regression",description = "Validate the search Button functionality ",retryAnalyzer = RetryAnalyzer.class )
	public void validateSearchButtonfunctionality()
	{
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();
		adminUserPage=homePage.selectAdminUser();
		
		adminUserPage.searchNameinTable("Danial","Admin");
		boolean actualstatus=adminUserPage.searchUser();
		
		homePage.logOutFunctionality();
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();
		adminUserPage=homePage.selectAdminUser();
		Assert.assertTrue(actualstatus,".........RESULT NOT FOUND.......");
	}
	@Test(groups = "smoke")
	public void validateDuplicateUserEnterEntry()
	{
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();
		
		adminUserPage=homePage.selectAdminUser();
		adminUserPage.addUserDetails("Deni", "Rahul123", "Admin");
		
		String actualAlert=adminUserPage.duplicateUser();
		adminUserPage.searchNameinTable("Deni", "Admin");
		String expectedAlert="× Alert! Username already exists.";
		Assert.assertEquals(actualAlert, expectedAlert);
		
	}
@Test
public void validatePasswordVisibility() throws InterruptedException
{
	loginPage=new LoginPage(driver);
	homePage=loginPage.logIn();;
	adminUserPage=homePage.selectAdminUser();	
	boolean actualstatus=adminUserPage.clickPasswordDropdown();
	Assert.assertTrue(actualstatus,"Unable list all users password");
	
}
@Test(retryAnalyzer = RetryAnalyzer.class)
public void validateUserStatusButtonFunctionality()
{
	loginPage=new LoginPage(driver);
	homePage=loginPage.logIn();;
	adminUserPage=homePage.selectAdminUser();
	adminUserPage.userStatusUpdateButtonClick("Danial","Admin");
	String actualStatus=adminUserPage.statusChange();
	String expectedStatus="× Alert! User Status Changed Successfully";
	Assert.assertEquals(actualStatus, expectedStatus);
}
@Test(retryAnalyzer = RetryAnalyzer.class ,groups = "regression")
public void validateUserDetailUpdateButtonFunctionality()
{
	loginPage=new LoginPage(driver);
	homePage=loginPage.logIn();
	adminUserPage=homePage.selectAdminUser();
	
	adminUserPage.userNameUpdateButtonClick("Sree.Hary","SreeLal","Admin");
	String actualStatus=adminUserPage.updteAlert();
	String expectedStatus="× Alert! User Updated Successfully";
	
	homePage.logOutFunctionality();
	homePage=loginPage.logIn();
	adminUserPage=homePage.selectAdminUser();
	
	Assert.assertEquals(actualStatus, expectedStatus);	
}	
@Test(retryAnalyzer = RetryAnalyzer.class ,groups={"regression"})
public void validateAdminUserDeleteButtonFunctionality()
{
	loginPage=new LoginPage(driver);
	homePage=loginPage.logIn();;
	adminUserPage=homePage.selectAdminUser();
	adminUserPage.userDeleteButtonClick("Soniya	","Staff");
	
	String actualStatus=adminUserPage.deleteUserStatus();
	String expectedStatus="× Alert! User Deleted Successfully";
	Assert.assertEquals(actualStatus, expectedStatus);	
}	
}
