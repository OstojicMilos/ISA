package service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enums.FriendshipStatus;
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
	
	public boolean addFriendship(Friendship friendship) {
		
		if(this.getFriendshipFor(friendship.getFirstUser(), friendship.getSecondUser()) == null) {
			friendshipRepository.save(friendship);
			return true;
		}
		return false;
	}
	
	public List<User> findPendingRequestsFor(User user) {
		List<User> result = new ArrayList<User>();
		for(Friendship f : friendshipRepository.findByFirstUserOrSecondUser(user, user)) {
			if((f.getFirstUser().getId() == user.getId()) && (f.getStatus() == FriendshipStatus.SENT_BY_SECOND_USER)) {
				result.add(f.getSecondUser());
			}
			else if((f.getSecondUser().getId() == user.getId()) && (f.getStatus() == FriendshipStatus.SENT_BY_FIRST_USER)) {
				result.add(f.getFirstUser());
			}
		}
		return result;
		
	}

	public void confirmFriendship(Friendship friendship) {
		Friendship f = getFriendshipFor(friendship.getFirstUser(), friendship.getSecondUser());
		f.setStatus(FriendshipStatus.CONFIRMED);
		friendshipRepository.save(f);
	}
	
	public void deleteFriendship(Friendship friendship) {
		Friendship f = getFriendshipFor(friendship.getFirstUser(), friendship.getSecondUser());
		friendshipRepository.delete(f);
	}

	public List<User> findFriendsFor(User user) {
		List<User> result = new ArrayList<User>();
		for(Friendship f : friendshipRepository.findByFirstUserOrSecondUser(user, user)) {
			if((f.getFirstUser().getId() == user.getId()) && (f.getStatus() == FriendshipStatus.CONFIRMED)) {
				result.add(f.getSecondUser());
			}
			else if((f.getSecondUser().getId() == user.getId()) && (f.getStatus() == FriendshipStatus.CONFIRMED)) {
				result.add(f.getFirstUser());
			}
		}
		
		return result;
		
	}
	
	
	
	
	
	
}
