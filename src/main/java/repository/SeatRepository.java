package repository;

import org.springframework.data.repository.CrudRepository;

import model.Seat;

public interface SeatRepository extends CrudRepository<Seat, Integer> {

}
