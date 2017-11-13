package rest;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fanZone")
public class FanZoneController {

	@RequestMapping(method=RequestMethod.GET)
	public String hello() {
		return "hello";
	}
}
