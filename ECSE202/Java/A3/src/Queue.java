// Class contains methods Enqueue and Dequeue
// Note: When dequeuing if queue is empty, will return value of null


public class Queue {
	
	// Use listNode to represent elements of the queue
	// To start, a queue has a start and end
	listNode start;
	listNode end;
	
	// Need two methods: Enqueue and Dequeue
	
	/**
	 * Enqueue is for adding elements to the queue
	 * Like its name, new elements are added to the end
	 * Each element in the queue would contain a value and a reference to the next element
	 * @param given_value is the new value to be added to the queue
	 */
	public void Enqueue(String given_value) {
		
		// Creates a new node and assigns value
		listNode newNode = new listNode();
		newNode.value = given_value;
		
		// First need to check if queue is empty
		if(isEmpty()) {
			
			// If empty then given_value will be both start and end 
			start = newNode;
			end = newNode;
			
		}
		else {
			
			// If the queue already has elements
			// The last element will have a reference to the newNode
			// The newNode will be the end of the queue
			end.next = newNode;
			end = newNode;
			
		}
		
	}
	
	/**
	 * Dequeue is the method for outputting values of the queue
	 * The value at the start would be the output
	 * The following element would serve as the new start
	 * @return Returns the value of the element at the start of queue
	 */
	public String Dequeue() {
		
		// Check if queue is empty
		if(isEmpty())
			return null;
		else {
			
			// Return value of the element at the start of queue
			String Start = start.value;
			
			// The following element in the queue will be the new start of queue
			start = start.next;
			return Start;
			
		}
		
	}
	
	/**
	 * isEmpty checks if the queue is empty
	 * @return Returns a boolean value, true if queue is empty and false if not
	 */
	public boolean isEmpty() {
		
		// Check the start since start is the first element of the queue
		if (start == null) 
			return true;
		else
			return false;

	}
	
}
