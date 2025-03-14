package com.sevenrmartsupermarket.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sevenrmartsupermarket.utilities.PageUtility;

public class HomePage {
WebDriver driver;
	PageUtility pageutility=new PageUtility(driver);
	@FindBy(xpath = "//li[@class=\"nav-item dropdown show\"]//img")
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
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
}
	
	public String getProfileName()
	{
		return profileName_Element.getText();
	}
	public String getPageTiltle()
	{
		return driver.getTitle();
	}
	 public List<WebElement> getMoreInfoLinks()
	 {
		 List<WebElement> moreInfo=driver.findElements(By.xpath("//a[contains(text(),'More info')]"));
	        return moreInfo;
	    }
	 public String getCurrentURL()
	 {
		 return driver.getCurrentUrl();
	 }
	 public boolean logOutFunctionality()
	 {
		 pageutility=new PageUtility(driver);
		pageutility.mouseClick(profile_Element); 
		pageutility.mouseClick(logOut_Element);
		if(getCurrentURL().equals("https://groceryapp.uniqassosiates.com/admin/login"))
		return true;
		else
			return false;
		 
	 }
	 public AdminUserPage selectAdminUser()
	 {
		 adminUser_Element.click(); 
		 return  new AdminUserPage(driver);
	 }
	 public CategoryPage selectCategoryPage()
	 {
		 category_Element.click();
		 return new CategoryPage(driver);
	 }
	 						


}
