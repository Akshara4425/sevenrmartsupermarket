package com.sevenrmartsupermarket.pages;

import java.lang.annotation.ElementType;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class AdminUserPage {
	WebDriver driver;
	WaitUtility waitutility;
	PageUtility pageutility;
	GeneralUtility generalUtility;
	@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'User Deleted Successfully')]")
	private WebElement successDeleteAlert_Message;
	@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'User Updated Successfully')]")
	private WebElement successUpdateAlert_Message;
	@FindBy(xpath = "//div[contains(@class, 'alert-danger') and contains(., 'Username already exists.')]")
	private WebElement failureAlert_Message;
	@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'User Created Successfully ')]")
	private WebElement successAlert_Message;
	@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'User Status Changed Successfully')]")
	private WebElement updateStatusSuccessAlert_Message;
	@FindBy(xpath = "//a[ contains(text(), 'New')]")
	private WebElement newUser_Element;
	@FindBy(xpath = "//a[ contains(text(), ' Search')]")
	private WebElement searchUser_Element;
	@FindBy(xpath = "//a[ contains(text(), 'Reset')]")
	private WebElement resetUser_Element;
	@FindBy(xpath = "//input[ @id='username']")
	private WebElement userName_Elemement;
	@FindBy(xpath = "//input[ @id='password']")
	private WebElement password_Element;
	@FindBy(xpath = "//select[ @id='user_type']")
	private WebElement userType_Element;
	@FindBy(xpath = "//button[ @name='Create']")
	private WebElement saveButton_Element;
	@FindBy(xpath = "//div[@class=\"card-footer center\"]//a[@href=\"https://groceryapp.uniqassosiates.com/admin/list-admin\"]")
	private WebElement resetNewUser_Element;
	@FindBy(xpath = "//input[@id='un']")
	private WebElement userSearch_Element;
	@FindBy(xpath = "//select[@id='ut']")
	private WebElement searchUserType_Element;
	@FindBy(xpath = "//button[@name='Search']")
	private WebElement searchButton_Element;
	@FindBy(xpath = "//div[@class=\"card-body\"]/a[text()='Reset']")
	private WebElement searchReset_Element;
	@FindBy(xpath = ("//table//tbody//tr//td[1]"))
	List<WebElement> table_Element;
	@FindBy(xpath = "//a[@title='Show Details']")
	private List<WebElement> password_DropDown;
	@FindBy(xpath = "//div[@class=\"profile-info-name\"]")
	private List<WebElement> password_displaied;
	@FindBy(xpath = "//table//tbody//tr[1]//td[5]//a[1]")
	private WebElement statusUpdate_Element;
	@FindBy(xpath = "//table//tbody//tr[1]//td[3]")
	private WebElement statusUpdateLink_Element;
	@FindBy(xpath = "//table//tbody//tr[1]//td[5]//a[2]")
	private WebElement updateButtonLink_Element;
	@FindBy(xpath = "//table//tbody//tr[1]//td[5]/a[3]")
	private WebElement DeleteButton_Element;
	@FindBy(xpath = "//button[@name=\'Update\']")
	private WebElement updateButton_Element;
	@FindBy(xpath = "//table//tbody//tr")
	private List<WebElement> tableContent_Element;

	public AdminUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public String getURL() {
		return driver.getCurrentUrl();
	}
	public boolean IsButtonInthePageAreVisible() {
		if ((newUser_Element.isEnabled()) && (searchUser_Element.isEnabled()) && (resetUser_Element.isEnabled())
				&& (statusUpdateLink_Element.isEnabled()) && (updateButtonLink_Element.isEnabled())&&(DeleteButton_Element.isEnabled()))
			return true;
		else
			return false;
	}
