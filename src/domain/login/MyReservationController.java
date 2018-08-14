package domain.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/MyReservationController")
public class MyReservationController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3974127543421115054L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mode = request.getParameter("mode");
		HttpSession session = request.getSession();
		Student stu = (Student) session.getAttribute("user");
		
		ReservationDAO resDao = new ReservationDAOImpl();
		HashMap<Integer, Reservation> pastReservations = resDao.getCurrentReservations(stu);
		
		if(mode.equals("cancel")){
			String eventToBeCancelled = request.getParameter("eventToBeCancelled");
			for(int regId : pastReservations.keySet()){
				Reservation resSelected = pastReservations.get(regId);
				for(Event eventsInReservation:resSelected.getCart().getEventsSelected()){
					if(eventsInReservation.getEventId()==Integer.parseInt(eventToBeCancelled)){
						resDao.modifyReservation(regId,eventsInReservation,resSelected.getCart().getTotalCost());
					}
				}
			}
			HashMap<Integer, Reservation> pastnewReservations = resDao.getCurrentReservations(stu);
			List<Reservation> pastReserves = new ArrayList<>();
			for(int resID:pastnewReservations.keySet()){
				pastReserves.add(pastnewReservations.get(resID));
			}
			
			List<Event> pastAllReservations = new ArrayList<>();
			for(Reservation res: pastReserves){
				List<Event> pastEventsRegistered = res.getCart().getEventsSelected();
				pastAllReservations.addAll(pastEventsRegistered);
			}
			request.setAttribute("message", "Hello"+" " + stu.getEmail());
			request.setAttribute("pastEvents", pastAllReservations);
			request.getRequestDispatcher("myreservations.jsp").forward(request, response);
			
		}
		if(mode.equals("fetch")){
			
			
			List<Reservation> pastReserves = new ArrayList<>();
			for(int resID:pastReservations.keySet()){
				pastReserves.add(pastReservations.get(resID));
			}
			
			List<Event> pastAllReservations = new ArrayList<>();
			for(Reservation res: pastReserves){
				List<Event> pastEventsRegistered = res.getCart().getEventsSelected();
				pastAllReservations.addAll(pastEventsRegistered);
			}
			request.setAttribute("message", "Hello"+" " + stu.getEmail());
			request.setAttribute("pastEvents", pastAllReservations);
			request.getRequestDispatcher("myreservations.jsp").forward(request, response);
		}
	}
}
