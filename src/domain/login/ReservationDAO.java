package domain.login;

import java.util.ArrayList;
import java.util.HashMap;

public interface ReservationDAO {

	public HashMap<Integer, Reservation> getCurrentReservations(Student student);

	public void modifyReservation(int regId, Event event, double total);
}
