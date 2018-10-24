/*
 * Name: Assignment 1
 * Objective: Simulate online Market place. No imports.
 * @author Raymond Haoqin Yang
 * Date: 2018/10/05
 */

public abstract class MarketProduct {
	private String name;	// the name of product
	
	/*
	 * Constructor of MarketProduct used to initialize name of product
	 * @param str A String input which is the name of the product
	 */
	public MarketProduct(String str) {
		this.name = str;
	}
	
	/*
	 * getName method which retrieves the name of the product
	 * is a final method
	 * calls toString() method to ensure actual name is returned rather than address of String
	 * @return returns the name of the product
	 */
	public final String getName() {
		return name.toString();
	}
	
	/*
	 * An abstract method used to calculate the total cost 
	 * @return returns an int which indicates the total cost in cents
	 */
	public abstract int getCost();
	
	/*
	 * An abstract method used to compare products
	 * @param product An object as input
	 * @return A boolean value signaling whether two products are the same
	 */
	public abstract boolean equals(Object O);
	
}
