package project.testdata.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdatedUser {

	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private int age;
	
	
	public UpdatedUser setEmail(String email)
	{
		this.email=email;
		return this;
	}
	
	public UpdatedUser setFirstName(String firstName)
	{
		this.firstName=firstName;
		return this;
	}
	
	public UpdatedUser setLastName(String lastName)
	{
		this.lastName=lastName;
		return this;
	}
}
