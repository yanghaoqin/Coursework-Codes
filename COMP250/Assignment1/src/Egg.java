/*
 * The Egg class which is a subclass of the MarketProduct class
 * @author Raymond Haoqin Yang
 * Date: 2018/10/05
 */

public class Egg extends MarketProduct {
	private int num;	// the number of eggs
	private int price;	// the price of a dozen (12) eggs in cents
	
	/*
	 * Constructor for Egg class
	 * @param str The name of the product (inherited field), calls superclass constructor to initiate
	 * @param num The amount of eggs
	 * @param price The price of a dozen (12) eggs
	 */
	public Egg(String str, int num, int price) {
		super(str);		// calls superclass constructor and initiates name field, which is inherited
		if(num < 0)
			throw new IllegalArgumentException("Number of eggs cannot be negative.");	// throws an exception in case of negative egg numbers
		else if(price < 0)
			throw new IllegalArgumentException("Price of eggs cannot be negative.");	// throws an exception in case of negative egg price
		else {
			this.num = num;			// initiates name field
			this.price = price;		// initiates price field
		}
	}
	
	/*
	 * Method getCost to retrieve cost of eggs
	 * @return returns the total price of eggs in cents as type int
	 */
	public int getCost() {
		return (int)(this.num / 12.0 * this.price); 	// the price of egg
	}
	
	/*
	 * This method compares if the object is a Egg by comparing fields
	 * @param product is an object of MarketProduct
	 * @return returns a boolean value. True if object is a Egg.  
	 */
	public boolean equals(Object product) {
		// compares if objects are of same type
		if(product instanceof Egg) {
			// compares if two objects have same name, price, and number field
			if((((Egg) product).getName()).equals(this.getName()) && ((Egg) product).price == this.price && ((Egg) product).num == this.num) {
				return true;
			} else 
				return false; // does not have same name or price, or number
		} else
			return false; // objects are not the same type
	}

}
