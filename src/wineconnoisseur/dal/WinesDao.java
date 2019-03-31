package wineconnoisseur.dal;

import wineconnoisseur.models.Wines;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class WinesDao {

	private static WinesDao instance = null;
	
	public static WinesDao getInstance() {
		if (instance == null) {
			instance = new WinesDao();
		}
		return instance;
	}
	
	public Wines create(Wines wine) throws SQLException {
		String insertSql = 
				"INSERT INTO Wines(Name, Description, Price, Variety, Vineyard, WineryName) "
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, wine.getName());
			ps.setString(2, wine.getDescription());
			ps.setInt(3, wine.getPrice());
			ps.setString(4, wine.getVariety());
			ps.setString(5, wine.getVineyard());
			ps.setString(6, wine.getWineryName());
			affectedRowCount = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			int wineId = -1;
			if (rs.next()) {
				wineId = rs.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			wine.setWineId(wineId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to insert the specified wine record into the database!");
		}
		System.out.println("Successfully created a new wine record \n");
		return wine;
	}
	
	public Wines getWineById(int wineId) throws SQLException {
		String selectSql = "SELECT * FROM Wines WHERE WineID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Wines wine = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, wineId);
			rs = ps.executeQuery();
			if (rs.next()) {
				int wineIdInDB = rs.getInt("WineID");
				String name = rs.getString("Name");
				String description = rs.getString("Description");
				int price = rs.getInt("Price");
				String variety = rs.getString("Variety");
				String vineyard = rs.getString("Vineyard");
				String wineryName = rs.getString("WineryName");
				wine = new Wines(wineIdInDB, name, description, price, variety, vineyard, wineryName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return wine;
	}
	
	public List<Wines> getWinesByWineryName(String wineryName) throws SQLException {
		List<Wines> wines = new ArrayList<Wines>();
		String selectSql = "SELECT * FROM Wines WHERE WineryName = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setString(1, wineryName);
			rs = ps.executeQuery();
			while(rs.next()) {
				int wineId = rs.getInt("WineId");
				String name = rs.getString("Name");
				String description = rs.getString("Description");
				int price = rs.getInt("Price");
				String variety = rs.getString("Variety");
				String vineyard = rs.getString("Vineyard");
				String wineryNameInDB = rs.getString("WineryName");
				Wines wine = new Wines(wineId, name, description, price, variety, vineyard, wineryNameInDB);
				wines.add(wine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return wines;
	}
	
	public List<Wines> getWinesByWineName(String wineName) throws SQLException {
		List<Wines> wines = new ArrayList<Wines>();
		String selectSql = "SELECT * FROM Wines WHERE Name LIKE ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setString(1, wineName+'%');
			rs = ps.executeQuery();
			while(rs.next()) {
				int wineId = rs.getInt("WineId");
				String name = rs.getString("Name");
				String description = rs.getString("Description");
				int price = rs.getInt("Price");
				String variety = rs.getString("Variety");
				String vineyard = rs.getString("Vineyard");
				String wineryNameInDB = rs.getString("WineryName");
				Wines wine = new Wines(wineId, name, description, price, variety, vineyard, wineryNameInDB);
				wines.add(wine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return wines;
	}
	
	public List<Wines> getAllWines() throws SQLException {
		List<Wines> wines = new ArrayList<Wines>();
		String selectSql = "SELECT * FROM Wines LIMIT 100;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int wineId = rs.getInt("WineId");
				String name = rs.getString("Name");
				String description = rs.getString("Description");
				int price = rs.getInt("Price");
				String variety = rs.getString("Variety");
				String vineyard = rs.getString("Vineyard");
				String wineryNameInDB = rs.getString("WineryName");
				Wines wine = new Wines(wineId, name, description, price, variety, vineyard, wineryNameInDB);
				wines.add(wine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return wines;
	}
	public Wines updatePrice(Wines wine, int newPrice) throws SQLException {
		String updateSql = "UPDATE Wines SET Price = ? WHERE WineID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(updateSql);
			ps.setInt(1, newPrice);
			ps.setInt(2, wine.getWineId());
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
		wine.setPrice(newPrice);
		return wine;
	}
	
	public Wines delete(Wines wine) throws SQLException {
		String deleteSql = "DELETE FROM Wines WHERE WineID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(deleteSql);
			ps.setInt(1, wine.getWineId());
			affectedRowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to delete the specified wine record!");
		}
		return null;
	}
}
