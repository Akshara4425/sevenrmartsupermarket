package com.sevenrmartsupermarket.pages;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.utilities.PageUtility;

public class HomePage {
	WebDriver driver;
	PageUtility pageutility = new PageUtility(driver);
	@FindBy(xpath = "//li[@class=\"nav-item dropdown\"]//a[@data-toggle=\"dropdown\"]")
	private WebElement profile_Element;
	@FindBy(xpath = "//div[@class=\"small-box bg-info\"]//p[contains(text(),'Admin Users')][1]/following::a[contains(text(),'More info')][1]")
	private WebElement adminUser_Element;
	@FindBy(xpath = "//div[@class=\"small-box bg-info\"]//p[contains(text(),'Category')][1]/following::a[contains(text(),'More info')][1]")
	private WebElement category_Element;
	@FindBy(xpath = "//div[@class=\"small-box bg-info\"]//p[contains(text(),'Sub Category')][1]/following::a[contains(text(),'More info')][1]")
	private WebElement sub_Category_Element;
	@FindBy(xpath = "//div[@class=\"small-box bg-info\"]//p[contains(text(),'Manage Contact')][1]/following::a[contains(text(),'More info')][1]")
	private WebElement manage_Contact_Element;
	@FindBy(xpath = "//div[@class=\"small-box bg-info\"]//p[contains(text(),'Manage Product')][1]/following::a[contains(text(),'More info')][1]")
	private WebElement manage_Product_Element;
	@FindBy(xpath = "//div[@class=\"small-box bg-info\"]//p[contains(text(),'Manage Footer Text')][1]/following::a[contains(text(),'More info')][1]")
	private WebElement Manage_Footer_Text_Element;
	@FindBy(xpath = "//a[(@class=\"dropdown-item\") and(@href=\"https://groceryapp.uniqassosiates.com/admin/logout\") ]")
	private WebElement logOut_Element;
	@FindBy(xpath = "//a[contains(text(),'Admin')]")
	private WebElement profileName_Element;
	@FindBy(xpath = "//a[contains(text(),'More info')]")
	private List<WebElement> moreInfo;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getProfileName() {
		return profileName_Element.getText();
	}

	public String getPageTiltle() {
		return driver.getTitle();
	}

	public List<WebElement> getMoreInfoLinks() {
		return moreInfo;
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public void selectDropDown() {
		pageutility = new PageUtility(driver);
		pageutility.mouseClick(profile_Element);
	}

	public void chooseFromDropDown() {
		pageutility = new PageUtility(driver);
		pageutility.mouseClick(logOut_Element);
	}
	public boolean morelinkNavigation()
	{
		pageutility = new PageUtility(driver);
	
		boolean status=true;
		
			
		for (int i = 0; i < moreInfo.size(); i++) {
			getMoreInfoLinks().get(i).click();
			String currentURL = getCurrentURL();
			
			if((("https://groceryapp.uniqassosiates.com/admin").equals(currentURL))||(("https://groceryapp.uniqassosiates.com/admin/home").equals(currentURL)))
			{
				System.out.println("Navigation failed at the index: " + i);
				status=true;
			}
			if(i!=5&&i!=6&&i!=11)
			driver.navigate().back();
			
		}
		return status;
	}

	public boolean logOutFunctionality() {
		selectDropDown();
		chooseFromDropDown();
		if (getCurrentURL().equals("https://groceryapp.uniqassosiates.com/admin/login"))
			return true;
		else
			return false;

	}

	public AdminUserPage selectAdminUser() {
		adminUser_Element.click();
		return new AdminUserPage(driver);
	}

	public CategoryPage selectCategoryPage() {
		category_Element.click();
		return new CategoryPage(driver);
	}

}
