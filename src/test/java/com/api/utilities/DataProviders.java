package com.api.utilities;

import java.io.File;

import org.testng.annotations.DataProvider;


public class DataProviders {

    @DataProvider(name = "UserData")
    public static Object[][] getLoginData() {

    		String filePath = System.getProperty("user.dir") + File.separator + "testdata" + File.separator + "userData.xlsx";
        return ExcelUtils.getExcelData("Sheet1",filePath);  
    }
    
//    @DataProvider(name = "prompData")
//    public static Object[][] getPromptData(){
//    	System.out.println("Data provider called, no of rows: " + ExcelUtil.getExcelData("Sheet1",AppConstants.PROMPT_DATA_EXCEL_SHEET_PATH).length);
//    	return ExcelUtil.getExcelData("Sheet1",AppConstants.PROMPT_DATA_EXCEL_SHEET_PATH);
//    }
    
}

