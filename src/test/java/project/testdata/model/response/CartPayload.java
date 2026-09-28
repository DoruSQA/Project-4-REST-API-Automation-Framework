package project.testdata.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class CartPayload {

    private int id;
    private int userId;
    private List<CartProductPayload> products;
    private int totalProducts;
    private int totalQuantity;
    private double total;
    private double discountedTotal;
}
