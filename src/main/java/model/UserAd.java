package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import enums.AdStatus;

@Entity
public class UserAd {

	@Id @GeneratedValue
	private int id;
	@OneToOne
	private UsedProp usedProp;
	private AdStatus adStatus;
	private Date availableUntil;
	private boolean sold;
	@OneToMany
	private List<OfferForUsedProp> offers;
	@ManyToOne
	private User owner;
	
	public UserAd() {
		offers = new ArrayList<OfferForUsedProp>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UsedProp getUsedProp() {
		return usedProp;
	}

	public void setUsedProp(UsedProp usedProp) {
		this.usedProp = usedProp;
	}

	public AdStatus getAdStatus() {
		return adStatus;
	}

	public void setAdStatus(AdStatus adStatus) {
		this.adStatus = adStatus;
	}

	public Date getAvailableUntil() {
		return availableUntil;
	}

	public void setAvailableUntil(Date availableUntil) {
		this.availableUntil = availableUntil;
	}

	public boolean isSold() {
		return sold;
	}

	public void setSold(boolean sold) {
		this.sold = sold;
	}

	public List<OfferForUsedProp> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferForUsedProp> offers) {
		this.offers = offers;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "UserAd [id=" + id + ", usedProp=" + usedProp + ", adStatus=" + adStatus + ", availableUntil="
				+ availableUntil + ", sold=" + sold + ", offers=" + offers + ", owner=" + owner + "]";
	}
	
	
}
