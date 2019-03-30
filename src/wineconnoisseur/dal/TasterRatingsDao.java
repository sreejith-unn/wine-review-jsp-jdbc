package wineconnoisseur.dal;

import wineconnoisseur.models.TasterRatings;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TasterRatingsDao {

	private static TasterRatingsDao instance = null;
	
	public static TasterRatingsDao getInstance() {
		if (instance == null) {
			instance = new TasterRatingsDao();
		}
		return instance;
	}
	
	public TasterRatings create(TasterRatings tasterRating) throws SQLException {
		String insertSql = 
				"INSERT INTO TasterRatings(TasterID, WineID, Rating) "
				+ "VALUES(?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tasterRating.getTasterId());
			ps.setInt(2, tasterRating.getWineId());
			ps.setFloat(3, tasterRating.getRating());
			affectedRowCount = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			int ratingId = -1;
			if (rs.next()) {
				ratingId = rs.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			tasterRating.setRatingId(ratingId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to insert the specified taster-rating record into the database!");
		}
		System.out.println("Successfully created a new taster-rating record \n");
		return tasterRating;
	}
	
	public TasterRatings getTasterRatingById(int ratingId) throws SQLException {
		String selectSql = "SELECT * FROM TasterRatings WHERE RatingID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TasterRatings tasterRating = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, ratingId);
			rs = ps.executeQuery();
			if (rs.next()) {
				int ratingIdInDB = rs.getInt("RatingID");
				int tasterId = rs.getInt("TasterID");
				int wineId = rs.getInt("WineID");
				float rating = rs.getFloat("Rating");
				tasterRating = new TasterRatings(ratingIdInDB, tasterId, wineId, rating);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return tasterRating;
	}
	
	public List<TasterRatings> getTasterRatingsByTasterId(int tasterId) throws SQLException {
		List<TasterRatings> tasterRatings = new ArrayList<TasterRatings>();
		String selectSql = "SELECT * FROM TasterRatings WHERE TasterID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, tasterId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int ratingId = rs.getInt("RatingID");
				int tasterIdInDB = rs.getInt("TasterID");
				int wineId = rs.getInt("WineID");
				float rating = rs.getFloat("Rating");
				TasterRatings tasterRating = new TasterRatings(ratingId, tasterIdInDB, wineId, rating);
				tasterRatings.add(tasterRating);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return tasterRatings;
	}
	
	public List<TasterRatings> getTasterRatingsByWineId(int wineId) throws SQLException {
		List<TasterRatings> tasterRatings = new ArrayList<TasterRatings>();
		String selectSql = "SELECT * FROM TasterRatings WHERE WineID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, wineId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int ratingId = rs.getInt("RatingID");
				int tasterId = rs.getInt("TasterID");
				int wineIdInDB = rs.getInt("WineID");
				float rating = rs.getFloat("Rating");
				TasterRatings tasterRating = new TasterRatings(ratingId, tasterId, wineIdInDB, rating);
				tasterRatings.add(tasterRating);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return tasterRatings;
	}

	
	public TasterRatings delete(TasterRatings tasterRating) throws SQLException {
		String deleteSql = "DELETE FROM TasterRatings WHERE RatingID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(deleteSql);
			ps.setInt(1, tasterRating.getRatingId());
			affectedRowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to delete the specified taster-rating record!");
		}
		return null;
	}
}
