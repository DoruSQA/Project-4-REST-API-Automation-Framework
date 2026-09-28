package project.service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import project.base.BaseService;
import project.testdata.model.request.LoginUser;
import project.testdata.model.request.RefreshTokenUser;

public class AuthService {

	
	// Static Routes
	private static final String LOGIN_ROUTE= "/api/auth/login";
	private static final String REFRESH_TOKEN_ROUTE= "/api/auth/refresh";
	
	// HTTP request configuration
	public Response login(LoginUser userPayload)
	{
		return BaseService.newInit().contentType(ContentType.JSON).body(userPayload).when().post(LOGIN_ROUTE);
	}
	
	public Response refreshToken(RefreshTokenUser refreshTokenPayload)
	{
		return BaseService.newInit().contentType(ContentType.JSON).body(refreshTokenPayload).when().post(REFRESH_TOKEN_ROUTE);
	}
	
}
