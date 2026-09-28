package project.testdata.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartProduct {

	private int id;
    private int quantity;
    
    public CartProduct(int id, int quantity)
    {
    	this.id=id;
    	this.quantity=quantity;
    }
    
}
