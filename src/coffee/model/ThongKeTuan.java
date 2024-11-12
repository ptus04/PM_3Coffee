package coffee.model;

import java.time.LocalDate;

public class ThongKeTuan {

	private LocalDate[] ngayTaoThongKe;
	private double[] doanhThu;
	private int[] soLuongHoaDon, soLuongKhachHang, soLuongKhuyenMaiDaDung;

	public LocalDate[] getNgayTaoThongKe() {
		return ngayTaoThongKe;
	}

	public double[] getDoanhThu() {
		return doanhThu;
	}

	public int[] getSoLuongHoaDon() {
		return soLuongHoaDon;
	}

	public int[] getSoLuongKhachHang() {
		return soLuongKhachHang;
	}

	public int[] getSoLuongKhuyenMaiDaDung() {
		return soLuongKhuyenMaiDaDung;
	}

	public ThongKeTuan(LocalDate[] ngayTaoThongKe, double[] doanhThu, int[] soLuongHoaDon, int[] soLuongKhachHang,
			int[] soLuongKhuyenMaiDaDung) {
		this.ngayTaoThongKe = ngayTaoThongKe;
		this.doanhThu = doanhThu;
		this.soLuongHoaDon = soLuongHoaDon;
		this.soLuongKhachHang = soLuongKhachHang;
		this.soLuongKhuyenMaiDaDung = soLuongKhuyenMaiDaDung;
	}

}
