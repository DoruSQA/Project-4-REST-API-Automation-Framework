package project.testdata.model.response;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserPayload {
	
	private int id;
    private String phone;
    private String lastName;
    private int age;
    private String image;
    private String firstName;
    private String username;
    private String email;
    private String gender;
    
    // No nested classes needed 
	private JsonNode bank;
    private JsonNode company;
    private JsonNode address;
    
 
}
