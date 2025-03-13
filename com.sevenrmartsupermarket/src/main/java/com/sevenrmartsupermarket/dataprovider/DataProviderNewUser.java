package com.sevenrmartsupermarket.dataprovider;

import org.testng.annotations.DataProvider;

import com.sevenrmartsupermarket.utilities.GeneralUtility;

public class DataProviderNewUser {
	@DataProvider(name="newUser")
	public Object[][] flipcartproducts()
	{
		return new Object [][] {{GeneralUtility.getRandomName(),"Anitha123","Admin"},{GeneralUtility.getRandomName(),"Rahul123","Staff"}};
	}

}
