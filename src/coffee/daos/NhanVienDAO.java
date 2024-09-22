package coffee.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NhanVienDAO {

	public NhanVienDAO() {
		Connection conn;
		PreparedStatement stmt;
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://MSI;databaseName=3Coffee");
			stmt = conn.prepareStatement("SELECT * FROM NhanVien WHERE maNhanVien='NVQL-001'");
			ResultSet rs = stmt.executeQuery();
			rs.c
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
