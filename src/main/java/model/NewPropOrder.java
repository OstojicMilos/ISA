package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NewPropOrder {

	@Id @GeneratedValue
	private int id;
	@ManyToOne
	private OfficialProp officialProp;
	private int price;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
