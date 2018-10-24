/*
 * Jam class is a subclass of MarketProduct
 * @author Raymond Haoqin Yang
 * Date: 2018/10/05
 */

public class Jam extends MarketProduct {
	private int num;		// number of jars of jam
	private int price;		// price per jar of jam in cents
	
	/*
	 * The constructor for Jam class
	 * @param str calls MarketProduct class constructor and initiates the inherited field name
	 * @param num the number of jars of jam
	 * @param price the price of jar of jam
	 */
	public Jam(String str, int num, int price) {
		super(str);		// calls superclass constructor with a string input
		if(num < 0)
			throw new IllegalArgumentException("Number of jams cannot be negative.");	// throws an exception in case of negative jam numbers
		else if(price < 0)
			throw new IllegalArgumentException("Number of eggs cannot be negative.");	// throws an exception in case of negative jam prices
		else {
			this.num = num;			// initiates the number of jar of jam
			this.price = price;		// initiates the price of a jar of jam
		}
	}
	
	/*
	 * getCost method retrieves the price of jam
	 * @return returns an int indicating total price in cents
	 */
	public int getCost() {
		return this.num * this.price;
	}
	
	/*
	 * This method compares if the object is a Jam by comparing fields
	 * @param product is an object of MarketProduct
	 * @return returns a boolean value. True if object is a Jam.  
	 */
	public boolean equals(Object product) {
		// compares if objects are of same type
		if(product instanceof Jam) {
			// compares if two objects have same name, price, and number field
			if((((Jam) product).getName()).equals(this.getName()) && ((Jam) product).price == this.price && ((Jam) product).num == this.num) {
				return true;
			} else 
				return false; // does not have same name or price, or number
		} else
			return false; // objects are not the same type
	}
	
}
