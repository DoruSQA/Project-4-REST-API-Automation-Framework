package project.testdata.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SuccessRefreshTokenPayload {

	private String accessToken;
	private String token;

}
