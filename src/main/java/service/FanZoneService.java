package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repository.PropRepository;

@Service
public class FanZoneService {

	@Autowired
	private PropRepository propRepository;
	
	public String test() {
		return "it works";
	}
	
}
