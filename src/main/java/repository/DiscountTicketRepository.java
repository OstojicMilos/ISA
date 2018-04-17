package repository;

import org.springframework.data.repository.CrudRepository;

import model.DiscountTicket;

public interface DiscountTicketRepository extends CrudRepository<DiscountTicket, Integer> {

}
