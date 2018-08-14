package domain.login;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Cart cart;
	CartDao cartDao;

	public CartController(){
		this.cart = new Cart();
		this.cartDao = new CartDaoImp(cart);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mode = request.getParameter("mode");
		if(mode.equals("fetch")){
			request.setAttribute("eventsInCart", cartDao.getCartItems());
			request.setAttribute("orderTotal", cartDao.getOrderTotal());
			HttpSession session = request.getSession();
			Student stu = (Student) session.getAttribute("user");
			request.setAttribute("message", "Hello"+" " + stu.getEmail());
			request.getRequestDispatcher("cart.jsp").forward(request, response);

		}else if(mode.equals("delete")){
			//String eventToBeDeleted = request.getParameter("eventToBeDeleted");
			String eventToBeDeleted = request.getParameter("eventToBeDeleted");
			List<Event> listedEvents = cartDao.getCartItems();
			for(Event event : listedEvents){
				if(event.getEventId() == Integer.parseInt(eventToBeDeleted)){
					cartDao.deleteCartItem(event);
					HttpSession session = request.getSession();
					Student stu = (Student) session.getAttribute("user");
					request.setAttribute("message", "Hello"+" " + stu.getEmail());
					request.setAttribute("eventsInCart", cartDao.getCartItems());
					request.setAttribute("orderTotal", cartDao.getOrderTotal());
					request.getRequestDispatcher("cart.jsp").forward(request, response);
					return;
				}
			}

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//Cart  cart = new Cart();
			//CartDao cartDao = new CartDaoImp(cart);
			//String selectedEventId = request.getParameter("selectedEventId");
			//Event eventSelected = (Event) request.getSession().getAttribute(selectedEventId);

			//Event eventSelected = (Event) request.getSession().getAttribute(eventChosen);

			try {
				//Event eventSelected;
				String mode = request.getParameter("mode");
				if(mode.equals("Add to cart")){
					Event eventSelected = new Event(Integer.parseInt(request.getParameter("eventId")),request.getParameter("topic"),request.getParameter("eventType"),new java.sql.Date ((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("eventDate"))).getTime()),request.getParameter("location"),Float.parseFloat(request.getParameter("price")),request.getParameter("eventTime"),request.getParameter("description"));
					List<Event> listedEvents = cartDao.getCartItems();
					System.out.println(eventSelected.getTopic());
					for(Event event : listedEvents){
						System.out.println(event.getTopic());
					}
					
					if(listedEvents.size()!=0){
						for(Event event : listedEvents){
							if(event.getEventId() == eventSelected.getEventId()){
								request.setAttribute("cannotBeAdded","Event already into the cart");
								request.setAttribute("eventsInCart", cartDao.getCartItems());
								request.setAttribute("orderTotal", cartDao.getOrderTotal());
								request.getRequestDispatcher("cart.jsp").forward(request, response);
								return;
							}
							
						}
					}
					
					cartDao.addCartItem(eventSelected);
					
					//cartDao.calculateOrderTotal();
					HttpSession session = request.getSession();
					Student stu = (Student) session.getAttribute("user");
					request.setAttribute("message", "Hello"+" " + stu.getEmail());
					request.setAttribute("eventsInCart", cartDao.getCartItems());
					request.setAttribute("orderTotal", cartDao.getOrderTotal());

					request.getRequestDispatcher("cart.jsp").forward(request, response);
				}else if(mode.equals("Checkout")){
					HttpSession session = request.getSession();
					Student studentLoggedIn = (Student) session.getAttribute("user");
					Reservation reservation = new Reservation(studentLoggedIn,cart);
					ReservationDAO reservations = new ReservationDAOImpl();
					HashMap<Integer, Reservation> existingReservation= reservations.getCurrentReservations(studentLoggedIn);
					List<Event> eventSelected = cart.getEventsSelected();
					ArrayList<Event> copySelectedReg = new ArrayList<>(eventSelected);
					for(int regId : existingReservation.keySet()){
						List<Event> eventsRegistered = existingReservation.get(regId).getCart().getEventsSelected();

						//copySelectedReg.removeAll(eventsRegistered);
						for(Event regi : eventsRegistered){
							Iterator<Event> j = copySelectedReg.iterator();
							
							while(j.hasNext()){
								String title = j.next().getTopic();
								if(title.equals(regi.getTopic())){
								j.remove();}
						}}
						
						
						for(Event e1 : copySelectedReg){
							System.out.println("new modified: "+e1.getTopic());
						}
					}
					
					cart.setEventsSelected(copySelectedReg);
					
					boolean status = cartDao.checkout(reservation);

					if(status){
						cart.setEventsSelected(new ArrayList<Event>()); 
						cart.setTotalCost(0);
						
						request.setAttribute("message", "Hello"+" " + studentLoggedIn.getEmail());
						request.getRequestDispatcher("thankyou.jsp").forward(request, response);
					}
					else{
						request.setAttribute("message", "Hello"+" " + studentLoggedIn.getEmail());
						request.setAttribute("errormessage","Transaction invalid");
						request.getRequestDispatcher("thankyou.jsp").forward(request, response);
					}

				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			//eventSelected = new Event(111,"FoodBall Match","Sports",new java.sql.Date ((new SimpleDateFormat("yyyy/MM/dd").parse("2018/11/11")).getTime()),"UTD",(float) 0.00,"6:00pm","Match");




		}
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}


