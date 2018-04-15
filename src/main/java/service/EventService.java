package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.EventDetailsDto;
import enums.EstablishmentType;
import enums.EventType;
import model.Establishment;
import model.Event;
import model.EventDetails;
import model.Hall;
import model.Seat;
import repository.EstablishmentRepository;
import repository.EventDetailsRepository;
import repository.EventRepository;
import repository.HallRepository;

@Service
public class EventService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);
	@Autowired
	private EstablishmentRepository establishmentRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private EventDetailsRepository eventDetailsRepository;
	@Autowired
	private HallRepository hallRepository;

	public Event createEvent(Integer establishmentId, Event event) {

		Establishment establishment = establishmentRepository.findOne(establishmentId);
		if (establishment == null) {
			return null;
		}
		event.setEstablishment(establishment);
		
		if (establishment.getType() == EstablishmentType.CINEMA) {
			event.setType(EventType.MOVIE);
		}
		else {
			event.setType(EventType.SHOW);
		}
		event.setRating(new BigDecimal(0));
		return eventRepository.save(event);
	}

	public EventDetails addEventDetails(Integer eventId, EventDetailsDto eventDetailsDto) {
		Event event = eventRepository.findOne(eventId);
		if (event == null) {
			LOGGER.error("Event with id " + eventId + " wasn't found.");
			return null;
		}

		// if there is no projections add a new one
		if (event.getSchedule().isEmpty()) {
			LOGGER.info("No previously added projections. All halls are available.");
			return createProjection(event, eventDetailsDto);
		}

		boolean areHallsAvailable = checkHallAvailability(event, eventDetailsDto);
		if (!areHallsAvailable)
			return null;

		LOGGER.info("All halls are available at the requested time.");
		return createProjection(event, eventDetailsDto);
	}

	public EventDetails updateEventDetails(Integer eventId, Integer detailsId, EventDetailsDto newDetails) {
		Event event = eventRepository.findOne(eventId);
		if (event == null) {
			LOGGER.error("Event with id " + eventId + " wasn't found.");
			return null;
		}

		EventDetails oldDetails = eventDetailsRepository.findOne(detailsId);
		if (oldDetails == null) {
			LOGGER.error("Event details with id " + detailsId + " wasn't found.");
			return null;
		}

		if (newDetails.getPrice() != null)
			oldDetails.setPrice(newDetails.getPrice());
		
		// updating only date and time of the projection
		if (newDetails.getDate() != null && newDetails.getHalls().isEmpty()) {
			for (Hall h : oldDetails.getHalls())
				newDetails.getHalls().add(h.getId());
			
			boolean areHallsAvailable = checkHallAvailability(event, newDetails);
			if (!areHallsAvailable)
				return null;
			
			oldDetails.setDateAndTime(newDetails.getDate());
		}
		// updating only halls of the projection
		else if (newDetails.getDate() == null && !newDetails.getHalls().isEmpty()) {
			newDetails.setDate(oldDetails.getDateAndTime());
			
			List<Integer> hallsBackup = new ArrayList<>(newDetails.getHalls());
			
			// filtering halls that are newly added
			List<Integer> hallsToCheck = new ArrayList<>(newDetails.getHalls());
			for (Hall h : oldDetails.getHalls()) {
				if (hallsToCheck.contains(h.getId())) {
					hallsToCheck.remove(h.getId());
				}
			}
			newDetails.setHalls(hallsToCheck);
			boolean areHallsAvailable = checkHallAvailability(event, newDetails);
			if (!areHallsAvailable)
				return null;
			
			oldDetails.getHalls().clear();
			newDetails.setHalls(hallsBackup);
			for (Integer h : newDetails.getHalls()) {
				Hall hall = hallRepository.findOne(h);
				if (hall != null) {
					oldDetails.addHall(hall);
				}
			}
		}
		// updating both date(time) and halls
		else if (newDetails.getDate() != null && !newDetails.getHalls().isEmpty()) {
			boolean areHallsAvailable = checkHallAvailability(event, newDetails);
			if (!areHallsAvailable)
				return null;
			
			oldDetails.setDateAndTime(newDetails.getDate());
			oldDetails.getHalls().clear();
			for (Integer h : newDetails.getHalls()) {
				Hall hall = hallRepository.findOne(h);
				if (hall != null) {
					oldDetails.addHall(hall);
				}
			}
		}
		return eventDetailsRepository.save(oldDetails);
	}

	public EventDetails removeEventDetails(Integer eventId, Integer detailsId) {
		Event event = eventRepository.findOne(eventId);
		if (event == null) {
			LOGGER.error("Event with id " + eventId + " wasn't found.");
			return null;
		}

		EventDetails eventDetails = eventDetailsRepository.findOne(detailsId);
		if (eventDetails == null) {
			LOGGER.error("Event details with id " + detailsId + " wasn't found.");
			return null;
		}

		event.getSchedule().remove(eventDetails);
		eventRepository.save(event);
		return eventDetails;
	}

	private boolean checkHallAvailability(Event event, EventDetailsDto newProjection) {
		for (Integer hallId : newProjection.getHalls()) {
			List<EventDetails> projectionsInHall = eventDetailsRepository.getAllProjectionsForEventInHall(event,
					hallId);

			for (EventDetails projection : projectionsInHall) {
				Long differenceBetween = Math
						.abs(projection.getDateAndTime().getTime() - newProjection.getDate().getTime());
				if (TimeUnit.MILLISECONDS.toMinutes(differenceBetween) < event.getDuration()) {
					LOGGER.error("Hall with id " + hallId + " is not available at the requested time.");
					return false;
				}
			}
		}
		return true;
	}

	private EventDetails createProjection(Event event, EventDetailsDto eventDetailsDto) {
		EventDetails eventDetails = new EventDetails();
		eventDetails.setEvent(event);
		eventDetails.setDateAndTime(eventDetailsDto.getDate());
		eventDetails.setPrice(eventDetailsDto.getPrice());

		for (Integer h : eventDetailsDto.getHalls()) {
			Hall hall = hallRepository.findOne(h);
			if (hall != null) {
				eventDetails.addHall(hall);
				for (Seat seat : hall.getSeats()) {
					eventDetails.addSeat(seat);
				}
			}
		}
		return eventDetailsRepository.save(eventDetails);
	}

}
