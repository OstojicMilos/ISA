package repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import model.PrivilegedUserCategory;

public interface PrivilegedUserCategoryRepository extends CrudRepository<PrivilegedUserCategory, Integer>{

	public List<PrivilegedUserCategory> findAllByOrderByPointsRequired();
	
}
