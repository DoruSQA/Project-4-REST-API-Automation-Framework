package project.tests.product;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import project.base.BaseService;
import project.base.BaseTest;
import project.testdata.TestDataGenerator;
import project.testdata.expected.ProductExpected;
import project.testdata.model.response.FailMessagePayload;
import project.testdata.model.response.ProductPayload;
import project.utils.JsonSchema;

public class ProductGetTests extends BaseTest {
	
	
	@Test(groups = {"Smoke", "Regression", "Product" }, description = "API_PROD_001: Get all products")
	public void getAllProducts() {
		
		// Send request
		Response httpAuthResponse = productService.getAllProducts();

		// Validate status code and response headers via response specification
		httpAuthResponse.then().spec(BaseService.okResponse());

		// Deserialization
		List<ProductPayload> products = httpAuthResponse.jsonPath().getList("products", ProductPayload.class);

		// Validate response body
		softAssertion.assertTrue(!products.isEmpty());
		softAssertion.assertNotNull(httpAuthResponse.jsonPath().getInt("total"));
		softAssertion.assertAll();

	}
	
	@Test(groups = { "Smoke", "Regression", "Product" }, description = "API_US_002: Get product with valid existing ID")
	public void getProductWithValidExistingID() { // valid ID range from 1-200
		
		// Send request
		Response httpAuthResponse = productService.getProductById("1");

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.okResponse());

		// Deserialization
		ProductPayload productPayload = httpAuthResponse.as(ProductPayload.class);

		// Validation 2: On JSON Schema
		JsonSchema.validate(httpAuthResponse, ProductExpected.GET_PRODUCT_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertEquals(productPayload.getId(), Integer.parseInt("1"), "ID is not matching");
		softAssertion.assertNotNull(productPayload.getTitle(), "Title value is missing");
		softAssertion.assertNotNull(productPayload.getDescription(), "Product value is missing");
		softAssertion.assertAll();

	}
	
	@Test(groups = {"Regression", "Product" }, description = "API_US_003: Get product with non existing ID")
	public void getProductWithNonExistingID() {
		
		// Send request
		Response httpAuthResponse = productService.getProductById(TestDataGenerator.randomNumericValue(10));

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.notFoundResponse());

		// Deserialization
		FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

		// Validation 2: On JSON Schema
		JsonSchema.validate(httpAuthResponse, ProductExpected.ERROR_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertNotNull(failMsgPayload.getMessage(), "Error message is missing");
		softAssertion.assertTrue(failMsgPayload.getMessage().contains("not found"),"Error message doesn't contain the expected text: '...not found'");
		softAssertion.assertAll();

	}
	

	@Test(groups = {"Regression", "Product" }, description = "API_US_004: Get product with invalid ID format")
	public void getProductWithInvalidIDFormat() {
		
		// Send request
		Response httpAuthResponse = productService.getProductById(TestDataGenerator.randomCharacters(5));

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.badRequestResponse());

		// Deserialization
		FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

		// Validation 2: On JSON Schema
		JsonSchema.validate(httpAuthResponse, ProductExpected.ERROR_SCHEMA_PATH);

		// Validate response body
		softAssertion.assertNotNull(failMsgPayload.getMessage(), "Error message is missing");
		softAssertion.assertTrue(failMsgPayload.getMessage().contains("invalid format"),"Error message doesn't contain the expected text: '...invalid format'");
		softAssertion.assertAll();

	}
	
	
	@Test(groups = {"Regression", "Product" }, description = "API_US_005: Search product with non existing title")
	public void searchProductWithNonExistingTitle() {
		
		// Send request
		Response httpAuthResponse = productService.getProductByTitle(TestDataGenerator.randomCharacters(5));

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.okResponse());

		// Deserialization
		List<ProductPayload> productsWithThatTitle = httpAuthResponse.jsonPath().getList("products", ProductPayload.class);

		// Validate response body
		softAssertion.assertTrue(productsWithThatTitle.isEmpty(), "Product details should not be empty");
		softAssertion.assertEquals(httpAuthResponse.jsonPath().getInt("total"),0);
		softAssertion.assertAll();

	}
	
	@Test(groups = {"Regression", "Product" }, description = "API_US_006: Search product with empty search term")
	public void searchProductWithEmptySearchTerm() {
		
		// Send request
		Response httpAuthResponse = productService.getProductByTitle(TestDataGenerator.emptyString());

		// Validate status code and response headers
		httpAuthResponse.then().spec(BaseService.okResponse());

		// Deserialization
		List<ProductPayload> productsWithThatTitle = httpAuthResponse.jsonPath().getList("products", ProductPayload.class);

		// Validate response body
		softAssertion.assertTrue(productsWithThatTitle.isEmpty(), "Product details should not be empty");
		softAssertion.assertEquals(httpAuthResponse.jsonPath().getInt("total"),0);
		softAssertion.assertAll();

	}
	
	
}
