package rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.DiscountTicketDto;
import dto.RatingDto;
import dto.ReservationDto;
import model.DiscountTicket;
import service.DiscountTicketService;

@RestController
public class DiscoutTicketController {

	@Autowired
	DiscountTicketService discountTicketService;

	@RequestMapping(method = RequestMethod.GET, value = "/establishments/{id}/discountedtickets")
	public Map<String, List<DiscountTicket>> getDiscountedTicketsForEstablishment(@PathVariable Integer id) {
		List<DiscountTicket> tickets = discountTicketService.getDiscountedTicketsByEstablishmentId(id);
		if (tickets.isEmpty()) {
			return new HashMap<>();
		}
		Map<String, List<DiscountTicket>> map = new HashMap<>();
		for (DiscountTicket discountTicket : tickets) {
			if (!discountTicket.getIsReserved()) {
				String key = discountTicket.getProjection().getEvent().getName();
				List<DiscountTicket> list = map.get(key);
				if (list == null) {
					map.put(key, list = new ArrayList<>());
				}
				list.add(discountTicket);
			}
		}
		return map;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/discountedtickets")
	public ResponseEntity<?> addDiscountedTicket(@RequestBody DiscountTicketDto ticketDto) {
		DiscountTicket ticket = discountTicketService.createTicket(ticketDto);
		if (ticket == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(ticket);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/discountedtickets/{ticketId}")
	public ResponseEntity<?> reserveDiscountedTicket(@PathVariable Integer ticketId,
			@RequestBody ReservationDto reservationDto) {
		boolean isReserved = discountTicketService.reserveTicket(ticketId, reservationDto);
		if (!isReserved) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/discountedtickets/{ticketId}")
	public ResponseEntity<?> deleteDiscountedTicket(@PathVariable Integer ticketId) {
		boolean isDeleted = discountTicketService.deleteTicket(ticketId);
		if (!isDeleted) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/discountedtickets/{ticketId}/rating")
	public ResponseEntity<?> updateRating(@PathVariable Integer ticketId, @RequestBody RatingDto dto) {
		boolean isUpdated = discountTicketService.updateRating(ticketId, dto);
		if (!isUpdated) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().build();
	}
}
