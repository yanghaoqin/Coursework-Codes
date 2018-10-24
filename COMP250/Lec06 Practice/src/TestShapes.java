
public class TestShapes {

	public static void main(String args[]) {
		
		Shape s = new Shape();
		Circle c = new Circle();
		
		s.setColor("Green");
		c.setColor("Blue"); // setColor method in circle inherited from shape
	
		System.out.println(s.getColor());
		System.out.println(c.getRadius());
		System.out.println(c.getColor());
		
		Shape shape = new Circle(); // type conversion example
		System.out.println(shape.getColor()); // method will execute
		// TestShapes t = new TestShapes();
		// t.setColor("Red"); // this is an error since not inheriting Shape class 
		
	}
	
	// Shallow copy of array, new references refer to the same object in old list
	public static Shape[] shallowCopy(Shape[] shapes) {
		Shape[] copy = new Shape[shapes.length];
		for(int i=0; i<shapes.length; i++) {
			copy[i] = shapes[i];
		}
		return copy;	
	}
	
	// This is a deep copy, creates a new list with new references
	public static Shape[] deepCopy(Shape[] shapes) {
		Shape[] copy = new Shape[shapes.length];
		for(int i=0; i<shapes.length; i++) {
			Shape copyShape = new Shape();
			copyShape.setColor(shapes[i].getColor());
			copy[i] = copyShape;
		}
		return copy;	
	}
	
	
}
