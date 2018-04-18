package service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.EstablishmentDto;
import enums.EstablishmentType;
import model.Establishment;
import model.Event;
import model.Hall;
import model.EventDetails;

import repository.EstablishmentRepository;

@Service
public class EstablishmentService {
	
	@Autowired
	EstablishmentRepository establishmentRepository;
	
	public List<Establishment> getAllEstablishmentsByType(EstablishmentType type) {
		List<Establishment> temp =establishmentRepository.findByType(type);
		return temp;
	}

	public List<Event> getCinemaEvents(Integer establishmentId, Integer dayOffset) {
		Establishment e = establishmentRepository.findOne(establishmentId);
		if (e == null || e.getType() != EstablishmentType.CINEMA) return null;
		ArrayList<Event> result = new ArrayList<>();
		for(Event ev : e.getEvents()) {
			
			ArrayList<EventDetails> eventsOnGivenDay = new ArrayList<>();
			
			for(EventDetails ed : ev.getSchedule()) {
				
				Calendar day = Calendar.getInstance();
				day.add(Calendar.DATE, dayOffset);
				
				Calendar eventDay = Calendar.getInstance();
				eventDay.setTime(ed.getDateAndTime());
				
				boolean sameDay = (day.get(Calendar.YEAR) == eventDay.get(Calendar.YEAR)) &&
									(day.get(Calendar.DAY_OF_YEAR) == eventDay.get(Calendar.DAY_OF_YEAR));
	
				if (sameDay) {
					eventsOnGivenDay.add(ed);
				}
			}
			if(!eventsOnGivenDay.isEmpty()) {
				ev.setSchedule(eventsOnGivenDay);
				result.add(ev);
			}
		}
		return result;
	}
	
	public List<Event> getTheatreEvents(Integer establishmentId) {
		Establishment e = establishmentRepository.findOne(establishmentId);
		if (e == null  || e.getType() != EstablishmentType.THEATRE)  return null;
		
		return e.getEvents();
	}
	
	public List<Hall> getHalls(Integer establishmentId) {
		Establishment e = establishmentRepository.findOne(establishmentId);
		if (e == null) return null;
		return e.getHalls();
	}

	public Establishment updateEstablishment(Integer id, EstablishmentDto establishmentDto) {
		Establishment est = establishmentRepository.findOne(id);
		if (est == null)
			return null;
		
		if (establishmentDto.getName() != null)
			est.setName(establishmentDto.getName());
		
		if (establishmentDto.getAddress() != null)
			est.setAddress(establishmentDto.getAddress());
		
		if (establishmentDto.getCity() != null)
			est.setCity(establishmentDto.getCity());
		
		return establishmentRepository.save(est);
	}
	
	public Establishment getEstablishmentById(Integer id) {
		return establishmentRepository.findOne(id);
	}
}
