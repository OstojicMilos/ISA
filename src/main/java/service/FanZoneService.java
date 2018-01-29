package service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enums.AdStatus;
import enums.OfferStatus;
import model.Notification;
import model.OfferForUsedProp;
import model.OfficialProp;
import model.Prop;
import model.UsedProp;
import model.User;
import model.UserAd;
import repository.NotificationRepository;
import repository.OfferForUsedPropRepository;
import repository.OfficialPropRepository;
import repository.PropRepository;
import repository.UsedPropRepository;
import repository.UserAdRepository;
import repository.UserRepository;

@Service
public class FanZoneService {

	@Autowired
	private UsedPropRepository usedPropRepository;
	@Autowired
	private OfficialPropRepository officialPropRepository;
	@Autowired
	private UserAdRepository userAdRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OfferForUsedPropRepository offerForUsedPropRepository;
	@Autowired
	private NotificationRepository notificationRepository;
	
	public List<UsedProp> getAllUsedProps(){
		return (List<UsedProp>)usedPropRepository.findAll();
	}
	
	public void addNewUserAd(UserAd userAd) {
		usedPropRepository.save(userAd.getUsedProp());
		userAdRepository.save(userAd);
	}
	
	public List<OfficialProp> getAllProps(){
		return officialPropRepository.findByAvailable(true);
	}
	
	public List<UserAd> getActiveUserAds(){
		return userAdRepository.findActiveAds();
	}

	public void reserveNewProp(int propId, String email) {
		OfficialProp propToReserve = officialPropRepository.findOne(propId);
		if(propToReserve.isAvailable()) {
			User user = userRepository.findByEmail(email);
			user.getReservedProps().add(propToReserve);
			userRepository.save(user);
		}	
	}

	public UserAd getUserAd(int id) {
		return userAdRepository.findOne(id);
	}

	public void deleteOffer(int adId, int offerId) {
		UserAd userAd = userAdRepository.findOne(adId);
		for(OfferForUsedProp offer: userAd.getOffers()) {
			if(offer.getId() == offerId) {
				userAd.getOffers().remove(offer);
				userAdRepository.save(userAd);
				offerForUsedPropRepository.delete(offerId);
				
			}
		}	
	}

	public void placeOffer(int adId, int offerId, int offeredSum, int userId) {
		
		OfferForUsedProp offer = offerForUsedPropRepository.findOne(offerId);
		if(offer == null) {
			offer = new OfferForUsedProp();
			offer.setOfferedSum(offeredSum);
			offer.setOfferStatus(OfferStatus.PENDING);
			User user = userRepository.findOne(userId);
			offer.setUser(user);
			offerForUsedPropRepository.save(offer);
			UserAd userAd = userAdRepository.findOne(adId);
			userAd.getOffers().add(offer);
			userAdRepository.save(userAd);		
		}else {
			offer.setOfferedSum(offeredSum);
			offerForUsedPropRepository.save(offer);
		}
	}

	public void acceptOffer(int adId, int offerId) {
		UserAd userAd = userAdRepository.findOne(adId);
		userAd.setSold(true);
		userAdRepository.save(userAd);
		for (OfferForUsedProp offer : userAd.getOffers()) {
			Notification notification = new Notification();
			if(offer.getId() == offerId) {
				offer.setOfferStatus(OfferStatus.ACCEPTED);
			} else {
				offer.setOfferStatus(OfferStatus.REJECTED);
			}
			offerForUsedPropRepository.save(offer);
			notification.setOffer(offer);
			notification.setUserAd(userAd);
			notificationRepository.save(notification);
		}
	}

	public List<Notification> getUsersNotifications(int userId){
		return notificationRepository.findByOfferUserId(userId);
	}

	
}
