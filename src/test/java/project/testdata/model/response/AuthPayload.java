package project.testdata.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthPayload {

	    private String accessToken;
	    private String refreshToken;
	    private String token;
	    private int id;
	    private String username;
	    private String email;
	    private String firstName;
	    private String lastName;
	    private String image;
	    private int statusCode;

	  
}
