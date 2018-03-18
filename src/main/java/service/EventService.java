package service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.EventDetailsDto;
import model.Establishment;
import model.Event;
import model.EventDetails;
import model.Hall;
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

		for (Integer hallId : eventDetailsDto.getHalls()) {
			LOGGER.info("Searching projections for event with id " + event.getId() + " in hall with id " + hallId);
			List<EventDetails> projectionsInHall = eventDetailsRepository.getAllProjectionsForEventInHall(event, hallId);
			for (EventDetails projection : projectionsInHall) {
				boolean isAvailable = checkHallAvailability(projection.getDateAndTime(), eventDetailsDto.getDate(), event.getDuration());
				if (!isAvailable) {
					LOGGER.error("Hall with id " + hallId + " is not available at the requested time.");
					return null;
				}
			}
		}
		LOGGER.info("All halls are available at the requested time.");
		return createProjection(event, eventDetailsDto);
	}
	
	public EventDetails removeEventDetails(Integer eventId, Integer detailsId) {
		Event event = eventRepository.findOne(eventId);
		if (event == null) 
			return null;
		
		EventDetails eventDetails = eventDetailsRepository.findOne(detailsId);
		if (eventDetails == null)
			return null;
		
		event.getSchedule().remove(eventDetails);
		eventRepository.save(event);
		return eventDetails;
	}


	private boolean checkHallAvailability(Date oldProjectionTime, Date newProjectionTime, Integer duration) {
		LOGGER.info("Checking if hall is available at the specified time.");
		Long differenceBetween = Math.abs(oldProjectionTime.getTime() - newProjectionTime.getTime());
		Long differenceBetweenInMinutes = TimeUnit.MILLISECONDS.toMinutes(differenceBetween);
		if (differenceBetweenInMinutes > duration) {
			return true;
		}
		return false;
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
			}
		}
		
		return eventDetailsRepository.save(eventDetails);
	}

}
