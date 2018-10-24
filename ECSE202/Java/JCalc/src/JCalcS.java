/**
 * @author Raymond Haoqin Yang
 * McGill ID: 260777792
 * Date: 2018/03/16
 * OS: MacOS
 * Description: 
 * The infix interpreter. The user enters an infix expression and the program will convert it to postfix then evaluate it.
 * Program also works for infix expressions with parentheses and unary operators
 */

import acm.program.*;
import java.util.*;

public class JCalcS extends ConsoleProgram{

	/**
	 * Checks if the character dequeued from the postfix queue is an operator or operand
	 * @param input is the character dequeued from the postfix queue
	 * @return returns either true or false, true for operators and false for operands
	 */
	public boolean isOperator(String input) {
		
		// If it is an operator of either + - * /
		if(input.compareToIgnoreCase("+") == 0 || input.compareToIgnoreCase("-") == 0 || input.compareToIgnoreCase("*") == 0 || input.compareToIgnoreCase("/") == 0) {
			return true;
		}
		
		// If it is an operand
		else {
			return false;
		}
			
	}
	
	/**
	 * This method evaluates the postfix expression from the queue
	 * Basically when an operator is encountered
	 * The two elements in the stack before it is the left and right operand
	 * When the postfix queue is empty we should get a single integrated evaluation
	 * @param input is the postfix queue with the postfix expression
	 */
	public void evalpf(Queue input) {
		
		// Stack that stores interpreted results (both intermediate and final)
		Stack eval_stack = new Stack();
		
		// The count for operators (used to get the number of evaluation)
		int count = 0;
		
		// While postfix queue still has elements, repeat process
		while(input.isEmpty() == false) {
			
			// Takes an element from the postfix queue
			String str = input.Dequeue();
			
			if(isOperator(str) == false) {
				
				// So element is an operand
				// Add to eval_stack
				eval_stack.push(str);
				
			}
			else {
				
				// The number of evaluation is the same as the number of times an operator shows up
				count++;
				
				// If element is an operator
				// Pop two elements and evaluate
				
				// The first element popped will be the operand on the right
				String r_oprd = eval_stack.pop();
				
				// The second element following the first will be the left operand
				String l_oprd = eval_stack.pop();
				
				// The name of the current evaluation, i.e. eval1, eval2, etc.
				String eval_name = "Eval" + count;
				
				// The value of the current evaluation (mathematical argument)
				// Insert the operator in between and add brackets
				String eval_value = "<" + l_oprd + str + r_oprd + ">";
				
				// Print out evaluation statement
				println(eval_name + " = " + eval_value);
				
				// Push evaluation in integrated form back in eval_stack and continue process
				eval_stack.push(eval_name);
				
			}
			
		}
		
		// Empty line at the end of one  infix expression evaluation
		println("");
		
	}
	
	
	public void run() {
		
		println("Infix to Postfix Interpreter");
		
		// Infinite loop
		while(true) {
			
			// Read infix input
			String str = readLine("Enter expression (blank line to exit): ");
			
			// If blank line then terminate program
			// Trim line (get rid of empty spaces)
			// Then check if empty
			if(str.trim().equals("")) {
				System.exit(0); 	// "0" means program exits not due to any error
			}
			
			// To ensure a valid expression is entered
			StringTokenizer token = new StringTokenizer(str, "+-*/", true);
			
			// Count of elements in expression
			int ct = 0; 
			
			// Loop to extract elements
			while(token.hasMoreTokens()) {
			
				// Gets element
				token.nextToken();
				
				// Updates count
				ct++;
			
			}
			
			// Checks number of elements
			if(ct <= 2) {
				println("Unable to evaluate. Please enter a valid expression.");
				println("");
				continue;
			}
			
			// Creates object in2pf and calls method to convert infix input to postfix
			In2pJ in2pf = new In2pJ();

			// Evaluates the infix which is converted to postfix by method postfix in class in2pf
			evalpf(in2pf.postfix(str));
			
		}
		
	}
	
}
