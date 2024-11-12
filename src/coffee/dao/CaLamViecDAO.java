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
	private Connection connection;

	public CaLamViecDAO(Connection connection) {
		this.connection = connection;
	}

	// Phương thức để thêm ca làm việc
	public void addCaLamViec(CaLamViec caLamViec) throws SQLException {
		String sql = "INSERT INTO CaLam(maCaLam, tenCaLam, thoiGianBatDau, thoiGianKetThuc, ghiChu) VALUES(?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, caLamViec.getMaCaLam());
			stmt.setString(2, caLamViec.getTenCaLam());
			stmt.setObject(3, caLamViec.getThoiGianBatDau());
			stmt.setObject(4, caLamViec.getThoiGianKetThuc());
			stmt.setString(5, caLamViec.getGhiChu());
			stmt.executeUpdate();
		}
	}

	// Phương thức để lấy tất cả ca làm việc
	public List<CaLamViec> getAllCaLamViec() throws SQLException {
		List<CaLamViec> caLamViecList = new ArrayList<>();
		String sql = "SELECT * FROM CaLam";
		try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				CaLamViec caLamViec = new CaLamViec(rs.getString("maCaLam"), rs.getString("tenCaLam"),
						rs.getObject("thoiGianBatDau", LocalDateTime.class),
						rs.getObject("thoiGianKetThuc", LocalDateTime.class), rs.getString("ghiChu"));
				caLamViecList.add(caLamViec);
			}
		}
		return caLamViecList;
	}

	// Phương thức để lấy ca làm việc theo mã
	public CaLamViec getCaLamViecByMa(String maCaLam) throws SQLException {
		String sql = "SELECT * FROM CaLam WHERE maCaLam = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, maCaLam);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return new CaLamViec(rs.getString("maCaLam"), rs.getString("tenCaLam"),
							rs.getObject("thoiGianBatDau", LocalDateTime.class),
							rs.getObject("thoiGianKetThuc", LocalDateTime.class), rs.getString("ghiChu"));
				}
			}
		}
		return null; // Trả về null nếu không tìm thấy
	}

	// Phương thức để cập nhật ca làm việc
	public void updateCaLamViec(CaLamViec caLamViec) throws SQLException {
		String sql = "UPDATE CaLam SET tenCaLam = ?, thoiGianBatDau = ?, thoiGianKetThuc = ?, ghiChu = ? WHERE maCaLam = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, caLamViec.getTenCaLam());
			stmt.setObject(2, caLamViec.getThoiGianBatDau());
			stmt.setObject(3, caLamViec.getThoiGianKetThuc());
			stmt.setString(4, caLamViec.getGhiChu());
			stmt.setString(5, caLamViec.getMaCaLam());
			stmt.executeUpdate();
		}
	}

	// Phương thức để xóa ca làm việc
	public void deleteCaLamViec(String maCaLam) throws SQLException {
		String sql = "DELETE FROM CaLam WHERE maCaLam = ?";
		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, maCaLam);
			stmt.executeUpdate();
		}
	}
}
