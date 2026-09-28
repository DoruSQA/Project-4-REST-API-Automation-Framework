package project.tests.auth;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import project.base.BaseService;
import project.base.BaseTest;
import project.testdata.TestDataProvider;
import project.testdata.expected.AuthExpected;
import project.testdata.model.request.LoginUser;
import project.testdata.model.request.RefreshTokenUser;
import project.testdata.model.response.AuthPayload;
import project.testdata.model.response.FailMessagePayload;
import project.testdata.model.response.SuccessRefreshTokenPayload;
import project.utils.JsonSchema;

public class AuthTests extends BaseTest {

	@Test(groups = { "Smoke", "Regression","Auth" }, description = "API_AUTH_001: Login with valid credentials", 
			dataProvider = "userWithValidCredentials", dataProviderClass = TestDataProvider.class)
	public void loginWithValidCredentials(LoginUser userWithValidCredentials) {

		// Send request
		Response httpAuthResponse = authService.login(userWithValidCredentials);

		// Validate status code and response headers via response specification
		httpAuthResponse.then().spec(BaseService.okResponse());

		// Deserialization
		AuthPayload authPayload = httpAuthResponse.as(AuthPayload.class);

		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, AuthExpected.AUTH_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertNotNull(authPayload.getAccessToken(), "Access token value is missing");
		softAssertion.assertNotNull(authPayload.getRefreshToken(), "Refresh token value is missing");
		softAssertion.assertAll();

	}

	@Test(groups = { "Regression","Auth" }, description = "API_AUTH_002: Login with valid username and wrong password",
			dataProvider = "userWithWrongPassword", dataProviderClass = TestDataProvider.class)
	public void loginWithValidUsernameAndWrongPassword(LoginUser userWithWrongPassword) {

		// Send request
		Response httpAuthResponse = authService.login(userWithWrongPassword);

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.unauthorizedResponse());

		// Deserialization
		FailMessagePayload failedResponsePayload = httpAuthResponse.as(FailMessagePayload.class);

		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, AuthExpected.AUTH_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertNotNull(failedResponsePayload.getMessage(), "Error msg is missing");
		softAssertion.assertAll();

	}

	@Test(groups = { "Regression", "Auth" }, description = "API_AUTH_003: Login with valid password and wrong username", 
			dataProvider = "userWithWrongUsername", dataProviderClass = TestDataProvider.class)
	public void loginWithValidPasswordAndWrongUsername(LoginUser userWithWrongUsername) {

		// Send request
		Response httpAuthResponse = authService.login(userWithWrongUsername);

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.unauthorizedResponse());

		// Deserialization
		FailMessagePayload failedResponsePayload = httpAuthResponse.as(FailMessagePayload.class);

		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, AuthExpected.AUTH_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertNotNull(failedResponsePayload.getMessage(), "Error msg is missing");
		softAssertion.assertAll();

	}

	@Test(groups = { "Smoke", "Regression","Auth" }, description = "API_AUTH_004: Refresh the access token by using a valid token", 
			dataProvider = "userWithValidRefreshToken", dataProviderClass = TestDataProvider.class)
	public void refreshAccessTokenByUsingAValidToken(RefreshTokenUser userWithValidRefreshToken) {

		// Send request
		Response httpAuthResponse = authService.refreshToken(userWithValidRefreshToken);

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.okResponse());

		// Deserialization
		SuccessRefreshTokenPayload successResponsePayload = httpAuthResponse.as(SuccessRefreshTokenPayload.class);

		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, AuthExpected.REFRESH_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertNotNull(successResponsePayload.getAccessToken(), "Access Token is missing");
		softAssertion.assertNotNull(successResponsePayload.getToken(), "Token is missing");
		softAssertion.assertAll();

	}

	@Test(groups = { "Regression","Auth" }, description = "API_AUTH_005: Refresh the access token by using a wrong token", 
			dataProvider = "userWithWrongRefreshToken", dataProviderClass = TestDataProvider.class)
	public void refreshAccessTokenByUsingAWrongToken(RefreshTokenUser userWithWrongRefreshToken) {

		// Send request
		Response httpAuthResponse = authService.refreshToken(userWithWrongRefreshToken);

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.unauthorizedResponse());

		// Deserialization
		FailMessagePayload successResponsePayload = httpAuthResponse.as(FailMessagePayload.class);

		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, AuthExpected.ERROR_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertNotNull(successResponsePayload.getMessage(), "Error msg is missing");
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression", "Auth" }, description = "API_AUTH_006: Refresh the access token by providing no token", 
			dataProvider = "userWithMissingRefreshToken", dataProviderClass = TestDataProvider.class)
	public void refreshAccessTokenWIthMissingToken(RefreshTokenUser userWithMissingRefreshToken) {

		// Send request
		Response httpAuthResponse = authService.refreshToken(userWithMissingRefreshToken);

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.badRequestResponse());

		// Deserialization
		FailMessagePayload successResponsePayload = httpAuthResponse.as(FailMessagePayload.class);

		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, AuthExpected.ERROR_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertNotNull(successResponsePayload.getMessage(), "Error msg is missing");
		softAssertion.assertEquals(successResponsePayload.getMessage(), AuthExpected.REFRESH_TOKEN_IS_REQUIRED);
		softAssertion.assertAll();
	}

	@Test(groups = { "Regression","Auth" }, description = "API_AUTH_007: Refresh access token by using an invald token format", 
			dataProvider = "userWithMalformedRefreshToken", dataProviderClass = TestDataProvider.class)
	public void refreshAccessTokenByProvidingMalformedToken(RefreshTokenUser userWithMalformedRefreshToken) {

		// Send request
		Response httpAuthResponse = authService.refreshToken(userWithMalformedRefreshToken);

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.unauthorizedResponse());

		// Deserialization
		FailMessagePayload successResponsePayload = httpAuthResponse.as(FailMessagePayload.class);

		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, AuthExpected.ERROR_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertNotNull(successResponsePayload.getMessage());
		softAssertion.assertEquals(successResponsePayload.getMessage(), AuthExpected.INVALID_REFRESH_TOKEN_FORMAT);
		softAssertion.assertAll();
	}
}
