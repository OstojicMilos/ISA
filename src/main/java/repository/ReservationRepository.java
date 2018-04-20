package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import model.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
	
	@Query("SELECT res FROM Reservation res " + 
			"INNER JOIN res.projection p " +
			"INNER JOIN p.event e " + 
			"INNER JOIN e.establishment est " +
			"WHERE est.id = :establishmentId")
	List<Reservation> getReservationsForEstablishment(@Param("establishmentId")Integer establishmentId);
	
}
