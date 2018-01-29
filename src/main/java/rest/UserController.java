package rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Friendship;
import model.FriendshipWrapper;
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
	/*
	@PostMapping("/pending")
	public List<Friendship> findAllPendingRequestsFor(@PathVariable User user) {
		try {
			return friendshipService.findByRecipient(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	*/
	
	//add
	@PostMapping("/newFriendship")
	public Friendship newFriendship(@PathVariable FriendshipWrapper friendship) {
		try {
			Friendship f = new Friendship(friendship.getFirst(), friendship.getSecond());
			return friendshipService.addFriendship(f);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
