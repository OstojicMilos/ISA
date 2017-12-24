package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class OfficialProp extends Prop{

	private boolean available;
	@ManyToOne
	private Establishment establishment;
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Establishment getEstablishment() {
		return establishment;
	}
	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}
	
	
}
