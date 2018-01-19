package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class NewPropOrder {

	@ManyToOne
	private OfficialProp officialProp;
	private int price;
	
	public OfficialProp getOfficialProp() {
		return officialProp;
	}
	public void setOfficialProp(OfficialProp officialProp) {
		this.officialProp = officialProp;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
}
