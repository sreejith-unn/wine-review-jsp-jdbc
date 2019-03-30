package wineconnoisseur.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wineconnoisseur.models.Customers;
import wineconnoisseur.models.Follow;
import wineconnoisseur.models.Tasters;

public class FollowDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static FollowDao instance = null;
	protected FollowDao() {
		connectionManager = new ConnectionManager();
	}
	public static FollowDao getInstance() {
		if(instance == null) {
			instance = new FollowDao();
		}
		return instance;
	}
	
	public Follow createFollow(Follow follow) throws SQLException{
		String insertFollow = "INSERT INTO follow(TasterID,CustomerID)"
				+ " VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFollow,Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setInt(1, follow.getTasterId());
			insertStmt.setInt(2, follow.getCustomerId());
			
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int id = -1;
			if(resultKey.next()) {
				id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			follow.setFollowId(id);
		
			return follow;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	public Follow deleteFollow(Follow follow) throws SQLException {
		String deleteReview = "DELETE FROM follow WHERE FollowID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, follow.getFollowId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Persons instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	public List<Customers> getFollowersForTasterId(int tasterId) throws SQLException {
		List<Customers> customers = new ArrayList<Customers>();
		String selectCustomers = "SELECT follow.CustomerID AS CustomerID,UserName,"
				+ "Password,FirstName,LastName,About FROM " + 
				"(follow INNER JOIN customers ON follow.CustomerID=customers.CustomerID)" + 
				" INNER JOIN users ON follow.CustomerID=users.UserID WHERE TasterID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCustomers);
			selectStmt.setInt(1, tasterId);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int customerId = results.getInt("CustomerID");
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String about = results.getString("About");
				
				
				
				Customers customer = new Customers(customerId,userName,password,
						firstName,lastName,about);
				
				
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return customers;
	}
	
	public List<Tasters> getTastersFollowedByCustomerById(int customerId) throws SQLException {
		List<Tasters> tasters = new ArrayList<Tasters>();
		String selectTasters = "SELECT follow.TasterID AS TasterID, UserName,Password,"
				+ "FirstName,LastName,TwitterHandle FROM " + 
				"(follow INNER JOIN tasters ON follow.TasterID=tasters.TasterID) " + 
				"INNER JOIN users ON follow.TasterID=users.UserID WHERE CustomerID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTasters);
			selectStmt.setInt(1, customerId);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int tasterId = results.getInt("TasterID");
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				String twitter = results.getString("TwitterHandle");
				
				Tasters taster = new Tasters(tasterId,userName,password,
						firstName,lastName,twitter);
				
				
				tasters.add(taster);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return tasters;
	}
}
