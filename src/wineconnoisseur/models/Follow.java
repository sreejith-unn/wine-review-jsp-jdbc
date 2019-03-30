package wineconnoisseur.models;

public class Follow {
	protected int followId;
	protected int tasterId;
	protected int customerId;
	
	public Follow(int fId, int tId, int cId) {
		this.followId = fId;
		this.tasterId = tId;
		this.customerId = cId;
	}
	
	public Follow(int tId, int cId) {
		this.tasterId = tId;
		this.customerId = cId;
	}

	public int getFollowId() {
		return followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public int getTasterId() {
		return tasterId;
	}

	public void setTasterId(int tasterId) {
		this.tasterId = tasterId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	
}
