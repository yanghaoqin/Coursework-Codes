// Name: Raymond Haoqin Yang


import acm.program.*;

public class dec2base extends ConsoleProgram {
		
	// The base converting algorithm dec2B, but order is in reverse
	private static String dec2B(int number, int base) {
		
		// Defining the necessary components
		int lastQ = number; // The number to convert
		String R = ""; // Empty string used to store results. Note: Results in reverse order
		String LUT = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // Base greater than 10 would require other forms of representation
		
		// Implementing a while loop, dividing the number by its base multiple times
		while(lastQ > 0) {
			R = R + LUT.charAt(lastQ % base); // Getting the remainder and putting it in the string
			lastQ = lastQ / base; // Updates variable lastQ
		}
		return R; // Returns the string that stores the results
	}
	
	// The method that reverses the string obtained above
	private static String reverseString(String R) {
		String newR = ""; // Empty string used to store results in correct order
		// Implementing a for loop to reverse order
		for(int a = R.length() - 1; a >= 0; a--) {
			newR = newR + R.charAt(a); // newR is the correct order, reverse order of R
		}
		return newR; // Returns the string with the correct order
	}
		
	// Now both methods are complete
	// Need to ask user for input and limit input values
	public void run() {
	
		println("Java base conversion demo: "); // Prints the program name
		
		// Implementing a while loop to enforce user input value for variables number and base
		while (true) {
			
			// Asking for number
			int number = readInt("Enter number to convert: "); // Using "readInt()" to record user input (the number)
			
			// Asking for base 
			int base = readInt("Enter base to convert to: "); // Using "readInt()" to record user input (the base)
			
			if (number < 0 || base < 2 || number > 2147483647) {
				break; // If input value does not meet requirements (>=0), program exits
			}
			else {
				String R = reverseString(dec2B(number, base)); // converts to target base and reverses order in the string
				println(number+ " is represented in Base-"+base+" as "+R); // Prints out converted number
			}
			
		}
		println("Program ended");
	}	
	
}
