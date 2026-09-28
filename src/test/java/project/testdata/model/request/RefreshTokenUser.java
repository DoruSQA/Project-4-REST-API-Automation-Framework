package project.testdata.model.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RefreshTokenUser {


	private String refreshToken;


	public RefreshTokenUser setRefreshToken(String token)
	{
		this.refreshToken=token;
		return this;
	}
		
}
