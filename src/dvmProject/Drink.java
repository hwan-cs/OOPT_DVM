package dvmProject;

import java.util.*;

//Unit Test done
public class Drink 
{

	public String name;
	public int price;
	public int stock;
	public String drinkCode;

	public Drink(String name, int price, int stock, String drinkCode) 
	{
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.drinkCode = drinkCode;
	}

	public String getName() 
	{
		// TODO implement here
		return this.name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getPrice() 
	{
		return this.price;
	}

	public void setPrice(int price) 
	{
		this.price = price;
	}

	public int getStock() 
	{
		return this.stock;
	}

	public void setStock(int stock) 
	{
		this.stock = stock;
	}

	public String getDrinkCode() 
	{
		return this.drinkCode;
	}

	public void setDrinkCode(String drinkCode) 
	{
		this.drinkCode = drinkCode;
	}
}
