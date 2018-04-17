package rest;

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
import model.Hall;
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
	
	@RequestMapping(method = RequestMethod.GET, value = "establishments/{id}/events")
	public ResponseEntity<?> getEvents(@PathVariable Integer id) {
		List<Event> events = establishmentService.getEvents(id);
		if (events == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(events);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "establishments/{id}/halls")
	public ResponseEntity<?> getHalls(@PathVariable Integer id) {
		List<Hall> halls = establishmentService.getHalls(id);
		if (halls == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(halls);
	}
	
}
