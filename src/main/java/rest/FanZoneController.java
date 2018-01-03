package rest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import model.Prop;
import model.UsedProp;
import service.FanZoneService;

@RestController
@RequestMapping("/fanZone")
public class FanZoneController {

	@Autowired
	private FanZoneService fanZoneService;
	
	@GetMapping
	public List<Prop> getAllProps() {
		return fanZoneService.getAllProps();
	}
	
	@GetMapping("/usedProps")
	public List<UsedProp> getAllUsedProps(){
		return fanZoneService.getAllUsedProps();
	}
	
	@PostMapping
	public void AddNewProp(Prop prop) {
		fanZoneService.addNewProp(prop);
	}
	
	@PostMapping("/usedProps")
	public void AddUsedProp(UsedProp usedProp) {
		fanZoneService.addUsedProp(usedProp);
	}
}
