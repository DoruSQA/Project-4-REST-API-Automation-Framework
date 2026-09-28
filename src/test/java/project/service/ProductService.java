package project.service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import project.base.BaseService;
import project.testdata.model.request.ProductUser;
import project.testdata.model.request.UpdateProduct;

public class ProductService {

	// Static Routes
	private static final String GET_ALL_PRODUCTS_ROUTE = "/api/products";
	private static final String GET_SIGLE_PRODUCT_ROUTE = "/api/products/{id}";
	private static final String SEARCH_PRODUCT_BY_TITLE_ROUTE = "/api/products/search"; // ?q=title
	private static final String CREATE_PRODUCT_ROUTE = "/api/products";
	private static final String UPDATE_PRODUCT_ROUTE= "api/products/{id}";
	private static final String DELETE_PRODUCT_ROUTE= "/api/products/{id}";

	// HTTP request configuration
	public Response getAllProducts() {
		return BaseService.newInit().when().get(GET_ALL_PRODUCTS_ROUTE);
	}

	public Response getProductById(String id) {

		return BaseService.newInit().pathParam("id", id).when().get(GET_SIGLE_PRODUCT_ROUTE);
	}

	public Response getProductByTitle(String title) {

		return BaseService.newInit().queryParam("q", title).when().get(SEARCH_PRODUCT_BY_TITLE_ROUTE);
	}

	public Response createProduct(ProductUser productUser)
	{
		return BaseService.newInit().contentType(ContentType.JSON).body(productUser).when()
				.post(CREATE_PRODUCT_ROUTE);
	}
	
	public Response updateUser(UpdateProduct updateProduct, String id)
	{
		return BaseService.newInit().pathParam("id", id).contentType(ContentType.JSON).body(updateProduct).when()
				.put(UPDATE_PRODUCT_ROUTE);
	}
	
	public Response deleteProduct(String id)
	{
		return BaseService.newInit().pathParam("id", id).when().delete(DELETE_PRODUCT_ROUTE);
	}
	
}
