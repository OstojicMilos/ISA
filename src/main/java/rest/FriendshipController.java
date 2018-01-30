package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import model.Friendship;
import model.FriendshipWrapper;
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
	
}
