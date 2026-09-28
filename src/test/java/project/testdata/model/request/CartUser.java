package project.testdata.model.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartUser {

	private int userID;
    private List<CartProduct> products;
	
    public CartUser addProducts(int numberOfProducts) {

        for (int i = 1; i <= numberOfProducts; i++) 
        {

            int stock = i;
            int quantity = i+2;

            products.add(new CartProduct(quantity, stock));
        }
        return this;
    }
    
    public CartUser setUserID(int id)
    {
    	this.userID=id;
    	return this;
    }
}
