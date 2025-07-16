package com.api.tests;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.endpoints.PostsApiServices;
import com.api.models.PostsRequestPayload;
import com.github.javafaker.Faker;

import io.restassured.response.Response;

public class TC_02_Assign2_AuthorizationTest {

	Logger logger;
	Faker faker;
	PostsRequestPayload postReqestPayload;
	PostsApiServices postApiServices;
	String userId;
	String title;
	String body;

	@BeforeClass
	public void setup() {
		faker = new Faker();
		postReqestPayload = new PostsRequestPayload();
		postApiServices = new PostsApiServices();
		
		userId = "11";
	    title = faker.book().title();
		body = faker.lorem().sentence();
		postReqestPayload.setUserId(Integer.parseInt(userId));
		postReqestPayload.setTitle(title);
		postReqestPayload.setBody(body);
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(description = "Verify authorization test")
	public void verifyAuthorizationTest() {
		try {
			logger.info("==== TC_02_Assign2_AuthorizationTest started ====");

			logger.info("creating post");
			String authorizationToken = "Bearer_faketoken123";
			Response response = postApiServices.createPosts(postReqestPayload,authorizationToken);
			
			logger.info("validating authorization header and status code");
			Assert.assertEquals(response.getStatusCode(), 201, "Authorization Test failed! status code is not 201..");
			
			logger.info("validating response values");
	        Assert.assertEquals(response.jsonPath().getInt("userId"), Integer.parseInt(userId), "userId does not match");
	        Assert.assertEquals(response.jsonPath().getString("title"), title, "title does not match");
	        Assert.assertEquals(response.jsonPath().getString("body"), body, "body does not match");
			
			logger.info("==== TC_02_Assign2_AuthorizationTest finished ====");
			
		}catch(Exception e){
			logger.error("Test failed in catch block with error: {}", e.getMessage());
	        	Assert.fail("Test failed with exception: " + e.getMessage());
		}
	}
	
}
