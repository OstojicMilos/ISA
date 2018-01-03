package model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


public class Visitor extends User {

	private List<UsedProp> propsForSale;
	private List<UsedProp> soldProps;
	private List<Prop> purchasedProps;
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
	public List<Prop> getPurchasedProps() {
		return purchasedProps;
	}
	public void setPurchasedProps(List<Prop> purchasedProps) {
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
