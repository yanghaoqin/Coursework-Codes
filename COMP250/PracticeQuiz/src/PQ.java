
public class PQ {
	
	private obj object = new obj();
	
	public static void main(String args[]) {
		changeNum(object);
		System.out.println(object.getNum());
		
	}
	
	public static void changeNum(obj object) {
		object.setnum(100);
	}
	
}