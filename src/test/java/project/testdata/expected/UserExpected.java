package project.testdata.expected;

public class UserExpected {

	// Schema Path
	public static final String DELETE_USER_SCHEMA_PATH = "userService/deleteUserSchema.json";
	public static final String UPDATE_USER_SCHEMA_PATH = "userService/updatedUserSchema.json";
	public static final String CREATE_USER_SCHEMA_PATH = "userService/validCreatedUserSchema.json";
	public static final String GET_USER_SCHEMA_PATH = "userService/userSchema.json";
	public static final String ERROR_SCHEMA_PATH = "errorSchema.json";

	// Error Message
	public static final String USER_FIRST_NAME_IS_REQUIRED = "first name is required";
	public static final String USER_LAST_NAME_IS_REQUIRED = "last name is required";
	public static final String USER_EMAIL_IS_REQUIRED = "email is required";
	public static final String INVALID_EMAIL_FORMAT = "invalid email format";
	public static final String FIRST_NAME_NOT_EXCEED_20_CHARS ="first name must not exceed 20 characters";
	public static final String LAST_NAME_NOT_EXCEED_20_CHARS ="last name must not exceed 20 characters";
	public static final String USER_ID_NOT_FOUND ="user ID was not found";
	public static final String INVALID_USER_ID_FORMAT = "invalid user ID format";
	public static final String USER_ID_IS_REQUIRED ="user IDis required";
	public static final String USER_ID_NOT_NEGATIVE="user ID must be grather than 0";

	public static final String CONTENT_TYPE = "application/json";
	
	
	
	
}
