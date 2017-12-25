package repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import enums.AdStatus;
import model.UserAd;

public interface UserAdRepository extends CrudRepository<UserAd, Integer>{

	@Query("SELECT ad " + 
		   "FROM UserAd ad " + 
		   "WHERE ad.sold = false AND ad.adStatus = 1 AND CURRENT_DATE < ad.availableUntil")
	public List<UserAd> findActiveAds();
	
	public List<UserAd> findByAdStatus(AdStatus adStatus);
}
