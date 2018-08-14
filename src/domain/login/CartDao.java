package domain.login;

import java.util.List;

public interface CartDao {

	public int getEventsCount();
	
	public void deleteCartItem(Event eventToBeDeleted);
	
	public void addCartItem(Event eventToBeAdded);
	
	public List<Event> getCartItems();
	
	public void calculateOrderTotal();
	
	public float getOrderTotal();

	public boolean checkout(Reservation res);
}
