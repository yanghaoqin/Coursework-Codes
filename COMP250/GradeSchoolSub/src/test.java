
public class test {
	public static void main(String args[]) {
		int i = 4;
		int sum = 1;
		while(i > 0) {
			sum = sum * i;
			while(sum % 10 == 0) {
				sum = sum % 10;
				System.out.println(sum);
			}
			sum = sum - (sum/10 * 10);
			i--;
		}
	}
}
