package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enums.EstablishmentType;
import model.Establishment;
import model.Event;
import repository.EstablishmentRepository;

@Service
public class EstablishmentService {
	
	@Autowired
	EstablishmentRepository establishmentRepository;
	
	public List<Establishment> getAllEstablishmentsByType(EstablishmentType type) {
		return establishmentRepository.findByType(type);
	}

	public List<Event> getEvents(Integer establishmentId) {
		Establishment e = establishmentRepository.findOne(establishmentId);
		if (e == null) return null;
		return e.getEvents();
	}
}
