package model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Hall {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="fk_estabilishment")
	private Establishment estabilishment;
	
	//sedista

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Establishment getEstabilishment() {
		return estabilishment;
	}

	public void setEstabilishment(Establishment estabilishment) {
		this.estabilishment = estabilishment;
	}
}
