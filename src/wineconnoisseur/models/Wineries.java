package wineconnoisseur.models;

public class Wineries {
	
	private String wineryName;
	private String region;
	private String province;
	private String country;
	
	public Wineries() {}
	
	public Wineries(String wineryName) {
		this.wineryName = wineryName;
	}
	
	public Wineries(String region, String province, String country) {
		this.region = region;
		this.province = province;
		this.country = country;
	}
	
	public Wineries(String wName, String region, String province, String country) {
		this.wineryName = wName;
		this.region = region;
		this.province = province;
		this.country = country;
	}

	public String getWineryName() {
		return wineryName;
	}

	public void setWineryName(String wineryName) {
		this.wineryName = wineryName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}	
}
