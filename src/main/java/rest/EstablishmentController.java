package rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.EstablishmentDto;
import dto.IncomeReportDto;
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/establishments/{id}")
	public ResponseEntity<?> getEstablishment(@PathVariable Integer id) {
		Establishment e = establishmentService.getEstablishmentById(id);
		if (e == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(e);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/establishments/{id}")
	public ResponseEntity<?> updateEstablishmentInfo(@PathVariable Integer id, @RequestBody EstablishmentDto establishmentDto) {
		Establishment updatedEstablishment = establishmentService.updateEstablishment(id, establishmentDto);
		if (updatedEstablishment == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(updatedEstablishment);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cinemas/{id}/events")
	public ResponseEntity<?> getAllCinemaEvents(@PathVariable Integer id) {
		List<Event> events = establishmentService.getAllCinemaEvents(id);
		if (events == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(events);
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
	
	@RequestMapping(method = RequestMethod.GET, value = "establishments/{id}/halls")
	public ResponseEntity<?> getHalls(@PathVariable Integer id) {
		List<Hall> halls = establishmentService.getHalls(id);
		if (halls == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(halls);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "establishments/{id}/rating")
	public ResponseEntity<?> getEstablishmentRating(@PathVariable Integer id) {
		Double rating = establishmentService.calculateEstablishmentRating(id);
		if (rating == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(rating);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "establishments/{id}/income")
	public ResponseEntity<?> getEstablishmentIncome(@PathVariable Integer id, @RequestBody IncomeReportDto dto) {
		Double income = establishmentService.getEstablishmentIncome(id, dto);
		if (income == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(income);
	}
	
}
