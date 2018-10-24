public class charRS {

	public static char charRightShift(char a, int n) {
		
		if(a >= 'a' && a <= 'z') {
			return (char)(a + n);
		}
		else {
			return a;
		}
	}
	
	public static void main(String args[]) {
		
		char a = '#';
		int n = 2;
		
		char out = charRightShift(a, n);
		
		System.out.println(out);
	}
	
}
