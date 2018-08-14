package domain.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import db.DbManager;

public class ReservationDAOImpl implements ReservationDAO{

	public HashMap<Integer, Reservation> getCurrentReservations(Student student){
		// TODO Auto-generated method stub
		DbManager db = new DbManager();
		Connection conn = db.getConnection();;
		PreparedStatement ps1;
		PreparedStatement ps2;
		PreparedStatement ps3;

		try {
			//ArrayList<Integer> currRegIds = new ArrayList<>();
			HashMap<Integer,Float> reservations = new HashMap<>();
			HashMap<Integer, Reservation> pastReservations = new HashMap<>();

			//List<Integer> status = new ArrayList<>();

			ps1 =conn.prepareStatement("select * from registration where Email = ?");
			ps1.setString(1, student.getEmail());


			ResultSet regs = (ps1.executeQuery());
			while(regs.next()){
				reservations.put(regs.getInt(1), regs.getFloat(3));
			}


			for(int key : reservations.keySet()){

				Cart cart = new Cart();
				ArrayList<Event> events = new ArrayList<>();
				ps2 = conn.prepareStatement("select EventID from checkoutlist where RegID = ?");
				ps2.setString(1, Integer.toString(key));
				ResultSet rs1 = ps2.executeQuery();
				while(rs1.next()){
					//System.out.println(rs1.getInt(1));
					Event currEvent = new Event();
					int EventId = rs1.getInt(1);
					ps3 = conn.prepareStatement("select * from Events where EventId = ?");
					ps3.setString(1, Integer.toString(EventId));
					ResultSet rs2 = ps3.executeQuery();
					while(rs2.next()){
						currEvent.setEventId(EventId);
						currEvent.setTopic(rs2.getString(2));
						currEvent.setEventType(rs2.getString(3));
						currEvent.setEventDate(rs2.getDate(4));
						currEvent.setLocation(rs2.getString(5));
						currEvent.setPrice(rs2.getFloat(6));
						currEvent.setEventTime(rs2.getString(7));
						currEvent.setDescription(rs2.getString(8));
					}
					events.add(currEvent);
				}
				cart.setEventsSelected(events);
				cart.setTotalCost(reservations.get(key));
				Reservation res = new Reservation(student,cart);
				pastReservations.put(key,res);

			}



			conn.close();

			return pastReservations;


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}



	}

	@Override
	public void modifyReservation(int regId, Event event, double total) {
		DbManager db = new DbManager();
		Connection conn = db.getConnection();;
		PreparedStatement ps1;
		PreparedStatement ps2;
		PreparedStatement ps3;
		PreparedStatement ps4;
		try{
			ps1 = conn.prepareStatement("delete from checkoutlist where RegID = ? and EventID = ?");
			ps1.setString(1, Integer.toString(regId));
			ps1.setString(2, Integer.toString(event.getEventId()));
			ps1.executeUpdate();
			int countOfEventsForRegId = 0;
			ps2 = conn.prepareStatement("select count(RegId) from checkoutlist where RegId = ?");
			ps2.setString(1, Integer.toString(regId));
			ResultSet rs2 = ps2.executeQuery();
			while(rs2.next()){
				countOfEventsForRegId = Integer.parseInt(rs2.getString(1));
			}
			if(countOfEventsForRegId==0){
				ps3 = conn.prepareStatement("delete from registration where RegId = ?");
				ps3.setString(1, Integer.toString(regId));
				ps3.executeUpdate();
			}else{
				double newTotal = total - event.getPrice();
				ps4 = conn.prepareStatement("update registration set Total = ? where RegId = ?");
				ps4.setString(1, Double.toString(newTotal));
				ps4.setString(2, Integer.toString(regId));
				ps4.executeUpdate();
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
