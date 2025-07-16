package com.api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.endpoints.UserApiServices;
import com.api.models.UserRequestPayload;
import com.api.utilities.DataProviders;

import io.restassured.response.Response;

public class TC_03_Assign3_CreateUsersDDTest {
	Logger logger;

	@Test(dataProvider = "UserData", dataProviderClass = DataProviders.class)
	public void createUsersDataDriven(String userFullname, String username, String userEmail) {
		try {
			logger = LogManager.getLogger(this.getClass());
			logger.info("==== TC_03_Assign3_CreateUsersDDTest started ====");
			UserRequestPayload userPayload = new UserRequestPayload();
			UserApiServices userService = new UserApiServices();

			logger.info("setting userPayload data from excelsheet to pojo class");
			userPayload.setName(userFullname);
			userPayload.setUsername(username);
			userPayload.setEmail(userEmail);

			logger.info("creating user for username: {}", username);
			Response response = userService.createUser(userPayload);

			logger.info("validating the response status code");
			Assert.assertEquals(response.getStatusCode(), 201);
			
			logger.info("==== TC_03_Assign3_CreateUsersDDTest finished ====");
			
		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
	}
}
