package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enums.Role;
import model.Establishment;
import model.User;
import repository.EstablishmentRepository;
import repository.UserRepository;

@Service
public class SysAdminService {

	@Autowired
	private EstablishmentRepository establishmentRepository;
	@Autowired
	private UserRepository userRepository;
	
	public void newAdmin(User admin) {
		userRepository.save(admin);
	}
	
	public List<User> getUsersByRole(Role role){
		return userRepository.findByRole(role);
	}
	
	public void newCinema(Establishment cinema) {
		establishmentRepository.save(cinema);
	}
	
	public void newTheatre(Establishment theatre) {
		establishmentRepository.save(theatre);
	}
	
}
