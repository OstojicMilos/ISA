package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.DiscountTicketDto;
import model.DetailsSeat;
import model.DiscountTicket;
import model.Establishment;
import model.Event;
import model.EventDetails;
import model.Seat;
import model.User;
import repository.DiscountTicketRepository;
import repository.EstablishmentRepository;
import repository.EventDetailsRepository;
import repository.SeatRepository;
import repository.UserRepository;

@Service
public class DiscountTicketService {
	
	@Autowired 
	EventDetailsRepository eventDetailsRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SeatRepository seatRepository;
	@Autowired
	EstablishmentRepository establishmentRepository;
	@Autowired
	DiscountTicketRepository discountTicketRepository;
	
	public DiscountTicket createTicket(DiscountTicketDto ticketDto) {
		EventDetails projection = eventDetailsRepository.findOne(ticketDto.getProjectionId());
		if (projection == null) return null;
		Seat seat = seatRepository.findOne(ticketDto.getSeatId());
		if (seat == null) return null;
		
		List<DetailsSeat> seats = projection.getSeats();
		for (DetailsSeat detailsSeat : seats) {
			if (detailsSeat.getSeat().getId() == seat.getId()) {
				if (!detailsSeat.getIsAvailable()) {
					return null;
				}
				detailsSeat.setIsAvailable(false);
				eventDetailsRepository.save(projection);
				break;
			}
		}
		
		DiscountTicket discountedTicket = new DiscountTicket();
		discountedTicket.setProjection(projection);
		discountedTicket.setSeat(seat);
		discountedTicket.setDiscount(ticketDto.getDiscount());
		discountedTicket.setIsReserved(false);
		discountedTicket.setAmbintRating(0);
		discountedTicket.setEventRating(0);
		return discountTicketRepository.save(discountedTicket);
	}
	
	public List<DiscountTicket> getDiscountedTicketsByEstablishmentId(Integer establishmentId) {
		return discountTicketRepository.getDiscountedTicketsForEstablishment(establishmentId);
	}

}
