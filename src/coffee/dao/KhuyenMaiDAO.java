package coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import coffee.entity.KhuyenMai;

public class KhuyenMaiDAO {

	private static KhuyenMaiDAO instance;

	public static KhuyenMaiDAO getInstance() {
		if (instance == null)
			instance = new KhuyenMaiDAO();
		return instance;
	}

	public List<KhuyenMai> getAll() throws SQLException {
		String sql = "SELECT * FROM KhuyenMai";
		Connection conn = Database.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<KhuyenMai> list = new ArrayList<KhuyenMai>();
		while (rs.next()) {
			String maKhuyenMai = rs.getString(1);
			Double triGia = rs.getDouble(2);
			LocalDateTime ngayHieuLuc = rs.getTimestamp(3).toLocalDateTime();
			LocalDateTime ngayHetHan = rs.getTimestamp(4).toLocalDateTime();
			String ghiChu = rs.getString(5);

			list.add(new KhuyenMai(maKhuyenMai, triGia, ngayHieuLuc, ngayHetHan, ghiChu));
		}

		return list;
	}

	public KhuyenMai getById(String id) throws SQLException {
		String sql = "SELECT * FROM KhuyenMai WHERE maKhuyenMai LIKE ?";
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			String maKhuyenMai = rs.getString(1);
			Double triGia = rs.getDouble(2);
			LocalDateTime ngayHieuLuc = rs.getTimestamp(3).toLocalDateTime();
			LocalDateTime ngayHetHan = rs.getTimestamp(4).toLocalDateTime();
			String ghiChu = rs.getString(5);
			return new KhuyenMai(maKhuyenMai, triGia, ngayHieuLuc, ngayHetHan, ghiChu);
		}

		return null;
	}

	public boolean them(KhuyenMai entity) throws SQLException {
		String sql = "INSERT INTO KhuyenMai VALUES(?, ?, ?, ?, ?)";
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, entity.getMaKhuyenMai());
		stmt.setDouble(2, entity.getTriGia());
		stmt.setTimestamp(3, Timestamp.valueOf(entity.getNgayHieuLuc()));
		stmt.setTimestamp(4, Timestamp.valueOf(entity.getNgayHetHan()));
		stmt.setString(5, entity.getGhiChu());
		int rs = stmt.executeUpdate();

		if (rs == 1) {
			return true;
		}

		return false;
	}

	public boolean capNhat(KhuyenMai entity) throws SQLException {
		String sql = "UPDATE KhuyenMai SET triGia = ?, ngayHieuLuc = ?, ngayHetHan = ?, ghiChu = ? WHERE maKhuyenMai LIKE ?";
		Connection conn = Database.getInstance().getConnection();
		conn.setAutoCommit(false);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setDouble(1, entity.getTriGia());
		stmt.setTimestamp(2, Timestamp.valueOf(entity.getNgayHieuLuc()));
		stmt.setTimestamp(3, Timestamp.valueOf(entity.getNgayHetHan()));
		stmt.setString(4, entity.getGhiChu());
		stmt.setString(5, entity.getMaKhuyenMai());
		int rs = stmt.executeUpdate();

		if (rs == 1) {
			conn.commit();
			return true;
		}

		conn.rollback();
		return false;
	}

	public boolean xoa(String id) throws SQLException {
		String sql = "DELETE FROM KhuyenMai WHERE maKhuyenMai LIKE ?";
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
