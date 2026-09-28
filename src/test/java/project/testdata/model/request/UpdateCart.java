package project.testdata.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateCart {

	private int totalProducts;
	private int totalQuantity;
}
