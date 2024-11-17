package coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coffee.entity.*;

public class ChiTietCaLamViecDAO {

	// Phương thức để thêm chi tiết ca làm việc
	public void addChiTietCaLamViec(ChiTietCaLamViec chiTiet) throws SQLException {
		String sql = "INSERT INTO ChiTietCaLamViec(maNhanVien, maCaLam) VALUES(?, ?)";
		try (PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql)) {
			stmt.setString(1, chiTiet.getNhanvien().getMaNhanVien());
			stmt.setString(2, chiTiet.getCalam().getMaCaLam());
			stmt.executeUpdate();
		}
	}

	// Phương thức để lấy chi tiết ca làm việc của một nhân viên
	public List<ChiTietCaLamViec> getChiTietByMaNhanVien(String maNhanVien) throws SQLException {
		List<ChiTietCaLamViec> chiTietList = new ArrayList<>();
		String sql = "SELECT * FROM ChiTietCaLamViec WHERE maNhanVien = ?";
		try (PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql)) {
			stmt.setString(1, maNhanVien);
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					NhanVien nhanVien = new NhanVien();
					nhanVien.setMaNhanVien(rs.getString("maNhanVien"));

			

					CaLamViec caLam = new CaLamViec(rs.getString("maCaLam"), "", null, null, "");
					caLam.setTenCaLam(rs.getString("maCaLam"));
					
					// caLam = caLamViecDAO.getCaLamByMa(maCaLam); // Nếu cần lấy thêm thông tin

					ChiTietCaLamViec chiTiet = new ChiTietCaLamViec(nhanVien, caLam);
					chiTietList.add(chiTiet);
				}
			}
		}
		return chiTietList;
	}

	// Phương thức lấy theo mã nhân viên và mã ca làm việc
	public ChiTietCaLamViec getById(String maNhanVien, String maCaLam) throws SQLException {
		String sql = "SELECT * FROM ChiTietCaLamViec WHERE maNhanVien = ? AND maCaLam = ?";
		try (PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql)) {
			stmt.setString(1, maNhanVien);
			stmt.setString(2, maCaLam);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					NhanVien nhanVien = new NhanVien();
					nhanVien.setMaNhanVien(rs.getString("maNhanVien"));

					CaLamViec caLam = new CaLamViec(rs.getString("maCaLam"), "", null, null, "");
					return new ChiTietCaLamViec(nhanVien, caLam);
				}
			}
		}
		return null;
	}

	// Phương thức để xóa chi tiết ca làm việc
	public void deleteChiTietCaLamViec(String maNhanVien, String maCaLam) throws SQLException {
		String sql = "DELETE FROM ChiTietCaLamViec WHERE maNhanVien = ? AND maCaLam = ?";
		try (PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql)) {
			stmt.setString(1, maNhanVien);
			stmt.setString(2, maCaLam);
			stmt.executeUpdate();
		}
	}

}
