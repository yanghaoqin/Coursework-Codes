/*
 * This class is for customers 
 * @author Raymond Haoqin Yang
 * Date: 2018/10/07
 */

public class Customer {
	private String name;		// name of customer
	private int balance;		// balance of customer
	private Basket basket;		// customer's basket of products
	
	/*
	 * Constructor for Customer class, initiates the name, balance, and basket fields
	 * @param str the customer's name
	 * @param balance the customer's balance in cents
	 */
	public Customer(String str, int balance) {
		this.name = str;
		this.balance = balance;
		basket = new Basket();		// creates new Basket object which creates an array to store products
	}
	
	/*
	 * This method returns the name of the customer
	 * @return returns a String type indicating the name of the customer
	 */
	public String getName() {
		return this.name;
	}
	
	/*
	 * This method returns the customer's balance in cents
	 * @return returns an int type indicating customer's balance
	 */
	public int getBalance() {
		return this.balance;
	}
	
	/*
	 * This method returns the reference to the customer's basket
	 * @return returns the reference to a Basket type object
	 */
	public Basket getBasket() {
		return this.basket;
	}
	
	/*
	 * This method adds funds to the customer's balance 
	 * @param funds is the funds to be added in cents, must be positive
	 * @return returns an integer specifying the updated balance
	 */
	public int addFunds(int funds) {
		if(funds < 0)
			throw new IllegalArgumentException("Funds added cannot be negative.");		// throws an exception for negative fund inputs
		else {
			this.balance += funds;		// updates funds
			return this.balance;		// returns updated funds in cents
		}
	}
	
	/*
	 * This method adds a product to the customer's basket 
	 * @param product MarketProduct object to be added to the customer's basket
	 */
	public void addToBasket(MarketProduct product) {
		this.basket.add(product);		// calls the add method in basket class
	}
	
	/*
	 * The method removes a product from the basket 
	 * @param product MarketProduct to be removed from customer's basket
	 * @return returns boolean value indicating whether the operation was successful
	 */
	public boolean removeFromBasket(MarketProduct product) {
		return this.basket.remove(product);		// calls the remove method in basket class which returns a boolean value
	}
	
	/*
	 * This method performs checkout operation for customer
	 * charges the amount from balance and prints receipt 
	 * @return returns String type of receipt for the purchase
	 */
	public String checkOut() {
		if((this.balance - this.basket.getTotalCost()) < 0) {			// checks if balance is enough
			throw new IllegalStateException("Insufficient Funds.");		// throws an exception if there is insufficient funds
		} else {
			this.balance -= this.basket.getTotalCost();					// the total cost is subtracted from the balance
			String output = this.basket.toString();						// temp variable stores the receipt
			this.basket.clear();										// empties the basket
			return output;												// returns the receipt
		}
	}
		
}
