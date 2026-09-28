package project.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import project.service.AuthService;
import project.service.CartService;
import project.service.ProductService;
import project.service.UserService;

public class BaseTest {

	// Services
	public AuthService authService;
	public UserService userService;
	public ProductService productService;
	public CartService cartService;
	
	// Soft Assert
	public SoftAssert softAssertion;
	

	
	@BeforeClass(alwaysRun = true)
	public void setUp()
	{
	
		// Init Service
		authService = new AuthService();
		userService = new UserService();
		productService = new ProductService();
		cartService = new CartService();
		
		// Init Assertion
		softAssertion= new SoftAssert();
	}

	
	@AfterMethod(alwaysRun = true)
	public void cleanupAfterTest() {

		softAssertion = new SoftAssert();
	}
	
}
