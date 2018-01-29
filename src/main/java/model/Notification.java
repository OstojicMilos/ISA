package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Notification {
	
	@Id @GeneratedValue
	private int id;
	@OneToOne
	private OfferForUsedProp offer;
	@ManyToOne
	private UserAd userAd;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OfferForUsedProp getOffer() {
		return offer;
	}
	public void setOffer(OfferForUsedProp offer) {
		this.offer = offer;
	}
	public UserAd getUserAd() {
		return userAd;
	}
	public void setUserAd(UserAd userAd) {
		this.userAd = userAd;
	}
	
	
}
