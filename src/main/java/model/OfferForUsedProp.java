package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class OfferForUsedProp {

	@Id @GeneratedValue
	private int id;
	@ManyToOne
	private User user;
	private int offeredSum;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getOfferedSum() {
		return offeredSum;
	}
	public void setOfferedSum(int offeredSum) {
		this.offeredSum = offeredSum;
	}
	
}
