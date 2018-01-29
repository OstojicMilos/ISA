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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Establishment;
import model.OfficialProp;
import model.User;
import model.UserAd;
import service.FanZoneAdminService;

@RestController
@RequestMapping("/fanZoneAdmin")
public class FanZoneAdminController {

	@Autowired
	private FanZoneAdminService fanZoneAdminService;
	
	@PostMapping("/officialProps")
	public void createNewOfficialProp(@RequestBody OfficialProp officialProp) {
		try {
			fanZoneAdminService.newOfficialProp(officialProp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/officialProps")
	public List<OfficialProp> getAvailableProps(){
		try {
			return fanZoneAdminService.getAvailableProps();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@DeleteMapping("/officialProps/{propId}")
	public void deleteOfficialProp(@PathVariable int propId) {
		try {
			fanZoneAdminService.deleteOfficialProp(propId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/officialProps/{propId}")
	public void updateOfficialProp(@PathVariable int propId, @RequestBody OfficialProp officialProp) {
		try {
			fanZoneAdminService.updateOfficialProp(propId, officialProp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PutMapping("/update")
	public void updatePersonalData(@RequestBody User admin) {
		try {
			fanZoneAdminService.updatePersonalData(admin);
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	@PutMapping("/changePassword/{userId}")
	public void changePassword(@PathVariable int userId, @RequestBody String password) {
		try {
			fanZoneAdminService.changePassword(userId, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/establishments")
	public List<Establishment> getEstablishments(){
		try {
			return fanZoneAdminService.getEstablishments();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/userAds")
	public List<UserAd> getPendingUserAds(){
		try {
			return fanZoneAdminService.getPendingUserAds();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping("/userAds/{adId}")
	public void updateAdStatus(@RequestBody UserAd userAd, @PathVariable int adId) {
		try {
			fanZoneAdminService.updateUserAdStatus(userAd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
