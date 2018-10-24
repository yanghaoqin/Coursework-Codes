import java.math.BigInteger;

public class Polynomial 
{
	private SLinkedList<Term> polynomial;	// SLL

	public int size()
	{	
		return polynomial.size();
	}

	// constructor for Polynomial object
	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}

	// constructor for Polynomial object
	public Polynomial()
	{
		polynomial = new SLinkedList<Term>();
	}

	// Returns a deep copy of the object.
	public Polynomial deepClone()
	{	
		return new Polynomial(polynomial.deepClone());
	}

	/* 
	 * TODO: Add new term to the polynomial. Also ensure the polynomial is
	 * in decreasing order of exponent.
	 */
	public void addTerm(Term t)
	{	
		/**** ADD CODE HERE ****/

		// Hint: Notice that the function SLinkedList.get(index) method is O(n), 
		// so if this method were to call the get(index) 
		// method n times then the method would be O(n^2).
		// Instead, use a Java enhanced for loop to iterate through 
		// the terms of an SLinkedList.
		/*
		for (Term currentTerm: polynomial)
		{
			// The for loop iterates over each term in the polynomial!!
			// Example: System.out.println(currentTerm.getExponent()) should print the exponents of each term in the polynomial when it is not empty.  
		}
		 */

		Term input = t.deepClone();
		SLinkedList<Term> temp = new SLinkedList<Term>();	// emtpy SLL to store polynomial
		boolean flag = false;								// indicates whether term has already been added
		if((input.getCoefficient()).equals(BigInteger.ZERO) == true){		// if coefficient is 0
			return;
		}
		if(input.getExponent() < 0) {						// if exponent of term is negative
			throw new IllegalArgumentException("Exponent cannot be a negative value");
		}
		if(polynomial.isEmpty() == true) {					// if the polynomial is empty
			temp.addLast(input);							// Put at last
		} else {
			for(Term currentTerm: polynomial) {		// O(n) operation
				// compare terms
				if(flag == false) {		// term has not been added yet
					if(input.getExponent() == currentTerm.getExponent()) {
						// getExponent is O(1) operation
						// if they have same exponent then set the sum as the new exponent
						BigInteger tempcoeff = input.getCoefficient().add(currentTerm.getCoefficient());
						if(tempcoeff.equals(BigInteger.ZERO) == true) {
							// if coeff after addition is zero, don't add 
						} else {
							input.setCoefficient(tempcoeff);
							temp.addLast(input);	// O(1) operation
						}
						flag = true;				// signals already added
					} else if(currentTerm.getExponent() < input.getExponent()){
						temp.addLast(input);		// O(1) operation adds the input term
						temp.addLast(currentTerm);	// then add current term
						flag = true;				// signals already added
					} else {	
						temp.addLast(currentTerm);	// O(1) operation
					}
				} else {
					temp.addLast(currentTerm);	// if already added then skip comparison process
				}
			}
			// edge case where there is no term to iterate, but term has not been added
			if(flag == false)
				temp.addLast(input);
		}
		polynomial = temp;
	}

	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}

	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2)
	{
		/**** ADD CODE HERE ****/
		
		Polynomial poly = new Polynomial();		// empty polynomial to store results

		if(p1.polynomial.size() == 0) return p2.deepClone();
		if(p2.polynomial.size() == 0) return p1.deepClone();

		// neither p1 or p2 are empty	
		int arrlength;		// arrlength is the length of the longest array + 1
		if(p1.polynomial.get(0).getExponent() - p2.polynomial.get(0).getExponent() >= 0) {
			// p1's largest exponent >= p2's largest exponent
			arrlength = p1.polynomial.get(0).getExponent() + 1;
		} else {
			arrlength = p2.polynomial.get(0).getExponent() + 1;
		}

		// an array of big integer with size = the largest exponent in both polynomials
		BigInteger[] arr = new BigInteger[arrlength];

		for(Term curTerm: p1.polynomial) {
			arr[curTerm.getExponent()] = curTerm.getCoefficient();	// represents polynomial by setting indices as exponents and values as coefficients
			// full array from i = 0 to i = largest exponent
		}

		// now iterate p2 and update array values
		for(Term curTerm: p2.polynomial) {
			// check if term already exists in polynomial
			if(arr[curTerm.getExponent()] != null) {
				// does exist, add up the two coefficients and check sum
				BigInteger temp = (curTerm.getCoefficient()).add(arr[curTerm.getExponent()]);
				if(temp.equals(BigInteger.ZERO) == false) {
					// if the sum temp is not 0, put in array
					arr[curTerm.getExponent()] = temp;
				} else {
					// if sum temp i 0, make the array at index [exponent] point to null
					arr[curTerm.getExponent()] = null;
				}
			} else {
				// if term does not exist yet, put coefficient in array directly
				arr[curTerm.getExponent()] = curTerm.getCoefficient();
			}
		}

		// now the array contains all the info needed
		// convert array into SLL, iterate array
		for(int i = arrlength - 1; i >= 0; i--) {
			if(arr[i] != null) {
				poly.addTermLast(new Term(i, arr[i]));		// insert into polymonial
			}
		}
		return poly;
}


	//TODO: multiply this polynomial by a given term.
	private void multiplyTerm(Term t)
	{	
		/**** ADD CODE HERE ****/
		// check validity of polynomial
		if(t.getExponent() < 0) throw new IllegalArgumentException("Exponent cannot be negative.");
		
		// check if polynomial is empty
		if(polynomial.size() == 0) return;
		
		// check if term is has 0 coefficient
		if((t.getCoefficient()).equals(BigInteger.ZERO) == true) {
			polynomial.clear();				// return empty polynomial
			return;
		}
		
		for(Term curTerm: polynomial) {
			// for each term, coeff multiplies coeff and exponent adds exponent
			// coefficient will not be zero
			curTerm.setCoefficient((curTerm.getCoefficient()).multiply(t.getCoefficient()));
			curTerm.setExponent(curTerm.getExponent() + t.getExponent());
		}
	}

	
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2)
	{
		/**** ADD CODE HERE ****/
		
		if(p1.size() == 0 || p2.size() == 0) {
			Polynomial poly = new Polynomial();
			return poly;						// if either size is 0, the resulting polynomial will be 0
		}
		
		Polynomial poly = new Polynomial();
		int temp1 = p1.polynomial.get(0).getExponent() + 1;
		int temp2 = p2.polynomial.get(0).getExponent() + 1;
		int temp3 = temp1 + temp2;
		
		BigInteger[] arr1 = new BigInteger[temp1];
		BigInteger[] arr2 = new BigInteger[temp2];
		BigInteger[] arrout = new BigInteger[temp3];
		
		for(Term curTerm: p1.polynomial) {
			arr1[curTerm.getExponent()] = curTerm.getCoefficient();
		}
		for(Term curTerm: p2.polynomial) {
			arr2[curTerm.getExponent()] = curTerm.getCoefficient();
		}
		
		for(int i = 0; i < arr1.length; i++) {
			if(arr1[i] != null) {
				for(int j = 0; j < arr2.length; j++) {
					if(arr2[j] != null) {
						if(arrout[i+j] == null) {
							arrout[i+j] = arr1[i].multiply(arr2[j]);	// multiplication of coeff and addition of exp
						} else {
							arrout[i+j] = arrout[i+j].add(arr1[i].multiply(arr2[j]));
						}
					}
				}
			}
		}
		
		for(int i = temp3-1; i >= 0; i--) {
			if(arrout[i] != null && arrout[i].equals(BigInteger.ZERO) == false) {
				// a term with exponent i exists and does not have coeff 0
				poly.addTermLast(new Term(i, arrout[i]));
			}
		}
		return poly;
}	
	
	
	
	//TODO: evaluate this polynomial.
	// Hint:  The time complexity of eval() must be order O(m), 
	// where m is the largest degree of the polynomial. Notice 
	// that the function SLinkedList.get(index) method is O(m), 
	// so if your eval() method were to call the get(index) 
	// method m times then your eval method would be O(m^2).
	// Instead, use a Java enhanced for loop to iterate through 
	// the terms of an SLinkedList.

	public BigInteger eval(BigInteger x)
	{
		/**** ADD CODE HERE ****/
		
		// If the polynomial does not exist, return 0
		if(this.polynomial.size() == 0)	return new BigInteger("0");
		BigInteger sum = new BigInteger("0");	// initialize sum
		
		// we want to use pointer as a mechanism for Horner's method
		// solving the problem of incomplete list of exponents
		int pointer = this.polynomial.get(0).getExponent();
		
		for(Term curTerm: polynomial) {		// iterate through polynomial
			
			while(pointer != curTerm.getExponent()) {	// not at correct exponent
				sum = sum.multiply(x);					// multiply x until exponent is at the next term in the list of polynomials
				pointer--;								// update pointer
			}
			
			// pointer at the right place
			sum = sum.multiply(x).add(curTerm.getCoefficient());
			pointer--;
		}	
			
		// clear off remaining pointers
		for(int i = 0; i <= pointer; i++) {
			sum = sum.multiply(x);
		}
		return sum;
	}

	// Checks if this polynomial is same as the polynomial in the argument.
	// Used for testing whether two polynomials have same content but occupy disjoint space in memory.
	// Do not change this code, doing so may result in incorrect grades.
	public boolean checkEqual(Polynomial p)
	{	
		// Test for null pointer exceptions!!
		// Clearly two polynomials are not same if they have different number of terms
		if (polynomial == null || p.polynomial == null || size() != p.size())
			return false;
		
		int index = 0;
		// Simultaneously traverse both this polynomial and argument. 
		for (Term term0 : polynomial)
		{
			// This is inefficient, ideally you'd use iterator for sequential access.
			Term term1 = p.getTerm(index);

			if (term0.getExponent() != term1.getExponent() || // Check if the exponents are not same
					term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || // Check if the coefficients are not same
					term1 == term0) // Check if the both term occupy same memory location.
				return false;

			index++;
		}
		return true;
	}

	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	// Do not change this code, doing so may result in incorrect grades.
	public void addTermLast(Term t)
	{	
		polynomial.addLast(t);
	}

	// This is used for testing multiplyTerm.
	// Do not change this code, doing so may result in incorrect grades.
	public void multiplyTermTest(Term t)
	{
		multiplyTerm(t);
	}

	@Override
	public String toString()
	{	
		if (polynomial.size() == 0) return "0";
		return polynomial.toString();
	}
}
