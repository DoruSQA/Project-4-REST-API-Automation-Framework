package project.tests.cart;

import org.testng.annotations.Test;

import codesmash.test_metadata.TestCase;
import io.restassured.response.Response;
import project.base.BaseService;
import project.base.BaseTest;
import project.testdata.TestDataProvider;
import project.testdata.expected.CartExpected;
import project.testdata.model.request.CartUser;
import project.testdata.model.response.CreateCartPayload;
import project.testdata.model.response.FailMessagePayload;
import project.utils.JsonSchema;

public class CartCreateTests extends BaseTest {

    @Test(groups = {"Smoke", "Regression", "Cart"}, dataProvider = "userWithValidCartDetails", dataProviderClass = TestDataProvider.class)
    @TestCase(id = "API_CART_006", name = "Create cart with valid details")
    public void createCartWithValidDetails(CartUser userWithValidCartDetails) {

        // Send request
        Response httpAuthResponse = cartService.createProduct(userWithValidCartDetails);

        // Validate status code and response headers via response specification
        httpAuthResponse.then().spec(BaseService.createdResponse());

        // Deserialization
        CreateCartPayload createCartPayload = httpAuthResponse.as(CreateCartPayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, CartExpected.CREATE_CART_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertNotNull(createCartPayload.getId());
        softAssertion.assertTrue(createCartPayload.isCreated());
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Cart"}, dataProvider = "userWithMultipleProducts", dataProviderClass = TestDataProvider.class)
    @TestCase(id = "API_CART_007", name = "Create cart with multiple products")
    public void createCartWithMultipleProducts(CartUser userWithMultipleProducts) {

        // Send request
        Response httpAuthResponse = cartService.createProduct(userWithMultipleProducts);

        // Validate status code and response headers
        httpAuthResponse.then().spec(BaseService.createdResponse());

        // Deserialization
        CreateCartPayload createCartPayload = httpAuthResponse.as(CreateCartPayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, CartExpected.CREATE_CART_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertNotNull(createCartPayload.getId());
        softAssertion.assertTrue(createCartPayload.getProducts().size() >= 2);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Cart"}, dataProvider = "userWithNegativeProductQuantity", dataProviderClass = TestDataProvider.class)
    @TestCase(id = "API_CART_008", name = "Create cart with negative product quantity")
    public void createCartWithNegativeProductQuantity(CartUser userWithNegativeProductQuantity) {

        // Send request
        Response httpAuthResponse = cartService.createProduct(userWithNegativeProductQuantity);

        // Validate status code and response headers
        httpAuthResponse.then().spec(BaseService.badRequestResponse());

        // Deserialization
        FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, CartExpected.ERROR_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertEquals(failMsgPayload.getMessage(), CartExpected.QUANTITY_GRATHER_THAN_0);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Cart"}, dataProvider = "userWithEmptyUserID", dataProviderClass = TestDataProvider.class)
    @TestCase(id = "API_CART_009", name = "Create cart with empty user ID")
    public void createCartWithEmptyUserID(CartUser userWithEmptyUserID) {

        // Send request
        Response httpAuthResponse = cartService.createProduct(userWithEmptyUserID);

        // Validate status code and response headers
        httpAuthResponse.then().spec(BaseService.badRequestResponse());

        // Deserialization
        FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, CartExpected.ERROR_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertEquals(failMsgPayload.getMessage(), CartExpected.USER_ID_IS_REQUIRED);
        softAssertion.assertAll();
    }

    @Test(groups = {"Regression", "Cart"}, dataProvider = "userWIthEmptyCart", dataProviderClass = TestDataProvider.class)
    @TestCase(id = "API_CART_010", name = "Create cart with empty products")
    public void createCartWithEmptyProducts(CartUser userWIthEmptyCart) {

        // Send request
        Response httpAuthResponse = cartService.createProduct(userWIthEmptyCart);

        // Validate status code and response headers
        httpAuthResponse.then().spec(BaseService.badRequestResponse());

        // Deserialization
        FailMessagePayload failMsgPayload = httpAuthResponse.as(FailMessagePayload.class);

        // Validate JSON schema
        JsonSchema.validate(httpAuthResponse, CartExpected.ERROR_SCHEMA_PATH);

        // Validate response body
        softAssertion.assertEquals(failMsgPayload.getMessage(), CartExpected.MINIMUM_ONE_PRODUCT_REQUIRED);
        softAssertion.assertAll();
    }
}