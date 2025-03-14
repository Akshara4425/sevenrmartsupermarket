package com.sevenrmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.utilities.GeneralUtility;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class CategoryPage {
	WebDriver driver;
	@FindBy(xpath = "//a[text()=' New']")
	WebElement newCategoryButton;
	@FindBy(xpath = "//a[text()=' Search']")
	WebElement searchCategoryButton;
	@FindBy(xpath = "//a[text()='Reset']")
	WebElement resetCategoryButton;
	@FindBy(xpath = "//table//tbody//tr//td[3]")
	WebElement statusLink;
	@FindBy(xpath = "//table//tbody//tr//td[4]//a[1]")
	WebElement updateButton;
	@FindBy(xpath = "//table//tbody//tr//td[4]//a[2]")
	WebElement deleteButton;
	@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'Category Status Changed Successfully')]")
	WebElement statusChangedAlert;
	@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'Category Updated Successfully ')]")
	WebElement updateStatusAlert;
	@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'Category Created Successfully ')]")
	WebElement newCategoryAlert;
	@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'Category Deleted Successfully')]")
	WebElement deleteStatusAlert;
	@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'Category already exists')]")
	WebElement duplicateStatusAlert;
	@FindBy(xpath = "//input[@id='category']")
	WebElement categoryInput;
	@FindBy(xpath = "//input[@name='un']")
	WebElement searchCategoryInput;
	@FindBy(xpath = "//div[@class='ms-selectable']//ul//li")
	WebElement selectGroupElement;
	@FindBy(xpath = "//div[@class='ms-selection']//ul//li")
	WebElement selectedGroupElement;
	@FindBy(xpath = "//input[@id='main_img']")
	WebElement imagePathInput;
	@FindBy(xpath = "//input[@name='top_menu'][1]")
	WebElement topMenuYesOption;
	@FindBy(xpath = "//input[@name='top_menu'][2]")
	WebElement topMenuNoOption;
	@FindBy(xpath = "//input[@name='show_home'][1]")
	WebElement showHomeYesOption;
	@FindBy(xpath = "//input[@name='show_home' and @value='no']")
	WebElement showHomeNoOption;
	@FindBy(xpath = "//button[@name='create']")
	WebElement createButton;
	@FindBy(xpath = "//a[text()='Cancel']")
	WebElement cancelButton;
	@FindBy(xpath = "//button[@name='update']")
	WebElement updateButtonElement;
	@FindBy(xpath = "//button[@name='Search']")
	WebElement searchButton;
	@FindBy(xpath = "//table/tbody/tr/td")
	List<WebElement> tableElements;
	@FindBy(xpath = "//a//span[@class=\"fas fa-trash-alt\"]")
	WebElement imageDeleteButton;
	String xpath = "//a//span[@class=\"fas fa-trash-alt\"]";
	WaitUtility waitUtility;
	PageUtility pageUtility;
	GeneralUtility generalUtility;

	public CategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getURL() {
		return driver.getCurrentUrl();
	}

	public boolean IsButtonInthePageAreVisible() {
		if ((newCategoryButton.isEnabled()) && (searchButton.isEnabled()) && (resetCategoryButton.isEnabled())
				&& (updateButton.isEnabled()) && (deleteButton.isEnabled()))
			return true;
		else
			return false;
	}
public boolean IsCategoriesAreDisplaiedInTable()
{
	boolean status = true;
	for (WebElement element : tableElements)
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

	public void insertNewCategory(String category, String image) {
		categoryInput.sendKeys(category);
		pageUtility.mouseClick(selectGroupElement);
		pageUtility.uploadImage(imagePathInput, image);
		pageUtility.mouseClick(topMenuYesOption);
		pageUtility.mouseClick(showHomeNoOption);
	}

	public String addCategory(String category, String image) {
		waitUtility = new WaitUtility(driver);
		pageUtility = new PageUtility(driver);
		newCategoryButton.click();
		insertNewCategory(category, image);
		pageUtility.mouseClick(createButton);
		return checkAlertMessage(newCategoryAlert);
	}

	public String addDuplicateCategory(String category, String image) {
		insertNewCategory(category, image);
		pageUtility.mouseClick(createButton);
		return checkAlertMessage(duplicateStatusAlert);
	}

	public boolean searchCategory(String category) {
		performSearch(category);
		boolean status = true;
		for (WebElement element : tableElements)
			if (element.getText().equals(".........RESULT NOT FOUND.......")) {
				status = false;
				break;
			}
		return status;
	}

	public void performSearch(String category) {
		pageUtility = new PageUtility(driver);
		searchCategoryButton.click();
		searchCategoryInput.sendKeys(category);
		searchButton.click();
	}

	public String updateCategory(String category, String updateCategory, String image) {
		pageUtility = new PageUtility(driver);
		performSearch(category);
		pageUtility.mouseClick(updateButton);
		categoryInput.clear();
		pageUtility.switchToAlert(driver, imageDeleteButton);
		categoryInput.sendKeys(updateCategory);
		pageUtility.uploadImage(imagePathInput, image);
		pageUtility.uploadImage(imagePathInput, image);
		pageUtility.mouseClick(updateButtonElement);
		return checkAlertMessage(updateStatusAlert);
	}

	public String deleteCategory(String category) {
		pageUtility = new PageUtility(driver);
		performSearch(category);
		pageUtility.switchToAlert(driver, deleteButton);
		return checkAlertMessage(deleteStatusAlert);
	}
}
