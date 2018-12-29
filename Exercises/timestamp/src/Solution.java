/*
 * reads a text file and capture a timestamp from each line of text
 * creates a text file with a list of all timestamps that occur multiple times, each on its own line.
 * 
 */

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Solution {
    private static final Scanner scan = new Scanner(System.in);
    
    public static void main(String args[]) throws Exception {
        // read the string filename
        String filename;
        filename = scan.nextLine();        // stores file name
        BufferedReader rd = new BufferedReader(new FileReader(filename));
        
        ArrayList<String> out = new ArrayList<String>(1000);
        ArrayList<String> output = new ArrayList<String>(1000);
        String line;
        
        while((line = rd.readLine()) != null) {     // checks if there are more lines
            
            // parse string for timestamp
            String[] str = line.split("\\[");
            line = str[1];
            str = line.split("\\]");
            line = str[0];
            str = line.split(" ");
            line = str[0];
            
            out.add(line);        // put into output array
            if(out.size() > 1) {    // check occurrence
                if((out.get(out.size() - 2)).equals(line)){
                    // occurs more than once
                    // check for existence in output array
                    if(output.size() > 0) {
                        if(output.get(output.size() - 1).equals(line)) {
                            // already exists in output
                            // don't add
                        } else {
                            output.add(line);
                        }
                    } else {
                        // add to output
                        output.add(line);
                    }
                } else {
                    // first time occurrence, do nothing
                	// empty output array if there is any
                	
                }
            } else {
                // first line, do nothing
            }
        }        
        
        // print out to string
        String outfile = "";
        for(String term: output) {
            outfile = outfile + term +"\n";
        }

        // write to file
        BufferedWriter wt = new BufferedWriter(new FileWriter("req_" + filename));
        wt.write(outfile);
        wt.close();

    }
}

