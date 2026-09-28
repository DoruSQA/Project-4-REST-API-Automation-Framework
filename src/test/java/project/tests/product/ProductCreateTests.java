package project.tests.product;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import project.base.BaseService;
import project.base.BaseTest;
import project.testdata.TestDataProvider;
import project.testdata.expected.ProductExpected;
import project.testdata.model.request.ProductUser;
import project.testdata.model.response.CreateProductPayload;
import project.testdata.model.response.FailMessagePayload;
import project.utils.JsonSchema;

public class ProductCreateTests extends BaseTest{

	
	@Test(groups = { "Smoke", "Regression", "Product" }, description = "API_PROD_007: Create product with valid details",
			dataProvider = "productWithValidDetails", dataProviderClass = TestDataProvider.class)
	public void createProductWithValidDetails(ProductUser productWithValidDetails) {
		
		// Send request
		Response httpAuthResponse = productService.createProduct(productWithValidDetails);

		// Validate status code and response headers via response specification
		httpAuthResponse.then().spec(BaseService.createdResponse());

		// Deserialization
		CreateProductPayload productCreatedPayload = httpAuthResponse.as(CreateProductPayload.class);

		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, ProductExpected.CREATE_PRODUCT_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertTrue(productCreatedPayload.isCreated(), "Product status is not 'true'");
		softAssertion.assertNotNull(productCreatedPayload.getId(), "Product ID is missing");
		softAssertion.assertAll();

	}
	
	@Test(groups = {"Regression", "Product" }, description = "API_PROD_08: Create product with stock zero",
			dataProvider = "productWithStockZero", dataProviderClass = TestDataProvider.class)
	public void createProductWithZeroStock(ProductUser productWithStockZero) {
		
		// Send request
		Response httpAuthResponse = productService.createProduct(productWithStockZero);

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.badRequestResponse());

		// Deserialization
		FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, ProductExpected.ERROR_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertEquals(failMsgPayload.getMessage(), ProductExpected.STOCK_GRATHER_THAN_0);
		softAssertion.assertAll();

	}
	
	@Test(groups = {"Regression", "Product" }, description = "API_PROD_09: Create product with empty title",
			dataProvider = "productWithEmptyTitle", dataProviderClass = TestDataProvider.class)
	public void createProductWithEmptyTitle(ProductUser productWithEmptyTitle) {
		
		// Send request
		Response httpAuthResponse = productService.createProduct(productWithEmptyTitle);

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.badRequestResponse());

		// Deserialization
		FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, ProductExpected.ERROR_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertEquals(failMsgPayload.getMessage(), ProductExpected.PRODUCT_TITLE_IS_REQUIRED);
		softAssertion.assertAll();

	}
	
	
	@Test(groups = {"Regression", "Product" }, description = "API_PROD_010: Create product with negative price",
			dataProvider = "productWithNegativePrice", dataProviderClass = TestDataProvider.class)
	public void createProductWithNegativePrice(ProductUser productWithNegativePrice) {
		
		// Send request    
		Response httpAuthResponse = productService.createProduct(productWithNegativePrice);

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.badRequestResponse());

		// Deserialization
		FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

		// Validate JSON schema
		JsonSchema.validate(httpAuthResponse, ProductExpected.ERROR_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertEquals(failMsgPayload.getMessage(), ProductExpected.PRICE_GRATHER_THAN_0);
		softAssertion.assertAll();
	}
	
	
}
