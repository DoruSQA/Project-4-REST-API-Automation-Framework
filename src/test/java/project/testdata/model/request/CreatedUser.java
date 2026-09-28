package project.testdata.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreatedUser {

	
	private String firstName;
	private String lastName;
	private String email;
	
	public CreatedUser setEmail(String email)
	{
		this.email=email;
		return this;
	}
	
	public CreatedUser setFirstName(String name)
	{
		this.firstName=name;
		return this;
	}
	
	public CreatedUser setLastName(String name)
	{
		this.lastName=name;
		return this;
	}
		
}
