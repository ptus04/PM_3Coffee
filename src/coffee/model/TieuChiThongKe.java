package coffee.model;

import java.time.LocalDate;

import coffee.entity.CaLamViec;
import coffee.entity.KhachHang;
import coffee.entity.KhuyenMai;
import coffee.entity.NhanVien;
import coffee.entity.SanPham;

public class TieuChiThongKe {

	private String loaiThongKe;
	private NhanVien nhanVien;
	private KhachHang khachHang;
	private SanPham sanPham;
	private KhuyenMai khuyenMai;
	private CaLamViec caLam;
	private LocalDate tuNgay;
	private LocalDate denNgay;

	public String getLoaiThongKe() {
		return loaiThongKe;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public CaLamViec getCaLam() {
		return caLam;
	}

	public LocalDate getTuNgay() {
		return tuNgay;
	}

	public LocalDate getDenNgay() {
		return denNgay;
	}

	public TieuChiThongKe(String loaiThongKe,
			NhanVien nhanVien, KhachHang khachHang, SanPham sanPham,
			KhuyenMai khuyenMai, CaLamViec caLam, LocalDate tuNgay, LocalDate denNgay) {
		this.loaiThongKe = loaiThongKe;
		this.nhanVien = nhanVien;
		this.khachHang = khachHang;
		this.sanPham = sanPham;
		this.khuyenMai = khuyenMai;
		this.caLam = caLam;
		this.tuNgay = tuNgay;
		this.denNgay = denNgay;
	}

}
