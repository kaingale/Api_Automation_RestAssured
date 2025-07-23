package com.api.tests;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.endpoints.PostsApiServices;

import io.restassured.response.Response;

public class TC_10_Assign10_PaginationTest {
	Logger logger;
	public int PAGE_SIZE = 10;
	public int TOTAL_PAGES = 5;
	PostsApiServices postApiService;

	@Test
	public void testMultiplePagesPagination() {
		try {
			logger = LogManager.getLogger(this.getClass());
			logger.info("==== TC_10_Assign10_PaginationTest started ====");
			Set<Integer> allIds = new HashSet<>();
			postApiService = new PostsApiServices();

			for (int page = 1; page <= TOTAL_PAGES; page++) {
				logger.info("calling readallpostspagination method");
				Response response = postApiService.readAllPostsPagination(page, PAGE_SIZE);

				logger.info("validating status code");
				Assert.assertEquals(response.getStatusCode(), 200, "Status code for page " + page);

				logger.info("creating list of ids from response body");
				List<Integer> ids = response.jsonPath().getList("id");

				if (ids.size() < PAGE_SIZE && page != TOTAL_PAGES) {
					logger.info("validating items on the pages");
					Assert.fail("Page " + page + " returned fewer items than expected before the last page.");
				}

				for (Integer id : ids) {
					logger.info("validating ids");
					Assert.assertTrue(allIds.add(id), "Duplicate ID found across pages: " + id);
				}
			}
		} catch (Exception e) {
			logger.error("Test failed in catch block with error: {}", e.getMessage());
			Assert.fail("Test failed with exception: " + e.getMessage());
		}
		logger.info("==== TC_10_Assign10_PaginationTest finished ====");
	}
}
