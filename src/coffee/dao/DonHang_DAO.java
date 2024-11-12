package coffee.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import coffee.entity.DonHang;
import coffee.entity.KhachHang;
import coffee.entity.KhuyenMai;
import coffee.entity.NhanVien;

public class DonHang_DAO {

	private static DonHang_DAO instance;

	public static DonHang_DAO getInstance() {
		if (instance == null) {
			instance = new DonHang_DAO();
		}
		return instance;
	}

	public int add(DonHang t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection c = Database.getInstance().getConnection();

			String sql = "INSERT INTO [dbo].[DonHang] "
					+ "(maDonHang, khachTra, thue, thoiGianTao, thoiGianIn, ghiChu, "
					+ "phuongThucThanhToan, maNhanVien, soDienThoai, maKhuyenMai) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement st = c.prepareStatement(sql);

			st.setString(1, t.getMaDonHang());
			st.setDouble(2, t.getKhachTra());
			st.setBigDecimal(3, t.getThue());
			st.setTimestamp(4, Timestamp.valueOf(t.getThoiGianTao()));
			st.setTimestamp(5, Timestamp.valueOf(t.getThoiGianIn()));
			st.setString(6, t.getGhiChu());
			st.setInt(7, t.getPhuongThucThanhToan());
			st.setString(8, t.getNhanvien().getMaNhanVien());
			st.setString(9, t.getKhachHang().getSoDienThoai());
			st.setString(10, t.getKhuyenmai().getMaKhuyenMai());

			result = st.executeUpdate(sql);

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int remove(DonHang t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection c = Database.getInstance().getConnection();

			String sql = "DELETE FROM [dbo].[DonHang] " + "WHERE DonHang.maDonHang = ? ";

			PreparedStatement st = c.prepareStatement(sql);

			st.setString(1, t.getMaDonHang());

			result = st.executeUpdate(sql);

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int update(DonHang t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection c = Database.getInstance().getConnection();

			String sql = "UPDATE [dbo].[DonHang] "
					+ "SET DonHang.khachTra = ? , DonHang.thue = ? , DonHang.thoiGianTao = ? , "
					+ "DonHang.thoiGianIn = ? , DonHang.ghichu = ? , DonHang.phuongThucThanhToan = ? ,"
					+ "DonHang.maNhanVien = ? , DonHang.soDienThoai = ? , DonHang.maKhuyenMai = ? "
					+ "WHERE DonHang.maDonHang = ? ";

			PreparedStatement st = c.prepareStatement(sql);

			st.setDouble(1, t.getKhachTra());
			st.setBigDecimal(2, t.getThue());
			st.setTimestamp(3, Timestamp.valueOf(t.getThoiGianTao()));
			st.setTimestamp(4, Timestamp.valueOf(t.getThoiGianIn()));
			st.setString(5, t.getGhiChu());
			st.setInt(6, t.getPhuongThucThanhToan());
			st.setString(7, t.getNhanvien().getMaNhanVien());
			st.setString(8, t.getKhachHang().getSoDienThoai());
			st.setString(9, t.getKhuyenmai().getMaKhuyenMai());
			st.setString(10, t.getMaDonHang());

			result = st.executeUpdate(sql);

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<DonHang> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<DonHang> listofDonHang = new ArrayList<DonHang>();
		try {
			Connection c = Database.getInstance().getConnection();

			Statement st = c.createStatement();

			String sql = "SELECT * FROM [dbo].[DonHang]";

			ResultSet result = st.executeQuery(sql);

			while (result.next()) {
				String maDonHang = result.getString("maDonHang");
				Double khachTra = result.getDouble("khachTra");
				BigDecimal thue = result.getBigDecimal("thue");
				Timestamp thoiGianTao_temp = result.getTimestamp("thoiGianTao");
				LocalDateTime thoiGianTao = thoiGianTao_temp.toLocalDateTime();
				Timestamp thoiGianIn_temp = result.getTimestamp("thoiGianIn");
				LocalDateTime thoiGianIn = thoiGianIn_temp.toLocalDateTime();
				String ghiChu = result.getString("ghiChu");
				int phuongThucThanhToan = result.getInt("phuongThucThanhToan");
				String maNhanVien = result.getString("maNhanVien");
				String soDienThoai = result.getString("soDienThoai");
				String maKhuyenMai = result.getString("maKhuyenMai");

				NhanVien nhanVien = new NhanVien(maNhanVien);
				KhachHang khachHang = new KhachHang(soDienThoai);
				KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai);
				DonHang donHang = new DonHang(maDonHang, phuongThucThanhToan, thue, thoiGianTao, thoiGianIn, ghiChu,
						phuongThucThanhToan, khachHang, nhanVien, khuyenMai);

				listofDonHang.add(donHang);
			}

//			System.out.println("Bạn đã thực thi: " + sql);
//			System.out.println("Có "+result+" dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listofDonHang;
	}

	public DonHang selectById(DonHang t) {
		// TODO Auto-generated method stub
		DonHang info = null;
		try {
			Connection c = Database.getInstance().getConnection();

			String sql = "SELECT * FROM [dbo].[DonHang]" + "WHERE DonHang = ?";

			PreparedStatement st = c.prepareStatement(sql);

			st.setString(1, t.getMaDonHang());

			ResultSet result = st.executeQuery(sql);

			while (result.next()) {
				String maDonHang = result.getString("maDonHang");
				Double khachTra = result.getDouble("khachTra");
				BigDecimal thue = result.getBigDecimal("thue");
				Timestamp thoiGianTao_temp = result.getTimestamp("thoiGianTao");
				LocalDateTime thoiGianTao = thoiGianTao_temp.toLocalDateTime();
				Timestamp thoiGianIn_temp = result.getTimestamp("thoiGianIn");
				LocalDateTime thoiGianIn = thoiGianIn_temp.toLocalDateTime();
				String ghiChu = result.getString("ghiChu");
				int phuongThucThanhToan = result.getInt("phuongThucThanhToan");
				String maNhanVien = result.getString("maNhanVien");
				String soDienThoai = result.getString("soDienThoai");
				String maKhuyenMai = result.getString("maKhuyenMai");

				NhanVien nhanVien = new NhanVien(maNhanVien);
				KhachHang khachHang = new KhachHang(soDienThoai);
				KhuyenMai khuyenMai = new KhuyenMai(maKhuyenMai);
				info = new DonHang(maDonHang, phuongThucThanhToan, thue, thoiGianTao, thoiGianIn, ghiChu,
						phuongThucThanhToan, khachHang, nhanVien, khuyenMai);

			}

//			System.out.println("Bạn đã thực thi: " + sql);
//			System.out.println("Có "+result+" dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return info;
	}

}
