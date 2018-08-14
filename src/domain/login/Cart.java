package domain.login;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	public List<Event> eventsSelected = new ArrayList<>();
	public float totalCost;
	
	public List<Event> getEventsSelected() {
		return eventsSelected;
	}
	public void setEventsSelected(List<Event> eventsSelected) {
		this.eventsSelected = eventsSelected;
	}
	public float getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	
	

}
