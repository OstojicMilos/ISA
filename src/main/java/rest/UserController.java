package rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dto.ReservationViewDto;
import model.User;
import model.UserWrapper;
import service.FriendshipService;
import service.UserService;

@RestController
public class UserController {
	
	@Autowired	
	private UserService userService;
	
	@Autowired
	private FriendshipService friendshipService;
	
	@PostMapping("/register")
	public User processRegistrationForm(@Valid @RequestBody User user, HttpServletRequest request) {
		try {
			return userService.processRegistrationForm(user, request);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/activate/{token}")
	public User activateNewUser(@PathVariable("token") String token) {
		try {
			return userService.activateNewUser(token);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@PostMapping("/login")
	public User authenticateUser(@RequestBody UserWrapper credentials) {
		try {
			return userService.authenticateUser(credentials.getEmail(), credentials.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/search/{searchCriteria}")
	public List<User> searchForUser(@PathVariable String searchCriteria) {
		try {
			String[] criteria = searchCriteria.split(" ");
			return userService.searchForUser(criteria);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("account/updateData")
	public void updateUserData(@RequestBody User user) {
		try {
			userService.updateUserdata(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@GetMapping("owner/reservations/{userId}")
	public List<ReservationViewDto> getReservationsAsOwner(@PathVariable Integer userId){
		try {
			return userService.getReservationsAsOwner(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("guest/reservations/{userId}")
	public List<ReservationViewDto> getReservationsAsGuest(@PathVariable Integer userId){
		try {
			return userService.getReservationsAsGuest(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
}
