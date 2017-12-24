package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.OfficialProp;

public interface OfficialPropRepository extends CrudRepository<OfficialProp, Integer>{

	public List<OfficialProp> findByAvailable(boolean available);
}
