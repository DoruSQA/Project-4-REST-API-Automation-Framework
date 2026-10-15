package project.tests.product;

import org.testng.Assert;
import org.testng.annotations.Test;

import codesmash.test_metadata.TestCase;
import io.restassured.response.Response;
import project.base.BaseTest;
import project.testdata.TestDataGenerator;
import project.testdata.expected.ProductExpected;
import project.testdata.expected.StatusCode;
import project.testdata.model.response.DeletePayload;
import project.testdata.model.response.FailMessagePayload;
import project.utils.JsonSchema;

public class ProductDeleteTests extends BaseTest {

    @Test(groups = {"Smoke", "Regression", "Product"})
    @TestCase(id = "API_PROD_016", name = "Delete product with valid ID")
    public void deleteProductWithValidID()
    {
        // Send request
        Response httpAuthResponse = productService.deleteProduct("1");

        // Validate status code and response headers via response specification
        Assert.assertEquals(httpAuthResponse.getStatusCode(), StatusCode.OK, "Status code doesn't match");
        Assert.assertEquals(httpAuthResponse.getContentType(), ProductExpected.CONTENT_TYPE, "Content-Type doesn't match");

        // Deserialization
        DeletePayload deleteUserPayload = httpAuthResponse.as(DeletePayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, ProductExpected.DELETE_PRODUCT_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertTrue(deleteUserPayload.getMessage().contains("deleted"), "Error message doesn't contain the expected text: '...deleted'");
        softAssertion.assertTrue(deleteUserPayload.isDeleted(), "Expected 'deleted' to be true");
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Product"})
    @TestCase(id = "API_PROD_017", name = "Delete product by providing wrong ID")
    public void deleteProductByProvidingWrongID()
    {
        // Send request
        Response httpAuthResponse = productService.deleteProduct(TestDataGenerator.randomNumericValue(9));

        // Validate status code and response headers
        Assert.assertEquals(httpAuthResponse.getStatusCode(), StatusCode.NOT_FOUND, "Status code doesn't match");
        Assert.assertEquals(httpAuthResponse.getContentType(), ProductExpected.CONTENT_TYPE, "Content-Type doesn't match");

        // Deserialization
        FailMessagePayload failedMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, ProductExpected.ERROR_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertEquals(failedMsgPayload.getMessage(), ProductExpected.PRODUCT_ID_NOT_FOUND);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Product"})
    @TestCase(id = "API_PROD_018", name = "Delete product by providing empty ID")
    public void deleteProductByProvidingEmptyID()
    {
        // Send request
        Response httpAuthResponse = productService.deleteProduct(TestDataGenerator.emptyString());

        // Validate status code and response headers
        Assert.assertEquals(httpAuthResponse.getStatusCode(), StatusCode.BAD_REQUEST, "Status code doesn't match");
        Assert.assertEquals(httpAuthResponse.getContentType(), ProductExpected.CONTENT_TYPE, "Content-Type doesn't match");

        // Deserialization
        FailMessagePayload failedMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, ProductExpected.ERROR_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertEquals(failedMsgPayload.getMessage(), ProductExpected.PRODUCT_ID_IS_REQUIRED);
        softAssertion.assertAll();
    }
}