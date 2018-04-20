package rest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import enums.Role;
import model.Notification;
import model.OfficialProp;
import model.Prop;
import model.UsedProp;
import model.UserAd;
import service.FanZoneService;
import service.UserService;

@RestController
@RequestMapping("/fanZone")
public class FanZoneController {

	@Autowired
	private FanZoneService fanZoneService;
	@Autowired
	private UserService userService;
	
	@GetMapping("/newProps")
	public List<OfficialProp> getAllProps(@RequestHeader("Authorization") String userCredentials) {
		try {
			if(userService.getUserRole(userCredentials) == Role.DEFAULT) return fanZoneService.getAllProps();
			else return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	@GetMapping("/usedProps")
	public List<UsedProp> getAllUsedProps(@RequestHeader("Authorization") String userCredentials){
		try {
			if(userService.getUserRole(userCredentials) == Role.DEFAULT) return fanZoneService.getAllUsedProps();
			else return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/newProps/{propId}")
	public void reserveNewProp(@RequestHeader("Authorization") String userCredentials, @PathVariable int propId, @RequestBody String email) {
		try {
			if(userService.getUserRole(userCredentials) == Role.DEFAULT) fanZoneService.reserveNewProp(propId, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/userAds")
	public void addNewUserAd(@RequestHeader("Authorization") String userCredentials, @RequestBody UserAd userAd) {
		try {
			if(userService.getUserRole(userCredentials) == Role.DEFAULT) fanZoneService.addNewUserAd(userAd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/userAds")
	public List<UserAd> getActiveUserAds(@RequestHeader("Authorization") String userCredentials){
		try {
			if(userService.getUserRole(userCredentials) == Role.DEFAULT) return fanZoneService.getActiveUserAds();
			else return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/userAds/{id}")
	public UserAd getActiveUserAdsByUser(@RequestHeader("Authorization") String userCredentials, @PathVariable int id){
		try {
			if(userService.getUserRole(userCredentials) == Role.DEFAULT) return fanZoneService.getUserAd(id);
			else return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/userAds/{adId}/offers/{offerId}")
	public void deleteAdOffer(@RequestHeader("Authorization") String userCredentials, @PathVariable int adId, @PathVariable int offerId) {
		try {
			if(userService.getUserRole(userCredentials) == Role.DEFAULT) fanZoneService.deleteOffer(adId, offerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/userAds/{adId}/users/{userId}/offers/{offerId}")
	public void placeAdOffer(@RequestHeader("Authorization") String userCredentials, @PathVariable int adId, @PathVariable int offerId, @PathVariable int userId, @RequestBody int offeredSum) {
		try {
			if(userService.getUserRole(userCredentials) == Role.DEFAULT) fanZoneService.placeOffer(adId, offerId, offeredSum, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/userAds/{adId}/offers/{offerId}")
	public void acceptOffer(@RequestHeader("Authorization") String userCredentials, @PathVariable int adId, @PathVariable int offerId) {
		try {
			if(userService.getUserRole(userCredentials) == Role.DEFAULT) fanZoneService.acceptOffer(adId, offerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/users/{userId}/notifications")
	public List<Notification> getUsersNotifications(@RequestHeader("Authorization") String userCredentials, @PathVariable int userId){
		try {
			if(userService.getUserRole(userCredentials) == Role.DEFAULT) return fanZoneService.getUsersNotifications(userId);
			else return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
