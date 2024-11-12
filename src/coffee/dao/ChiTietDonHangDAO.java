package coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import coffee.entity.ChiTietDonHang;
import coffee.entity.DonHang;
import coffee.entity.SanPham;

public class ChiTietDonHangDAO {

	private static ChiTietDonHangDAO instance;

	public static ChiTietDonHangDAO getInstance() {
		if (instance == null)
			instance = new ChiTietDonHangDAO();
		return instance;
	}

	public List<ChiTietDonHang> getAll() throws SQLException {
		String sql = "SELECT * FROM ChiTietDonHang";
		Connection conn = Database.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<ChiTietDonHang> list = new ArrayList<ChiTietDonHang>();
		while (rs.next()) {
			int soLuong = rs.getInt(1);
			String ghiChu = rs.getString(2);
			SanPham sanPham = new SanPham(rs.getString(3));
			DonHang donHang = new DonHang(rs.getString(4));

			list.add(new ChiTietDonHang(soLuong, ghiChu, sanPham, donHang));
		}

		return list;
	}

	public ChiTietDonHang getByMaDonHang(String id) throws SQLException {
		String sql = "SELECT * FROM ChiTietDonHang WHERE maDonHang LIKE ?";
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			int soLuong = rs.getInt(1);
			String ghiChu = rs.getString(2);
			SanPham sanPham = new SanPham(rs.getString(3));
			DonHang donHang = new DonHang(rs.getString(4));

			return new ChiTietDonHang(soLuong, ghiChu, sanPham, donHang);
		}

		return null;
	}

	public ChiTietDonHang getByMaSanPham(String id) throws SQLException {
		String sql = "SELECT * FROM ChiTietDonHang WHERE maSanPham LIKE ?";
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery(sql);

		if (rs.next()) {
			int soLuong = rs.getInt(1);
			String ghiChu = rs.getString(2);
			SanPham sanPham = new SanPham(rs.getString(3));
			DonHang donHang = new DonHang(rs.getString(4));

			return new ChiTietDonHang(soLuong, ghiChu, sanPham, donHang);
		}

		return null;
	}

	public boolean them(ChiTietDonHang entity) throws SQLException {
		String sql = "INSERT INTO ChiTietDonHang VALUES(?, ?, ?, ?)";
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, entity.getSoLuong());
		stmt.setString(2, entity.getGhiChu());
		stmt.setString(3, entity.getSanPham().getMaSanPham());
		stmt.setString(4, entity.getDonHang().getMaDonHang());
		int rs = stmt.executeUpdate();

		if (rs == 1) {
			return true;
		}

		return false;
	}

	public boolean capNhat(ChiTietDonHang entity) throws SQLException {
		String sql = "UPDATE ChiTietDonHang SET soLuong = ?, ghiChu = ? WHERE maSanPham LIKE ? AND maDonHang LIKE ?";
		Connection conn = Database.getInstance().getConnection();
		conn.setAutoCommit(false);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setDouble(1, entity.getSoLuong());
		stmt.setString(2, entity.getGhiChu());
		stmt.setString(3, entity.getSanPham().getMaSanPham());
		stmt.setString(4, entity.getDonHang().getMaDonHang());
		int rs = stmt.executeUpdate();

		if (rs == 1) {
			conn.commit();
			return true;
		}

		conn.rollback();
		return false;
	}

	public boolean xoa(ChiTietDonHang entity) throws SQLException {
		String sql = "DELETE FROM ChiTietDonHang WHERE maSanPham LIKE ? AND maDonHang LIKE ?";
		Connection conn = Database.getInstance().getConnection();
		conn.setAutoCommit(false);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, entity.getSanPham().getMaSanPham());
		stmt.setString(2, entity.getDonHang().getMaDonHang());
		int rs = stmt.executeUpdate();

		if (rs == 1) {
			conn.commit();
			return true;
		}

		conn.rollback();
		return false;
	}

}
