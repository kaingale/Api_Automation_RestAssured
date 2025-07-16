package com.api.endpoints;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Routes {

	//Base url
	private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
	
	//User module urls
	protected static String userGetUrl = BASE_URL + "/users/{id}";
	protected static String userPostUrl = BASE_URL + "/users";
	protected static String userPutUrl = BASE_URL + "/users/{id}";
	protected static String userDeleteUrl = BASE_URL + "/users/{id}";

	//posts module urls
	protected static String postsPostUrl = BASE_URL + "/posts";
	protected static String postsGetUrlQueryParam = BASE_URL + "/posts";
	
	
	//comments module urls
	protected static String commentsGetUrl = BASE_URL + "/comments";
	
}
