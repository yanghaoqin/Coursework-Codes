/*
 * sortFile.c
 * Name: Raymond Haoqin Yang
 */

/*
 * The following piece of code is imported and modified.
 ============================================================================
 Name        : listFile.c
 Author      : F. Ferrie
 Version     :
 Copyright   : Your copyright notice
 Description : A "C" version of the fileReader program originally
             : written Java for Assignment 2.  This one uses the command
             : line interface to obtain the file name.  Like its predecessor,
             : this program reads a text file line by line, displaying the
             : output on the standard output.  Use this program as a basis
             : for Assignment 6.
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXBUF 100

// This is the structure of the binary tree
// Components include data value, left and right nodes
struct btree{


	char data[MAXBUF]; // String that stores data

	// These two are pointers/addresses pointing to nothing
	struct btree* left;
	struct btree* right;

};

// This creates a new tree at the address pointed to by the existing tree
// directly a level up, and returns the address of the new tree,
// and also assigns value to the string as well as initialize the pointers.
struct btree* newtree(char* input, struct btree* tree){

	// Allocate memory space the same size as a btree
	// Note the size is not the size of the btree pointer
	// The space allocated should be big enough to store: the data, left and right node addresses
	tree = (struct btree*)malloc(sizeof(struct btree));

	// The value of the root will have the input value
	strcpy(tree->data, input);

	// Left and right nodes will point to NULL
	tree->left = NULL;
	tree->right = NULL;

	// The address of the btree created will be returned
	return tree;
}

// This function is used for adding nodes to the binary tree
// Reads in the input value and the address of the tree at the current level
// Returns the address of the tree with the added node
// Basically through this function, the pointers within levels of "thetree" are modified
// When all data are added in through this function, they are already sorted
struct btree* addNode(char* input, struct btree* btree){

	// If node points to null then create node by calling newtree
	// The input value will be copied to btree->data
	if(btree == NULL){
		btree = newtree(input, btree);
		return btree;
	}
	else{

		// If the root has a value already
		if(strcmp(input, btree->data) < 0){

			// input is smaller than current node data
			// Go left and see if there are other nodes
			btree->left = addNode(input, btree->left);
		}

		else{

			// Input is larger than current node data
			// Go right and check if the right node has a value
			btree->right = addNode(input, btree->right);

		}
		return btree;
	}
}

// Prints values from smallest to largest in increasing order
// This function is reads the data in the btree in a particular order.
// The traversal method is left node, root node, right node
void inorder_traversal(struct btree* btree){

	// Check if left node is null
	// if not, traverse left
	if(btree->left != NULL){
		inorder_traversal(btree->left);
	}

	// If the function reaches this point, then basically the leftmost node is found
	// Print this value as it will be the smallest. The next time the program reaches
	// here, the value is should be the second smallest, and so on. Starting from here,
	// the program is going back up the tree
	printf("%s", btree->data);

	// After reaching the leftmost node, check if there are nodes on the right,
	// since they will be greater than the left nodes and root node but smaller
	// than the nodes above this level.
	if(btree->right != NULL){
		inorder_traversal(btree->right);
	}

}

// Prints values from largest to smallest in decreasing order
// This function is reads the data in the btree in a particular order.
// The traversal method is right node, root node, left node
void revorder_traversal(struct btree* btree){

	// Check if there are greater values
	// If not, greatest value reached
	if(btree->right != NULL){
		revorder_traversal(btree->right);
	}

	// Prints value of each node, from rightmost to leftmost overall
	printf("%s", btree->data);

	// Check for smaller values
	// If exists, traverse left
	if(btree->left != NULL){
		revorder_traversal(btree->left);
	}

}

int main(int argc, char *argv[]) {
	// Internal declarations
	FILE * FileD;			// File descriptor (an object)!
	char* line;				// Pointer to buffer used by getline function
	int bufsize = MAXBUF;	// Size of buffer to allocate
	int linelen;				// Length of string returned (getline)

	// Argument check
	if (argc != 2) {
		printf("Usage: fileReader [text file name]\n");
		return -1;
	}

	// Attempt to open the user-specified file.  If no file with
	// the supplied name is found, exit the program with an error
	// message.

	if ((FileD=fopen(argv[1],"r"))==NULL) {
		printf("Can't read from file %s\n",argv[1]);
		return -2;
	}

	// Allocate a buffer for the getline function to return data to.

	line=(char *)malloc(bufsize+1);
	if (line==NULL) {
		printf("Unable to allocate a buffer for reading.\n");
		return -3;
	}

	// If the file exists and a buffer was successfully allocated,
	// use the getline function to read the file libe by line.  This
	// is directly analogous to the readLine method in Java.
	//
	// Notice that the first argument to getline is a pointer to
	// a pointer.  This type of argument passing is used when
	// the function modifies that actual value of the pointer itself
	// as well as the data that the pointer references.  This detail
	// is beyond the scope of this course.

	// Another detail about the behavior of the getline function -
	// it returns the \n (newline) delimiter at the end of the
	// current line, so you need to remember to strip this off
	// depending on how you use the string.  Since this function
	// simply prints the file, the newline is left in and does not
	// have to be added in the printf call.

	// Title
	printf("Assignment 6 - File Sorting Program\n");

	// Name of file
	printf("Enter name of file to sort: %s\n\n\n", argv[1]);

	printf("File in sort order:\n\n");

	// The starting(top) level of the binary tree
	struct btree* thetree = NULL;

	while ((linelen=getline(&line,(size_t *)&bufsize,FileD))>0){

		thetree = addNode(line, thetree); // For every entry add new node

	}

	// Sort file using traversal method
	inorder_traversal(thetree);

	printf(" \n\nFile in reverse sort order:\n\n");

	// In reverse order
	revorder_traversal(thetree);

	printf("\nProgram terminated\n");

	free(thetree); // Frees memory

	return EXIT_SUCCESS;
}



