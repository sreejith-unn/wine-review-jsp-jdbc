package wineconnoisseur.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import wineconnoisseur.models.Customers;
import wineconnoisseur.models.Tasters;
import wineconnoisseur.models.Users;

public class CustomersDao extends UsersDao{
	private static CustomersDao instance = null;
	
	public static CustomersDao getInstance() {
		if(instance ==null) {
			instance = new CustomersDao();
		}
		return instance;
	}
	
	public Customers create(Customers customer) throws SQLException{
		Users user = create(new Users(customer.getUserName(),customer.getPassword(),
				customer.getFirstName(),customer.getLastName()));
		
		String insertTaster = "INSERT INTO customers(CustomerID,About) "
				+ "VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = ConnectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTaster);
			insertStmt.setInt(1, user.getUserId());
			insertStmt.setString(2, customer.getAbout());
			insertStmt.executeUpdate();
			
			customer.setUserId(user.getUserId());
			return customer;
		}catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(insertStmt);
		}
	}
	
	public Customers getCustomerById(int customerId) throws SQLException {
		String selectCustomer = "SELECT customers.CustomerID AS CustomerID, Username,"
				+ "FirstName,LastName,Password,About FROM customers INNER JOIN users"
				+ " ON customers.CustomerID=users.UserID WHERE customers.CustomerID=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = ConnectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCustomer);
			selectStmt.setInt(1, customerId);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String userName = results.getString("UserName");
				String password = results.getString("Password");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				
				String about = results.getString("About");
				
				Customers customer = new Customers(customerId,userName,password,firstName,
						lastName,about);
				
				
				return customer;
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
	
	public Customers update(Customers customer) throws SQLException {
		super.update(new Users(customer.getUserId(),customer.getUserName(),
				customer.getPassword(),customer.getFirstName(),customer.getLastName()));
		String updateSql = "UPDATE customers SET About = ? WHERE CustomerID = ?;";
		Connection connection = null;
		PreparedStatement ps = null;
		int affectedRowCount = -1;
		try {
			connection = ConnectionManager.getConnection();
			ps = connection.prepareStatement(updateSql);
			ps.setString(1, customer.getAbout());
			ps.setInt(2, customer.getUserId());
			affectedRowCount = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.closeConnection(connection);
			ConnectionManager.closeStatement(ps);
		}
		if (affectedRowCount == 0) {
			throw new SQLException("Failed to update the specified customer!");
		}
		customer = getCustomerById(customer.getUserId());
		return customer;
	}
	
	public Customers delete(Customers customer) throws SQLException {
		super.delete(customer);
		return null;
	}
}
