package coffee.model;

import java.time.LocalDate;
import java.util.List;

public class ThongKeTheoTieuChi {

	private List<LocalDate> ngay;
	private List<Double> doanhThu;
	private List<Integer> soLuongHoaDon;
	private List<Integer> soLuongKhachHang;
	private List<Integer> soLuongKhuyenMai;

	public ThongKeTheoTieuChi(List<LocalDate> ngay, List<Double> doanhThu, List<Integer> soLuongHoaDon,
			List<Integer> soLuongKhachHang, List<Integer> soLuongKhuyenMai) {
		this.ngay = ngay;
		this.doanhThu = doanhThu;
		this.soLuongHoaDon = soLuongHoaDon;
		this.soLuongKhachHang = soLuongKhachHang;
		this.soLuongKhuyenMai = soLuongKhuyenMai;
	}

	public List<LocalDate> getNgay() {
		return ngay;
	}

	public void setNgay(List<LocalDate> ngay) {
		this.ngay = ngay;
	}

	public List<Double> getDoanhThu() {
		return doanhThu;
	}

	public void setDoanhThu(List<Double> doanhThu) {
		this.doanhThu = doanhThu;
	}

	public List<Integer> getSoLuongHoaDon() {
		return soLuongHoaDon;
	}

	public void setSoLuongHoaDon(List<Integer> soLuongHoaDon) {
		this.soLuongHoaDon = soLuongHoaDon;
	}

	public List<Integer> getSoLuongKhachHang() {
		return soLuongKhachHang;
	}

	public void setSoLuongKhachHang(List<Integer> soLuongKhachHang) {
		this.soLuongKhachHang = soLuongKhachHang;
	}

	public List<Integer> getSoLuongKhuyenMai() {
		return soLuongKhuyenMai;
	}

	public void setSoLuongKhuyenMai(List<Integer> soLuongKhuyenMai) {
		this.soLuongKhuyenMai = soLuongKhuyenMai;
	}

}
