package com.api.endpoints;

import static io.restassured.RestAssured.given;

import com.api.models.PostsRequestPayload;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostsApiServices {

	public Response createPosts(PostsRequestPayload payLoad, String fakeAuthToken) {
		Response response = given()
				.header("Authorization", fakeAuthToken)
				.contentType(ContentType.JSON)
				.body(payLoad)
			.when()
				.post(Routes.postsPostUrl);
	
		return response;
	}
	
	public Response readPostsQueryParam(int givenUserId) {
		Response response = given()
				.queryParam("userId", givenUserId)
			.when()
				.get(Routes.postsGetUrlQueryParam);
		
		return response;
	}

	public Response readAllPosts() {
		Response response = given()
				.when()
				.get(Routes.postsGetUrlQueryParam);
		
		return response;
	}
	
	public Response readAllPostsPagination(int page, int pageSize) {
		Response response = given()
                .queryParam("_page", page)
                .queryParam("_limit", pageSize)
                .get(Routes.postsGetUrlQueryParam);
		
		return response;
	}
	
}
