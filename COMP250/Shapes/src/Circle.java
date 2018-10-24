
public class Circle extends Shape{
	private double radius;
	
	public boolean equals() {
		return true;
	}
	
	public Circle(String color, double r) {
		super(color);
		this.radius = r;
	}
	
	
	public double getRadius() {
		return this.radius;
	}
	
	public double getArea() {
		return Math.PI*this.radius*this.radius;
	}
	
	public void displayInfo() {
		System.out.println("This is a " + this.getColor() + " circle");
	}
	
}
