package project.testdata.expected;

public class ProductExpected {

	// Schema Path
	public static final String DELETE_PRODUCT_SCHEMA_PATH = "productService/deleteProductSchema.json";
	public static final String UPDATE_PRODUCT_SCHEMA_PATH = "productService/updateProductSchema.json";
	public static final String CREATE_PRODUCT_SCHEMA_PATH = "productService/createProductSchema.json";
	public static final String GET_PRODUCT_SCHEMA_PATH = "productService/productUserSchema.json";
	public static final String ERROR_SCHEMA_PATH = "errorSchema.json";

	// Error Message
	public static final String PRODUCT_ID_IS_REQUIRED = "product ID is required";
	public static final String INVALID_PRODUCT_ID_FORMAT = "invalid product ID format";
	public static final String PRODUCT_ID_NOT_FOUND = "product ID not found";
	public static final String STOCK_GRATHER_THAN_0 ="Stock must be grather than 0";
	public static final String PRODUCT_TITLE_IS_REQUIRED ="product title is required";
	public static final String TITLE_NOT_EXCEED_20_CHARS ="Title must not exceed 20 characters";
	public static final String PRICE_GRATHER_THAN_0="Price must be grather than 0";

	public static final String CONTENT_TYPE = "application/json";
	


}
