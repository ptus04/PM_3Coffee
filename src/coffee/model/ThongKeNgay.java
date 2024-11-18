package coffee.model;

import java.time.LocalDate;

public class ThongKeNgay {

	private LocalDate ngayTaoThongKe;
	private double doanhThu;
	private int soLuongHoaDon, soLuongKhachHang, soLuongKhuyenMaiDaDung;

	public LocalDate getNgayTaoThongKe() {
		return ngayTaoThongKe;
	}

	public double getDoanhThu() {
		return doanhThu;
	}

	public int getSoLuongHoaDon() {
		return soLuongHoaDon;
	}

	public int getSoLuongKhachHang() {
		return soLuongKhachHang;
	}

	public int getSoLuongKhuyenMaiDaDung() {
		return soLuongKhuyenMaiDaDung;
	}

	public ThongKeNgay(LocalDate ngayTaoThongKe, double doanhThu, int soLuongHoaDon, int soLuongKhachHang,
			int soLuongKhuyenMaiDaDung) {
		this.ngayTaoThongKe = ngayTaoThongKe;
		this.doanhThu = doanhThu;
		this.soLuongHoaDon = soLuongHoaDon;
		this.soLuongKhachHang = soLuongKhachHang;
		this.soLuongKhuyenMaiDaDung = soLuongKhuyenMaiDaDung;
	}

	@Override
	public String toString() {
		return "ThongKeNgay {ngayTaoThongKe: " + ngayTaoThongKe + ", doanhThu: " + doanhThu + ", soLuongHoaDon: "
				+ soLuongHoaDon + ", soLuongKhachHang: " + soLuongKhachHang + ", soLuongKhuyenMaiDaDung: "
				+ soLuongKhuyenMaiDaDung + "}";
	}

}
