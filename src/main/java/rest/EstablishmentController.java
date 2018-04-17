package rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import enums.EstablishmentType;
import model.Establishment;
import model.Event;
import service.EstablishmentService;

@RestController
public class EstablishmentController {
	
	@Autowired
	private EstablishmentService establishmentService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cinemas")
	public List<Establishment> getAllCinemas() {
		return establishmentService.getAllEstablishmentsByType(EstablishmentType.CINEMA);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/theatres")
	public List<Establishment> getAllTheatres() {
		return establishmentService.getAllEstablishmentsByType(EstablishmentType.THEATRE);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "establishments/{id}/{day}/events")
	public ResponseEntity<?> getCinemaEvents(@PathVariable Integer id, @PathVariable Integer day) {
		List<Event> events = new ArrayList<>();
		
		if(day < 5 && day >=0) {
			events = establishmentService.getCinemaEvents(id, day);
		}
		else {
			events = establishmentService.getCinemaEvents(id, 0);
		}
		if (events == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(events);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "establishments/{id}/events")
	public ResponseEntity<?> getTheatreEvents(@PathVariable Integer id) {
		List<Event> events = establishmentService.getTheatreEvents(id);
		if (events == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(events);
	}
	
}
