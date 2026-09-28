package project.testdata;

import org.testng.annotations.DataProvider;

import project.service.AuthService;
import project.testdata.model.request.CartUser;
import project.testdata.model.request.LoginUser;
import project.testdata.model.request.ProductUser;
import project.testdata.model.request.RefreshTokenUser;
import project.testdata.model.request.UpdateCart;
import project.testdata.model.request.UpdateProduct;
import project.testdata.model.response.AuthPayload;

public class TestDataProvider {

    @DataProvider(name = "userWithValidCredentials")
    public Object[][] userWithValidCredentials() {

        LoginUser userWithValidCredentials =
                TestDataFactory.createValidLoginUserData();

        return new Object[][] { { userWithValidCredentials } };
    }

    @DataProvider(name = "userWithWrongPassword")
    public Object[][] userWithWrongPassword() {

        LoginUser userWithWrongPassword =
                TestDataFactory.createValidLoginUserData();

        userWithWrongPassword.setPassword("wrong_password");

        return new Object[][] { { userWithWrongPassword } };
    }

    @DataProvider(name = "userWithWrongUsername")
    public Object[][] userWithWrongUsername() {

        LoginUser userWithWrongUsername =
                TestDataFactory.createValidLoginUserData();

        userWithWrongUsername.setUsername("wrong_username");

        return new Object[][] { { userWithWrongUsername } };
    }

    @DataProvider(name = "userWithValidRefreshToken")
    public Object[][] userWithValidRefreshToken() {

        RefreshTokenUser userWithValidRefreshToken =TestDataFactory.createWrongRefreshTokenData();
        String validRefreshToken = new AuthService()
                .login(TestDataFactory.createValidLoginUserData())
                .as(AuthPayload.class)
                .getAccessToken();
        userWithValidRefreshToken.setRefreshToken(validRefreshToken);

        return new Object[][] { { userWithValidRefreshToken } };
    }
    
    @DataProvider(name = "userWithWrongRefreshToken")
    public Object[][] userWithWrongRefreshToken() {

        RefreshTokenUser userWithWrongRefreshToken =
                TestDataFactory.createWrongRefreshTokenData();

        return new Object[][] { { userWithWrongRefreshToken } };
    }

    @DataProvider(name = "userWithMissingRefreshToken")
    public Object[][] userWithMissingRefreshToken() {

        RefreshTokenUser userWithMissingRefreshToken =
                TestDataFactory.createWrongRefreshTokenData();

        userWithMissingRefreshToken.setRefreshToken(
                TestDataGenerator.emptyString()
        );

        return new Object[][] { { userWithMissingRefreshToken } };
    }

    @DataProvider(name = "userWithMalformedRefreshToken")
    public Object[][] userWithMalformedRefreshToken() {

        RefreshTokenUser userWithMalformedRefreshToken =
                TestDataFactory.createWrongRefreshTokenData();

        userWithMalformedRefreshToken.setRefreshToken(
                TestDataGenerator.randomNumericValue(10)
        );

        return new Object[][] { { userWithMalformedRefreshToken } };
    }
    
    // Cart ==============================================================================
    
    @DataProvider(name = "userWithValidCartDetails")
    public Object[][] userWithValidCartDetails() {

    	CartUser userWithValidCartDetails = TestDataFactory.createCartUserData();

        return new Object[][] { { userWithValidCartDetails } };
    }
    
    @DataProvider(name = "userWithMultipleProducts")
    public Object[][] userWithMultipleProducts() {

    	CartUser userWithMultipleProducts = TestDataFactory.createCartUserData();
    	userWithMultipleProducts.addProducts(2);

        return new Object[][] { { userWithMultipleProducts } };
    }
    
    @DataProvider(name = "userWithNegativeProductQuantity")
    public Object[][] userWithNegativeProductQuantity() {

    	CartUser userWithNegativeProductQuantity = TestDataFactory.createCartUserData();
    	userWithNegativeProductQuantity.getProducts().get(1).setQuantity(-1);

        return new Object[][] { { userWithNegativeProductQuantity } };
    }
    
    @DataProvider(name = "userWithEmptyUserID")
    public Object[][] userWithEmptyUserID() {

    	CartUser userWithEmptyUserID = TestDataFactory.createCartUserData();
    	userWithEmptyUserID.setUserID(TestDataGenerator.emptyValue());

        return new Object[][] { { userWithEmptyUserID } };
    }
    
