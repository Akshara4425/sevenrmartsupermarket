package com.sevenrmartsupermarket.dataprovider;

import org.testng.annotations.DataProvider;

import com.sevenrmartsupermarket.utilities.ExcelReader;


public class DataProviderNewUser {
	ExcelReader excelreader=new ExcelReader();
	@DataProvider(name="newUser")
	public Object[][] newAdminUserList()
	{
		excelreader.setExcelFile("AdminUserCredentials", "AdminUserCredentials");
	return excelreader.getMultidimentionalData(6, 3);
	}

}
