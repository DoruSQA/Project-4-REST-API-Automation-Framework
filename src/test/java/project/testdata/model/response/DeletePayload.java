package project.testdata.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class DeletePayload {

    private String id;
    private boolean deleted;
    private String message;
}