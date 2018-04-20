package rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enums.Role;
import model.Establishment;
import model.OfficialProp;
import model.User;
import model.UserAd;
import service.FanZoneAdminService;
import service.UserService;

@RestController
@RequestMapping("/fanZoneAdmin")
public class FanZoneAdminController {

	@Autowired
	private FanZoneAdminService fanZoneAdminService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/officialProps")
	public void createNewOfficialProp(@RequestHeader("Authorization") String userCredentials, @RequestBody OfficialProp officialProp) {
		try {
			if(userService.getUserRole(userCredentials) == Role.FAN_ZONE_ADMIN) fanZoneAdminService.newOfficialProp(officialProp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/officialProps")
	public List<OfficialProp> getAvailableProps(@RequestHeader("Authorization") String userCredentials){
		try {
			if(userService.getUserRole(userCredentials) == Role.FAN_ZONE_ADMIN) return fanZoneAdminService.getAvailableProps();
			else return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/officialProps/{propId}")
	public void deleteOfficialProp(@RequestHeader("Authorization") String userCredentials, @PathVariable int propId) {
		try {
			if(userService.getUserRole(userCredentials) == Role.FAN_ZONE_ADMIN) fanZoneAdminService.deleteOfficialProp(propId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/officialProps/{propId}")
	public void updateOfficialProp(@RequestHeader("Authorization") String userCredentials, @PathVariable int propId, @RequestBody OfficialProp officialProp) {
		try {
			if(userService.getUserRole(userCredentials) == Role.FAN_ZONE_ADMIN)	fanZoneAdminService.updateOfficialProp(propId, officialProp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/update")
	public void updatePersonalData(@RequestHeader("Authorization") String userCredentials, @RequestBody User admin) {
		try {
			if(userService.getUserRole(userCredentials) == Role.FAN_ZONE_ADMIN)	fanZoneAdminService.updatePersonalData(admin);
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	@PutMapping("/changePassword/{userId}")
	public void changePassword(@RequestHeader("Authorization") String userCredentials, @PathVariable int userId, @RequestBody String password) {
		try {
			if(userService.getUserRole(userCredentials) == Role.FAN_ZONE_ADMIN)	fanZoneAdminService.changePassword(userId, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/establishments")
	public List<Establishment> getEstablishments(@RequestHeader("Authorization") String userCredentials){
		try {
			if(userService.getUserRole(userCredentials) == Role.FAN_ZONE_ADMIN)	return fanZoneAdminService.getEstablishments();
			else return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/userAds")
	public List<UserAd> getPendingUserAds(@RequestHeader("Authorization") String userCredentials){
		try {
			if(userService.getUserRole(userCredentials) == Role.FAN_ZONE_ADMIN)	return fanZoneAdminService.getPendingUserAds();
			else return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping("/userAds/{adId}")
	public void updateAdStatus(@RequestHeader("Authorization") String userCredentials, @RequestBody UserAd userAd, @PathVariable int adId) {
		try {
			if(userService.getUserRole(userCredentials) == Role.FAN_ZONE_ADMIN)	fanZoneAdminService.updateUserAdStatus(userAd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
