package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Establishment;
import model.OfficialProp;
import model.User;
import repository.EstablishmentRepository;

@Service
public class FanZoneAdminService {

	@Autowired
	private EstablishmentRepository establishmentRepository;
	
	public void updatePersonalData(User admin) {
			
	}

	public List<Establishment> getEstablishments() {
		return (List<Establishment>) establishmentRepository.findAll();
	}

	public void newOfficialProp(OfficialProp officialProp) {
		
	}

}
