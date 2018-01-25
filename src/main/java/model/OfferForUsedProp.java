package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import enums.OfferStatus;

@Entity
public class OfferForUsedProp {

	@Id @GeneratedValue
	private int id;
	@ManyToOne
	private User user;
	private int offeredSum;
	private OfferStatus offerStatus;
	
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OfferStatus getOfferStatus() {
		return offerStatus;
	}
	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}
	
}
