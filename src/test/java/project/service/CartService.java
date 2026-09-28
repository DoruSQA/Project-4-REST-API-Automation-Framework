package project.service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import project.base.BaseService;
import project.testdata.model.request.CartUser;
import project.testdata.model.request.UpdateCart;

public class CartService {

	// Static Routes
	private static final String GET_ALL_CARTS_ROUTE = "/api/carts";
	private static final String GET_SIGLE_CART_ROUTE = "/api/carts/{id}";
	private static final String CREATE_CART_ROUTE = "/api/carts";
	private static final String UPDATE_CART_ROUTE = "/api/carts/{id}";
	private static final String DELETE_CART_ROUTE = "/api/carts/{id}";

	// HTTP request configuration
	public Response getAllProducts() {
		
		return BaseService.newInit().when().get(GET_ALL_CARTS_ROUTE);
	}
	
	public Response getCartById(String id) {
		
		return BaseService.newInit().pathParam("id", id).when().get(GET_SIGLE_CART_ROUTE);
	}
	
	public Response createProduct(CartUser cartUserData)
	{
		return BaseService.newInit().contentType(ContentType.JSON).body(cartUserData).when()
				.post(CREATE_CART_ROUTE);
	}
	
	public Response updateCart(UpdateCart upcateCard, String id)
	{
		return BaseService.newInit().pathParam("id", id).contentType(ContentType.JSON).body(upcateCard).when()
				.put(UPDATE_CART_ROUTE);
	}
	
	public Response deleteCart(String id)
	{
		return BaseService.newInit().pathParam("id", id).when().delete(DELETE_CART_ROUTE);
	}

}
