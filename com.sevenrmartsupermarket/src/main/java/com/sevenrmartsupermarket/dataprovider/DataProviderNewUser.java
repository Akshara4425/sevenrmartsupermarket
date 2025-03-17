package com.sevenrmartsupermarket.dataprovider;

import org.testng.annotations.DataProvider;

import com.sevenrmartsupermarket.utilities.ExcelReader;
import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class DataProviderNewUser {
	ExcelReader excelreader=new ExcelReader();
	@DataProvider(name="newUser")
	public Object[][] flipcartproducts()
	{
		excelreader.setExcelFile("AdminUserCredentials", "AdminUserCredentials");
	return excelreader.getMultidimentionalData(6, 3);
	}

}
