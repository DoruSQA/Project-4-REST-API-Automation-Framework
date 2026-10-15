package project.tests.product;

import org.testng.annotations.Test;

import codesmash.test_metadata.TestCase;
import io.restassured.response.Response;
import project.base.BaseService;
import project.base.BaseTest;
import project.testdata.TestDataProvider;
import project.testdata.expected.ProductExpected;
import project.testdata.model.request.UpdateProduct;
import project.testdata.model.response.FailMessagePayload;
import project.testdata.model.response.UpdateProductPayload;
import project.utils.JsonSchema;

public class ProductUpdateTests extends BaseTest {

    @Test(groups = {"Smoke", "Regression", "Product"},
          dataProvider = "productWithUpdatedDetails", dataProviderClass = TestDataProvider.class)
    @TestCase(id = "API_PROD_011", name = "Update product with valid details")
    public void updateProductWithValidDetails(UpdateProduct productWithUpdatedDetails, String validID) {

        // Send request
        Response httpAuthResponse = productService.updateUser(productWithUpdatedDetails, validID);

        // Validate status code and response headers via response specification
        httpAuthResponse.then().spec(BaseService.okResponse());

        // Deserialization
        UpdateProductPayload updateProductPayload = httpAuthResponse.as(UpdateProductPayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, ProductExpected.UPDATE_PRODUCT_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertEquals(updateProductPayload.getTitle(), productWithUpdatedDetails.getTitle());
        softAssertion.assertEquals(updateProductPayload.getPrice(), productWithUpdatedDetails.getPrice(), "Price doesn't match");
        softAssertion.assertEquals(updateProductPayload.getStock(), productWithUpdatedDetails.getStock());
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Product"},
          dataProvider = "productupdatedWithEmptyTitle", dataProviderClass = TestDataProvider.class)
    @TestCase(id = "API_PROD_012", name = "Update product with empty title")
    public void updateProductWithEmptyTitle(UpdateProduct productupdatedWithEmptyTitle, String validID) {

        // Send request
        Response httpAuthResponse = productService.updateUser(productupdatedWithEmptyTitle, validID);

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

    @Test(groups = {"Regression", "Product"},
          dataProvider = "productWithUpdatedDetailsAndWrongID", dataProviderClass = TestDataProvider.class)
    @TestCase(id = "API_PROD_013", name = "Update product by providing wrong ID")
    public void updateProductByProvidingNonExistingID(UpdateProduct productWithUpdatedDetails, String nonExistingID) {

        // Send request - ID range for this app: 0-200
        Response httpAuthResponse = productService.updateUser(productWithUpdatedDetails, nonExistingID);

        // Validate status code and response headers
        httpAuthResponse.then().spec(BaseService.notFoundResponse());

        // Deserialization
        FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, ProductExpected.ERROR_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertEquals(failMsgPayload.getMessage(), ProductExpected.PRODUCT_ID_NOT_FOUND);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Product"},
          dataProvider = "productUpdatedWithStockZero", dataProviderClass = TestDataProvider.class)
    @TestCase(id = "API_PROD_014", name = "Update product with stock zero")
    public void updateProductWithStockZero(UpdateProduct productUpdatedWithStockZero, String validID) {

        // Send request
        Response httpAuthResponse = productService.updateUser(productUpdatedWithStockZero, validID);

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

    @Test(groups = {"Regression", "Product"},
          dataProvider = "productUpdatedWithNegativePrice", dataProviderClass = TestDataProvider.class)
    @TestCase(id = "API_PROD_015", name = "Update product with negative price")
    public void updateProductWithNegativePrice(UpdateProduct productUpdatedWithNegativePrice, String validID) {

        // Send request
        Response httpAuthResponse = productService.updateUser(productUpdatedWithNegativePrice, validID);

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