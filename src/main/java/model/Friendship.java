package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import enums.FriendshipStatus;

@Entity
public class Friendship implements Serializable{

	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	
	
	private FriendshipStatus status;
	

	@ManyToOne
	private User firstUser;
	
	@ManyToOne
	private User secondUser;
	
	
	public Friendship(User first, User second) {
		if(first.getEmail().compareTo(second.getEmail()) < 0) {
			this.firstUser = first;
			this.secondUser = second;
			this.status = FriendshipStatus.SENT_BY_FIRST_USER;
		}
		else {
			this.firstUser = second;
			this.secondUser = first;
			this.status = FriendshipStatus.SENT_BY_SECOND_USER;
		}
		
	}
	

	public FriendshipStatus getStatus() {
		return status;
	}

	public void setStatus(FriendshipStatus status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}


	public User getFirstUser() {
		return firstUser;
	}


	public void setFirstUser(User user) {
		this.firstUser = user;
	}


	public User getSecondUser() {
		return secondUser;
	}


	public void setSecondUser(User user) {
		this.secondUser = user;
	}
	
	
}
