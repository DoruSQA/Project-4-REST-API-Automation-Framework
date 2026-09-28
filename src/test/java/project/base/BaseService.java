package project.base;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import project.testdata.expected.AuthExpected;
import project.testdata.expected.StatusCode;

public class BaseService {

	private static final String BASE_URL = "https://dummyapi.codesmash.in";

	public static RequestSpecification newInit() {
		return RestAssured.given().baseUri(BASE_URL);
	}

	public static ResponseSpecification okResponse() {
		return new ResponseSpecBuilder()
				   .expectStatusCode(StatusCode.OK)
				   .expectContentType(AuthExpected.CONTENT_TYPE)
				   .build();
	}
	
	public static ResponseSpecification createdResponse() {
		return new ResponseSpecBuilder()
					.expectStatusCode(StatusCode.CREATED)
					.expectContentType(AuthExpected.CONTENT_TYPE)
					.build();
	}

	public static ResponseSpecification unauthorizedResponse() {
		return new ResponseSpecBuilder()
					.expectStatusCode(StatusCode.UNAUTHORIZED)
					.expectContentType(AuthExpected.CONTENT_TYPE)
					.build();
	}

	public static ResponseSpecification badRequestResponse() {
		return new ResponseSpecBuilder()
					.expectStatusCode(StatusCode.BAD_REQUEST)
					.expectContentType(AuthExpected.CONTENT_TYPE)
					.build();
	}
	
	public static ResponseSpecification notFoundResponse() {
		return new ResponseSpecBuilder()
					.expectStatusCode(StatusCode.NOT_FOUND)
					.expectContentType(AuthExpected.CONTENT_TYPE)
					.build();
	}

}
