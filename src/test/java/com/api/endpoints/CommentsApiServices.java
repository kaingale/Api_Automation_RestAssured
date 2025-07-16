package com.api.endpoints;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;

public class CommentsApiServices {
		
		public Response getCommentsByPostsId(int postId) {
			Response response = given()
					.header("X-Custom-Header", "TestValue")
                    	.cookie("session_id", "12345")
					.queryParam("postId", postId)
				.when()
					.get(Routes.commentsGetUrl);
			
			return response;
		}
}
