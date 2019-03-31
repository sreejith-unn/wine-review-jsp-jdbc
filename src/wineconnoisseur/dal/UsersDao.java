package wineconnoisseur.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import wineconnoisseur.dal.ConnectionManager;
import wineconnoisseur.models.Tasters;
import wineconnoisseur.models.Users;


public class UsersDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static UsersDao instance = null;
	
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	public Users create(Users user) throws SQLException{
		String insertUser = "INSERT INTO users(UserName,Password,FirstName,"
				+ "LastName) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = ConnectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser,Statement.RETURN_GENERATED_KEYS);
			
			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getFirstName());
			insertStmt.setString(4, user.getLastName());
			
		
			
			insertStmt.executeUpdate();
			resultKey = insertStmt.getGeneratedKeys();
			int id = -1;
			if(resultKey.next()) {
				id = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			user.setUserId(id);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(insertStmt);
			ConnectionManager.closeResultSet(resultKey);
		}
	}
	
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM users WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setInt(1, user.getUserId());
			affectedRowCount = deleteStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(deleteStmt);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to delete the specified user!");
		}
		return null;
	}
	
	public Users getUserByUserId(int userId) throws SQLException {
		String selectUser = "SELECT UserId,UserName,Password,FirstName,LastName"
				+ " FROM users WHERE UserID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = ConnectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setInt(1, userId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				Users user = new Users(userId, userName, password, firstName,
						lastName);
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(selectStmt);
			ConnectionManager.closeResultSet(results);
		}
		return null;
	}
	
	public Users update(Users user) throws SQLException {
		String updateSql = "UPDATE users SET Password = ?, FirstName = ?, LastName = ? WHERE UserID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(updateSql);
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getFirstName());
			ps.setString(3, user.getLastName());
			ps.setInt(4, user.getUserId());
			affectedRowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to update the specified wine record!");
		}
		user = getUserByUserId(user.getUserId());
		return user;
	}
}
