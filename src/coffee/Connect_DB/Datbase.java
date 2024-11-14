package coffee.Connect_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import coffee.dao.Database;




public class Datbase {

    // Chuỗi kết nối SQL Server với tham số trustServerCertificate=true
    private final String url = "jdbc:sqlserver://phung-qlbvt.database.windows.net;databaseName=3Coffee;encrypt=true;trustServerCertificate=true;";
    private final String user = "sa";
    private String password;

    public Connection connection;
    public static Database connectDB;

    // Singleton: Lấy đối tượng duy nhất của lớp Database
    public static Database getInstance() {
        if (connectDB == null) {
            connectDB = new Database();
        }

        return connectDB;
    }

    // Constructor để lấy mật khẩu từ biến môi trường
    public void Database() {
        password = System.getenv("MSSQL_PASSWORD");  // Lấy mật khẩu từ biến môi trường MSSQL_PASSWORD
        if (password == null) {
            throw new IllegalArgumentException("MSSQL_PASSWORD environment variable is not set.");
        }
    }

    // Phương thức để kết nối tới cơ sở dữ liệu
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    // Phương thức để ngắt kết nối
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
