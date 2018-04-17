package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.DiscountTicketDto;
import model.DiscountTicket;
import service.DiscountTicketService;

@RestController
public class DiscoutTicketController {
	
	@Autowired
	DiscountTicketService discountTicketService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/establishment/{id}/discounted")
	public List<DiscountTicket> getDiscountedTicketsForEstablishment(@PathVariable Integer id) {
		return discountTicketService.getDiscountedTicketsByEstablishmentId(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/discountedtickets")
	public ResponseEntity<?> addDiscountedTicket(@RequestBody DiscountTicketDto ticketDto) {
		DiscountTicket ticket = discountTicketService.createTicket(ticketDto);
		if (ticket == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(ticket);
	}
}
