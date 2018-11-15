// Author: Raymond H. Yang

import java.util.StringTokenizer;
import acm.program.ConsoleProgram;

public class In2pJ extends ConsoleProgram { 
	
	/**
	 * isOperand method takes input and checks if input is an operand (number)
	 * This is accomplished by comparing the input to all operators
	 * If nothing matches then we are sure it is an operand
	 * @param st is the value parsed from the StringTokenizer
	 * @return Returns a boolean value, true for operand and false for operators
	 */
	public boolean isOperand(String st) {
		
		// Operands include " * / + - ( ) "
		if(st.compareToIgnoreCase("*") == 0 || st.compareToIgnoreCase("/") == 0 || st.compareToIgnoreCase("+") == 0 || st.compareToIgnoreCase("-") == 0 || st.compareToIgnoreCase("(") == 0 || st.compareToIgnoreCase(")") == 0) {
			return false;
		}
		else {
			return true;
		}
			
	}
	
	/**
	 * getPrecedence gets the relative precedence of the operators
	 * An operator with higher precedence will get a higher integer value
	 * @param token is the input value from the StringTokenizer
	 * @return Returns an integer that represents the operator's relative precedence
	 */
	public int getPrecedence(String token) {
		
		// Operators with highest level of precedence will have large int values
		// From high prec level to low prec level: */ +-
		// For unary + and - need to distinguish between normal + and -
		if(token.compareToIgnoreCase("*") == 0 || token.compareToIgnoreCase("/") == 0) {
			return 2;
		}
		
		// This is for + and -
		// Will not distinguish between unary and binary operators yet
		else if(token.compareToIgnoreCase("+") == 0 || token.compareToIgnoreCase("-") == 0) {
			return 1;
		}
		
		// Left parentheses have highest precedence
		else if(token.compareToIgnoreCase("(") == 0) {
			return 4;
		}
		
		// Right parentheses have lowest precedence
		// since whenever they appear all elements between the parentheses need to be popped and added to queue
		else {
			return 0;
		}
		
	}
	
