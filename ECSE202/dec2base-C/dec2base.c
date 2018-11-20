/*
 * dec2base.c
 *
 * Name: Raymond Haoqin Yang
 * Completed on: 2018/03/29
 * 
 * Usage: First input is the number in decimal base, second input is the target base to convert to
 */

#include<stdio.h>

// Maximum number of digits possible is 32 (binary)
// 1 space more for '\0' at the end
const int MAX_DIGITS = 32;

// The length of the array after conversion
int LENGTH;

// The look up table. Bases greater than 10 requires alphabetical letters to represent
const char LUT[] = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

/*
 * This function is used to convert from decimal to any base
 * Parameter "input": the number in decimal base
 * Parameter "base": the target base
 * Parameter "str": pointer variable points to str[0] of char type
 */
void dec2base(int input, int base, char* str){

	// Index variable
	int i = 0;

	// If input is 0 then the only element in str is 0
	if(input == 0) {
		str[i] = LUT[input];
		i++;
	}

	else{

		// Each time through the loop, a value will be added into a corresponding spot in "str"
		while(input > 0){

			// Corresponding elements from look up table
			str[i] = LUT[input % base];

			// Assigns "input" a new value which will be used in the next loop
			input = input / base;

			// Updates index
			i++;

		}
	}

	// The length of the array
	LENGTH = i;

}

/*
 * This converts a string to its reverse order
 * Parameter "str": pointer variable that points to str[0] of character char
 * Parameter "length": length of the string
 */
void revStr(char* str, int length){

	char temp;

	// Index variable for loop
	int i;

	// length/2 is half-way of the array
	for(i = 0; i <= (length - 1)/2; i++) {

		// Using a temporary variable to reverse order of string
		temp = str[i];
		str[i] = str[length - 1 - i];
		str[length - 1 - i] = temp;

	}

	// Signals the end of array
	str[length] = '\0';

}

int main(int argc, char* argv[]) {

	// Variable declaration
	// Here "input" is of type unsigned int, max value is 4294967295
	int input;
	int base;
	char str[MAX_DIGITS];

	// Check if there are correct number of arguments
	if(argc > 3 || argc < 2){
		printf("Wrong number of arguments\n\n");
		return 0;
	}

	// Reads inputs from CLI
	sscanf(argv[1], "%d", &input);

	// Check if input entered is within bounds
	// Input values greater than 2147483647 will be converted
	// automatically to integers in range [-2147483648, 2147483647]
	if(input < 0) {
		printf("Decimal value out of bounds\n\n");
		return 0;
	}

	// If the base is also specified
	if(argc == 3) {

		sscanf(argv[2], "%d", &base);

		// Check if base entered is within bounds
		if(base < 2 || base > 36) {
			printf("Base value out of bounds\n\n");
			return 0;
		}

	}

	// If base is left blank then default base is 2
	if(argc == 2) {
		base = 2;
	}

	printf("The Base-%d form of %d is: ", base, input);

	// Calls function dec2base
	// Converts from decimal base to target base
	dec2base(input, base, str);

	// Changes the output from reverse to the correct order
	revStr(str, LENGTH);

	// Loop that prints elements of the array
	int i;
	for(i = 0; str[i] != '\0'; i++){
		printf("%c", str[i]);
	}

	// Two empty lines
	printf("\n\n");

	return 0;
}

