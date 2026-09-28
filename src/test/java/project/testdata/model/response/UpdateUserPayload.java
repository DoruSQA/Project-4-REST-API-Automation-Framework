package project.testdata.model.response;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateUserPayload {

    private String id;
    private String firstName;
    private String phone;
    private String gender;

    private JsonNode company;
    private JsonNode bank;
    private JsonNode address;

    private String image;
    private int age;
    private String email;
    private String username;
    private String lastName;
    private boolean updated;
}
