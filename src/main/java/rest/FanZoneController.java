package rest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import model.Notification;
import model.OfficialProp;
import model.Prop;
import model.UsedProp;
import model.UserAd;
import service.FanZoneService;

@RestController
@RequestMapping("/fanZone")
public class FanZoneController {

	@Autowired
	private FanZoneService fanZoneService;
	
	@GetMapping("/newProps")
	public List<OfficialProp> getAllProps() {
		try {
			return fanZoneService.getAllProps();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	@GetMapping("/usedProps")
	public List<UsedProp> getAllUsedProps(){
		try {
			return fanZoneService.getAllUsedProps();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/newProps/{propId}")
	public void reserveNewProp(@PathVariable int propId, @RequestBody String email) {
		try {
			fanZoneService.reserveNewProp(propId, email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/userAds")
	public void addNewUserAd(@RequestBody UserAd userAd) {
		try {
			fanZoneService.addNewUserAd(userAd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/userAds")
	public List<UserAd> getActiveUserAds(){
		try {
			return fanZoneService.getActiveUserAds();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/userAds/{id}")
	public UserAd getActiveUserAdsByUser(@PathVariable int id){
		try {
			return fanZoneService.getUserAd(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/userAds/{adId}/offers/{offerId}")
	public void deleteAdOffer(@PathVariable int adId, @PathVariable int offerId) {
		try {
			fanZoneService.deleteOffer(adId, offerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/userAds/{adId}/users/{userId}/offers/{offerId}")
	public void placeAdOffer(@PathVariable int adId, @PathVariable int offerId, @PathVariable int userId, @RequestBody int offeredSum) {
		try {
			fanZoneService.placeOffer(adId, offerId, offeredSum, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/userAds/{adId}/offers/{offerId}")
	public void acceptOffer(@PathVariable int adId, @PathVariable int offerId) {
		try {
			fanZoneService.acceptOffer(adId, offerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/users/{userId}/notifications")
	public List<Notification> getUsersNotifications(@PathVariable int userId){
		try {
			return fanZoneService.getUsersNotifications(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
