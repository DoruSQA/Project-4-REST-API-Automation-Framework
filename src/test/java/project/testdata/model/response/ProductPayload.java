package project.testdata.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductPayload {

    private int id;
    private String title;
    private String thumbnail;
    private double rating;
    private int stock;
    private String sku;
    private String brand;
    private List<String> images;
    private double price;
    private String description;
    private String category;
    private double discountPercentage;
}
