import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * This piece of code is provided by Professor Frank Ferrie
 * This program opens a file, reads, and displays it on the output device line by line
 */

public class A2 {
	@SuppressWarnings("resource")
	public static void main(String args[]) {
		
		// Creates a new binary tree
		bTree nameTree = new bTree();
		
// Prompt user for a file name.  If no name is entered, terminate
// the program, otherwise attempt to open the file. If file open
// is not successful, prompt again for a new name.  Keep doing this
// until successful open, or a blank line is entered.
		
		System.out.println("Assignment 2 - File Sorting Program");
		Scanner sc = new Scanner(System.in);
		BufferedReader rd = null;
		
		while(rd == null) {
			System.out.print("Enter name of file to sort: ");
			String filename = sc.nextLine();
			if (filename.equals("")) {
				System.out.println("Program terminated");
				System.exit(0);									// Exit
			}
// Try to open the specified file
			try {
				rd = new BufferedReader(new FileReader(filename));
			}
			catch (IOException ex) {
				System.out.println("Unable to open file, try again.");
			}
		}

// Read the file a line at a time into a string.  Print as read to the output display.
// Modify the code below as necessary.
		
		System.out.println("\n");
		System.out.println("Files in sort order: \n");

		try {
			while (true) {
			
				String line = rd.readLine();
			
				if (line == null) {
					break;								
				}
				
				// Each time a line is read, the string is added into the binary tree
				nameTree.addNode(line);
				
			}	
			
			// Goes through inorder traversal to sort data
			nameTree.inOrderTraversal();
			
			System.out.println("\n\nFiles in reverse sort order: \n");
			
			// The stack is already pushed, only need to pop stack to retrieve list in reverse
			// Need to avoid the printing of the null value at the end
			while(true) {
				
				// Temp is used to store the element popped from stack each loop
				String temp = nameTree.nameStack.pop();
				
				// Exits loop when stack pops a null value
				if(temp == null) break;
				
				System.out.println(temp);
				
			}
					
		}
		catch (IOException ex) {
			System.out.println("I/O Error - program terminated");
			System.exit(-1);
		}
					
		System.out.println("\n\nProgram terminated");

	}
}
