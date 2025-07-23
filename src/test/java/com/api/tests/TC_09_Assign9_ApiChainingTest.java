package com.api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.utilities.ExtentReportManager;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TC_09_Assign9_ApiChainingTest {
	Logger logger;

	@Test
	public void testApiChaining_CreateAndGetObject() {
		try {
			logger = LogManager.getLogger(this.getClass());
			logger.info("==== TC_09_Assign9_ApiChainingTest started ====");
			ExtentReportManager.logStep("==== TC_09_Assign9_ApiChainingTest started ====");
			
			logger.info("payload setup");
			ExtentReportManager.logStep("payload setup");
			JSONObject createPayload = new JSONObject();
			createPayload.put("name", "Test Object");
			createPayload.put("data", new JSONObject().put("type", "example").put("year", 2024));

			logger.info("creating post request");
			ExtentReportManager.logStep("creating post request");
			Response createResponse = RestAssured.given().header("Content-Type", "application/json")
					.body(createPayload.toString()).when().post("https://api.restful-api.dev/objects").then()
					.statusCode(200).extract().response();

			logger.info("getting object id from response");
			ExtentReportManager.logStep("getting object id from response");
			String objectId = createResponse.jsonPath().getString("id");
			
			logger.info("validating object id");
			ExtentReportManager.logStep("validating object id");
			Assert.assertNotNull(objectId, "Test failed! Object ID should not be null");
			System.out.println("Object id: " + objectId);

			logger.info("using object id from post request in get request to read object by id");
			ExtentReportManager.logStep("using object id from post request in get request to read object by id");
			Response getResponse = RestAssured.given().queryParam("id", objectId).when()
					.get("https://api.restful-api.dev/objects").then().statusCode(200).extract().response();

			logger.info("extracting id and name from getresponse");
			ExtentReportManager.logStep("extracting id and name from getresponse");
			String returnedId = getResponse.jsonPath().getString("[0].id");
			String returnedName = getResponse.jsonPath().getString("[0].name");

			logger.info("validating object id and name");
			ExtentReportManager.logStep("validating object id and name");
			Assert.assertEquals(returnedId, objectId, "Test failed! Returned ID should match created object ID");
			Assert.assertEquals(returnedName, "Test Object",
					"Test failed! Returned name should match created object name");

		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			ExtentReportManager.logStep("Test failed in catch block");
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
		
		logger.info("==== TC_09_Assign9_ApiChainingTest finished ====");
		ExtentReportManager.logStep("==== TC_09_Assign9_ApiChainingTest finished ====");
	}
}
