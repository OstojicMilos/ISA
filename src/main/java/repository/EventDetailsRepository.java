package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import model.Event;
import model.EventDetails;

public interface EventDetailsRepository extends CrudRepository<EventDetails, Integer> {
	
	@Query("SELECT ed FROM EventDetails ed " + 
			"INNER JOIN ed.halls eh " +
			"WHERE ed.event = :event AND eh.id = :hallId")
	List<EventDetails> getAllProjectionsForEventInHall(@Param("event")Event event, @Param("hallId")Integer hallId);
	
}
