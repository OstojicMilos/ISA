package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class EventDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotNull
	@Future
	private Date dateAndTime;
	@NotNull
	@DecimalMin("0.00")
	private BigDecimal price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_event")
	@JsonBackReference("event_projections")
	private Event event;

	@ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "event_hall", 
		joinColumns = @JoinColumn(name = "fk_event"), 
		inverseJoinColumns = @JoinColumn(name = "fk_hall"))
	//@JsonManagedReference("projections_halls")
	//@JsonIgnore
	@NotEmpty
	private List<Hall> halls = new ArrayList<>();

	@OneToMany(mappedBy = "details", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference("projection_seats")
	private List<DetailsSeat> seats = new ArrayList<>();
	
	@OneToMany(mappedBy = "projection", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<DiscountTicket> discountedTickets = new ArrayList<>();
	
	
	public EventDetails() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}
	
	public void addHall(Hall hall) {
		halls.add(hall);
		hall.getProjections().add(this);
	}
	
	public void removeHall(Hall hall) {
		halls.remove(hall);
		hall.getProjections().remove(this);
	}

	public List<DetailsSeat> getSeats() {
		return seats;
	}

	public void setSeats(List<DetailsSeat> seats) {
		this.seats = seats;
	}
	
	public void addSeat(Seat seat) {
		DetailsSeat ds = new DetailsSeat(this, seat);
		seats.add(ds);
	}

	public List<DiscountTicket> getDiscountedTickets() {
		return discountedTickets;
	}

	public void setDiscountedTickets(List<DiscountTicket> discountedTickets) {
		this.discountedTickets = discountedTickets;
	}
	

}
