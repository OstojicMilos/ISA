package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	private EventDetails projection;
	
	@ManyToOne
	private User owner;
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "reservation_guest", 
		joinColumns = @JoinColumn(name = "fk_reservation"), 
		inverseJoinColumns = @JoinColumn(name = "fk_guest"))
	private List<User> guests = new ArrayList<>();
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "reservation_seat", 
		joinColumns = @JoinColumn(name = "fk_reservation"), 
		inverseJoinColumns = @JoinColumn(name = "fk_seat"))
	private List<Seat> seats = new ArrayList<>();
	
	private Integer ambientRating;
	private Integer eventRating;
	
	public Reservation() {
	}

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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<User> getGuests() {
		return guests;
	}

	public void setGuests(List<User> guests) {
		this.guests = guests;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Integer getAmbientRating() {
		return ambientRating;
	}

	public void setAmbientRating(Integer ambientRating) {
		this.ambientRating = ambientRating;
	}

	public Integer getEventRating() {
		return eventRating;
	}

	public void setEventRating(Integer eventRating) {
		this.eventRating = eventRating;
	}

	

	
}
