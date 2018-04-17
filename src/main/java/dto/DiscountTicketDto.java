package dto;

public class DiscountTicketDto {
	
	private Integer projectionId;
	private Integer userId;
	private Integer seatId;
	private Integer discount;
	
	public DiscountTicketDto() {
	}

	public Integer getProjectionId() {
		return projectionId;
	}

	public void setProjectionId(Integer projectionId) {
		this.projectionId = projectionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSeatId() {
		return seatId;
	}

	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	
}
