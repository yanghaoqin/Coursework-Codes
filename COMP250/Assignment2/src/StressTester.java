import java.lang.*;
import java.math.BigInteger;

// Note that this tester will not check the correctness of your code.
// It only checks the time complexity.
// All tests should terminate in less than 30 seconds on a 10 year old or newer computer.
public class StressTester {
	
	// Creates a long polynomial and clones it.
	public static void testClone()
	{
		int nTerms = 1000000;
		Polynomial p1 = new Polynomial();
		
		// Create a long polynomial
		for (int i = nTerms - 1; i >= 0; i--)
			p1.addTermLast(new Term(i, new BigInteger(Integer.toString(i+1)))); // Append the terms in descending order of exponent.
		
		System.out.print("Starting stress test for deepClone O(n)...");
		
		p1.deepClone();
		
		System.out.println("Passed");
	}
	
	// Creates a long polynomial and then adds 50 terms at the end of polynomial.
	public static void testAddTerm()
	{
		int nTerms = 1000000;
		Polynomial p = new Polynomial();
		
		// Create a long polynomial
		for (int i = nTerms - 1; i >= 0; i--)
			p.addTermLast(new Term(i, new BigInteger(Integer.toString(i+1)))); // Append the terms in descending order of exponent.
		
		System.out.print("Starting stress test for addTerms O(n)...");
		
		// Test: add 50 new terms to long polynomial.
		for (int i = 0; i < 50; i++)
			p.addTerm(new Term(i, new BigInteger("1")));
				
		System.out.println("Passed");
	}
	
	// Creates two long polynomial and adds them.
	public static void testAdd()
	{
		int nTerms = 1000000;
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		
		// Create two polynomials of length nTerms.
		for (int i = nTerms - 1; i >= 0; i--)
		{
			p1.addTermLast(new Term(i, new BigInteger(Integer.toString(i+1))));
			p2.addTermLast(new Term(i, new BigInteger(Integer.toString(i+1))));
		}
				
		System.out.print("Starting stress test for add O(n1 + n2)...");
		
		// Add two polynomials of length nTerms
//		long begin = System.nanoTime();
		Polynomial.add(p1, p2);
//		long end = System.nanoTime();

//		System.out.println("Time taken: " + (end-begin)/10000000);

		
		System.out.println("Passed");
	}
	
	// Creates a long polynomial and multiplies it with a single term.
	public static void testMultiplyTerm()
	{
		int nTerms = 1000000;
		Polynomial p1 = new Polynomial();
		
		// Create a long polynomial
		for (int i = nTerms - 1; i >= 0; i--)
			p1.addTermLast(new Term(i, new BigInteger(Integer.toString(i+1))));
		
		System.out.print("Starting stress test for multiplyTerm O(n)...");
		long start = System.nanoTime();
		// Multiply a long polynomial with a single term
		p1.multiplyTermTest(new Term(1, new BigInteger("1")));
		long end = System.nanoTime();
		
		System.out.println("Time taken: " + (end-start)/10000000);

		System.out.println("Passed");
	}
	
	// This will test if the Polynomial.multiply implementation is either O(n1 * n2^2) or O(n1^2 * n2).
	// Create two polynomial and multiply them.
	public static void testMultiply_1()
	{
		int nTerms = 1500;
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		
		// Create a polynomial with nTerms having exponents nTerms-1, nTerms-2, nTerms-3...,2,1,0
		for (int i = nTerms - 1; i >= 0; i--)
			p1.addTermLast(new Term(i, new BigInteger(Integer.toString(i+1))));
		
		// Create a polynomial with nTerms having exponents nTerms-1, nTerms-2, nTerms-3...,2,1,0
		for (int i = nTerms - 1; i >= 0; i--)
			p2.addTermLast(new Term(i, new BigInteger(Integer.toString(i+1))));
				
		System.out.print("Starting stress test for multiply O(n1*n2^2)...");
		
		Polynomial.multiply(p1, p2);
	
		System.out.println("Passed");
	}
	
	// This will test whether Polynomial.multiply implementation is O(n1 * n2).
	// Difference from testMultiply_1() is that the second polynomial's exponents are multiples of nTerms.
	// i.e p1 = 1 + x + x^2 + x^3...x^nTerms
	// and p2 = 1 + x^nTerm + x^(2*nTerm) + x^(3*nTerm)...x^(nTerms*nTerms).
	public static void testMultiply_2()
	{
		int nTerms = 1500;
		Polynomial p1 = new Polynomial();
		Polynomial p2 = new Polynomial();
		
		// Create a polynomial with nTerms having exponents nTerms-1, nTerms-2, nTerms-3...,2,1,0
		for (int i = nTerms - 1; i >= 0; i--)
			p1.addTermLast(new Term(i, new BigInteger(Integer.toString(i+1))));
		
		// Create a polynomial with nTerms having exponents nTerms*(nTerms-1), nTerms*(nTerms-2), nTerms*(nTerms-3)...,2 nTerms, nTerms, 0
		for (int i = nTerms - 1; i >= 0; i--)
			p2.addTermLast(new Term(i * nTerms, new BigInteger(Integer.toString(i+1))));
				
		System.out.print("Starting stress test for multiply O(n1*n2)...");
		
		
		long begin = System.nanoTime();
		Polynomial.multiply(p1, p2);
		long end = System.nanoTime();
		
		System.out.println("Passed");
		System.out.println("Time taken: " + (end-begin)/1000000000);
	}
	
	// Creates a long polynomial and evaluates it at x=1.
	public static void testEval()
	{
		int nTerms = 1000000;
		Polynomial p1 = new Polynomial();
				
		for (int i = nTerms - 1; i >= 0; i--)
			p1.addTermLast(new Term(i, new BigInteger(Integer.toString(i+1))));
				
		System.out.print("Starting stress test for eval O(n)...");
		p1.eval(new BigInteger("1"));
	
		System.out.println("Passed");
	}
	
	public static void main(String[] args) 
	{
		testClone();
		testAddTerm();
		testAdd();
		testMultiplyTerm();
		testMultiply_1();
		testMultiply_2();
		testEval();
	}
}
