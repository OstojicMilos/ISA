package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import model.Event;
import model.EventDetails;
import service.EventService;

@RestController

public class SeatReservationController {
		
	//@Autowired 
	//private SeatReservationService seatReservationService;
	
	@Autowired
	private EventService eventService;
	
	@GetMapping("/seatReservation/{establishmentId}/{eventId}/{scheduleId}")
	public ResponseEntity<?> getSeatReservations(@PathVariable Integer establishmentId, @PathVariable Integer eventId, @PathVariable Integer scheduleId){
		Event foundEvent = eventService.getEvent(establishmentId, eventId);
		
		for (EventDetails s : foundEvent.getSchedule()) {
			if (s.getId() == scheduleId) {
				return ResponseEntity.ok(s);
			}
		}
		
		return ResponseEntity.badRequest().build();
	}
	
}
