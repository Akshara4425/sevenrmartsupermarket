package com.sevenrmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	String path = "//div[contains(@class, 'alert-success') and contains(., 'User Created Successfully ')]";
	String duplicateAlert = "//div[contains(@class, 'alert-danger') and contains(., 'Username already exists.')]";

	public AdminUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getURL() {
		return driver.getCurrentUrl();
	}

	public boolean IsButtonInthePageAreVisible() {
		if ((newUser_Element.isEnabled()) && (searchUser_Element.isEnabled()) && (resetUser_Element.isEnabled())
				&& (statusUpdateLink_Element.isEnabled()) && (updateButtonLink_Element.isEnabled())
				&& (DeleteButton_Element.isEnabled()))
			return true;
		else
			return false;
	}

	public boolean IsAdminUserDetailsAreDisplaiedInTable() {
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

	public void enterUsername(String userName) {
		userName_Elemement.clear();
		userName_Elemement.sendKeys(userName);
	}

	public void enterPassword(String password) {
		password_Element.sendKeys(password);
	}

	public void selectTypeOfUser(String userType) {
		pageutility.selectByVisibleText(userType_Element, userType);
	}

	public void buttonClick(WebElement element) {
		element.click();
	}

	public void addUserDetails(String userName, String password, String userType) {
		pageutility = new PageUtility(driver);
		buttonClick(resetUser_Element);
		buttonClick(newUser_Element);
		enterUsername(userName);
		enterPassword(password);
		selectTypeOfUser(userType);
		buttonClick(saveButton_Element);
	}

	public String addNewUser(String userName, String password, String userType) {
		waitutility = new WaitUtility(driver);

		waitutility.waitForXpath(path, 40);

		return checkAlertMessage(successAlert_Message);
	}

	public void enterUserToBeSearched(String userName) {
		userSearch_Element.sendKeys(userName);
	}

	public void enterTypeOfUserToBeSearched(String userType) {
		pageutility = new PageUtility(driver);
		pageutility.selectByVisibleText(searchUserType_Element, userType);
	}

	public boolean searchUser() {
		
		return IsAdminUserDetailsAreDisplaiedInTable();
	}

	public String duplicateUser() {
		waitutility = new WaitUtility(driver);
		waitutility.waitForXpath(duplicateAlert, 40);
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

	public void searchNameinTable(String userName, String type) {
		buttonClick(resetUser_Element);
		buttonClick(searchUser_Element);
		enterUserToBeSearched(userName);
		enterTypeOfUserToBeSearched(type);
		buttonClick(searchButton_Element);
	}
public String statusChange()
{
	return checkAlertMessage(updateStatusSuccessAlert_Message);
}
	public void userStatusUpdateButtonClick(String userName, String userType) {
		pageutility = new PageUtility(driver);
		searchNameinTable(userName, userType);
		String statusPresent = "";
		String statusChanged = "";
		statusPresent = statusUpdateLink_Element.getText();
		pageutility.mouseClick(statusUpdate_Element);
		pageutility.mouseClick(statusUpdateLink_Element);
		statusChanged = statusUpdateLink_Element.getText();
		System.out.println("Status of " + userName + " is :" + statusPresent);
		System.out.println("Status of " + userName + " is :" + statusChanged);
		searchNameinTable(userName, userType);
		
	}
	public void userNameUpdateButtonClick(String userName, String nameUpdate, String userType) {
		pageutility = new PageUtility(driver);
		searchNameinTable(userName, userType);
		pageutility.mouseClick(updateButtonLink_Element);
		enterUsername(nameUpdate);
		selectTypeOfUser(userType);	
		buttonClick(updateButton_Element);
		searchNameinTable(nameUpdate, userType);
	}
	public String updteAlert()
	{
		return checkAlertMessage(successUpdateAlert_Message);
	}

	public void userDeleteButtonClick(String userName, String userType) {
		pageutility = new PageUtility(driver);
		waitutility = new WaitUtility(driver);
		searchNameinTable(userName, userType);
		pageutility.switchToAlert(driver, DeleteButton_Element);
		pageutility.switchToAlert(driver, DeleteButton_Element);
		searchNameinTable(userName, userType);
	}
	public String deleteUserStatus()
	{
		return checkAlertMessage(successDeleteAlert_Message);
	}
}