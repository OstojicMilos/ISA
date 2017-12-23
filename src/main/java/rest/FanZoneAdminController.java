package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import model.Establishment;
import model.OfficialProp;
import model.User;
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
	
	@PutMapping("/update")
	public void updatePersonalData(@RequestBody User admin) {
		try {
			fanZoneAdminService.updatePersonalData(admin);
		}catch(Exception e) {
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
}
