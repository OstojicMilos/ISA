package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Friendship;
import model.User;
import repository.FriendshipRepository;

@Service
public class FriendshipService {
	
	@Autowired
	private FriendshipRepository friendshipRepository;
	
	public Friendship getFriendshipFor(User first, User second) {
		
		if(first.getEmail().compareTo(second.getEmail()) < 0) 
			return friendshipRepository.findByFirstUserAndSecondUser(first, second);
		else 
			return friendshipRepository.findByFirstUserAndSecondUser(second, first);
		
	}
	

	
	/*public List<Friendship> findByRecipient(User recipient) {
		
		if(friendshipRepository.findByRecipient(recipient) != null) {
			List<Friendship> result = new ArrayList<Friendship>();
			for(Friendship f : friendshipRepository.findByRecipient(recipient)) {
				
				if(!f.isActivated()) {
					result.add(f);
				}
			}
			return result;
			
		}
		return null;
	}
	*/
	
	public Friendship addFriendship(Friendship friendship) {
		if(this.getFriendshipFor(friendship.getFirstUser(), friendship.getSecondUser()) == null) {
			friendshipRepository.save(friendship);
			return friendship;
		}
		return null;
	}
	
	
	
}
