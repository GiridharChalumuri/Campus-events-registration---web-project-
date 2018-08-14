package domain.login;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DbManager;

public class CartDaoImp implements CartDao{
	
	Cart cart;
	
	public CartDaoImp(Cart cart){
		this.cart = cart;
	}
	

	@Override
	public int getEventsCount() {
		return cart.getEventsSelected().size();
	}

	@Override
	public void deleteCartItem(Event eventToBeDeleted) {
		List<Event> chosenEvents = cart.getEventsSelected();
		chosenEvents.remove(eventToBeDeleted);
		cart.setEventsSelected(chosenEvents);
		calculateOrderTotal();
		
	}

	@Override
	public void addCartItem(Event eventToBeAdded) {
		List<Event> chosenEvents = cart.getEventsSelected();
			chosenEvents.add(eventToBeAdded);
			cart.setEventsSelected(chosenEvents);
			calculateOrderTotal();
		}

	
	
	 public List<Event> getCartItems() {
		  return cart.getEventsSelected();
	}

	@Override
	public void calculateOrderTotal() {
		float total = 0;
		if(cart.getEventsSelected().size()!=0){
			for(Event event: cart.getEventsSelected()){
				total = total + event.getPrice();
			}
		}
       // BigDecimal bd = new BigDecimal(Float.toString(total));
       // bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
       // return bd.floatValue();
		cart.setTotalCost(total);
		
	}

	@Override
	public float getOrderTotal() {
		return cart.getTotalCost();
	}


	@Override
	public boolean checkout(Reservation res) {
		// TODO Auto-generated method stub
		DbManager db = new DbManager();
		Connection conn = db.getConnection();;
		PreparedStatement ps1;
		PreparedStatement ps2;
		
		try {
			int currRegId = 0;
			List<Integer> status = new ArrayList<>();
			
			ps1 =conn.prepareStatement("insert into registration (Email,Total) values(?,?)");
			ps1.setString(1, res.getStudent().getEmail());
			ps1.setDouble(2, res.getCart().getTotalCost());
			
			status.add(ps1.executeUpdate());
			
			ps2 =conn.prepareStatement("select max(RegID) from registration where Email = ?");
			ps2.setString(1, res.getStudent().getEmail());
			ResultSet rs = ps2.executeQuery();
			while(rs.next()){
				currRegId = rs.getInt(1);
			}
			
			for(Event chosenEvent : cart.getEventsSelected()){
				PreparedStatement ps3 = conn.prepareStatement("insert into checkoutlist values (?,?)");
				ps3.setInt(1, currRegId);
				ps3.setLong(2, chosenEvent.getEventId());
				status.add(ps3.executeUpdate());
			}
			
			
			conn.close();
			
			for(int check:status){
				if(check >0)
					continue;
				else{
					return false;
				}
				
			}
			return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		
	}


}
