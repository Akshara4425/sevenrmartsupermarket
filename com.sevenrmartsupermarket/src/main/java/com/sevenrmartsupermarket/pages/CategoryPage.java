package com.sevenrmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.constants.Constants;
import com.sevenrmartsupermarket.utilities.PageUtility;
import com.sevenrmartsupermarket.utilities.WaitUtility;

public class CategoryPage {
	WebDriver driver;
	@FindBy(xpath = "//a[text()=' New']")
	WebElement newCategory_Element ;
@FindBy(xpath = "//a[text()=' Search']")
	WebElement  searchCategory_Element;
@FindBy(xpath = "//a[text()='Reset']")
	WebElement  resetCategory_Element;
@FindBy(xpath = "//table//tbody//tr//td[3]")
	WebElement statusLink_Element ;
@FindBy(xpath = "//table//tbody//tr//td[4]//a[1]")
	WebElement updateButton_Element ;
@FindBy(xpath = "//table//tbody//tr//td[4]//a[2]")
	WebElement deleteButton_Element ;
@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'Category Status Changed Successfully')]")
	WebElement statusChangedAlert ;
@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'Category Updated Successfully ')]")
	WebElement updateStatusAlert;
@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'Category Created Successfully ')]")
WebElement newCategoryAlert;
@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'Category Deleted Successfully')]")
	WebElement deleteStatusAlert ;
@FindBy(xpath = "//div[contains(@class, 'alert-success') and contains(., 'Category already exists')]")
WebElement duplicateStatusAlert ;
@FindBy(xpath = "//input[@id='category']")
	WebElement  inputCategory;
@FindBy(xpath = "//input[@name='un']")
WebElement  searchInputCategory;
@FindBy(xpath = "//div[@class='ms-selectable']//ul//li")
	WebElement  selectGroupElement;
@FindBy(xpath = "//div[@class='ms-selection']//ul//li")
	WebElement  SelectedGroupElement;
@FindBy(xpath = "//input[@id='main_img']")
	WebElement imagePathElement ;
@FindBy(xpath = "//input[@name='top_menu'][1]")
	WebElement topMenuYesElement ;
@FindBy(xpath = "//input[@name='top_menu'][2]")
	WebElement topMenuNoElement ;
@FindBy(xpath = "//input[@name='show_home'][1]")
	WebElement  showHomeYesElement;
@FindBy(xpath = "//input[@name='show_home' and @value='no']")
	WebElement showHomeNoElement ;
@FindBy(xpath = "//button[@name='create']")
	WebElement submitButtonElement ;
@FindBy(xpath = "//a[text()='Cancel']")
	WebElement cancelButtonElement ;
@FindBy(xpath = "//button[@name='update']")
	WebElement updateButton ;
@FindBy(xpath = "//button[@name='Search']")
WebElement searchButton ;
@FindBy(xpath = "//table/tbody/tr/td")
List<WebElement> table_Element ;
@FindBy(xpath = "//a[@href=\"https://groceryapp.uniqassosiates.com/admin/Category/delete_image?id=1366&page_ad=1\"]")
WebElement imageDeleteButton;

WaitUtility waitUtility;
PageUtility pageUtility;

public String getUpdateSuccessAlert() {
	return updateStatusAlert.getText().trim().replaceAll("\\s+", " ");
}
public String getDeleteSuccessAlert() {
	return deleteStatusAlert.getText().trim().replaceAll("\\s+", " ");
}
public String getSuccessAlert() {
	return statusChangedAlert.getText().trim().replaceAll("\\s+", " ");
}
public String getNewCategorySuccessAlert()
{
return newCategoryAlert.getText().trim().replaceAll("\\s+", " ");
}

	public CategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getURL() {
		return driver.getCurrentUrl();
	}

	public String addCategory(String category,String image) {
		String Url="https://groceryapp.uniqassosiates.com/admin/list-category";
		waitUtility = new WaitUtility(driver);
		pageUtility=new PageUtility(driver);
		//pageUtility.mouseClick(resetCategory_Element);
		newCategory_Element.click();
		inputCategory.sendKeys(category);
		pageUtility.mouseClick(selectGroupElement);
		pageUtility.uploadImage(imagePathElement,image);
	//	 pageutility.uploadImage(choose_ImageFile, "BeautyProducts"); 
		pageUtility.mouseClick(topMenuYesElement);
		pageUtility.mouseClick(showHomeNoElement);
		pageUtility.mouseClick(submitButtonElement);
		if(newCategoryAlert.isDisplayed())
		{	
		return getNewCategorySuccessAlert();
		}
		else
		{	
			return "fail";
		}
	}
	public boolean searchCategory(String category) {
		pageUtility=new PageUtility(driver);
		searchCategory_Element.click();
		 searchInputCategory.sendKeys(category);
		searchButton.click();
		boolean status = true;
		for (WebElement element : table_Element)
			if (element.getText().equals(".........RESULT NOT FOUND......."))
				status = false;
		return status;
	}

	public void searchUpdateandDelete(String category) {
		pageUtility=new PageUtility(driver);
		searchCategory_Element.click();
		 searchInputCategory.sendKeys(category);
		searchButton.click();
		
	}


public String UpdateCategory(String category,String image) {
	pageUtility=new PageUtility(driver);
	searchUpdateandDelete( category) ;
	pageUtility.mouseClick(updateButton_Element);
	inputCategory.clear();
	inputCategory.sendKeys(category);
	pageUtility.switchToAlert(driver,imageDeleteButton);
	pageUtility.uploadImage(imagePathElement,image); 
	pageUtility.mouseClick(topMenuYesElement);
	pageUtility.mouseClick(showHomeNoElement);
	pageUtility.mouseClick(updateButton);
	if(updateStatusAlert.isDisplayed())	
	return getUpdateSuccessAlert();
	else	
		return "fail";
	
}
}
