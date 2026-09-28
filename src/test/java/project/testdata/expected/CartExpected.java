package project.testdata.expected;

public class CartExpected {

	// Schema Path
	public static final String DELETE_CART_SCHEMA_PATH = "cartService/deleteCartSchema.json";
	public static final String UPDATE_CART_SCHEMA_PATH ="cartService/updateCartSchema.json";
	public static final String CREATE_CART_SCHEMA_PATH ="cartService/createCartSchema.json";
	public static final String GET_CART_SCHEMA_PATH ="cartService/cartSchema.json";
	public static final String ERROR_SCHEMA_PATH = "errorSchema.json";
	
	// Error Message
	public static final String CART_ID_NOT_FOUND = "cart ID not found";
	public static final String INVALID_CART_ID_FORMAT = "invalid cart ID format";
	public static final String CART_ID_IS_REQUIRED ="cart ID is required";
	public static final String USER_ID_IS_REQUIRED ="user ID is required";
	public static final String QUANTITY_GRATHER_THAN_0 ="quantity must be grather than 0";
	public static final String MINIMUM_ONE_PRODUCT_REQUIRED ="at least one product is required";
	
	public static final String CONTENT_TYPE = "application/json";

}
