package coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import coffee.entity.NhanVien;
import coffee.entity.SanPham;
import coffee.entity.TaiKhoan;

public class TaiKhoanDAO {

	private static TaiKhoanDAO instance;

	public static TaiKhoanDAO getInstance() {
		if (instance == null)
			instance = new TaiKhoanDAO();
		return instance;
	}

	public List<TaiKhoan> getAll() throws SQLException {
		String sql = "SELECT * FROM TaiKhoan";
		Connection conn = Database.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<TaiKhoan> list = new ArrayList<TaiKhoan>();
		while (rs.next()) {
			String tenDangNhap = rs.getString(1);
			String matKhau = rs.getString(2);
			NhanVien nhanVien = new NhanVien(rs.getString(3));

			list.add(new TaiKhoan(tenDangNhap, matKhau, nhanVien));
		}

		return list;
	}

	public TaiKhoan get(String tenDangNhap, String matKhau) throws SQLException {
		String sql = "SELECT maNhanVien FROM TaiKhoan WHERE tenDangNhap = ? AND matKhau = ?";
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, tenDangNhap);
		stmt.setString(2, matKhau);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			NhanVien nhanVien = new NhanVien(rs.getString(1));
			return new TaiKhoan(tenDangNhap, matKhau, nhanVien);
		}

		return null;
	}

	public void them(TaiKhoan entity) throws SQLException {
		String sql = "INSERT INTO TaiKhoan VALUES(?, ?, ?)";
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, entity.getTenDangNhap());
		stmt.setString(2, entity.getMatKhau());
		stmt.setString(3, entity.getNhanVien().getMaNhanVien());
		int rs = stmt.executeUpdate();
	}

}
