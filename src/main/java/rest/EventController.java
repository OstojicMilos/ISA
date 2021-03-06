package rest;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dto.EventDetailsDto;
import model.Event;
import model.EventDetails;
import service.EventService;

@RestController
public class EventController {

	@Autowired
	private EventService eventService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/establishments/{establishmentId}/events/{eventId}")
	public ResponseEntity<?> getEvent(@PathVariable Integer establishmentId, @PathVariable Integer eventId) {
		Event foundEvent = eventService.getEvent(establishmentId, eventId);
		if (foundEvent == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(foundEvent);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/establishments/{establishmentId}/events")
	public ResponseEntity<?> createEvent(@PathVariable Integer establishmentId, @RequestBody Event event) {
		Event createdEvent = eventService.createEvent(establishmentId, event);
		if (createdEvent == null)
			return ResponseEntity.notFound().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdEvent.getId()).toUri();
		return ResponseEntity.created(location).body(createdEvent);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/establishments/{establishmentId}/events/{eventId}")
	public ResponseEntity<?> updateEvent(@PathVariable Integer establishmentId, @PathVariable Integer eventId,
			@RequestBody Event event) {
		Event updatedEvent = eventService.updateEvent(establishmentId, eventId, event);
		if (updatedEvent == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(updatedEvent);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/establishments/{establishmentId}/events/{eventId}")
	public ResponseEntity<?> deleteEvent(@PathVariable Integer establishmentId, @PathVariable Integer eventId) {
		boolean isDeleted = eventService.deleteEvent(establishmentId, eventId);
		if (!isDeleted) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/events/{eventId}/details")
	public ResponseEntity<?> addEventDetails(@PathVariable Integer eventId, @RequestBody EventDetailsDto eventDetails) {
		EventDetails addedDetails = eventService.addEventDetails(eventId, eventDetails);
		if (addedDetails == null)
			return ResponseEntity.badRequest().build();

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(addedDetails.getId()).toUri();
		return ResponseEntity.created(location).body(addedDetails);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/events/{eventId}/details/{detailsId}")
	public ResponseEntity<?> updateEventDetails(@PathVariable Integer eventId, @PathVariable Integer detailsId,
			@RequestBody EventDetailsDto newDetails) {

		EventDetails updatedDetails = eventService.updateEventDetails(eventId, detailsId, newDetails);
		if (updatedDetails == null)
			return ResponseEntity.badRequest().build();

		return ResponseEntity.ok(updatedDetails);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/events/{eventId}/details/{detailsId}")
	public ResponseEntity<?> removeEventDetails(@PathVariable Integer eventId, @PathVariable Integer detailsId) {
		EventDetails removedDetails = eventService.removeEventDetails(eventId, detailsId);
		if (removedDetails == null)
			return ResponseEntity.badRequest().build();

		return ResponseEntity.ok(removedDetails);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/events/{eventId}/rating")
	public ResponseEntity<?> getEventRating(@PathVariable Integer eventId) {
		Double rating = eventService.calculateEventRating(eventId);
		if (rating == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(rating);
	}
}
