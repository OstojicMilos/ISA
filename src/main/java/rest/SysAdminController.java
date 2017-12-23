package rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enums.Role;
import model.Establishment;
import model.User;
import service.SysAdminService;

@RestController
@RequestMapping("/sysAdmin")
public class SysAdminController {

	@Autowired
	private SysAdminService sysAdminService;
	
	@PostMapping("/newAdmin")
	public void newAdmin(@RequestBody User admin) {
		try {
			sysAdminService.newAdmin(admin);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/newCinema")
	public void newCinema(@RequestBody Establishment cinema) {
		try {
			sysAdminService.newCinema(cinema);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/newTheatre")
	public void newTheatre(@RequestBody Establishment theatre) {
		try {
			sysAdminService.newTheatre(theatre);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/users/establishmentAdmins")
	public List<User> getEstablishmentAdmins(){
		try {
			return sysAdminService.getUsersByRole(Role.ESTABLISHMENT_ADMIN);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
