package com.sevenrmartsupermarket.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sevenrmartsupermarket.base.Base;
import com.sevenrmartsupermarket.dataprovider.DataProviderNewUser;
import com.sevenrmartsupermarket.listeners.RetryAnalyzer;
import com.sevenrmartsupermarket.pages.AdminUserPage;
import com.sevenrmartsupermarket.pages.HomePage;
import com.sevenrmartsupermarket.pages.LoginPage;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class AdminUserTest extends Base{
	HomePage homePage;
	LoginPage loginPage;
	AdminUserPage adminUserPage;
	DataProviderNewUser dataprovidernewuser=new DataProviderNewUser();
	
	@Test(groups={"regression"})
	public void verifyNavigationToAdminUserPage()
	{
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();;
		adminUserPage=homePage.selectAdminUser();
		String currentURL=adminUserPage.getURL();
		String expectedURL="https://groceryapp.uniqassosiates.com/admin/list-admin";
		Assert.assertEquals(currentURL, expectedURL);
	}
	@Test (retryAnalyzer = RetryAnalyzer.class , dataProvider = "newUser",dataProviderClass =DataProviderNewUser.class )
	public void validateNewUserCreation(String userName,String password,String userType)
	{
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();;
		adminUserPage=homePage.selectAdminUser();
		String actualAlert=adminUserPage.addNewUser(userName, password, userType);
		String expectedAlert="× Alert! User Created Successfully";
		Assert.assertEquals(actualAlert, expectedAlert);
	}
	@Test
	public void validateSearchButtonfunctionality()
	{
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();;
		adminUserPage=homePage.selectAdminUser();
		boolean actualstatus=adminUserPage.searchUser("sidney.ondricka","Admin");
		Assert.assertEquals(actualstatus, true);
	}
	@Test
	public void validateDuplicateUserEnterEntry()
	{
		loginPage=new LoginPage(driver);
		homePage=loginPage.logIn();;
		adminUserPage=homePage.selectAdminUser();
		String actualAlert=adminUserPage.duplicateUser("nakesha.little", "Rahul123", "Staff");
		boolean status=adminUserPage.searchUser("nakesha.little", "Staff");
		String expectedAlert="× Alert! Username already exists.";
		Assert.assertEquals(actualAlert, expectedAlert);
		
	}
@Test
public void validatePasswordDropdownClick() throws InterruptedException
{
	loginPage=new LoginPage(driver);
	homePage=loginPage.logIn();;
	adminUserPage=homePage.selectAdminUser();	
	boolean actualstatus=adminUserPage.clickPasswordDropdown();
	Assert.assertEquals(actualstatus, true);
	
}
@Test
public void validateUserStatusButtonFunctionality()
{
	loginPage=new LoginPage(driver);
	homePage=loginPage.logIn();;
	adminUserPage=homePage.selectAdminUser();
	String actualStatus=adminUserPage.userStatusUpdateButtonClick("orval.harvey","Admin");
	String expectedStatus="× Alert! User Status Changed Successfully";
	Assert.assertEquals(actualStatus, expectedStatus);
	
}
@Test
public void validateUserDetailUpdateButtonFunctionality()
{
	loginPage=new LoginPage(driver);
	homePage=loginPage.logIn();;
	adminUserPage=homePage.selectAdminUser();
	String actualStatus=adminUserPage.userNameUpdateButtonClick("ManuRoshan","donetta.bahringer","Staff");
	String expectedStatus="× Alert! User Updated Successfully";
	Assert.assertEquals(actualStatus, expectedStatus);
	
}	
@Test
public void validateAdminUserDeleteButtonFunctionality()
{
	loginPage=new LoginPage(driver);
	homePage=loginPage.logIn();;
	adminUserPage=homePage.selectAdminUser();
	String actualStatus=adminUserPage.userDeleteButtonClick("nakesha.little","Staff");
	String expectedStatus="× Alert! User Deleted Successfully";
	Assert.assertEquals(actualStatus, expectedStatus);
	
}	
}
