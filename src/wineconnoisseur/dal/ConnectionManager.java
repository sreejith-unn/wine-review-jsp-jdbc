package wineconnoisseur.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	private static final String DB_USER_NAME = "root";
	private static final String DB_PASSWORD = "MySqlxiaof123";
	private static final String DB_HOST_NAME = "localhost";
	private static final int DB_PORT= 3306;
	private static final String SCHEMA_NAME = "WineReview";

	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Properties connectionProperties = new Properties();
			connectionProperties.put("user", DB_USER_NAME);
			connectionProperties.put("password", DB_PASSWORD);
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
			connection = DriverManager.getConnection(
			    "jdbc:mysql://" + DB_HOST_NAME + ":" + DB_PORT + "/" + SCHEMA_NAME + "?useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false",
			    connectionProperties);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return connection;
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeStatement(PreparedStatement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
