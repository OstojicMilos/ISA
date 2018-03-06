package repository;

import org.springframework.data.repository.CrudRepository;

import model.Event;

public interface EventRepository extends CrudRepository<Event, Integer> {

}
