package wineconnoisseur.models;

public class Customers extends Users{
	
	private String about;
	
	public Customers() {}
	
	public Customers(int customerId) {
		super(customerId);
	}
	
	public Customers(String userName, String password, String firstName,
			String lastName, String about) {
		super(userName, password, firstName, lastName);
		this.about = about;
	}
	
	public Customers(int userId, String userName, String password, 
			String firstName, String lastName, String about) {
		super(userId, userName, password, firstName, lastName);
		this.about = about;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}
}
