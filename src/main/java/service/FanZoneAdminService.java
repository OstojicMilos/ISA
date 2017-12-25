package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enums.AdStatus;
import model.Establishment;
import model.OfficialProp;
import model.User;
import model.UserAd;
import repository.EstablishmentRepository;
import repository.OfficialPropRepository;
import repository.UserAdRepository;

@Service
public class FanZoneAdminService {

	@Autowired
	private EstablishmentRepository establishmentRepository;
	@Autowired
	private OfficialPropRepository officialPropRepository;
	@Autowired
	private UserAdRepository userAdRepository;
	
	public void updatePersonalData(User admin) {
			
	}

	public List<Establishment> getEstablishments() {
		return (List<Establishment>) establishmentRepository.findAll();
	}

	public void newOfficialProp(OfficialProp officialProp) {
		officialPropRepository.save(officialProp);
	}

	public void deleteOfficialProp(int propId) {
		OfficialProp toBeDeleted = officialPropRepository.findOne(propId);
		toBeDeleted.setAvailable(false);
		officialPropRepository.save(toBeDeleted);
	}
	
	public void updateOfficialProp(int propId, OfficialProp officialProp) {
		OfficialProp toBeUpdated = officialPropRepository.findOne(propId);
		toBeUpdated.setName(officialProp.getName());
		toBeUpdated.setDescription(officialProp.getDescription());
		toBeUpdated.setPrice(officialProp.getPrice());
		toBeUpdated.setImagePath(officialProp.getImagePath());
		officialPropRepository.save(toBeUpdated);
	}
	
	public List<OfficialProp> getAvailableProps(){
		return officialPropRepository.findByAvailable(true);
	}
	
	public List<UserAd> getPendingUserAds(){
		return userAdRepository.findByAdStatus(AdStatus.PENDING);
	}
	
	public void updateUserAdStatus(UserAd userAd) {
		UserAd toBeUpdated = userAdRepository.findOne(userAd.getId());
		if(toBeUpdated.getAdStatus() == AdStatus.PENDING) {
			toBeUpdated.setAdStatus(userAd.getAdStatus());
			userAdRepository.save(toBeUpdated);
		}
	}
}
