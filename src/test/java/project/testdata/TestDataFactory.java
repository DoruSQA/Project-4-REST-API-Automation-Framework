package project.testdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import project.testdata.model.request.CartUser;
import project.testdata.model.request.CreatedUser;
import project.testdata.model.request.LoginUser;
import project.testdata.model.request.ProductUser;
import project.testdata.model.request.RefreshTokenUser;
import project.testdata.model.request.UpdateCart;
import project.testdata.model.request.UpdateProduct;
import project.testdata.model.request.UpdatedUser;
import project.utils.DataReader;

public class TestDataFactory {

	public static LoginUser createValidLoginUserData() {

		return DataReader.read("loginUser", LoginUser.class);
	}

	public static RefreshTokenUser createWrongRefreshTokenData() {

		return DataReader.read("wrongRefreshTokenUser", RefreshTokenUser.class);
	}

	public static CreatedUser createValidUserData() {

		return DataReader.read("createdUser", CreatedUser.class);
	}

	public static UpdatedUser creteValidUpdatedUserData() {

		return DataReader.read("updatedUser", UpdatedUser.class);
	}
	
	public static ProductUser createValidProductUserData() {

		return DataReader.read("productUser", ProductUser.class);
	}
	
	public static UpdateProduct createValidUpdatedProductData()
	{
		return DataReader.read("updateProduct", UpdateProduct.class);
	}
	
	public static CartUser createCartUserData()
	{
		return DataReader.read("cartUser", CartUser.class);
	}
	
	public static UpdateCart createUpdateCartData()
	{
		return DataReader.read("updateCart", UpdateCart.class);
	}
	

	public static ObjectNode createUserWithoutField(CreatedUser user, String field) {

		ObjectMapper mapper = new ObjectMapper();

		ObjectNode userJson = mapper.valueToTree(user);
		userJson.remove(field);

		return userJson;
	}

}
