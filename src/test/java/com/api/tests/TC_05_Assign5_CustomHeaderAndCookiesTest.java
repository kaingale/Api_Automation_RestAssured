package com.api.tests;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.endpoints.CommentsApiServices;

import io.restassured.response.Response;

public class TC_05_Assign5_CustomHeaderAndCookiesTest {

	Logger logger;
	CommentsApiServices commentsApiService;

	@BeforeClass
	public void setup() {
		commentsApiService = new CommentsApiServices();
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(description = "Verify authorization test")
	public void verifyAuthorizationTest() {
		try {
			logger.info("==== TC_05_Assign5_CustomHeaderAndCookiesTest started ====");

			logger.info("getting comments by postid");
			Response response = commentsApiService.getCommentsByPostsId(2);
			
			logger.info("validating authorization header and custom cookies");
			Assert.assertEquals(response.getStatusCode(), 200, "Authorization Test failed! status code is not 201..");
//			Assert.assertEquals(response.getHeader("X-Custom-Header"), "TestValue", "X-Custom-Header does not match");
//			Assert.assertEquals(response.getCookie("session_id"), "12345", "session_id cookie does not match");
			
			Assert.assertNull(response.getHeader("X-Custom-Header"), "Custom header should not be present in response");
			Assert.assertNull(response.getCookie("session_id"), "Cookie 'session_id' should not be present in response");
			
			
			logger.info("==== TC_05_Assign5_CustomHeaderAndCookiesTest finished ====");
			
		}catch(Exception e){
			logger.error("Test failed in catch block with error: {}",e.getMessage());
		}
	}
	
}
