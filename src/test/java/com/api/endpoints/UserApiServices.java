package com.api.endpoints;

import static io.restassured.RestAssured.given;

import com.api.models.UserRequestPayload;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserApiServices extends Routes{

	public Response createUser(UserRequestPayload payload) {				
		Response response = given()
				.contentType(ContentType.JSON)
				.body(payload)
			.when()
				.post(Routes.userPostUrl);
	
	return response;
	}
	
	public Response readUser(String userId) {
		Response response = given()
				.pathParam("id", userId)
				.when()
				.get(Routes.userDeleteUrl);
		
		return response;
	}
	
	
	public Response updateUser(UserRequestPayload payload, String userId) {
		Response response = given()
					.pathParam("id", userId)
					.contentType(ContentType.JSON)
					.body(payload)
				.when()
					.put(Routes.userPutUrl);
		
		return response;
	}

	public Response deleteUser(String userId) {
		Response response = given()
				.pathParam("id", userId)
				.when()
				.delete(Routes.userDeleteUrl);
		
		return response;
	}
	
}
