package coffee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

import coffee.entity.CaLamViec;

public class CaLamViecDAO {

	private static CaLamViecDAO instance;

	public static CaLamViecDAO getInstance() {
		if (instance == null)
			instance = new CaLamViecDAO();
		return instance;
	}

	public void addCaLamViec(CaLamViec caLamViec) throws SQLException {
		String sql = "INSERT INTO CaLam(maCaLam, tenCaLam, thoiGianBatDau, thoiGianKetThuc, ghiChu) VALUES(?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql)) {
			stmt.setString(1, caLamViec.getMaCaLam());
			stmt.setString(2, caLamViec.getTenCaLam());
			stmt.setObject(3, caLamViec.getThoiGianBatDau());
			stmt.setObject(4, caLamViec.getThoiGianKetThuc());
			stmt.setString(5, caLamViec.getGhiChu());
			stmt.executeUpdate();
		}
	}

	public List<CaLamViec> getAllCaLamViec() throws SQLException {
		List<CaLamViec> caLamViecList = new ArrayList<>();
		String sql = "SELECT * FROM CaLam";
		try (PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				CaLamViec caLamViec = new CaLamViec(rs.getString("maCaLam"), rs.getString("tenCaLam"),
						rs.getTime("thoiGianBatDau").toLocalTime(), rs.getTime("thoiGianKetThuc").toLocalTime(),
						rs.getString("ghiChu"));
				caLamViecList.add(caLamViec);
			}
		}
		return caLamViecList;
	}

	public CaLamViec getCaLamViecByMa(String maCaLam) throws SQLException {
		String sql = "SELECT * FROM CaLam WHERE maCaLam = ?";
		try (PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql)) {
			stmt.setString(1, maCaLam);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new CaLamViec(rs.getString("maCaLam"), rs.getString("tenCaLam"),
							rs.getTime("thoiGianBatDau").toLocalTime(), rs.getTime("thoiGianKetThuc").toLocalTime(),
							rs.getString("ghiChu"));
				}
			}
		}
		return null;
	}

	public void updateCaLamViec(CaLamViec caLamViec) throws SQLException {
		String sql = "UPDATE CaLam SET tenCaLam = ?, thoiGianBatDau = ?, thoiGianKetThuc = ?, ghiChu = ? WHERE maCaLam = ?";
		try (PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql)) {
			stmt.setString(1, caLamViec.getTenCaLam());
			stmt.setObject(2, caLamViec.getThoiGianBatDau());
			stmt.setObject(3, caLamViec.getThoiGianKetThuc());
			stmt.setString(4, caLamViec.getGhiChu());
			stmt.setString(5, caLamViec.getMaCaLam());
			stmt.executeUpdate();
		}
	}

	public void deleteCaLamViec(String maCaLam) throws SQLException {
		String sql = "DELETE FROM CaLam WHERE maCaLam = ?";
		try (PreparedStatement stmt = Database.getInstance().getConnection().prepareStatement(sql)) {
			stmt.setString(1, maCaLam);
			stmt.executeUpdate();
		}
	}
}
