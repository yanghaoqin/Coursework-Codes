
public class Circle extends Shape {
	private double radius; // default set to 0
	private String color = "Black";
	
	public Circle() {
		
	}
	
	public double getRadius() {
		return this.radius;
	}
	
	public double getArea() {
		return Math.PI * this.radius * this.radius;
	}
	
	// overloading
	public String getColor(int n) {
		return this.color;
	}
	
	public String getColor(String st) {
		return this.color;
	}
	// end of overloading
	
	// overriding
	
	public String getColor() {
		return this.color;
	}
	
	
}
