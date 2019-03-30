package wineconnoisseur.dal;

import wineconnoisseur.models.Reviews;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewsDao {
	
	private static ReviewsDao instance = null;
	
	public static ReviewsDao getInstance() {
		if (instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	public Reviews create(Reviews review) throws SQLException {
		String insertSql = 
				"INSERT INTO Reviews(CustomerID, WineID, Content, Created) "
				+ "VALUES(?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, review.getCustomerId());
			ps.setInt(2, review.getWineId());
			ps.setString(3, review.getContent());
			ps.setTimestamp(4, new Timestamp(review.getCreated().getTime()));
			affectedRowCount = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			int reviewId = -1;
			if (rs.next()) {
				reviewId = rs.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(reviewId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to insert the specified review record into the database!");
		}
		System.out.println("Successfully created a new review record \n");
		return review;
	}
	
	public Reviews getReviewById(int reviewId) throws SQLException {
		String selectSql = "SELECT * FROM Reviews WHERE ReviewID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reviews review = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, reviewId);
			rs = ps.executeQuery();
			if (rs.next()) {
				int reviewIdInDB = rs.getInt("ReviewID");
				int customerId = rs.getInt("CustomerID");
				int wineId = rs.getInt("WineID");
				String content = rs.getString("Content");
				Date created = new Date(rs.getTimestamp("Created").getTime());
				review = new Reviews(reviewIdInDB, customerId, wineId, content, created);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return review;
	}
	
	public List<Reviews> getReviewsByCustomerId(int customerId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectSql = "SELECT * FROM Reviews WHERE CustomerID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, customerId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int reviewId = rs.getInt("ReviewID");
				int customerIdInDB = rs.getInt("CustomerID");
				int wineId = rs.getInt("WineID");
				String content = rs.getString("Content");
				Date created = new Date(rs.getTimestamp("Created").getTime());
				Reviews review = new Reviews(reviewId, customerIdInDB, wineId, content, created);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return reviews;
	}
	
	public List<Reviews> getReviewsByWineId(int wineId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectSql = "SELECT * FROM Reviews WHERE WineID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, wineId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int reviewId = rs.getInt("ReviewID");
				int customerId = rs.getInt("CustomerID");
				int wineIdInDB = rs.getInt("WineID");
				String content = rs.getString("Content");
				Date created = new Date(rs.getTimestamp("Created").getTime());
				Reviews review = new Reviews(reviewId, customerId, wineIdInDB, content, created);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return reviews;
	}
	
	public Reviews delete(Reviews review) throws SQLException {
		String deleteSql = "DELETE FROM Reviews WHERE ReviewID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(deleteSql);
			ps.setInt(1, review.getReviewId());
			affectedRowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to delete the specified review record!");
		}
		return null;
	}
}
