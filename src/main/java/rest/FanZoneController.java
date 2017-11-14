package rest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import service.FanZoneService;

@RestController
@RequestMapping("/fanZone")
public class FanZoneController {

	@Autowired
	private FanZoneService fanZoneService;
	
	@GetMapping
	public String hello() {
		return fanZoneService.test();
	}
}
