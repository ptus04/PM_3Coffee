package coffee.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private final String url = "jdbc:sqlserver://localhost:1433;databaseName=3Coffee;encrypt=false";
//	private final String url = "jdbc:sqlserver://phung-qlbvt.database.windows.net;databaseName=3Coffee;encrypt=false";
	private final String user = "sa";
//	private final String user = "app";
	private String password;

	private Connection connection;
	private static Database instance;

	public static Database getInstance() {
		if (instance == null)
			instance = new Database();
		return instance;
	}

	public Database() {
		password = System.getenv("MSSQL_PASSWORD");
//		password = "12345678Ab!";
	}

	public Connection getConnection() throws SQLException {
		if (connection == null || connection.isClosed())
			connection = DriverManager.getConnection(url, user, password);
		return connection;
	}

	public void disconnect() throws SQLException {
		if (connection != null)
			connection.close();
	}
}