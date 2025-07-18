package com.api.tests;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.endpoints.UserApiServices;
import com.api.models.UserRequestPayload;
import com.api.models.UserAddressPayload;
import com.api.models.UserGeoPayload;
import com.api.models.UserCompanyPayload;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class TC_01_Assign1_CrudUserTest {

	Faker faker;
	UserRequestPayload userPayload;
	UserGeoPayload geo;
	UserAddressPayload address;
	UserCompanyPayload company;
	Logger logger;
	UserApiServices userService;

	@BeforeClass
	public void setup() {
		faker = new Faker();
		userService = new UserApiServices();
		
		//setup payload
		geo = new UserGeoPayload("12.34", "56.78");
		address = new UserAddressPayload("Main St", "Suite 100", "Metropolis", "12345", geo);
		company = new UserCompanyPayload("Deloitte", "Innovate your future", "consulting");
		userPayload = new UserRequestPayload("John Doe", "johndoe", "john.doe@example.com", address, "123-456-7890",
				"johndoe.com", company);

		logger = LogManager.getLogger(this.getClass());

		logger.info("==== TC_01_Assign1_CrudUserTest started ====");
	}

	@Test(priority = 1)
	public void createUserTest() {
		try {
			logger.info("==== createUserTest started ====");
			logger.info("creating user");
			Response response = userService.createUser(userPayload);

			logger.info("assertions for status code");
			Assert.assertEquals(response.getStatusCode(), 201, "Post Test failed! status code is not 201..");
			logger.info("asserting name");
			Assert.assertEquals(response.jsonPath().getString("name"), "John Doe");
			logger.info("asserting username");
			Assert.assertEquals(response.jsonPath().getString("username"), "johndoe");
			logger.info("asserting email");
			Assert.assertEquals(response.jsonPath().getString("email"), "john.doe@example.com");
			logger.info("asserting address");
			Assert.assertEquals(response.jsonPath().getString("address.street"), "Main St");
			Assert.assertEquals(response.jsonPath().getString("address.suite"), "Suite 100");
			Assert.assertEquals(response.jsonPath().getString("address.city"), "Metropolis");
			Assert.assertEquals(response.jsonPath().getString("address.zipcode"), "12345");
			logger.info("asserting geo location");
			Assert.assertEquals(response.jsonPath().getString("address.geo.lat"), "12.34");
			Assert.assertEquals(response.jsonPath().getString("address.geo.lng"), "56.78");
			logger.info("asserting phone");
			Assert.assertEquals(response.jsonPath().getString("phone"), "123-456-7890");
			logger.info("asserting website");
			Assert.assertEquals(response.jsonPath().getString("website"), "johndoe.com");
			logger.info("asserting company details");
			Assert.assertEquals(response.jsonPath().getString("company.name"), "Deloitte");
			Assert.assertEquals(response.jsonPath().getString("company.catchPhrase"), "Innovate your future");
			Assert.assertEquals(response.jsonPath().getString("company.bs"), "consulting");

			logger.info("==== createUserTest finished ====");
		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
	}

//	@Test(priority = 2)
	public void readUserById() {
		try {
			logger.info("==== readUserById started ====");
			logger.info("reading user by id");
			Response response = userService.readUser("1");

			logger.info("asserting status code");
			Assert.assertEquals(response.getStatusCode(), 200, "Get Test failed! status code is not 200..");

			logger.info("==== readUserById finished ====");
		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
	}

//	@Test(priority = 3)
	public void updateUserTest() {
		try {
			logger.info("==== updateUserTest started ====");
			userPayload.setName(faker.name().fullName());
			userPayload.setUsername(faker.name().username());
			userPayload.setEmail(faker.internet().emailAddress());

			logger.info("updating user by id");
			Response response = userService.updateUser(userPayload, "1");
			response.then().log().body();
			logger.info("validating response status code");
			Assert.assertEquals(response.getStatusCode(), 200, "Put Test failed! status code is not 200..");

			logger.info("==== updateUserTest finished ====");
		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
	}

//	@Test(priority = 4)
	public void deleteUserTest() {
		try {
			logger.info("==== deleteUserTest started ====");
			logger.info("deleting user by id");
			Response response = userService.deleteUser("1");

			logger.info("validating response status code");
			Assert.assertEquals(response.getStatusCode(), 200, "Delete Test failed! status code is not 200..");

			logger.info("==== deleteUserTest finished ====");
			logger.info("==== TC_01_Assign1_CrudUserTest finished ====");
		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
	}
}
