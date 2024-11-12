package coffee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private final String url = "jdbc:sqlserver://localhost:1433;databaseName=3Coffee;encrypt=false";
	private final String user = "sa";
	private String password;

	private Connection connection;
	private static Database connectDB;

	public static Database getInstance() {
		if (connectDB == null)
			connectDB = new Database();
		return connectDB;
	}

	public Database() {
		password = System.getenv("MSSQL_PASSWORD");
	}

	public Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, user, password);
		}

		return connection;
	}

	public void disconnect() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
}