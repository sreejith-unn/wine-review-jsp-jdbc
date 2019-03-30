package wineconnoisseur.models;

public class Stores {
	
	private int storeId;
	private String name;
	private String street1;
	private String street2;
	private String city;
	private String state;
	private int zip;
	
	public Stores() {}
	
	public Stores(int storeId) {
		this.storeId = storeId;
	}
	
	public Stores(String name, String s1, String s2, 
			String city, String state, int zip) {
		this.name = name;
		this.street1 = s1;
		this.street2 = s2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	public Stores(int sId, String name, String s1, String s2, 
			String city, String state, int zip) {
		this.storeId = sId;
		this.name = name;
		this.street1 = s1;
		this.street2 = s2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
}
