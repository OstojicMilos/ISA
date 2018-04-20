package dto;

import model.Event;
import model.Reservation;

public class ReservationViewDto {

	private Reservation reservation;
	private Event event;
	
	
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
