/*
 * The Fruit class which is a subclass of MarketProduct class
 * @author Raymond Haoqin Yang	
 * Date: 2018/10/05
 */

public class Fruit extends MarketProduct {
	private double weight;	// double type indicating the weight in kg
	private int price;		// price of product in cents per kg
	
	/*
	 * Constructor for Fruit class.
	 * @param str is the name of the product, type String
	 * @param weight the weight in kg of the product, type double
	 * @param price the price in cents per kg of product, type int
	 */
	public Fruit(String str, double weight, int price) {
		super(str);		// calls superclass constructor which initiates name field
		if(weight < 0) 
			throw new IllegalArgumentException("Weight of Fruit cannot be negative.");	// throws an exception in case of negative fruit weight
		else if(price < 0)
			throw new IllegalArgumentException("Price of jams cannot be negative.");	// throws an exception in case of negative jam prices
		else {
			this.weight = weight;	// initiates weight field
			this.price = price;		// initiates price field
		}
	}
	
	/*
	 * The getCost method which retrieves price of product in cents
	 * @return an int indicating total price of fruits
	 */
	public int getCost() {
		return (int)(this.weight * this.price);
	}
	
	/*
	 * This method compares if the object is a Fruit by comparing fields
	 * @param product is an object of MarketProduct
	 * @return returns a boolean value. True if object is a Fruit.
	 */
	public boolean equals(Object product) {
		// compares if objects are of same type
		if(product instanceof Fruit) {
			// compares if two objects have same name, price, and number field
			if((((Fruit) product).getName()).equals(this.getName()) && ((Fruit) product).price == this.price && ((Fruit) product).weight == this.weight) {
				if(product.getClass().equals(this.getClass()))		// checks if classes are of same type
					return true;
				else
					return false;
			} else 
				return false; // does not have same name or price, or weight
		} else
			return false; // objects are not the same type
	}
	
}