public boolean IsAdminUserDetailsAreDisplaiedInTable()
{
	boolean status = true;
	for (WebElement element : tableContent_Element)
		if (element.getText().equals(".........RESULT NOT FOUND.......")) {
			status = false;
			break;
		}
	return status;
	
}
	public String checkAlertMessage(WebElement element) {
		generalUtility = new GeneralUtility();
		if (element.isDisplayed())
			return generalUtility.alertMessage(element);
		else
			return "fail";
	}
	
	public String addNewUser(String userName, String password, String userType) {
		waitutility = new WaitUtility(driver);
		pageutility=new PageUtility(driver);
		resetUser_Element.click();
		newUser_Element.click();
		userName_Elemement.sendKeys(userName);
		password_Element.sendKeys(password);
		pageutility.selectByVisibleText(userType_Element, userType);
		saveButton_Element.click();
		waitutility.waitForXpath(
				"//div[contains(@class, 'alert-success') and contains(., 'User Created Successfully ')]", 40);
		
		return checkAlertMessage(successAlert_Message);
	}

	public boolean searchUser(String userName, String userType) {
		pageutility=new PageUtility(driver);
		resetUser_Element.click();
		searchUser_Element.click();
		userSearch_Element.sendKeys(userName);
		pageutility.selectByVisibleText(searchUserType_Element, userType);
		searchButton_Element.click();
		return IsAdminUserDetailsAreDisplaiedInTable();
	}

	public String duplicateUser(String userName, String password, String userType) {
		pageutility=new PageUtility(driver);
		resetUser_Element.click();
		newUser_Element.click();
		userName_Elemement.sendKeys(userName);
		password_Element.sendKeys(password);
		pageutility.selectByVisibleText(userType_Element, userType);
		saveButton_Element.click();
		return checkAlertMessage(failureAlert_Message);
	}

	public boolean clickPasswordDropdown() throws InterruptedException {
		waitutility = new WaitUtility(driver);
		pageutility = new PageUtility(driver);
		boolean status = true;
		System.out.println(password_DropDown.size());
		int i = 0;
		for (WebElement element : password_DropDown) {
			pageutility.mouseClick(element);
			if (!(password_displaied.get(i).isDisplayed())) {
				status = true;
				System.out.println("Password details are not displaied at index" + i);

			}
			i++;
		}
		return status;
	}

	public void searchNameinTable(String userName,String type) {
		searchUser_Element.click();
		userSearch_Element.sendKeys(userName);
		pageutility.selectByVisibleText(searchUserType_Element, type);
		searchButton_Element.click();
	}

	public String userStatusUpdateButtonClick(String userName,String userType) {
		pageutility = new PageUtility(driver);
		searchNameinTable(userName,userType);
		int i = 0;
		String statusPresent = "";
		String statusChanged="";
			statusPresent = statusUpdateLink_Element.getText();
			pageutility.mouseClick(statusUpdate_Element);
			pageutility.mouseClick(statusUpdateLink_Element);
			statusChanged = statusUpdateLink_Element.getText();
			System.out.println("Status of "+userName+" is :"+statusPresent);
			System.out.println("Status of "+userName+" is :"+statusChanged);
		/*if (statusPresent.equals(statusChanged))
			return "Failed";
		else
			return updateStatusSuccessAlert();*/
			return checkAlertMessage(updateStatusSuccessAlert_Message);
	}
	public String userNameUpdateButtonClick(String userName,String nameUpdate,String userType) {
		pageutility = new PageUtility(driver);
		searchNameinTable(userName,userType);
		pageutility.mouseClick(updateButtonLink_Element);
		userName_Elemement.clear();
		userName_Elemement.sendKeys(nameUpdate);
		pageutility.mouseClick(updateButton_Element);
		return checkAlertMessage(successUpdateAlert_Message);
	}
	public String userDeleteButtonClick(String userName,String userType)
	{
		pageutility = new PageUtility(driver);
		waitutility=new WaitUtility(driver);
		searchNameinTable(userName,userType);
		pageutility.switchToAlert(driver, DeleteButton_Element);
		pageutility.switchToAlert(driver,DeleteButton_Element);
		return checkAlertMessage(successDeleteAlert_Message);
	}

}