package wineconnoisseur.models;

import java.util.Date;

public class Sales {
	
	private int saleId;
	private int wineId;
	private int storeId;
	private Date madeDate;
	private int numOfBottles;
	
	public Sales() {}
	
	public Sales(int saleId) {
		this.saleId = saleId;
	}
	
	public Sales(int wId, int stId, Date d, int no) {
		this.wineId = wId;
		this.storeId = stId;
		this.madeDate = d;
		this.numOfBottles = no;
	}
	
	public Sales(int wId, int stId, int no) {
		this.wineId = wId;
		this.storeId = stId;
		this.numOfBottles = no;
	}
	
	public Sales(int sId, int wId, int stId, Date d, int no) {
		this.saleId = sId;
		this.wineId = wId;
		this.storeId = stId;
		this.madeDate = d;
		this.numOfBottles = no;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public int getWineId() {
		return wineId;
	}

	public void setWineId(int wineId) {
		this.wineId = wineId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public Date getMadeDate() {
		return madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public int getNumOfBottles() {
		return numOfBottles;
	}

	public void setNumOfBottles(int numOfBottles) {
		this.numOfBottles = numOfBottles;
	}
}
