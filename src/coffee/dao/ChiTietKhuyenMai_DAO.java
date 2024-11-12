package coffee.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import coffee.entity.ChiTietKhuyenMai;
import coffee.entity.KhuyenMai;
import coffee.entity.SanPham;

public class ChiTietKhuyenMai_DAO {

	private static ChiTietKhuyenMai_DAO instance;

	public static ChiTietKhuyenMai_DAO getInstance() {
		if (instance == null)
			instance = new ChiTietKhuyenMai_DAO();
		return instance;
	}

	public int add(ChiTietKhuyenMai t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection c = Database.getInstance().getConnection();

			Statement st = c.createStatement();

			String sql = "INSERT INTO [dbo].[ChiTietKhuyenMai] (muaToiThieu, maKhuyenMai, maSanPham)" + "VALUES ("
					+ t.getMuaToiThieu() + ", '" + t.getKhuyenMai().getMaKhuyenMai() + "', '"
					+ t.getSanPham().getMaSanPham() + "')";

			result = st.executeUpdate(sql);

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int remove(ChiTietKhuyenMai t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection c = Database.getInstance().getConnection();

			Statement st = c.createStatement();

			String sql = "DELETE FROM [dbo].[ChiTietKhuyenMai]" + "WHERE ChiTietKhuyenMai.maKhuyenMai = '"
					+ t.getKhuyenMai().getMaKhuyenMai() + "' AND ChiTietKhuyenMai.maSanPham = '"
					+ t.getSanPham().getMaSanPham() + "' ";

			result = st.executeUpdate(sql);

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int update(ChiTietKhuyenMai t) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Connection c = Database.getInstance().getConnection();

			Statement st = c.createStatement();

			String sql = "UPDATE dbo.ChiTietKhuyenMai " + "SET muaToiThieu = " + t.getMuaToiThieu() + " "
					+ "WHERE ChiTietKhuyenMai.maKhuyenMai = '" + t.getKhuyenMai().getMaKhuyenMai() + "' "
					+ "AND ChiTietKhuyenMai.maSanPham = '" + t.getSanPham().getMaSanPham() + "' ";

			result = st.executeUpdate(sql);

			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + result + " dòng bị thay đổi!");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<ChiTietKhuyenMai> selectAll() {
		// TODO Auto-generated method stub
		ArrayList<ChiTietKhuyenMai> listofchitietkhuyenmai = new ArrayList<ChiTietKhuyenMai>();
		try {
			Connection c = Database.getInstance().getConnection();

			Statement st = c.createStatement();

			String sql = "SELECT * FROM [dbo].[ChiTietKhuyenMai]";

			ResultSet result = st.executeQuery(sql);

			while (result.next()) {
				int muaToiThieu = result.getInt("muaToiThieu");
				String maKhuyenMai = result.getString("maKhuyenMai");
				String maSanPham = result.getString("maSanPham");

				KhuyenMai khuyenmai = new KhuyenMai(maKhuyenMai);
				SanPham sanpham = new SanPham(maSanPham);
				ChiTietKhuyenMai chiTietKhuyenMai = new ChiTietKhuyenMai(khuyenmai, sanpham, muaToiThieu);

				listofchitietkhuyenmai.add(chiTietKhuyenMai);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return listofchitietkhuyenmai;
	}

	public ChiTietKhuyenMai selectById(ChiTietKhuyenMai t) {
		// TODO Auto-generated method stub
		ChiTietKhuyenMai info = null;
		try {
			Connection c = Database.getInstance().getConnection();

			Statement st = c.createStatement();

			String sql = "SELECT * FROM [dbo].[ChiTietKhuyenMai]" + "WHERE ChiTietKhuyenMai.maKhuyenMai = '"
					+ t.getKhuyenMai().getMaKhuyenMai() + "' " + "AND ChiTietKhuyenMai.maSanPham = '"
					+ t.getSanPham().getMaSanPham() + "' ";

			ResultSet result = st.executeQuery(sql);

			while (result.next()) {
				int muaToiThieu = result.getInt("muaToiThieu");
				String maKhuyenMai = result.getString("maKhuyenMai");
				String maSanPham = result.getString("maSanPham");

				KhuyenMai khuyenmai = new KhuyenMai(maKhuyenMai);
				SanPham sanpham = new SanPham(maSanPham);
				info = new ChiTietKhuyenMai(khuyenmai, sanpham, muaToiThieu);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return info;
	}

}
