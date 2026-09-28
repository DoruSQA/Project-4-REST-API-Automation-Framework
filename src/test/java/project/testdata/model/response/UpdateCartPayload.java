package project.testdata.model.response;

import com.fasterxml.jackson.databind.JsonNode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class UpdateCartPayload {
	 private int id;
	    private int totalProducts;
	    private int totalQuantity;
	    private double discountedTotal;
	    private JsonNode products;
	    private int userId;
	    private double total;
	    private boolean updated;
}
