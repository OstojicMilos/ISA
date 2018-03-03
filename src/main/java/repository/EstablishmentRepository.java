package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import enums.EstablishmentType;
import model.Establishment;

public interface EstablishmentRepository extends CrudRepository<Establishment, Integer> {

	List<Establishment> findByType(EstablishmentType type);

}