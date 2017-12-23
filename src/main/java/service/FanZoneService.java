package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Prop;
import model.UsedProp;
import model.User;
import repository.PropRepository;
import repository.UsedPropRepository;

@Service
public class FanZoneService {

	@Autowired
	private UsedPropRepository usedPropRepository;
	@Autowired
	private PropRepository propRepository;
	
	public List<UsedProp> getAllUsedProps(){
		return (List<UsedProp>)usedPropRepository.findAll();
	}
	
	public void addUsedProp(UsedProp usedProp) {
		usedPropRepository.save(usedProp);
	}
	
	public List<Prop> getAllProps(){
		return (List<Prop>)propRepository.findAll();
	}
	
	public void addNewProp(Prop prop) {
		propRepository.save(prop);
	}

	
}
