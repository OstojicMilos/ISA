package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enums.Role;
import model.Establishment;
import model.PrivilegedUserCategory;
import model.User;
import repository.EstablishmentRepository;
import repository.PrivilegedUserCategoryRepository;
import repository.UserRepository;

@Service
public class SysAdminService {

	@Autowired
	private EstablishmentRepository establishmentRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PrivilegedUserCategoryRepository privilegedUserCategoryRepository;
	
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
	
	public List<PrivilegedUserCategory> getPrivilegedUserCategories(){
		return (List<PrivilegedUserCategory>) privilegedUserCategoryRepository.findAllByOrderByPointsRequired();
	}
	
	public void addNewPrivilegedUserCategory(PrivilegedUserCategory privilegedUserCategory) {
		privilegedUserCategoryRepository.save(privilegedUserCategory);
	}
	
	public void updatePrivilegedUserCategory(PrivilegedUserCategory privilegedUserCategory) {
		PrivilegedUserCategory toBeUpdated = privilegedUserCategoryRepository.findOne(privilegedUserCategory.getId());
		toBeUpdated.setName(privilegedUserCategory.getName());
		toBeUpdated.setPointsRequired(privilegedUserCategory.getPointsRequired());
		privilegedUserCategoryRepository.save(toBeUpdated);
	}
	
}
