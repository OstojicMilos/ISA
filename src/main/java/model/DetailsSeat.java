package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class DetailsSeat {
	
	@EmbeddedId
	private DetailsSeatId id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @MapsId("eventDetailsId")
	@JsonBackReference("projection_seats")
	private EventDetails details;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("seatId")
	private Seat seat;
	
	private Boolean isAvailable;
	
	public DetailsSeat() {}

	public DetailsSeat(EventDetails details, Seat seat) {
		this.details = details;
		this.seat = seat;
		this.isAvailable = true;
		this.id = new DetailsSeatId(details.getId(), seat.getId());
	}

	public DetailsSeatId getId() {
		return id;
	}

	public void setId(DetailsSeatId id) {
		this.id = id;
	}

	public EventDetails getDetails() {
		return details;
	}

	public void setDetails(EventDetails details) {
		this.details = details;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Boolean getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
}
