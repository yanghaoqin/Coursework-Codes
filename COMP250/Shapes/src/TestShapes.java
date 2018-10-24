
public class TestShapes {
	
	public static void main(String[] args) {
		
		Triangle s = new Triangle("green", 5, 12);
		
		Circle c = new Circle("blue", 5);
		
		Triangle t = new Triangle("red", 4, 1);
		t.setColor("red");

		Shape[] shapes = {s,c,t};
		for(int i = 0; i < shapes.length; i++) {
			shapes[i].displayInfo();
		}	
			
		/* BAD
		Shape[] shapes = {s,c,t};
		for(int i = 0; i < shapes.length; i++) {
			if(shapes[i] instanceof Triangle) {
				((Triangle)shapes[i]).displayInfo();
			} else if(shapes[i] instanceof Circle) {
				((Circle)shapes[i]).displayInfo();
			} else {
				shapes[i].displayInfo();
			}
		}
		*/	
		
		/*
		s.displayInfo();
		c.displayInfo();
		t.displayInfo();
		*/
		
		/*
		System.out.println(s.getColor());
		System.out.println(c.getRadius());
		System.out.println(c.getColor());
		*/
		
	}
}
