package wineconnoisseur.dal;

import wineconnoisseur.models.Stores;
import wineconnoisseur.models.Tasters;
import wineconnoisseur.models.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StoresDao {

	private static StoresDao instance = null;
	
	public static StoresDao getInstance() {
		if (instance == null) {
			instance = new StoresDao();
		}
		return instance;
	}
	
	public Stores create(Stores store) throws SQLException {
		String insertSql = 
				"INSERT INTO Stores(Name, Street1, Street2, City, State, Zip) "
				+ "VALUES(?, ?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, store.getName());
			ps.setString(2, store.getStreet1());
			ps.setString(3, store.getStreet2());
			ps.setString(4, store.getCity());
			ps.setString(5, store.getState());
			ps.setInt(6, store.getZip());
			affectedRowCount = ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			int storeId = -1;
			if (rs.next()) {
				storeId = rs.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			store.setStoreId(storeId);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to insert the specified store record into the database!");
		}
		System.out.println("Successfully created a new store record \n");
		return store;
	}
	
	public Stores getStoreById(int storeId) throws SQLException {
		String selectSql = "SELECT * FROM Stores WHERE StoreID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Stores store = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, storeId);
			rs = ps.executeQuery();
			if (rs.next()) {
				int storeIdInDB = rs.getInt("StoreID");
				String name = rs.getString("Name");
				String street1 = rs.getString("Street1");
				String street2 = rs.getString("Street2");
				String city = rs.getString("City");
				String state = rs.getString("State");
				int zip = rs.getInt("Zip");
				store = new Stores(storeIdInDB, name, street1, street2, city, state, zip);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return store;
	}
	
	public List<Stores> getStoresByZip(int zip) throws SQLException {
		List<Stores> stores = new ArrayList<Stores>();
		String selectSql = "SELECT * FROM Stores WHERE Zip = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(selectSql);
			ps.setInt(1, zip);
			rs = ps.executeQuery();
			while(rs.next()) {
				int storeId = rs.getInt("StoreId");
				String name = rs.getString("Name");
				String street1 = rs.getString("Street1");
				String street2 = rs.getString("Street2");
				String city = rs.getString("City");
				String state = rs.getString("State");
				int zipInDB = rs.getInt("Zip");
				Stores store = new Stores(storeId, name, street1, street2, city, state, zipInDB);
				stores.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
			ConnectionManager.closeResultSet(rs);
		}
		return stores;
	}
	
	public Stores update(Stores store) throws SQLException {
	
		String updateSql = "UPDATE stores SET Name = ?, Street1 = ?, Street2 = ?,"
				+ "City = ?, State = ?, Zip = ? WHERE StoreID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(updateSql);
			ps.setString(1, store.getName());
			ps.setString(2, store.getStreet1());
			ps.setString(3, store.getStreet2());
			ps.setString(4, store.getCity());
			ps.setString(5, store.getState());
			ps.setInt(6, store.getZip());
			ps.setInt(7, store.getStoreId());
			affectedRowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to update the specified store!");
		}
		store = getStoreById(store.getStoreId());
		return store;
	}
	
	public Stores delete(Stores store) throws SQLException {
		String deleteSql = "DELETE FROM Stores WHERE StoreID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(deleteSql);
			ps.setInt(1, store.getStoreId());
			affectedRowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to delete the specified store record!");
		}
		return null;
	}
}
