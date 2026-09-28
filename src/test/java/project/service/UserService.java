package project.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import project.base.BaseService;
import project.testdata.model.request.CreatedUser;
import project.testdata.model.request.RefreshTokenUser;
import project.testdata.model.request.UpdatedUser;

public class UserService {

	// Static Routes
	private static final String GET_SIGLE_USER_ROUTE = "/api/users/{id}";
	private static final String GET_ALL_USERS_ROUTE = "api/users";
	private static final String CREATE_USER_ROUTE = "api/users";
	private static final String REFRESH_TOKEN_ROUTE = "/api/auth/refresh";
	private static final String UPDATE_USER_ROUTE = "/api/users/{id}";
	private static final String DELETE_USER_ROUTE ="/api/users/{id}";

	// HTTP request configuration
	public Response getUserById(String id) {
		
		return BaseService.newInit().pathParam("id", id).when().get(GET_SIGLE_USER_ROUTE);
	}
	
	public Response getAllUsers()
	{
		return BaseService.newInit().when().get(GET_ALL_USERS_ROUTE);
	}
	
	public Response createUser(CreatedUser validUser)
	{
		return BaseService.newInit().contentType(ContentType.JSON).body(validUser).when()
				.post(CREATE_USER_ROUTE);
	}
	
	public Response createUser(ObjectNode userWithMissingField)
	{
		return BaseService.newInit().contentType(ContentType.JSON).body(userWithMissingField).when()
				.post(CREATE_USER_ROUTE);
	}
	
	
	public Response updateUser(UpdatedUser updateUser, String id)
	{
		return BaseService.newInit().pathParam("id", id).contentType(ContentType.JSON).body(updateUser).when()
				.put(UPDATE_USER_ROUTE);
	}
	
	public Response deleteUser(String id)
	{
		return BaseService.newInit().pathParam("id", id).when().delete(DELETE_USER_ROUTE);
	}
	
	
	public Response refreshToken(RefreshTokenUser refreshTokenPayload) {
		
		return BaseService.newInit().contentType(ContentType.JSON).body(refreshTokenPayload).when()
				.post(REFRESH_TOKEN_ROUTE);
	}

}
