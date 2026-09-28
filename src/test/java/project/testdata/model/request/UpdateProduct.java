package project.testdata.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateProduct {

	
	private String title;
	private int price;
	private int stock;
	
	
	public UpdateProduct setTitle(String title)
	{
		this.title=title;
		return this;
	}
	
	public UpdateProduct setStock(int stock)
	{
		this.stock=stock;
		return this;
	}
	
	public UpdateProduct setPrice(int price)
	{
		this.price=price;
		return this;
	}
}