	// This part of code is the code for unary operators
	// Here only the unary plus ( + ) and unary minus ( - ) will be considered
	// 3 cases where unary operators will show up
	// 		1. At the beginning of the expression: -1*2+3
	// 		2. In the middle of expression: 5*-2+3
	//		3. In front of parentheses: -(2*3)
	// An unary operator is analogous to the number being subtracted by zero
	// i.e. -5 is analogous to (0-5)

	
	public void run() {

		// Creates output queue for storing operands and operator stack for storing operators
		Queue outputQueue = new Queue();
		Stack operatorStack = new Stack();
		
		// The StringTokenizer program reads strings and parses them into operands and operators
		String str = readLine("Enter string: ");
		StringTokenizer st = new StringTokenizer(str,"+-*/()",true); 
		
		
		// The next three instance variables are used to distinguish unary operators
		// We will determine if operator is unary based on the previous token
		String prev_token = "";
		
		// Records number of unary operators in stack
		// Since there might be the case of multiple unary operators stacked on top of one another
		// Need to know how many times to pop the stack
		int unary_count = 0;
		
		// Records number of left parentheses in stack
		// When unary operators are directly in front of a pair of parentheses
		// The content inside parentheses should be popped into queue first then the unary operator
		int lp_count = 0;
		
		while (st.hasMoreTokens()) {

			// Creates variable to store token
			String token = st.nextToken();
			
			// If the token is an operand --> goes in outputQueue
			// If the token is an operator --> goes in operatorStack
			if(isOperand(token)) {
				
				if(unary_count == 0) {
				
					outputQueue.Enqueue(token);
				
				}
				
				// Unary operator followed by a non parentheses operator
				else if(unary_count != 0 && prev_token.compareToIgnoreCase("(") != 0){
					
					// Check if there are parentheses in stack
					if(lp_count == 0) {

						// Put number in queue first
						outputQueue.Enqueue(token);

						// Release unary operator(s) from stack since there are no parentheses in stack
						for(int i = 0; i < unary_count; i++) {
							outputQueue.Enqueue(operatorStack.pop());
						}

						// Clear count since unary operators popped 
						unary_count = 0;

					}
					
					// Case where there are left parentheses in stack, cannot release unary operators until left parentheses cleared
					else {
						outputQueue.Enqueue(token);
					}
					
				}
				
				// Case where there is a unary operator followed by a left parentheses
				// Cannot release unary operator yet
				else {
					outputQueue.Enqueue(token);
				}
				
			}
			
			// If token is operator
			else {
				
				// Record left parentheses if encountered
				if(token.compareToIgnoreCase("(") == 0) {
					lp_count++;
				}
				
				// For operators need to check precedence
				// First check if the stack is empty
				if(operatorStack.top == null) {
					
					// Check if queue is empty and if operator is of specific type (+ or -) to determine if operator is unary
					// Case 1 of unary operators
					if(outputQueue.start ==  null && token.compareToIgnoreCase("+") == 0 || outputQueue.start ==  null && token.compareToIgnoreCase("-") == 0) {
						
						// -5 = (0 - 5)
						outputQueue.Enqueue("0");
						unary_count++;
						
						operatorStack.push(token);
						
					}
					
					// Operator is not unary and stack is empty — no need to check precedence
					else {
						operatorStack.push(token);
					}
					
				}
				
				// The stack contains elements, need to check precedence
				else {
					
					// If the previous token is an operator and the current token is also an operator of either (+ or -)
					// Current token is unary operator (Case 2)
					if(isOperand(prev_token) == false && prev_token.compareToIgnoreCase(")") != 0 && token.compareToIgnoreCase("+") == 0 || isOperand(prev_token) == false && prev_token.compareToIgnoreCase(")") != 0 && token.compareToIgnoreCase("-") == 0) {
						
						outputQueue.Enqueue("0");
						unary_count++;
						
						operatorStack.push(token);
						
					}
					
					// Case where previous token is not an operator
					else {

						// If token has same precedence level as operator at top of stack, pop only the top operator
						if(getPrecedence(token) == getPrecedence(operatorStack.top.value)) {
							
							// Case for double left parentheses: same precedence but nothing popped
							// Note: ( has precedence 4
							if(getPrecedence(token) == 4) {
								operatorStack.push(token);
							}
							
							// Previous operator is not (
							else {
								
								// Need to push operators popped into outputQueue							
								outputQueue.Enqueue(operatorStack.pop());
	
								// Add the operator to stack
								operatorStack.push(token);
							}
							
						}

						// If the operator at the top of the stack has strictly higher precedence
						// Pop all operators and push in new operator
						else if(getPrecedence(token) < getPrecedence(operatorStack.top.value)) {

							// If token is a right parentheses then pop until left parentheses reached
							if(token.compareToIgnoreCase(")") == 0) {

								while(operatorStack.top.value.compareToIgnoreCase("(") != 0) {

									outputQueue.Enqueue(operatorStack.pop());

								}

								// Throw away left parentheses
								operatorStack.pop();
								
								// Update count of left parentheses in stack
								lp_count--;

							}

							// If previous operator is ( , just push element in
							else if(getPrecedence(operatorStack.top.value) == 4) {
								operatorStack.push(token);
							}

							// If not a right parentheses pop all elements until a left parentheses is reached, if any
							else {
								
								// Check if previous operator is ( since we do not want ( popped into queue before all content between parentheses are entered
								while(operatorStack.top != null && getPrecedence(operatorStack.top.value) != 4) {
									outputQueue.Enqueue(operatorStack.pop());
								}

								operatorStack.push(token);

							}

						}

						// If new operator has higher precedence than previous operator we add it directly to stack
						else if(getPrecedence(token) > getPrecedence(operatorStack.top.value)) {
							operatorStack.push(token);
						}

					}

				}
				
			}
			
			// At the end of a loop the prev_token needs to be updated
			prev_token = token;
			
		}
		
		// After all tokens have been sorted, pop all operators from operatorStack to outputQueue
		while(operatorStack.top != null) {
			outputQueue.Enqueue(operatorStack.pop());
		}
		
		print("Postfix: ");
		
		// Print all items from queue
		while(outputQueue.start != null) {
			print(outputQueue.Dequeue()+" ");
		}
		
		println("");

	}
	
}

