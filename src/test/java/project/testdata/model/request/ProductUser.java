package project.testdata.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ProductUser {

	private String title;
	private double price;
	private String category;
	private int stock;
	
	
	public ProductUser setStock (int stock)
	{
		this.stock=stock;
		return this;
	}
	
	public ProductUser setTitle (String title)
	{
		this.title=title;
		return this;
	}
	
	public ProductUser setPrice (double price)
	{
		this.price=price;
		return this;
	}
}
