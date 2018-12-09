/**
 * @author Raymond Yang
 */

import java.math.BigInteger;
public class factorial {

	/**
	 * 
	 * @param x factorial number
	 * @return BigInteger storing the computed factorial
	 */
	public static BigInteger factorial(BigInteger x) {
		if(x.equals(new BigInteger("1"))) {
			return x;
		}
		
		return x.multiply(factorial(x.subtract(new BigInteger("1"))));
	}
	
	public static void main(String args[]) {
		BigInteger a = factorial(new BigInteger("5000"));
		
		System.out.println("Factorial result: " + a);
		
		// remove all zeros at the end
		while(a.mod(new BigInteger("10")).equals(BigInteger.ZERO) == true) {
			a = a.divide(new BigInteger("10"));
		}
		
		System.out.println("Result with all zeros at the end taken out: " + a);
		
		// extract last digit
		a = a.subtract(a.divide(new BigInteger("10")).multiply(new BigInteger("10")));
		
		System.out.println("Last non-zero digit is: " + a);
		
	}

}
