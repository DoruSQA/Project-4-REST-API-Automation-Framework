package project.tests.cart;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import project.base.BaseService;
import project.base.BaseTest;
import project.testdata.TestDataProvider;
import project.testdata.expected.CartExpected;
import project.testdata.model.request.UpdateCart;
import project.testdata.model.response.FailMessagePayload;
import project.testdata.model.response.UpdateCartPayload;
import project.utils.JsonSchema;

public class CartUpdateTests extends BaseTest {
	
	
	@Test(groups = {"Smoke","Regression", "Cart" }, description = "API_PROD_011: Update cart with valid details",
			dataProvider = "cartWithValidData", dataProviderClass = TestDataProvider.class)
	public void updateCartWithValidDetails(UpdateCart cartWithValidData, String validID) 
	{
		// Send request  
		Response httpAuthResponse =cartService.updateCart(cartWithValidData, validID); 

		// Validate status code and response headers via response specification
		httpAuthResponse.then().spec(BaseService.okResponse());

		// Deserialization 
		UpdateCartPayload updateCartPayload = httpAuthResponse.as(UpdateCartPayload.class);
		
		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, CartExpected.UPDATE_CART_SCHEMA_PATH);
		
		// Validate response body
		softAssertion.assertEquals(updateCartPayload.getId(), Integer.parseInt(validID));
		softAssertion.assertTrue(updateCartPayload.isUpdated());
		softAssertion.assertAll();
	}
	
	@Test(groups = {"Regression", "Cart" }, description = "API_PROD_012: Update cart with non existing cart ID",
			dataProvider = "cartWithValidDataAndNonExistingID", dataProviderClass = TestDataProvider.class)
	public void updateCartWithNonExistingCartID(UpdateCart cartWithValidData, String nonExistingID)  // => Valid ID range for this app: 0-200
	{
		// Http response    
		Response httpAuthResponse =cartService.updateCart(cartWithValidData, nonExistingID); 

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
	
	
	
	
	
	
	
	

}
