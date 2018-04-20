package repository;

import org.springframework.data.repository.CrudRepository;

import model.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {

}
