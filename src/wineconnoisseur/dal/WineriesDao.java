package wineconnoisseur.dal;

import wineconnoisseur.models.Wineries;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WineriesDao {

	private static WineriesDao instance = null;
	
	public static WineriesDao getInstance() {
		if (instance == null) {
			instance = new WineriesDao();
		}
		return instance;
	}
	
	public Wineries create(Wineries winery) throws SQLException {
		String insertSql = "INSERT INTO Wineries VALUES(?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(insertSql);
			ps.setString(1, winery.getWineryName());
			ps.setString(2, winery.getRegion());
			ps.setString(3, winery.getProvince());
			ps.setString(4, winery.getCountry());
			affectedRowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to insert the specified winery record into the database!");
		}
		System.out.println("Successfully created a new winery record \n");
		return winery;
	}
	
	public Wineries getWineryByWineryName(String wineryName) throws SQLException {
		String selectSql = "SELECT * FROM Wineries WHERE WineryName = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Wineries winery = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setString(1, wineryName);
			rs = ps.executeQuery();
			if (rs.next()) {
				String wineryNameInDB = rs.getString("WineryName");
				String region = rs.getString("Region");
				String province = rs.getString("Province");
				String country = rs.getString("Country");
				winery = new Wineries(wineryNameInDB, region, province, country);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return winery;
	}
	
	public List<Wineries> getWineriesByCountry(String country) throws SQLException {
		List<Wineries> wineries = new ArrayList<Wineries>();
		String selectSql = "SELECT * FROM Wineries WHERE Country = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setString(1, country);
			rs = ps.executeQuery();
			while(rs.next()) {
				String wineryName = rs.getString("WineryName");
				String region = rs.getString("Region");
				String province = rs.getString("Province");
				String countryInDB = rs.getString("Country");
				Wineries winery = new Wineries(wineryName, region, province, countryInDB);
				wineries.add(winery);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return wineries;
	}
	
	public Wineries delete(Wineries winery) throws SQLException {
		String deleteSql = "DELETE FROM Wineries WHERE WineryName = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(deleteSql);
			ps.setString(1, winery.getWineryName());
			affectedRowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to delete the specified restaurant record!");
		}
		return null;
	}
}
