package com.api.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.endpoints.UserApiServices;

import io.restassured.response.Response;

public class TC_08_Assign8_JsonSchemaValidationTest {

	UserApiServices userApiService;
	Logger logger;

	@Test
	public void validateUsersJsonSchemaFromFile() {
		try {
			logger = LogManager.getLogger(this.getClass());
			logger.info("==== TC_08_Assign8_JsonSchemaValidationTest started ====");
			userApiService = new UserApiServices();

			logger.info("reading all the users");
			Response response = userApiService.readAllUsers();

			logger.info("validating status code");
			Assert.assertEquals(response.getStatusCode(), 200, "Test failed! status code is not 200");

			logger.info("validating json schema using user-schema.json file");
			response.then().assertThat().body(matchesJsonSchemaInClasspath("user-schema.json"));
		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
		logger.info("==== TC_08_Assign8_JsonSchemaValidationTest finished ====");
	}

}
