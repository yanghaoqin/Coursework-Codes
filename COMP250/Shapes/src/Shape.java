
public abstract class Shape {
	private String color;
	
	public abstract double getArea(); // abstract method
	public abstract boolean equals();
	
	public Shape(String color) {
		this.color = color;
	}
	
	
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void displayInfo() {
		System.out.println("This is a " + this.color + " shape");
	}
}
