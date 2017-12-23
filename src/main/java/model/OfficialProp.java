package model;

import javax.persistence.Entity;

@Entity
public class OfficialProp extends Prop{

	private boolean available;
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
}
