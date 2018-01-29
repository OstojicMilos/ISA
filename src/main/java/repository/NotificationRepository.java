package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {

	public List<Notification> findByOfferUserId(int userId);
}
