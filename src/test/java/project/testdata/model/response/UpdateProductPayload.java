package project.testdata.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class UpdateProductPayload {

    private String id;
    private double rating;
    private String sku;
    private String title;
    private String thumbnail;
    private String category;
    private int stock;
    private List<String> images;
    private int price;
    private String description;
    private double discountPercentage;
    private String brand;
    private boolean updated;
}
