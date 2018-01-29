package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.Friendship;
import model.User;

public interface FriendshipRepository extends CrudRepository<Friendship, Integer> {

		List<Friendship> findByFirstUser(User user);
		List<Friendship> findBySecondUser(User user);
		List<Friendship> findByFirstUserOrSecondUser(User first, User second);
		Friendship findByFirstUserAndSecondUser(User first, User second);
		
}
