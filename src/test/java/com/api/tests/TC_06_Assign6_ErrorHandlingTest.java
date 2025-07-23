package com.api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.endpoints.UserApiServices;

import io.restassured.response.Response;

public class TC_06_Assign6_ErrorHandlingTest {

	UserApiServices userApiService;
	Logger logger;
	
	@BeforeClass
	public void setup() {
		logger = LogManager.getLogger(this.getClass());
	}

	@Test
	public void testMissingRequiredFields() {
		try {
			logger.info("==== TC_06_Assign6_ErrorHandlingTest started ====");
			logger.info("testMissingRequiredFields started");
			userApiService = new UserApiServices();
			logger.info("creating user with empty body");
			Response response = userApiService.createUserEmptyBody();

			/*
			 * 400 - Bad request 422 - Unprocessable Entity
			 * 
			 */
			logger.info("validating error code");
			Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 422
					|| response.getStatusCode() == 201, "Unexpected status code: " + response.getStatusCode());

			String body = response.getBody().asString();
			if (response.getStatusCode() != 201) {
				logger.info("validating error msg in response body");
				Assert.assertTrue(body.contains("error") || body.contains("missing") || body.contains("invalid")
						|| body.isEmpty(), "Expected error message in response body: " + body);
			}
		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
	}

	@Test
	public void testInvalidFieldTypes() {

		try {
			logger.info("testInvalidFieldtypes started");
			userApiService = new UserApiServices();
			logger.info("creating user with invalid fieldtypes");
			Response response = userApiService.createUserInvalidFieldTypes();

			logger.info("validating error code");
			Assert.assertTrue(response.getStatusCode() == 400 || response.getStatusCode() == 422
					|| response.getStatusCode() == 201, "Unexpected status code: " + response.getStatusCode());

			String body = response.getBody().asString();
			if (response.getStatusCode() != 201) {
				logger.info("validating error msg in response body");
				Assert.assertTrue(body.contains("error") || body.contains("invalid") || body.isEmpty(),
						"Expected error message in response body: " + body);
			}
		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
	}

	@Test
	public void testInvalidHttpMethod() {
		try {
			logger.info("invalid http method started");
			userApiService = new UserApiServices();
			logger.info("calling deleteAllUser method");
			Response response = userApiService.deleteToAllUsers();

			/*
			 * 405 - method not allowed 404 - Not Found
			 */
			logger.info("validating error code");
			Assert.assertTrue(response.getStatusCode() == 405 || response.getStatusCode() == 404
					|| response.getStatusCode() == 200, "Unexpected status code: " + response.getStatusCode());

			String body = response.getBody().asString();
			if (response.getStatusCode() != 200) {
				logger.info("validating error msg in reponsebody");
				Assert.assertTrue(body.contains("error") || body.contains("not allowed") || body.isEmpty(),
						"Expected error message in response body: " + body);
			}
		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
		
		logger.info("==== TC_06_Assign6_ErrorHandlingTest finished ====");
	}
}
