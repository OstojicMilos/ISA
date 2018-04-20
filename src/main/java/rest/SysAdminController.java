package rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import enums.Role;
import model.Establishment;
import model.PrivilegedUserCategory;
import model.User;
import service.SysAdminService;
import service.UserService;

@RestController
@RequestMapping("/sysAdmin")
public class SysAdminController {

	@Autowired
	private SysAdminService sysAdminService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/newAdmin")
	public void newAdmin(@RequestHeader("Authorization") String userCredentials, @RequestBody User admin) {
		try {
			if(userService.getUserRole(userCredentials) == Role.SYS_ADMIN) sysAdminService.newAdmin(admin);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/newCinema")
	public void newCinema(@RequestHeader("Authorization") String userCredentials, @RequestBody Establishment cinema) {
		try {
			if(userService.getUserRole(userCredentials) == Role.SYS_ADMIN) sysAdminService.newCinema(cinema);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/newTheatre")
	public void newTheatre(@RequestHeader("Authorization") String userCredentials, @RequestBody Establishment theatre) {
		try {
			if(userService.getUserRole(userCredentials) == Role.SYS_ADMIN) sysAdminService.newTheatre(theatre);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("/users/establishmentAdmins")
	public List<User> getEstablishmentAdmins(@RequestHeader("Authorization") String userCredentials){
		try {
			if(userService.getUserRole(userCredentials) == Role.SYS_ADMIN) return sysAdminService.getUsersByRole(Role.ESTABLISHMENT_ADMIN);
			else return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/privilegedUserCategories")
	public List<PrivilegedUserCategory> getPrivilegedUserCategories(@RequestHeader("Authorization") String userCredentials){
		try {
			if(userService.getUserRole(userCredentials) == Role.SYS_ADMIN) return sysAdminService.getPrivilegedUserCategories();
			else return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/privilegedUserCategories")
	public void addNewUserCategory(@RequestHeader("Authorization") String userCredentials, @RequestBody PrivilegedUserCategory privilegedUserCategory) {
		try {
			if(userService.getUserRole(userCredentials) == Role.SYS_ADMIN) sysAdminService.addNewPrivilegedUserCategory(privilegedUserCategory);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
