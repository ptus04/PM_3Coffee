package coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import coffee.entity.SanPham;

public class SanPhamDAO {

	private static SanPhamDAO instance;

	public static SanPhamDAO getInstance() {
		if (instance == null)
			instance = new SanPhamDAO();
		return instance;
	}

	public List<SanPham> getAll() throws SQLException {
		String sql = "SELECT * FROM SanPham";
		Connection conn = Database.getInstance().getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<SanPham> list = new ArrayList<SanPham>();
		while (rs.next()) {
			String maSanPham = rs.getString(1);
			String tenSanPham = rs.getString(2);
			String loaiSanPham = rs.getString(3);
			double donGia = rs.getDouble(4);
			String thanhPhan = rs.getString(5);
			String moTa = rs.getString(6);
			boolean con = rs.getBoolean(7);
			boolean laDoAn = rs.getBoolean(8);
			String hinhAnh = rs.getString(9);

			list.add(new SanPham(maSanPham, tenSanPham, loaiSanPham, donGia, thanhPhan, moTa, con, laDoAn, hinhAnh));
		}

		return list;
	}

	public SanPham getById(String id) throws SQLException {
		String sql = "SELECT * FROM SanPham WHERE maSanPham LIKE ?";
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, id);
		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			String maSanPham = rs.getString(1);
			String tenSanPham = rs.getString(2);
			String loaiSanPham = rs.getString(3);
			double donGia = rs.getDouble(4);
			String thanhPhan = rs.getString(5);
			String moTa = rs.getString(6);
			boolean con = rs.getBoolean(7);
			boolean laDoAn = rs.getBoolean(8);
			String hinhAnh = rs.getString(9);
			return new SanPham(maSanPham, tenSanPham, loaiSanPham, donGia, thanhPhan, moTa, con, laDoAn, hinhAnh);
		}

		return null;
	}

	public void them(SanPham sanPham) throws SQLException {
		String sql = "INSERT INTO SanPham VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection conn = Database.getInstance().getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, sanPham.getMaSanPham());
		stmt.setString(2, sanPham.getTenSanPham());
		stmt.setString(3, sanPham.getLoaiSanPham());
		stmt.setDouble(4, sanPham.getDonGia());
		stmt.setString(5, sanPham.getThanhPhan());
		stmt.setString(6, sanPham.getMoTa());
		stmt.setBoolean(7, sanPham.isCon());
		stmt.setBoolean(8, sanPham.isLaDoAn());
		stmt.setString(9, sanPham.getHinhAnh());
		int rs = stmt.executeUpdate();
	}

	public boolean capNhat(SanPham sanPham) throws SQLException {
		String sql = "UPDATE SanPham SET tenSanPham = ?, loaiSanPham = ?, donGia = ?, thanhPhan = ?, moTa = ?, con = ?, laDoAn = ?, hinhAnh = ? WHERE maSanPham LIKE ?";
		Connection conn = Database.getInstance().getConnection();
		conn.setAutoCommit(false);
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, sanPham.getTenSanPham());
		stmt.setString(2, sanPham.getLoaiSanPham());
		stmt.setDouble(3, sanPham.getDonGia());
		stmt.setString(4, sanPham.getThanhPhan());
		stmt.setString(5, sanPham.getMoTa());
		stmt.setBoolean(6, sanPham.isCon());
		stmt.setBoolean(7, sanPham.isLaDoAn());
		stmt.setString(8, sanPham.getHinhAnh());
		stmt.setString(9, sanPham.getMaSanPham());
		int rs = stmt.executeUpdate();

		if (rs == 1) {
			conn.commit();
			return true;
		}

		conn.rollback();
		return false;
	}

	public boolean xoa(String id) throws SQLException {
		String sql = "DELETE FROM SanPham WHERE maSanPham LIKE ?";
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
	public ArrayList<String> getID() throws SQLException {
		ArrayList<String> list=null;
		try {
			String sql = "SELECT maSanPham FROM SanPham";
			Connection conn = Database.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			list = new ArrayList<String>();
			while (rs.next()) {
				String maSanPham = rs.getString(1);

				list.add(maSanPham);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

}
