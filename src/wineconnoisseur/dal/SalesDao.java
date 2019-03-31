package wineconnoisseur.dal;

import wineconnoisseur.models.Sales;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesDao {

	private static SalesDao instance = null;
	
	public static SalesDao getInstance() {
		if (instance == null) {
			instance = new SalesDao();
		}
		return instance;
	}
	
	public Sales create(Sales sale) throws SQLException {
		String insertSql = 
				"INSERT INTO Sales(WineID, StoreID, NumOfBottles) "
				+ "VALUES(?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, sale.getWineId());
			ps.setInt(2, sale.getStoreId());
			ps.setInt(3, sale.getNumOfBottles());
			affectedRowCount = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			int saleId = -1;
			if (rs.next()) {
				saleId = rs.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			sale.setSaleId(saleId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to insert the specified sale record into the database!");
		}
		System.out.println("Successfully created a new sale record \n");
		return sale;
	}
	
	public Sales getSaleById(int saleId) throws SQLException {
		String selectSql = "SELECT * FROM Sales WHERE SaleID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Sales sale = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, saleId);
			rs = ps.executeQuery();
			if (rs.next()) {
				int saleIdInDB = rs.getInt("SaleID");
				int wineId = rs.getInt("WineID");
				int storeId = rs.getInt("StoreID");
				Date madeDate = new Date(rs.getTimestamp("MadeDate").getTime());
				int numOfBottles = rs.getInt("NumOfBottles");
				sale = new Sales(saleIdInDB, wineId, storeId, madeDate, numOfBottles);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return sale;
	}
	
	public List<Sales> getSalesByStoreName(String storeName) throws SQLException {
		List<Sales> sales = new ArrayList<Sales>();
		String selectSql = 
				"SELECT SaleID, WineID, Sales.StoreID, MadeDate, NumOfBottles " 
				+ "FROM Sales INNER JOIN Stores " 
				+ "ON Sales.StoreID = Stores.StoreID " 
				+ "WHERE Name = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setString(1, storeName);
			rs = ps.executeQuery();
			while(rs.next()) {
				int saleId = rs.getInt("SaleID");
				int wineId = rs.getInt("WineID");
				int storeId = rs.getInt("StoreID");
				Date madeDate = new Date(rs.getTimestamp("MadeDate").getTime());
				int numOfBottles = rs.getInt("NumOfBottles");
				Sales sale = new Sales(saleId, wineId, storeId, madeDate, numOfBottles);
				sales.add(sale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return sales;
	}
	
	public List<Sales> getSalesByStoreId(int storeId) throws SQLException {
		List<Sales> sales = new ArrayList<Sales>();
		String selectSql = 
				"SELECT SaleID, WineID, Sales.StoreID, MadeDate, NumOfBottles " 
				+ "FROM Sales INNER JOIN Stores " 
				+ "ON Sales.StoreID = Stores.StoreID " 
				+ "WHERE Stores.StoreID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, storeId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int saleId = rs.getInt("SaleID");
				int wineId = rs.getInt("WineID");
				Date madeDate = new Date(rs.getTimestamp("MadeDate").getTime());
				int numOfBottles = rs.getInt("NumOfBottles");
				Sales sale = new Sales(saleId, wineId, storeId, madeDate, numOfBottles);
				sales.add(sale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return sales;
	}
	
	public List<Sales> getSalesByWineId(int wineId) throws SQLException {
		List<Sales> sales = new ArrayList<Sales>();
		String selectSql = "SELECT * FROM Sales WHERE WineID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, wineId);
			rs = ps.executeQuery();
			while(rs.next()) {
				int saleId = rs.getInt("SaleID");
				int storeId = rs.getInt("StoreID");
				Date madeDate = new Date(rs.getTimestamp("MadeDate").getTime());
				int numOfBottles = rs.getInt("NumOfBottles");
				Sales sale = new Sales(saleId, wineId, storeId, madeDate, numOfBottles);
				sales.add(sale);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return sales;
	}
	
	public Sales delete(Sales sale) throws SQLException {
		String deleteSql = "DELETE FROM Sales WHERE SaleID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(deleteSql);
			ps.setInt(1, sale.getSaleId());
			affectedRowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to delete the specified sale record!");
		}
		return null;
	}
}
