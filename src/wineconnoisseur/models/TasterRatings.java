package wineconnoisseur.models;

public class TasterRatings {
	
	private int ratingId;
	private int tasterId;
	private int wineId;
	private float rating;
	
	public TasterRatings() {}
	
	public TasterRatings(int ratingId) {
		this.ratingId = ratingId;
	}
	
	public TasterRatings(int tId, int wId, float rating) {
		this.tasterId = tId;
		this.wineId = wId;
		this.rating = rating;
	}
	
	public TasterRatings(int rId, int tId, int wId, float rating) {
		this.ratingId = rId;
		this.tasterId = tId;
		this.wineId = wId;
		this.rating = rating;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	public int getTasterId() {
		return tasterId;
	}

	public void setTasterId(int tasterId) {
		this.tasterId = tasterId;
	}

	public int getWineId() {
		return wineId;
	}

	public void setWineId(int wineId) {
		this.wineId = wineId;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}
}
