package rest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import model.Prop;
import model.UsedProp;
import model.UserAd;
import service.FanZoneService;

@RestController
@RequestMapping("/fanZone")
public class FanZoneController {

	@Autowired
	private FanZoneService fanZoneService;
	
	@GetMapping
	public List<Prop> getAllProps() {
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
	
	@PostMapping
	public void addNewProp(Prop prop) {
		try {
			fanZoneService.addNewProp(prop);
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
}
