package assignments2018.a2template;

import java.math.BigInteger;

public class Polynomial 
{
	private SLinkedList<Term> polynomial;
	
	
	public int size()
	{	
		return polynomial.size();
	}
	private Polynomial(SLinkedList<Term> p)
	{
		polynomial = p;
	}
	
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
        int e=t.getExponent();
		BigInteger c=t.getCoefficient();
		BigInteger zero=new BigInteger("0");
		if (c.compareTo(zero)==0) return;
		int i=0; //counter

		if (polynomial.size()==0) {
			polynomial.addFirst(t);
		}else {
		for (Term currentTerm: polynomial) {
				if (currentTerm.getExponent()==e) {
					BigInteger newcoefficient=c.add(currentTerm.getCoefficient());
					if (newcoefficient.compareTo(zero)==0) {
						this.polynomial.remove(i);
						return;
					}else {
						currentTerm.setCoefficient(newcoefficient);
						return;
					}			
				}
				if (currentTerm.getExponent()<e){
				this.polynomial.add(i, t);
				return;
				}
				i=i+1;
			}
		polynomial.addLast(t);
		return;
		}
	}
	
	public Term getTerm(int index)
	{
		return polynomial.get(index);
	}
	
	//TODO: Add two polynomial without modifying either
	public static Polynomial add(Polynomial p1, Polynomial p2)
	{
		/**** ADD CODE HERE ****/
		SLinkedList<Term> sum=new SLinkedList<Term>();
		if (p1==null)
			return p2;
		if (p2==null)
			return p1;
		int length=p2.polynomial.get(0).getExponent()+1;
		if (p1.polynomial.get(0).getExponent()>=p2.polynomial.get(0).getExponent())
			length=p1.polynomial.get(0).getExponent()+1;
		int store[]= new int[length];	
		for (Term currentTerm:p1.polynomial) 
			store[currentTerm.getExponent()]=currentTerm.getCoefficient().intValue();
		for (Term currentTerm:p2.polynomial) {
			int a=store[currentTerm.getExponent()];
			store[currentTerm.getExponent()]=currentTerm.getCoefficient().intValue()+a;			
		}
			for (int i=length-1;i>=0;i--) {
			if (store[i]!=0) {
				int c=store[i];
				sum.addLast(new Term(i,BigInteger.valueOf(c)));
			}
		}		
		Polynomial newpoly=new Polynomial(sum);
		return newpoly;
	}
	
	
	
	
	
	//TODO: multiply this polynomial by a given term.
	private void multiplyTerm(Term t)
	{	
		/**** ADD CODE HERE ****/
		BigInteger zero=new BigInteger("0");
		if (t.getCoefficient().compareTo(zero)==0) {
			this.polynomial=new SLinkedList<Term>();

			//if multiply by 0, the polynomial becomes zero.
		}else {
			int e=t.getExponent();
			BigInteger c=t.getCoefficient();
			for (Term currentTerm: polynomial) {
				int newexponent=currentTerm.getExponent()+e;
				BigInteger coefficient=(currentTerm.getCoefficient()).multiply(c);
				currentTerm.setCoefficient(coefficient);
				currentTerm.setExponent(newexponent);		
			}		
	}	
	}
	
	//TODO: multiply two polynomials
	public static Polynomial multiply(Polynomial p1, Polynomial p2)
	{
		/**** ADD CODE HERE ****/
		Polynomial temp=new Polynomial();
		for (Term currentTerm:p1.polynomial) {
			SLinkedList<Term> copy2=p2.polynomial.deepClone();
			Polynomial ccopy=new Polynomial(copy2);
			ccopy.multiplyTerm(currentTerm);
			temp=Polynomial.add(temp, ccopy);
		}
			
		return temp;
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
		BigInteger value= new BigInteger("0");
		if(this.polynomial.size()==0)
			return value;
		int firstexp=this.polynomial.get(0).getExponent();
		int i=0;
		int lastexp=0;
		for (Term currentTerm:polynomial) {
				while(firstexp-i!=currentTerm.getExponent()) {
					value=value.multiply(x);
					i=i+1;
				}
				value=value.multiply(x).add(currentTerm.getCoefficient());
				i=i+1;
				lastexp=currentTerm.getExponent();
			}
	
		for(i=0;i<lastexp;i++) {
		value=value.multiply(x);
		}	
		return value;
	}
	
	// Checks if this polynomial is same as the polynomial in the argument
	public boolean checkEqual(Polynomial p)
	{	
		if (polynomial == null || p.polynomial == null || size() != p.size())
			return false;
		
		int index = 0;
		for (Term term0 : polynomial)
		{
			Term term1 = p.getTerm(index);
			
			if (term0.getExponent() != term1.getExponent() ||
				term0.getCoefficient().compareTo(term1.getCoefficient()) != 0 || term1 == term0)
					return false;
			
			index++;
		}
		return true;
	}
	
	// This method blindly adds a term to the end of LinkedList polynomial. 
	// Avoid using this method in your implementation as it is only used for testing.
	public void addTermLast(Term t)
	{	
		polynomial.addLast(t);
	}
	
	// This is used for testing multiplyTerm
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
