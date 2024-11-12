package coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import coffee.entity.LichSuHoatDong;
import coffee.entity.NhanVien;

public class LichSuHoatDong_DAO {

	private static LichSuHoatDong_DAO instance;

	public static LichSuHoatDong_DAO getInstance() {
		if (instance == null)
			instance = new LichSuHoatDong_DAO();
		return instance;
	}

	public int add(LichSuHoatDong t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection c = Database.getInstance().getConnection();

			String sql = "INSERT INTO [dbo].[LichSuHoatDong] "
					+ "(maLichSu, thoiGian, noiDung, soTienBanGiao, loaiLichSu, maNhanVien) "
					+ "VALUES (?, ?, ?, ?, ?, ?)";

			PreparedStatement st = c.prepareStatement(sql);

			st.setString(1, t.getMaLichSu());
			st.setTimestamp(2, Timestamp.valueOf(t.getThoiGian()));
			st.setString(3, t.getNoiDung());
			st.setDouble(4, t.getsoTienBanGiao());
			st.setInt(5, t.getLoaiLichSu());
			st.setString(6, t.getNhanVien().getMaNhanVien());

			result = st.executeUpdate(sql);

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int remove(LichSuHoatDong t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection c = Database.getInstance().getConnection();

			String sql = "DELETE FROM [dbo].[LichSuHoatDong] " + "WHERE LichSuHoatDong.maLichSu = ? ";

			PreparedStatement st = c.prepareStatement(sql);

			st.setString(1, t.getMaLichSu());

			result = st.executeUpdate(sql);

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int update(LichSuHoatDong t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection c = Database.getInstance().getConnection();

			String sql = "UPDATE [dbo].[LichSuHoatDong] "
					+ "SET LichSuHoatDong.thoiGian = ? , LichSuHoatDong.noiDung = ? , LichSuHoatDong.soTienBanGiao = ? , "
					+ "LichSuHoatDong.loaiLichSu = ? , LichSuHoatDong.maNhanVien = ? "
					+ "WHERE LichSuHoatDong.maLichSu = ? ";

			PreparedStatement st = c.prepareStatement(sql);

			st.setTimestamp(1, Timestamp.valueOf(t.getThoiGian()));
			st.setString(2, t.getNoiDung());
			st.setDouble(3, t.getsoTienBanGiao());
			st.setInt(4, t.getLoaiLichSu());
			st.setString(5, t.getNhanVien().getMaNhanVien());
			st.setString(6, t.getMaLichSu());

			result = st.executeUpdate(sql);

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<LichSuHoatDong> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<LichSuHoatDong> listoflichsuhoatdong = new ArrayList<LichSuHoatDong>();
		try {
			Connection c = Database.getInstance().getConnection();

			Statement st = c.createStatement();

			String sql = "SELECT * FROM [dbo].[LichSuHoatDong]";

			ResultSet result = st.executeQuery(sql);

			while (result.next()) {
				String maLichSu = result.getString("maLichSu");
				Timestamp thoiGian_temp = result.getTimestamp("thoiGian");
				LocalDateTime thoiGian = thoiGian_temp.toLocalDateTime();
				String noiDung = result.getString("noiDung");
				Double soTienBanGiao = result.getDouble("soTienBanGiao");
				int loaiLichSu = result.getInt("loaiLichSu");
				String maNhanVien = result.getString("maNhanVien");

				NhanVien nhanVien = new NhanVien(maNhanVien);

				LichSuHoatDong lichSuHoatDong = new LichSuHoatDong(maLichSu, thoiGian, noiDung, soTienBanGiao,
						loaiLichSu, nhanVien);

				listoflichsuhoatdong.add(lichSuHoatDong);
			}

//			System.out.println("Bạn đã thực thi: " + sql);
//			System.out.println("Có "+result+" dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listoflichsuhoatdong;
	}

	public LichSuHoatDong selectById(LichSuHoatDong t) {
		// TODO Auto-generated method stub
		LichSuHoatDong info = null;
		try {
			Connection c = Database.getInstance().getConnection();

			String sql = "SELECT * FROM [dbo].[LichSuHoatDong]" + "WHERE LichSuHoatDong.maLichSu = ?";

			PreparedStatement st = c.prepareStatement(sql);

			st.setString(1, t.getMaLichSu());

			ResultSet result = st.executeQuery();

			while (result.next()) {
				String maLichSu = result.getString("maLichSu");
				Timestamp thoiGian_temp = result.getTimestamp("thoiGian");
				LocalDateTime thoiGian = thoiGian_temp.toLocalDateTime();
				String noiDung = result.getString("noiDung");
				Double soTienBanGiao = result.getDouble("soTienBanGiao");
				int loaiLichSu = result.getInt("loaiLichSu");
				String maNhanVien = result.getString("maNhanVien");

				NhanVien nhanVien = new NhanVien(maNhanVien);

				info = new LichSuHoatDong(maLichSu, thoiGian, noiDung, soTienBanGiao, loaiLichSu, nhanVien);

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
