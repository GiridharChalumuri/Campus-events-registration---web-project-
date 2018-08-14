package domain.login;

public class Reservation {
	private Student student;
	private Cart cart;
	
	public Reservation(Student student, Cart cart) {
		super();
		this.student = student;
		this.cart = cart;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
