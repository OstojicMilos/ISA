package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import enums.EstablishmentType;

@Entity
public class Establishment {

	@Id
	@GeneratedValue
	private int id;
	@NotBlank
	private String name;
	private String address;
	private String city;
	@NotNull
	private EstablishmentType type;
	@ManyToOne
	private User admin;

	@OneToMany(mappedBy = "establishment", cascade = CascadeType.ALL)
	private List<Hall> halls = new ArrayList<>();

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public EstablishmentType getType() {
		return type;
	}

	public void setType(EstablishmentType type) {
		this.type = type;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}

	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}
	
	public void addHall(Hall hall) {
		hall.setEstablishment(this);
		this.halls.add(hall);
	}

}
