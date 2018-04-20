package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.ReservationDto;
import model.Event;
import model.EventDetails;
import model.Reservation;
import service.EventService;
import service.SeatReservationService;

@RestController

public class SeatReservationController {
		
	@Autowired 
	private SeatReservationService seatReservationService;
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/seatReservation/{establishmentId}/{eventId}/{scheduleId}")
	public ResponseEntity<?> getSeatReservations(@PathVariable Integer establishmentId, @PathVariable Integer eventId, @PathVariable Integer scheduleId) {
		Event foundEvent = eventService.getEvent(establishmentId, eventId);
		
		for (EventDetails s : foundEvent.getSchedule()) {
			if (s.getId() == scheduleId) {
				return ResponseEntity.ok(s);
			}
		}
		
		return ResponseEntity.badRequest().build();
	}
	
	
	@PostMapping("/confirmReservation/{scheduleId}")
	public Reservation confirmReservation(@PathVariable Integer scheduleId, @RequestBody ReservationDto reservation) {
		
		 return seatReservationService.confirmReservation(scheduleId, reservation);
	}
	
	@GetMapping("/reservations")
	public List<Reservation> getAllReservations() {
		return seatReservationService.getAllReservations();
	}
}
