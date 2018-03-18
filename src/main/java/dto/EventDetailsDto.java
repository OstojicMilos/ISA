package dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class EventDetailsDto {
	
	private BigDecimal price;
	private Date date;
	private List<Integer> halls;
	
	public EventDetailsDto() {
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Integer> getHalls() {
		return halls;
	}

	public void setHalls(List<Integer> halls) {
		this.halls = halls;
	}
	
}
