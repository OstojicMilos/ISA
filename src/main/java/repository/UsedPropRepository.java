package repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.UsedProp;

public interface UsedPropRepository extends CrudRepository<UsedProp, Integer>{

}
