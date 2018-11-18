
public class Stack {
	
	listNode top = null;
	
	/**
	 * This method is the push method for the stack
	 * Creates object node
	 * Assigns the data value to the node
	 * The top is now the node
	 * @param given_value is the external input data
	 */
	public void push(String given_value)
	{
		
		listNode node = new listNode();
		node.value = given_value;
		node.next = top;
		top = node;
		
	}
	
	/**
	 * This method is the pop method for the stack
	 * Returns the data at the top of the stack
	 * and the value after is the new top
	 * @return The string from the top of the stack
	 */
	public String pop() {
		
		// If the stack is empty, then nothing is popped
		if(top == null) {
			return null;
		}
		
		String value = top.value;
		
		// The new top is the value after the old top value
		top = top.next;
		
		return value;
		
	}
}
