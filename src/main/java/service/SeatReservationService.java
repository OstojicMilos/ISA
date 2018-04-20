package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.ReservationDto;
import model.DetailsSeat;
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

	public Reservation confirmReservation(Integer establishmentId, Integer eventId, Integer scheduleId,
			ReservationDto reservationDto) {
		
		EventDetails projection = eventDetailsRepository.findOne(scheduleId);
		if (projection == null) return null;
		
		User owner = userRepository.findOne(reservationDto.getUserId());
		
		List<User> friends = new ArrayList<>();
		for (Integer guestId: reservationDto.getGuests()) {
			User found = userRepository.findOne(guestId);
			if (found != null) {
				friends.add(found);
			}
		}
		
		List<Seat> seats = new ArrayList<>();
		for (Integer seatId: reservationDto.getSeats()) {
			Seat found = seatRepository.findOne(seatId);
			if (found != null) {
				seats.add(found);
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

}
