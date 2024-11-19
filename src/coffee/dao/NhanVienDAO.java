package coffee.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import coffee.entity.NhanVien;
import coffee.entity.TrangThaiLamViec;

public class NhanVienDAO {

	private static NhanVienDAO instance;

	public static NhanVienDAO getInstance() {
		if (instance == null)
			instance = new NhanVienDAO();
		return instance;
	}

	public List<NhanVien> getAllNhanVien() {
		List<NhanVien> nhanVienList = new ArrayList<>();
		String sql = "SELECT * FROM NhanVien";
		try (Statement stmt = Database.getInstance().getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				NhanVien nv = new NhanVien();
				nv.setMaNhanVien(rs.getString("maNhanVien"));
				nv.setHoTen(rs.getString("hoTen"));
				nv.setGioiTinh(rs.getBoolean("gioiTinh"));
				nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
				nv.setSoCanCuoc(rs.getString("soCanCuoc"));
				nv.setSoDienThoai(rs.getString("soDienThoai"));
				nv.setDiaChi(rs.getString("diaChi"));
				nv.setHeSoLuong(rs.getFloat("heSoLuong"));
				nv.setLaQuanLy(rs.getBoolean("laQuanLy"));
				nv.setTrangThaiLamViec(TrangThaiLamViec.fromValue(rs.getInt("trangThaiLamViec")));
				nv.setHinhAnh(rs.getString("hinhAnh"));
				nhanVienList.add(nv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhanVienList;
	}

	public NhanVien getByNhanVien(NhanVien nhanVien) throws SQLException {
		String sql = "SELECT * FROM NhanVien WHERE maNhanVien = ?";
		PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
		stmt.setString(1, nhanVien.getMaNhanVien());
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			NhanVien nv = new NhanVien();
			nv.setMaNhanVien(rs.getString("maNhanVien"));
			nv.setHoTen(rs.getString("hoTen"));
			nv.setGioiTinh(rs.getBoolean("gioiTinh"));
			nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
			nv.setSoCanCuoc(rs.getString("soCanCuoc"));
			nv.setSoDienThoai(rs.getString("soDienThoai"));
			nv.setDiaChi(rs.getString("diaChi"));
			nv.setHeSoLuong(rs.getFloat("heSoLuong"));
			nv.setLaQuanLy(rs.getBoolean("laQuanLy"));
			nv.setTrangThaiLamViec(TrangThaiLamViec.fromValue(rs.getInt("trangThaiLamViec")));
			nv.setHinhAnh(rs.getString("hinhAnh"));
			return nv;
		}

		return null;
	}

	public void addNhanVien(NhanVien nv) {
		String sql = "INSERT INTO NhanVien (maNhanVien, hoTen, gioiTinh, ngaySinh, soCanCuoc, soDienThoai, diaChi, heSoLuong, laQuanLy, trangThaiLamViec, hinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement pstmt = Database.getInstance().getConnection().prepareStatement(sql)) {
			pstmt.setString(1, nv.getMaNhanVien());
			pstmt.setString(2, nv.getHoTen());
			pstmt.setBoolean(3, nv.isGioiTinh());
			pstmt.setDate(4, Date.valueOf(nv.getNgaySinh()));
			pstmt.setString(5, nv.getSoCanCuoc());
			pstmt.setString(6, nv.getSoDienThoai());
			pstmt.setString(7, nv.getDiaChi());
			pstmt.setFloat(8, nv.getHeSoLuong());
			pstmt.setBoolean(9, nv.isLaQuanLy());
			pstmt.setInt(10, nv.getTrangThaiLamViec().getValue());
			pstmt.setString(11, nv.getHinhAnh());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public NhanVien getNhanVienChiTiet(String maNhanVien) throws SQLException {
	    NhanVien nv = null;
	    String sql = "SELECT * FROM NhanVien WHERE maNhanVien = ?";

	    try (PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql)) {
	        stmt.setString(1, maNhanVien);
	        try (ResultSet rs = stmt.executeQuery()) {
	            if (rs.next()) {
	                nv = new NhanVien();
	                nv.setMaNhanVien(rs.getString("maNhanVien"));
	                nv.setHoTen(rs.getString("hoTen"));
	                nv.setGioiTinh(rs.getBoolean("gioiTinh"));
	                nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
	                nv.setSoCanCuoc(rs.getString("soCanCuoc"));
	                nv.setSoDienThoai(rs.getString("soDienThoai"));
	                nv.setDiaChi(rs.getString("diaChi"));
	                nv.setHeSoLuong(rs.getFloat("heSoLuong"));
	                nv.setLaQuanLy(rs.getBoolean("laQuanLy"));
	                nv.setTrangThaiLamViec(TrangThaiLamViec.fromValue(rs.getInt("trangThaiLamViec")));
	                nv.setHinhAnh(rs.getString("hinhAnh"));
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return nv;
	}

	public List<NhanVien> timKiemNhanVien(String maNhanVien) throws SQLException {
		List<NhanVien> nhanVienList = new ArrayList<>();
		String sql = "SELECT * FROM NhanVien WHERE maNhanVien LIKE ?";

		try (PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql)) {
			stmt.setString(1, "%" + maNhanVien + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					NhanVien nv = new NhanVien();
					nv.setMaNhanVien(rs.getString("maNhanVien"));
					nv.setHoTen(rs.getString("hoTen"));
					nv.setGioiTinh(rs.getBoolean("gioiTinh"));
					nv.setNgaySinh(rs.getDate("ngaySinh").toLocalDate());
					nv.setSoCanCuoc(rs.getString("soCanCuoc"));
					nv.setSoDienThoai(rs.getString("soDienThoai"));
					nv.setDiaChi(rs.getString("diaChi"));
					nv.setHeSoLuong(rs.getFloat("heSoLuong"));
					nv.setLaQuanLy(rs.getBoolean("laQuanLy"));
					nv.setTrangThaiLamViec(TrangThaiLamViec.fromValue(rs.getInt("trangThaiLamViec")));
					nv.setHinhAnh(rs.getString("hinhAnh"));
					nhanVienList.add(nv);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nhanVienList;
	}

}