    @DataProvider(name = "userWIthEmptyCart")
    public Object[][] userWIthEmptyCart() {

    	CartUser userWIthEmptyCart = TestDataFactory.createCartUserData();
    	userWIthEmptyCart.getProducts().clear();

        return new Object[][] { { userWIthEmptyCart } };
    }
    
	@DataProvider(name = "cartWithValidData")
	public Object[][] cartWithValidData() {

		UpdateCart cartWithValidData = TestDataFactory.createUpdateCartData();
		String validID = "1";
		return new Object[][] { { cartWithValidData, validID } };
	}
	
	@DataProvider(name = "cartWithValidDataAndNonExistingID")
	public Object[][] cartWithValidDataAndNonExistingID() {

		UpdateCart cartWithValidData = TestDataFactory.createUpdateCartData();
		String nonExistingID = TestDataGenerator.randomNumericValue(5);
		return new Object[][] { { cartWithValidData, nonExistingID } };
	}
	
	//Product ==============================================================================
	
	@DataProvider(name = "productWithValidDetails")
	public Object[][] productWithValidDetails() {

		ProductUser productWithValidDetails = TestDataFactory.createValidProductUserData();
		return new Object[][] { { productWithValidDetails } };
	}
	
	@DataProvider(name = "productWithStockZero")
	public Object[][] productWithStockZero() {

		ProductUser productWithStockZero = TestDataFactory.createValidProductUserData();
		productWithStockZero.setStock(0);
		return new Object[][] { { productWithStockZero } };
	}
	
	@DataProvider(name = "productWithEmptyTitle")
	public Object[][] productWithEmptyTitle() {

		ProductUser productWithEmptyTitle = TestDataFactory.createValidProductUserData();
		productWithEmptyTitle.setTitle("");
		return new Object[][] { { productWithEmptyTitle } };
	}
	
	@DataProvider(name = "productWithNegativePrice")
	public Object[][] productWithNegativePrice() {

		ProductUser productWithNegativePrice = TestDataFactory.createValidProductUserData();
		productWithNegativePrice.setPrice(-1);
		return new Object[][] { { productWithNegativePrice } };
	}
	
	@DataProvider(name = "productWithUpdatedDetails")
	public Object[][] productWithUpdatedDetails() {

		UpdateProduct productWithUpdatedDetails =TestDataFactory.createValidUpdatedProductData();
		String validID = "1";
		return new Object[][] { { productWithUpdatedDetails, validID} };
	}
	
	@DataProvider(name = "productupdatedWithEmptyTitle")
	public Object[][] productupdatedWithEmptyTitle() {

		UpdateProduct productupdatedWithEmptyTitle =TestDataFactory.createValidUpdatedProductData();
		productupdatedWithEmptyTitle.setTitle("");
		String validID = "1";
		return new Object[][] { { productupdatedWithEmptyTitle, validID} };
	}
	
	@DataProvider(name = "productWithUpdatedDetailsAndWrongID")
	public Object[][] productWithUpdatedDetailsAndWrongID() {

		UpdateProduct productupdatedWithEmptyTitle =TestDataFactory.createValidUpdatedProductData();
		String wrongID = TestDataGenerator.randomNumericValue(4);
		return new Object[][] { { productupdatedWithEmptyTitle, wrongID} };
	}
	
	@DataProvider(name = "productUpdatedWithStockZero")
	public Object[][] productUpdatedWithStockZero() {

		ProductUser productUpdatedWithStockZero = TestDataFactory.createValidProductUserData();
		productUpdatedWithStockZero.setStock(0);
		String validID = "1";
		return new Object[][] { { productUpdatedWithStockZero, validID} };
	}
	
	@DataProvider(name = "productUpdatedWithNegativePrice")
	public Object[][] productUpdatedWithNegativePrice() {

		ProductUser productUpdatedWithNegativePrice = TestDataFactory.createValidProductUserData();
		productUpdatedWithNegativePrice.setPrice(-1);
		String validID = "1";
		return new Object[][] { { productUpdatedWithNegativePrice, validID} };
	}
	
	
}