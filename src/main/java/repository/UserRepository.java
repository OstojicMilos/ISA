package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import enums.Role;
import model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	public List<User> findByRole(Role role);
	public User findByEmail(String email);
	public User findByConfirmationToken(String token);
	
}
