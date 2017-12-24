package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Visitor extends User {

	@ManyToMany
	private List<OfficialProp> purchasedProps;
	private int points;
	
	public Visitor() {
		purchasedProps = new ArrayList<OfficialProp>();
	}

	public List<OfficialProp> getPurchasedProps() {
		return purchasedProps;
	}
	
	public void setPurchasedProps(List<OfficialProp> purchasedProps) {
		this.purchasedProps = purchasedProps;
	}

	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	

	
}
