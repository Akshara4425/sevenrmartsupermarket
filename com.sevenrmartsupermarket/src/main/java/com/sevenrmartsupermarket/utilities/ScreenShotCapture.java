package com.sevenrmartsupermarket.utilities;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.sevenrmartsupermarket.constants.Constants;

public class ScreenShotCapture {
	TakesScreenshot takesscreenshot;

	public void takeScreenShot(WebDriver driver, String imageName) {
		try {

			takesscreenshot = (TakesScreenshot) driver;
			File screenShot = takesscreenshot.getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(new Date());
			File destination = new File(Constants.SCREENSHOTS__FILE_PATH + imageName + "_" + timeStamp + ".png");
			FileHandler.copy(screenShot, destination);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
