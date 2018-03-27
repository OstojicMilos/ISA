package model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DetailsSeatId implements Serializable {
	
	private static final long serialVersionUID = 6609847959401443305L;
	
	@Column(name = "event_details_id")
	private Integer eventDetailsId;
	@Column(name = "seat_id")
	private Integer seatId;
	
	public DetailsSeatId() {}
	
	public DetailsSeatId(Integer eventDetailsId, Integer seatId) {
		this.setEventDetailsId(eventDetailsId);
		this.setSeatId(seatId);
	}

	public Integer getEventDetailsId() {
		return eventDetailsId;
	}

	public void setEventDetailsId(Integer eventDetailsId) {
		this.eventDetailsId = eventDetailsId;
	}

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) 
			return true;
		
		if (!(obj instanceof DetailsSeatId)) 
			return false;
		
		DetailsSeatId that = (DetailsSeatId)obj;
		return Objects.equals(getEventDetailsId(), that.getEventDetailsId()) &&
				Objects.equals(getSeatId(), that.getSeatId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getEventDetailsId(), getSeatId());
	}
}
