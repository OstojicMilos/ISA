package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Friendship;
import model.FriendshipWrapper;
import model.User;
import service.FriendshipService;

@RestController	
public class FriendshipController {
	
	@Autowired
	FriendshipService friendshipService;
	
	@PostMapping("/friendship")
	public boolean addFriendship(@RequestBody FriendshipWrapper friendship) {
		try {
			Friendship f = new Friendship(friendship.getFirst(), friendship.getSecond());
			return friendshipService.addFriendship(f);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	@PostMapping("/pending")
	public List<User> findPendingRequestsFor(@RequestBody User user) {
		try {
			return friendshipService.findPendingRequestsFor(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/friends")
	public List<User> findFriendsFor(@RequestBody User user){
		try {
			return friendshipService.findFriendsFor(user);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping("/confirmRequest")
	public boolean confirmFriendship(@RequestBody FriendshipWrapper friendship) {
		try {
			Friendship f = new Friendship(friendship.getFirst(), friendship.getSecond());
			friendshipService.confirmFriendship(f);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@PutMapping("/friendship")
	public boolean deleteFriendship(@RequestBody FriendshipWrapper friendship) {
		try {
			Friendship f = new Friendship(friendship.getFirst(), friendship.getSecond());
			friendshipService.deleteFriendship(f);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
