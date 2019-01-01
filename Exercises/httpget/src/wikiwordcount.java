/*
 * Author: Raymond Yang
 * Date: 20190101
 */

import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class wikiwordcount {
	
	// retrieves topic page from wikipedia
	public static String httpget(String topic) throws Exception {

		// the URL
		URL url = new URL("https://en.wikipedia.org/w/api.php?action=parse&section=0&prop=text&format=json&page=" + topic);

		// connection with API Server
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("GET");	// HTTP GET method
		
		// process data to string
		BufferedReader rd = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line;
		StringBuffer strbf = new StringBuffer();	// store output string
		while((line = rd.readLine()) != null) {
			strbf.append(line);		// builds string
		}
		rd.close();					// terminate reader
		
		return strbf.toString();
	}
	
	// counts the number of occurrences of a substring in a string
	public static int countstr(String str, String topic) {

		// case insensitive
		str = str.toLowerCase();
		topic = topic.toLowerCase();
		
		int num = str.split(topic).length - 1;		// number of delimiters = number of parsed substrings - 1
		if(num < 0) {	// if array of string is empty
			return 0;
		}
		return num;
	}
	
	public static void main(String args[]) {
		
		System.out.println("Enter keyword: ");
		Scanner scan = new Scanner(System.in);		// read input stream
		String topic = scan.next();					// the keyword
		
		// try to send http get request
		// if successful, parse string with the topic as delimiter
		try {
			String text = httpget(topic);		// string of returned json object
			int count  = countstr(text, topic);		// counts number of occurrences		
			System.out.println("The keyword \"" + topic + "\" appeared " + count + " times.");
			
		} catch (Exception e) {
			System.out.println("Unsuccessful");		// if get request is unsuccessful
		}
		
	}
	
}
