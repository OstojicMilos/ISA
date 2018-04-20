package dto;

import java.util.Date;

public class IncomeReportDto {
	
	private Date from;
	private Date to;
	
	public IncomeReportDto() {
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
	
}
