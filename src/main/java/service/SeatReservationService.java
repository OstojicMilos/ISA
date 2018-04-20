package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import dto.RatingDto;
import dto.ReservationDto;
import model.DetailsSeat;
import model.DiscountTicket;
import model.EventDetails;
import model.Reservation;
import model.Seat;
import model.User;
import repository.EventDetailsRepository;
import repository.ReservationRepository;
import repository.SeatRepository;
import repository.UserRepository;

@Service
public class SeatReservationService {

	@Autowired
	EventService eventService;
	
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	EventDetailsRepository eventDetailsRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	SeatRepository seatRepository;

	public Reservation confirmReservation(Integer scheduleId,ReservationDto reservationDto) {
		
		EventDetails projection = eventDetailsRepository.findOne(scheduleId);
		if (projection == null) return null;
		
		User owner = userRepository.findOne(reservationDto.getUserId());
		
		List<User> friends = new ArrayList<>();
		if(reservationDto.getGuests() != null) {
			for (Integer guestId: reservationDto.getGuests()) {
				User found = userRepository.findOne(guestId);
				if (found != null) {
					friends.add(found);
				}
			}
		}
		
		if(!friends.isEmpty()) {
			for(User friend : friends) {
				SimpleMailMessage reservationEmail = new SimpleMailMessage();
				reservationEmail.setTo(friend.getEmail());
				reservationEmail.setSubject("Pozivnica za projekciju");
				reservationEmail.setText("");
				reservationEmail.setFrom("noreply@isaproject.com");
				JavaMailSender sender = new JavaMailSenderImpl();
				sender.send(reservationEmail);
			}
		}
		
		List<Seat> seats = new ArrayList<>();
		if(reservationDto.getSeats() != null) {
			for (Integer seatId: reservationDto.getSeats()) {
				Seat found = seatRepository.findOne(seatId);
				if (found != null) {
					seats.add(found);
				}
			}
		}
		
		List<DetailsSeat> detailsSeats = projection.getSeats();
		for (DetailsSeat ds : detailsSeats) {
			for (Seat seat : seats) {
				if (ds.getSeat().getId() == seat.getId()) {
					ds.setIsAvailable(false);
				}
			}
		}
		eventDetailsRepository.save(projection);

		Reservation reservation = new Reservation();
		reservation.setAmbientRating(0);
		reservation.setEventRating(0);
		reservation.setOwner(owner);
		reservation.setProjection(projection);
		
		for (User u : friends) {
			reservation.getGuests().add(u);
		}
		for (Seat s : seats) {
			reservation.getSeats().add(s);
		}
		Reservation savedReservation = reservationRepository.save(reservation);
		return savedReservation;
	}

	public List<Reservation> getAllReservations() {
		List<Reservation> reservations = new ArrayList<>();
		reservationRepository.findAll().forEach(reservations::add);
		return reservations;
	}

	public boolean updateRating(Integer resId, RatingDto ratingDto) {
		Reservation ticket = reservationRepository.findOne(resId);

		if (ticket == null)
			return false;

		if (ratingDto.getAmbient() != null) {
			ticket.setAmbientRating(ratingDto.getAmbient());
		}
		if (ratingDto.getEvent() != null) {
			ticket.setEventRating(ratingDto.getEvent());
		}
		reservationRepository.save(ticket);
		// establishmentService.calculateEstablishmentRating(ticket.getProjection().getEvent().getEstablishment().getId());
		return true;
	}

}
