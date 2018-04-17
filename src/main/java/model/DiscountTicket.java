package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class DiscountTicket {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private EventDetails projection;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	private Seat seat;
	
	private Integer discount;
	private Integer ambintRating;
	private Integer eventRating;
	private Boolean isReserved;
	
	public DiscountTicket() { }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EventDetails getProjection() {
		return projection;
	}

	public void setProjection(EventDetails projection) {
		this.projection = projection;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Integer getAmbintRating() {
		return ambintRating;
	}

	public void setAmbintRating(Integer ambintRating) {
		this.ambintRating = ambintRating;
	}

	public Integer getEventRating() {
		return eventRating;
	}

	public void setEventRating(Integer eventRating) {
		this.eventRating = eventRating;
	}

	public Boolean getIsReserved() {
		return isReserved;
	}

	public void setIsReserved(Boolean isReserved) {
		this.isReserved = isReserved;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
}
