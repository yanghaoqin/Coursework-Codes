/*
 * Class basket which stores the items picked by customers
 * @author Raymond Haoqin Yang
 * Date: 2018/10/06
 */

public class Basket {
	private MarketProduct[] arr;	// the array used to store MarketProduct objects
	private int size;				// the number of element in array

	// Constructor for Basket. Takes no input and initiates MarketProduct array 
	public Basket() {
		arr = new MarketProduct[8000];		// initializes array with length 1
		size = 0;						// no elements are in the array
	}

	/*
	 * The getProducts method makes a shallowCopy of the list array of products
	 * @return returns an array of type MarketProduct
	 */
	public MarketProduct[] getProducts() {
		MarketProduct[] shallowCopy = new MarketProduct[arr.length]; // initializes the copy array with same length
		for(int i = 0; i < arr.length; i++) {
			shallowCopy[i] = arr[i];	// for each slot in new array, it corresponds to the slot in old array and points to same address
		}
		return shallowCopy;				// returns the shallow-copied array of type MarketProduct
	}

	/* 
	 * The add method adds a product to the end of the array
	 * @param product is the MarketProduct object to be added to the basket
	 */
	public void add(MarketProduct product) {
		// Add a item to end of array
		// Check if array is full
		if(size == arr.length) {
			MarketProduct[] newarr = new MarketProduct[2 * arr.length]; // create new bigger array with double the size
			for(int i = 0; i < size; i++) {
				newarr[i] = arr[i];		// copy all elements to new array
			}
			arr = newarr;				// assign newarr back to arr
		}
		arr[size] = product;			// add new element to the i + 1 place
		size++;							// updates number of products
	}

	/*
	 * The remove method removes an element from the array and fills up the empty slot
	 * Refer to equals method in the subclasses of MarketProduct to see criteria for comparisonx
	 * @param product is the product that want to be removed
	 * @return returns a boolean value, true for removed and false for not finding the product in the array
	 */
	public boolean remove(MarketProduct product) {
		for(int i = 0; i < size; i++) {			// scan through array for the product
			if((product).equals(arr[i])) {		// use the overridden equals method defined in product classes to find a match
				for(int j = i; j < size-1; j++) {
					arr[j] = arr[j+1];			// take out product by shifting all elements below up 1 slot and overwriting it
				}
				size--;							// update size of array (number of elements)
				return true;					// exit method because only 1 match is needed
			}
		}
		// if the program proceeds to here then no match has been found
		return false;							// exit method
	}

	/*
	 * The clear method clears the basket by redirecting arr to a new empty array with size 0 and length 1
	 */
	public void clear() {
		MarketProduct[] emptyarr = new MarketProduct[8000];	// creates new empty array with length 1
		arr = emptyarr;										// redirects arr to point to emptyarr
		size = 0;											// update size
	}

	/*
	 * This method retrieves the number of products; the field size is built for this
	 * @return returns an integer indicating the number of products in the basket
	 */
	public int getNumOfProducts() {
		return size;								// size shows how many products there are in the basket
	}

	/*
	 * This method gets the total costs before tax of the products in the basket 
	 * @return returns an integer type, indicating the total cost before tax in cents
	 */
	public int getSubTotal() {
		double subTotalCost = 0;					// the sub total cost
		for(int i = 0; i < size; i++) {
			subTotalCost += arr[i].getCost();		// iterates through the basket and adds up costs
		}
		return (int)(subTotalCost);					// convert into int
	}

	/* 
	 * This method gets the total tax (15%) of products
	 * Note only jam products are taxed
	 * @return returns an integer indicating the total tax of products
	 */
	public int getTotalTax() {
		double totalTax = 0;							// the total tax
		for(int i = 0; i < size; i++) {					// iterates through basket
			if(arr[i] instanceof Jam) {					// checks if arr[i] is of Jam class
				totalTax += arr[i].getCost() * 0.15;	// Apply a 15% tax rate to all Jam objects and sum them up
			}
		}
		return (int)(totalTax);
	}

	/* 
	 * This method gets the total cost tax included of the basket
	 * @return returns an integer type of total cost with tax
	 */
	public int getTotalCost() {
		double totalCost = 0;							// the total cost
		for(int i = 0; i < size; i++) {
			if(arr[i] instanceof Jam) { 				// tax only applies to Jam product
				totalCost += arr[i].getCost() * 1.15;	// Apply 15% tax to jam and add up total
			} else {									// for other products
				totalCost += arr[i].getCost();			// for products that are not applicable to tax
			}
		}
		return (int)(totalCost);		// convert into int
	}

	/*
	 * The toString method is overridden to print out a receipt for the products in basket with name price and total prices
	 */
	public String toString() {
		String output = "\n"; 																// empty line
		for(int i = 0; i < size; i++) {
			if(arr[i].getCost() <= 0) {
				output += arr[i].getName() + "\t" + "-" + "\n";	// iterates through basket and adds up costs
			} else {
				output += arr[i].getName() + "\t" + String.format("%.2f", arr[i].getCost() / 100.0) + "\n";	// iterates through basket and adds up costs
			}
		}
		output += "\n";																			// empty line
		if(getSubTotal() <= 0) {
			output += "Subtotal\t" + "-" + "\n";												// output "-" for subtotals <= 0
		} else {
			output += "Subtotal\t" + String.format("%.2f", getSubTotal() / 100.0) + "\n";		// subtotal with 2 decimals
		}
		
		if(getTotalTax() <= 0) {
			output += "Total Tax\t" + "-" + "\n";												// output "-" for total tax <= 0
		} else {
			output += "Total Tax\t" + String.format("%.2f", getTotalTax() / 100.0) + "\n";		// total tax with 2 decimals
		}
		
		output += "\n";																			// empty line
		
		if(getTotalCost() <= 0) {
			output += "Total Cost\t" + "-";														// output "-" for total cost <= 0
		} else {
			output += "Total Cost\t" + String.format("%.2f", getTotalCost() / 100.0);			// total cost with 2 decimals
		}
		
		return output;
	}

	/*
	 * Getter used to get the array of market products in the basket
	 * Note this is created solely for the purpose of testing 
	 * @return returns the reference to the MarketProduct array arr

	public MarketProduct[] getArray() {
		return this.arr;
	}
	 */

}
