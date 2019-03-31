package wineconnoisseur.models;

import java.util.Date;

public class Reviews {
	
	private int reviewId;
	private int customerId;
	private int wineId;
	private String content;
	private Date created;
	
	public Reviews() {}
	
	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}
	
	public Reviews(int cId, int wId, String content) {
		this.customerId = cId;
		this.wineId = wId;
		this.content = content;
	}
	
	public Reviews(int cId, int wId, String content, Date created) {
		this.customerId = cId;
		this.wineId = wId;
		this.created = created;
		this.content = content;
	}
	
	public Reviews(int rId, int cId, int wId, String content, Date created) {
		this.reviewId = rId;
		this.customerId = cId;
		this.wineId = wId;
		this.created = created;
		this.content = content;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getWineId() {
		return wineId;
	}

	public void setWineId(int wineId) {
		this.wineId = wineId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
