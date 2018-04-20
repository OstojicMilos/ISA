package dto;

import java.util.List;

public class ReservationDto {
	
	private Integer userId;
	
	private List<Integer> seats;
	
	private List<Integer> guests;
	
	public ReservationDto() {}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Integer> getSeats() {
		return seats;
	}

	public void setSeats(List<Integer> seats) {
		this.seats = seats;
	}

	public List<Integer> getGuests() {
		return guests;
	}

	public void setGuests(List<Integer> guests) {
		this.guests = guests;
	}
	
	
}
