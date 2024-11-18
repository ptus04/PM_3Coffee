package coffee.model;

import java.time.LocalDate;

public class ThongKeTuan {

	private LocalDate[] ngayThongKe;
	private double[] doanhThu;
	private int[] soLuongHoaDon, soLuongKhachHang, soLuongKhuyenMaiDaDung;

	public LocalDate[] getNgayThongKe() {
		return ngayThongKe;
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

	public ThongKeTuan(LocalDate[] ngayThongKe, double[] doanhThu, int[] soLuongHoaDon, int[] soLuongKhachHang,
			int[] soLuongKhuyenMaiDaDung) {
		this.ngayThongKe = ngayThongKe;
		this.doanhThu = doanhThu;
		this.soLuongHoaDon = soLuongHoaDon;
		this.soLuongKhachHang = soLuongKhachHang;
		this.soLuongKhuyenMaiDaDung = soLuongKhuyenMaiDaDung;
	}

}
