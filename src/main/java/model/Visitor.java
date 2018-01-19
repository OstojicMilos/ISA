package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Visitor extends User {

	@OneToMany
	private List<NewPropOrder> purchasedProps;
	private int points;
	
	public Visitor() {
		purchasedProps = new ArrayList<NewPropOrder>();
	}

	public List<NewPropOrder> getPurchasedProps() {
		return purchasedProps;
	}
	
	public void setPurchasedProps(List<NewPropOrder> purchasedProps) {
		this.purchasedProps = purchasedProps;
	}

	public int getPoints() {
		return points;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	

	
}
