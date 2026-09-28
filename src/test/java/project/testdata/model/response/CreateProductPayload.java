package project.testdata.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString


public class CreateProductPayload {

	   private String id;
	    private String title;
	    private double price;
	    private String category;
	    private int stock;
	    private boolean created;
	
	
}
