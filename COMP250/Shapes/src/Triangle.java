
public class Triangle extends Shape {
	private double base;
	private double height;
	
	public Triangle(String c, double b, double h) {
		super(c);
		this.base = b;
		this.height = h;
	}
	
	public double getArea() {	// must implement the abstract method
		return base*height/2;
	}
	
	public void displayInfo() {
		System.out.println("This is a " + this.getColor() + " triangle.");
	}
	
	
}
