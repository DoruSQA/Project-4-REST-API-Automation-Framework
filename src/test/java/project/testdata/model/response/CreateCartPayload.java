package project.testdata.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCartPayload {
	 private String id;
	    private int userID;
	    private List<CartProductPayload> products;
	    private boolean created;
}
