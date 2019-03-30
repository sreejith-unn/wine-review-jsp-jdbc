package wineconnoisseur.models;

public class Follows {
	
	private int followId;
	private int tasterId;
	private int customerId;
	
	public Follows() {}
	
	public Follows(int followId) {
		this.followId = followId;
	}
	
	public Follows(int tId, int cId) {
		this.tasterId = tId;
		this.customerId = cId;
	}

	public Follows(int fId, int tId, int cId) {
		this.followId = fId;
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
