package coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import coffee.entity.KhachHang;

public class KhachHangDAO {

	private static KhachHangDAO instance;

	public static KhachHangDAO getInstance() {
		if (instance == null)
			instance = new KhachHangDAO();
		return instance;
	}

	public List<KhachHang> getAll() throws SQLException {
		String sql = "SELECT * FROM KhachHang";
		Connection conn = Database.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<KhachHang> list = new ArrayList<KhachHang>();
		while (rs.next()) {
			String soDienThoai = rs.getString(1);
			String tenKhachHang = rs.getString(2);

			list.add(new KhachHang(soDienThoai, tenKhachHang));
		}

		return list;
	}

	public KhachHang getById(String id) throws SQLException {
		String sql = "SELECT * FROM KhachHang WHERE soDienThoai LIKE ?";
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			String soDienThoai = rs.getString(1);
			String tenKhachHang = rs.getString(2);
			return new KhachHang(soDienThoai, tenKhachHang);
		}

		return null;
	}

	public boolean them(KhachHang entity) throws SQLException {
		String sql = "INSERT INTO KhachHang VALUES (?, ?)";
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, entity.getSoDienThoai());
		stmt.setString(2, entity.getTenKhachHang());
		int rs = stmt.executeUpdate();

		if (rs == 1) {
			return true;
		}

		return false;
	}

	public boolean capNhat(KhachHang entity) throws SQLException {
		String sql = "UPDATE KhachHang SETtenKhachHang = ? WHERE soDienThoai LIKE ?";
		Connection conn = Database.getInstance().getConnection();
		conn.setAutoCommit(false);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, entity.getTenKhachHang());
		stmt.setString(2, entity.getSoDienThoai());
		int rs = stmt.executeUpdate();

		if (rs == 1) {
			conn.commit();
			return true;
		}

		conn.rollback();
		return false;
	}

	public boolean xoa(String id) throws SQLException {
		String sql = "DELETE FROM KhachHang WHERE soDienThoai LIKE ?";
		Connection conn = Database.getInstance().getConnection();
		conn.setAutoCommit(false);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		int rs = stmt.executeUpdate();

		if (rs == 1) {
			conn.commit();
			return true;
		}

		conn.rollback();
		return false;
	}

}
