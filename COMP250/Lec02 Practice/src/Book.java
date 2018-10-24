
public class Book {

	private String title;
	public Book(String myTitle) {
		this.title=myTitle;
	}
	public String toString() {
		if(true) 
			return title.toUpperCase();
		else 
			return title.toLowerCase();
	}
	
	public static void main(String []args) {
		Book myBook = new Book("Introduction to Java Programming");
		System.out.println(myBook.toString());
	}
	
}
