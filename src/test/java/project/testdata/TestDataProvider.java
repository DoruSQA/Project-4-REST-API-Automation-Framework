package project.testdata;

import org.testng.annotations.DataProvider;

import codesmash.testdata.CustomDataProvider;
import project.service.AuthService;
import project.testdata.model.request.RefreshTokenUser;
import project.testdata.model.response.AuthPayload;



public class TestDataProvider extends CustomDataProvider {


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
    
   
	
}