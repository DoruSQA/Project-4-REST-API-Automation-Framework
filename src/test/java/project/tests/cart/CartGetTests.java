package project.tests.cart;

import java.util.List;

import org.testng.annotations.Test;

import codesmash.test_metadata.TestCase;
import io.restassured.response.Response;
import project.base.BaseService;
import project.base.BaseTest;
import project.testdata.TestDataGenerator;
import project.testdata.expected.CartExpected;
import project.testdata.model.response.CartPayload;
import project.testdata.model.response.FailMessagePayload;
import project.utils.JsonSchema;

public class CartGetTests extends BaseTest {

    @TestCase(id = "API_CART_001", name = "Get all shopping carts")
    @Test(groups = {"Smoke", "Regression", "Cart"})
    public void getAllShoppingCarts() {

        // Send request
        Response httpAuthResponse = cartService.getAllProducts();

        // Validate status code and response headers via response specification
        httpAuthResponse.then().spec(BaseService.okResponse());

        // Deserialization
        List<CartPayload> cartList =
                httpAuthResponse.jsonPath().getList("carts", CartPayload.class);

        // Validate response body
        softAssertion.assertTrue(!cartList.isEmpty(), "Cart list should not be empty");
        softAssertion.assertNotNull(
                httpAuthResponse.jsonPath().getInt("total"),
                "Total products should not be empty"
        );
        softAssertion.assertAll();
    }

    @TestCase(id = "API_CART_002", name = "Get cart with valid ID")
    @Test(groups = {"Smoke", "Regression", "Cart"})
    public void getCartWithValidID() {

        // Send request
        Response httpAuthResponse = cartService.getCartById("1");

        // Validate status code and response headers
        httpAuthResponse.then().spec(BaseService.okResponse());

        // Deserialization
        CartPayload cart = httpAuthResponse.as(CartPayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, CartExpected.GET_CART_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertEquals(cart.getId(), Integer.parseInt("1"), "ID is not matching");
        softAssertion.assertTrue(cart.getTotalQuantity() > 0);
        softAssertion.assertAll();
    }

    @TestCase(id = "API_CART_003", name = "Get cart by providing non existing ID")
    @Test(groups = {"Regression", "Cart"})
    public void getCartByProvidingNonExistingID() {

        // Send request
        Response httpAuthResponse =
                cartService.getCartById(TestDataGenerator.randomNumericValue(9));

        // Validate status code and response headers
        httpAuthResponse.then().spec(BaseService.notFoundResponse());

        // Deserialization
        FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, CartExpected.ERROR_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertEquals(
                failMsgPayload.getMessage(),
                CartExpected.CART_ID_NOT_FOUND
        );
        softAssertion.assertAll();
    }

    @TestCase(id = "API_CART_004", name = "Get cart with empty ID")
    @Test(groups = {"Regression", "Cart"})
    public void getCartWithEmptyID() {

        // Send request
        Response httpAuthResponse =
                cartService.getCartById(TestDataGenerator.emptyString());

        // Validate status code and response headers
        httpAuthResponse.then().spec(BaseService.badRequestResponse());

        // Deserialization
        FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, CartExpected.ERROR_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertEquals(
                failMsgPayload.getMessage(),
                CartExpected.CART_ID_IS_REQUIRED
        );
        softAssertion.assertAll();
    }

    @TestCase(id = "API_CART_005", name = "Get cart by providing invalid ID format")
    @Test(groups = {"Regression", "Cart"})
    public void getCartWIthInvalidIDFormat() {

        // Send request
        Response httpAuthResponse =
                cartService.getCartById(TestDataGenerator.randomCharacters(5));

        // Validate status code and response headers
        httpAuthResponse.then().spec(BaseService.badRequestResponse());

        // Deserialization
        FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, CartExpected.ERROR_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertEquals(
                failMsgPayload.getMessage(),
                CartExpected.INVALID_CART_ID_FORMAT
        );
        softAssertion.assertAll();
    }
}