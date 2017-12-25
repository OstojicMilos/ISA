package service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Prop;
import model.UsedProp;
import model.User;
import model.UserAd;
import repository.PropRepository;
import repository.UsedPropRepository;
import repository.UserAdRepository;

@Service
public class FanZoneService {

	@Autowired
	private UsedPropRepository usedPropRepository;
	@Autowired
	private PropRepository propRepository;
	@Autowired
	private UserAdRepository userAdRepository;
	
	public List<UsedProp> getAllUsedProps(){
		return (List<UsedProp>)usedPropRepository.findAll();
	}
	
	public void addNewUserAd(UserAd userAd) {
		usedPropRepository.save(userAd.getUsedProp());
		userAdRepository.save(userAd);
	}
	
	public List<Prop> getAllProps(){
		return (List<Prop>)propRepository.findAll();
	}
	
	public void addNewProp(Prop prop) {
		propRepository.save(prop);
	}
	
	public List<UserAd> getActiveUserAds(){
		return userAdRepository.findActiveAds();
	}

	
}
