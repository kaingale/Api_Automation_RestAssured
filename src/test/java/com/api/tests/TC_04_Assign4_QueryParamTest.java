package com.api.tests;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.endpoints.PostsApiServices;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TC_04_Assign4_QueryParamTest {

	Logger logger;
	PostsApiServices postApiService;

	@BeforeClass
	public void setup() {
		postApiService = new PostsApiServices();
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(description = "Verify query parameter test")
	public void verifyAuthorizationTest() {
		try {
			logger.info("==== TC_04_Assign4_QueryParamTest started ====");
			
			logger.info("getting posts by userId");
			Response response = postApiService.readPostsQueryParam(2);

			logger.info("asserting status code");
			Assert.assertEquals(response.getStatusCode(), 200, "Get Test failed! status code is not 200..");
			
			logger.info("asserting total no of response objects present for userId");
			JsonPath jsonPath = new JsonPath(response.getBody().asString());
			int totalBodies = jsonPath.getList("$").size();
			Assert.assertTrue(totalBodies == 10, "Incorrect objects found");
			
			System.out.println("Total objects for postId=1: " + totalBodies);
			
		}catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
	}
	
}
