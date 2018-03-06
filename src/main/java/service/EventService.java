package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Establishment;
import model.Event;
import repository.EstablishmentRepository;
import repository.EventRepository;

@Service
public class EventService {

	@Autowired
	private EstablishmentRepository establishmentRepository;
	@Autowired
	private EventRepository eventRepository;

	public Event createEvent(Integer establishmentId, Event event) {

		Establishment establishment = establishmentRepository.findOne(establishmentId);
		if (establishment == null) {
			return null;
		}
		event.setEstablishment(establishment);
		return eventRepository.save(event);
	}
}
