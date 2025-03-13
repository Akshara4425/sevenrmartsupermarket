package com.sevenrmartsupermarket.pages;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sevenrmartsupermarket.constants.Constants;

public class LoginPage {
	WebDriver driver;
	Properties properties = new Properties();
	@FindBy(xpath = "//input[@name='username']")
	private WebElement userName_Field;
	@FindBy(xpath = "//input[@name='password']")
	private WebElement password_Field;
	@FindBy(xpath = "//button[contains(text(),'Sign In')]")
	private WebElement signIn_Button;
	@FindBy(xpath = "//div[contains(@class, 'alert-danger') and contains(., 'Invalid Username')]")
	private WebElement invalidAlert_Message;
	

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		try {
			FileInputStream ip = new FileInputStream(Constants.CONFIG_FILE_PATH);
			properties.load(ip);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public void enterUserName(String userName) {
		userName_Field.sendKeys(userName);
	}

	public void enterPassword(String password) {
		password_Field.sendKeys(password);
	}

	public void clickOnSignInButton() {
		signIn_Button.click();
	}

	public HomePage logIn(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		clickOnSignInButton();
		return new HomePage(driver);
	}

	public HomePage logIn() {
		String userName = properties.getProperty("username");
		String password = properties.getProperty("password");
		logIn(userName, password);
		return new HomePage(driver);
	}

	public String logInInvalidalert(String userName, String password) {
		enterUserName(userName);
		enterPassword(password);
		clickOnSignInButton();
		return  invalidAlert_Message.getText();

		
	}

}
