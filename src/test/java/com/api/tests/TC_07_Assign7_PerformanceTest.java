package com.api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.endpoints.PostsApiServices;

import io.restassured.response.Response;

public class TC_07_Assign7_PerformanceTest {
	Logger logger;

	@Test
	public void testApiPerformance() {
		try {
			logger = LogManager.getLogger(this.getClass());
			logger.info("==== TC_07_Assign7_PerformanceTest started ====");
			PostsApiServices postApiService = new PostsApiServices();

			logger.info("creating validation pointers");
			int requestCount = 100;

			long maxAllowedAvg = 1000;
			long maxAllowedMax = 4000;
			long maxAllowedMin = 500;

			long totalTime = 0;
			long maxTime = Long.MIN_VALUE;
			long minTime = Long.MAX_VALUE;

			for (int i = 0; i < requestCount; i++) {
				long startTime = System.currentTimeMillis();
				Response response = postApiService.readAllPosts();
				long responseTime = System.currentTimeMillis() - startTime;

				// Collect metrics
				totalTime += responseTime;
				if (responseTime > maxTime)
					maxTime = responseTime;
				if (responseTime < minTime)
					minTime = responseTime;

				Assert.assertEquals(response.getStatusCode(), 200, "Test failed! status code is not 200");
			}

			logger.info("printing all the validation times");
			double avgTime = totalTime / (double) requestCount;
			System.out.println("Performance Test Results:");
			System.out.println("Total Requests: " + requestCount);
			System.out.println("Average Response Time (ms): " + avgTime);
			System.out.println("Max Response Time (ms): " + maxTime);
			System.out.println("Min Response Time (ms): " + minTime);

			logger.info("validating avgtime");
			Assert.assertTrue(avgTime <= maxAllowedAvg,
					"Average response time exceeded threshold: " + avgTime + " > " + maxAllowedAvg);
			logger.info("validating maxtime");
			Assert.assertTrue(maxTime <= maxAllowedMax,
					"Max response time exceeded threshold: " + maxTime + " > " + maxAllowedMax);
			logger.info("validating mintime");
			Assert.assertTrue(minTime <= maxAllowedMin,
					"Min response time exceeded threshold: " + minTime + " > " + maxAllowedMin);
		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
		logger.info("==== TC_07_Assign7_PerformanceTest finished ====");
	}
}
