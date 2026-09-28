package project.testdata.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateUserPayload {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean created;
}
