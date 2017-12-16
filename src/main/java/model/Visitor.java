package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Visitor extends User {

	public Visitor() {
		propsForSale = new ArrayList<UsedProp>();
		soldProps = new ArrayList<UsedProp>();
		purchasedProps = new ArrayList<OfficialProp>();
		purchasedUsedProps = new ArrayList<UsedProp>();
	}
	
	@OneToMany
	private List<UsedProp> propsForSale;
	@OneToMany
	private List<UsedProp> soldProps;
	@OneToMany
	private List<OfficialProp> purchasedProps;
	@OneToMany
	private List<UsedProp> purchasedUsedProps;
	private int points;
	
	public List<UsedProp> getPropsForSale() {
		return propsForSale;
	}
	public void setPropsForSale(List<UsedProp> propsForSale) {
		this.propsForSale = propsForSale;
	}
	public List<UsedProp> getSoldProps() {
		return soldProps;
	}
	public void setSoldProps(List<UsedProp> soldProps) {
		this.soldProps = soldProps;
	}
	public List<OfficialProp> getPurchasedProps() {
		return purchasedProps;
	}
	public void setPurchasedProps(List<OfficialProp> purchasedProps) {
		this.purchasedProps = purchasedProps;
	}
	public List<UsedProp> getPurchasedUsedProps() {
		return purchasedUsedProps;
	}
	public void setPurchasedUsedProps(List<UsedProp> purchasedUsedProps) {
		this.purchasedUsedProps = purchasedUsedProps;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	

	
}
