package wineconnoisseur.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wineconnoisseur.models.Tasters;
import wineconnoisseur.models.Users;

public class TastersDao extends UsersDao{
	private static TastersDao instance = null;
	
	public static TastersDao getInstance() {
		if(instance ==null) {
			instance = new TastersDao();
		}
		return instance;
	}
	
	public Tasters create(Tasters taster) throws SQLException{
		Users user = create(new Users(taster.getUserName(),taster.getPassword(),
				taster.getFirstName(),taster.getLastName()));
		
		String insertTaster = "INSERT INTO tasters(TasterID,TwitterHandle) "
				+ "VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = ConnectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTaster);
			insertStmt.setInt(1, user.getUserId());
			insertStmt.setString(2, taster.getTwitterHandle());
			insertStmt.executeUpdate();
			
			taster.setUserId(user.getUserId());
			return taster;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(insertStmt);
		}
	}
	
	public Tasters getTasterByTasterId(int tasterId) throws SQLException {
		String selectTaster = "SELECT tasters.TasterID AS TasterID, Username,"
				+ "FirstName,LastName,Password,TwitterHandle FROM tasters INNER JOIN users"
				+ " ON tasters.TasterID=users.UserID WHERE tasters.TasterID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = ConnectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTaster);
			selectStmt.setInt(1, tasterId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				
				String twitterHandle = results.getString("TwitterHandle");
				
				Tasters taster = new Tasters(tasterId,userName,password,firstName,
						lastName,twitterHandle);
				
				
				return taster;
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
	
	public Tasters delete(Tasters taster) throws SQLException {
		super.delete(taster);
		return null;
	}

}
