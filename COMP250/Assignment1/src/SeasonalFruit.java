/*
 * SeasonalFruit is a subclass of Fruit class, which itself is a subclass of the MarketProduct class 
 * @author Raymond Haoqin Yang
 * Date: 2018/10/06
 */

public class SeasonalFruit extends Fruit {

	/*
	 * The constructor takes 3 inputs and calls the superclass constructor with those inputs 
	 * @param str A type String input indicating name of product
	 * @param weight A type double
	 * @param price
	 */
	public SeasonalFruit(String str, double weight, int price) {
		super(str, weight, price);
	}
	
	/* 
	 * getCost retrieves the total cost of the Fruit product and applies a 15% discount by calling superclass getCost method
	 * @return returns type int which is the discounted price and int-typecasted
	 */
	public int getCost() {
		return (int)(super.getCost() * 0.85);
	}

}
