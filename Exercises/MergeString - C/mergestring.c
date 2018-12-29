#include <math.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <assert.h>
#include <limits.h>
#include <stdbool.h>


/*
  Merges two strings together
*/
char *mergeStrings(char *a, char *b) {
  char *output = (char *)malloc(sizeof(char) * (512000 * 2 + 1)); // output array
  int index = 0;                // index of merged string
  int len_a = strlen(a);        // length of a
  int len_b = strlen(b);        // length of b
  
  // a is longer than b, traverse b first then finish with a
  if (len_a > len_b) {
    int i = 0;                  // index of individual arrays
    
    // traverse b
    while (b[i] != '\0') {
      output[index++] = a[i];
      output[index++] = b[i];
      i++;
    }
    
    // traverse a and finish
    while (a[i] != '\0') {
      output[index++] = a[i++];
    }
    
    output[index] = '\0';           // set end of array
    return output;
  
  } else {
    // b is longer than a, traverse a first then b
    int i = 0;
    
    // traverse a
    while (a[i] != '\0') {
      output[index++] = a[i];
      output[index++] = b[i];
      i++;
    }
   
    // traverse b and finish
    while (b[i] != '\0') {
      output[index++] = b[i++];
    }
    
    output[index] = '\0';           // set end
    return output;
  }
}

int main() {
    FILE *f = fopen(getenv("OUTPUT_PATH"), "w");
    char* res;
    char* _a;
    _a = (char *)malloc(512000 * sizeof(char));
    scanf("\n%[^\n]",_a);
    
    char* _b;
    _b = (char *)malloc(512000 * sizeof(char));
    scanf("\n%[^\n]",_b);
    
    res = mergeStrings(_a, _b);
    fprintf(f, "%s\n", res);
    
    fclose(f);
    return 0;
}
