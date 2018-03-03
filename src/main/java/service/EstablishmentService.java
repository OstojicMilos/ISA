package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enums.EstablishmentType;
import model.Establishment;
import repository.EstablishmentRepository;

@Service
public class EstablishmentService {
	
	@Autowired
	EstablishmentRepository establishmentRepository;
	
	public List<Establishment> getAllCinemas() {
		return establishmentRepository.findByType(EstablishmentType.CINEMA);
	}

	public List<Establishment> getAllTheatres() {
		return establishmentRepository.findByType(EstablishmentType.THEATRE);
	}

}
