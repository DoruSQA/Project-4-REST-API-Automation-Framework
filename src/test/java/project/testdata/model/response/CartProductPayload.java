package project.testdata.model.response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartProductPayload {

    private int id;
    private String title;
    private double total;
    private double price;
    private int quantity;
    private double discountPercentage;

}