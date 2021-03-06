package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import enums.Role;

@Entity
public class User implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotEmpty(message = "Please provide your name")
	private String name;
	
	@NotEmpty(message = "Please provide your surname")
	private String surname;
	
	@NotBlank
	private String city;
	
	@Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
	private String email;
	
	@NotBlank
	private String phoneNumber;
	
	@NotBlank
	@Transient
	private String password;
	
	private Role role;
	
	private Boolean activated;
	
	private String confirmationToken;

	@OneToMany
	@Column(nullable = true)
	private List<Friendship> friendships;
	
	@OneToMany(mappedBy = "owner")
	@JsonIgnore
	private List<Reservation> reservations = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<DiscountTicket> discountedTickets = new ArrayList<>();
	
	@ManyToMany(mappedBy = "guests")
	@JsonIgnore
	List<Reservation> reservationsAsGuest = new ArrayList<>();
	/*
	public List<Friendship> getFriendships() {
		return friendships;
	}
	
	public void setFriendships(List<Friendship> friendships) {
		this.friendships = friendships;
	}*/

	@ManyToMany
	private List<OfficialProp> reservedProps = new ArrayList<OfficialProp>();
	
	public List<OfficialProp> getReservedProps() {
		return reservedProps;
	}
	public void setReservedProps(List<OfficialProp> reservedProps) {
		this.reservedProps = reservedProps;
	}

//	public List<Reservation> getReservations() {
	//	return reservations;
	//}
	//public void setReservations(List<Reservation> reservations) {
	//	this.reservations = reservations;
	//}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Boolean getActivated() {
		return activated;
	}
	public void setActivated(Boolean activated) {
		this.activated = activated;
	}
	public String getConfirmationToken() {
		return confirmationToken;
	}
	public void setConfirmationToken(String confirmationToken) {
		this.confirmationToken = confirmationToken;
	}
	public List<DiscountTicket> getDiscountedTickets() {
		return discountedTickets;
	}
	public void setDiscountedTickets(List<DiscountTicket> discountedTickets) {
		this.discountedTickets = discountedTickets;
	}
	public List<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}
	public List<Reservation> getReservationsAsGuest() {
		return reservationsAsGuest;
	}
	public void setReservationsAsGuest(List<Reservation> reservationsAsGuest) {
		this.reservationsAsGuest = reservationsAsGuest;
	}
	
	
	
}
