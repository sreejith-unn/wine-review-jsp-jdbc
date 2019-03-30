package wineconnoisseur.models;

public class Wines {
	
	private int wineId;
	private String name;
	private String description;
	private int price;
	private String variety;
	private String vineyard;
	private String wineryName;
	
	public Wines() {}
	
	public Wines(int wineId) {
		this.wineId = wineId;
	}
	
	public Wines(String name, String desc, int price, 
			String var, String vineyard, String wName) {
		this.name = name;
		this.description = desc;
		this.price = price;
		this.variety = var;
		this.vineyard = vineyard;
		this.wineryName = wName;
	}
	
	public Wines(int wId, String name, String desc, int price,
			String var, String vineyard, String wName) {
		this.wineId = wId;
		this.name = name;
		this.description = desc;
		this.price = price;
		this.variety = var;
		this.vineyard = vineyard;
		this.wineryName = wName;
	}

	public int getWineId() {
		return wineId;
	}

	public void setWineId(int wineId) {
		this.wineId = wineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public String getVineyard() {
		return vineyard;
	}

	public void setVineyard(String vineyard) {
		this.vineyard = vineyard;
	}

	public String getWineryName() {
		return wineryName;
	}

	public void setWineryName(String wineryName) {
		this.wineryName = wineryName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}	
}
