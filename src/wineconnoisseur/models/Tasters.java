package wineconnoisseur.models;

public class Tasters extends Users{
	
	private String twitterHandle;
	
	public Tasters() {}
	
	public Tasters(int tasterId) {
		super(tasterId);
	}
	
	public Tasters(String userName, String password, String firstName,
			String lastName, String twitter) {
		super(userName, password, firstName, lastName);
		this.twitterHandle = twitter;
	}
	
	public Tasters(int userId, String userName, String password, 
			String firstName, String lastName, String twitter) {
		super(userId, userName, password, firstName, lastName);
		this.twitterHandle = twitter;
	}

	public String getTwitterHandle() {
		return twitterHandle;
	}

	public void setTwitterHandle(String twitterHandle) {
		this.twitterHandle = twitterHandle;
	}
}
