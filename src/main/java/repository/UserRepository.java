package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import enums.Role;
import model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	public List<User> findByRole(Role role);
	public User findByEmail(String email);
	public User findByConfirmationToken(String token);
	public User findByNameOrSurname(String name, String surname);
	public List<User> findByNameLike(String name);
	public List<User> findBySurnameLike(String surname);
	@Query("SELECT u.role FROM User u WHERE u.id=:userId")
	public Role getRole(@Param("userId") int userId);

}
