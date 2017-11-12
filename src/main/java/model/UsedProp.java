package model;

import java.util.Date;

public class UsedProp extends Prop {

	private boolean sold;
	private Date availableUntil;
	
	public boolean getSold() {
		return sold;
	}
	public void setSold(boolean sold) {
		this.sold = sold;
	}
	public Date getAvailableUntil() {
		return availableUntil;
	}
	public void setAvailableUntil(Date availableUntil) {
		this.availableUntil = availableUntil;
	}
	
	
}
