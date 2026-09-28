package project.tests.cart;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import project.base.BaseService;
import project.base.BaseTest;
import project.testdata.TestDataGenerator;
import project.testdata.expected.CartExpected;
import project.testdata.model.response.DeletePayload;
import project.testdata.model.response.FailMessagePayload;
import project.utils.JsonSchema;

public class CartDeleteTests extends BaseTest {
	

	@Test(groups = {"Smoke","Regression", "Cart" }, description = "API_CART_013: Delete cart by providing valid ID")
	public void deleteCarByProvidingValidID() // => Valid ID range for this app: 0-200
	{
		// Send request       
		Response httpAuthResponse =cartService.deleteCart("1"); 

		// Validate status code and response headers via response specification
		httpAuthResponse.then().spec(BaseService.okResponse());
		
		// Deserialization 
		DeletePayload deleteUserPayload = httpAuthResponse.as(DeletePayload.class);
		
		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, CartExpected.DELETE_CART_SCHEMA_PATH);
		
		// Validate response body
		softAssertion.assertTrue(deleteUserPayload.getMessage().contains("deleted"),"Error message doesn't contain the expected text: '...deleted'");
		softAssertion.assertTrue(deleteUserPayload.isDeleted(), "Expected 'deleted' to be true");
		softAssertion.assertAll();
	}
	
	@Test(groups = {"Regression", "Cart" }, description = "API_CART_014: Delete cart by providing non existing ID")
	public void deleteCartWithNonExistingID() // => Valid ID range for this app: 0-200
	{
		// Send request        
		Response httpAuthResponse =cartService.deleteCart(TestDataGenerator.randomNumericValue(5)); 

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.notFoundResponse());
		
		// Deserialization 
		FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);
		
		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, CartExpected.ERROR_SCHEMA_PATH);
		
		// Validate response body
		softAssertion.assertEquals(failMsgPayload.getMessage(), CartExpected.CART_ID_NOT_FOUND);
		softAssertion.assertAll();
	}
	
	@Test(groups = {"Regression", "Cart" }, description = "API_CART_015: Delete cart with invalid ID format")
	public void deleteCartWithInvalidIDFormat() // => Valid ID Format: numeric
	{
		// Send request     
		Response httpAuthResponse =cartService.deleteCart(TestDataGenerator.randomCharacters(5)); 

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.notFoundResponse());
		
		// Deserialization 
		FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);
		
		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, CartExpected.ERROR_SCHEMA_PATH);
		
		// Validate response body
		softAssertion.assertEquals(failMsgPayload.getMessage(), CartExpected.INVALID_CART_ID_FORMAT);
		softAssertion.assertAll();
	}
	
	
	@Test(groups = {"Regression", "Cart" }, description = "API_CART_016: Delete cart by providing empty ID")
	public void deleteCartByProvidingEmptyID() // =>  ID as path param: mandatory
	{
		// Send request     
		Response httpAuthResponse =cartService.deleteCart(TestDataGenerator.emptyString()); 

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.badRequestResponse());
		
		// Deserialization 
		FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);
		
		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, CartExpected.ERROR_SCHEMA_PATH);
		
		// Validate response body
		softAssertion.assertEquals(failMsgPayload.getMessage(), CartExpected.CART_ID_IS_REQUIRED);
		softAssertion.assertAll();
	}
	
	
}

