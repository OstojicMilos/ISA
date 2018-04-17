package repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import model.DiscountTicket;

public interface DiscountTicketRepository extends CrudRepository<DiscountTicket, Integer> {
	
	@Query("SELECT dt FROM DiscountTicket dt " + 
			"INNER JOIN dt.projection p " +
			"INNER JOIN p.event e " + 
			"INNER JOIN e.establishment est " +
			"WHERE est.id = :establishmentId")
	List<DiscountTicket> getDiscountedTicketsForEstablishment(@Param("establishmentId")Integer establishmentId);
}
