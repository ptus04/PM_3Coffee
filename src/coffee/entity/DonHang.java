package coffee.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class DonHang {
	private String maDonHang;
	private double khachTra;
	private double thue;
	private LocalDateTime thoiGianTao;
	private LocalDateTime thoiGianIn;
	private String ghiChu;
	private int phuongThucThanhToan;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private KhuyenMai khuyenMai;
	private ArrayList<ChiTietDonHang> danhsach;

	public DonHang(String maDonHang, double khachTra, double thue, LocalDateTime thoiGianTao, LocalDateTime thoiGianIn,
			String ghiChu, int phuongThucThanhToan, KhachHang khachHang, NhanVien nhanvien, KhuyenMai khuyenmai)
			throws Exception {
		setMaDonHang(maDonHang);
		setKhachTra(khachTra);
		this.thue = thue;
		this.thoiGianTao = (thoiGianTao != null) ? thoiGianTao : LocalDateTime.now();
		this.thoiGianIn = (thoiGianIn != null) ? thoiGianIn : LocalDateTime.now();
		this.ghiChu = ghiChu;
		setPhuongThucThanhToan(phuongThucThanhToan);
		this.khachHang = khachHang;
		this.nhanVien = nhanvien;
		this.khuyenMai = khuyenmai;
	}

	public DonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}

	public DonHang() {
		this.thoiGianTao = LocalDateTime.now();
		this.thoiGianIn = LocalDateTime.now();
	}

	public String getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(String maDonHang) throws Exception {
		if (maDonHang.length() > 0 && maDonHang.matches("DH\\d{10}"))
			this.maDonHang = maDonHang;
		else
			throw new Exception("Lỗi mã đơn hàng phải có dạng DH<10 chữ số>");
	}

	public double getKhachTra() {
		return khachTra;
	}

	public void setKhachTra(double khachTra) throws Exception {
		if (khachTra > 0)
			this.khachTra = khachTra;
		else
			throw new Exception("Số tiền khách trả phải > 0");
	}

	public double getThue() {
		return thue;
	}

	public void setThue(double thue) {
		this.thue = thue;
	}

	public LocalDateTime getThoiGianTao() {
		return thoiGianTao;
	}

	public void setThoiGianTao(LocalDateTime thoiGianTao) throws Exception {
		this.thoiGianTao = thoiGianTao;
	}

	public LocalDateTime getThoiGianIn() {
		return thoiGianIn;
	}

	public void setThoiGianIn(LocalDateTime thoiGianIn) throws Exception {
		this.thoiGianIn = thoiGianIn;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public int getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}

	public void setPhuongThucThanhToan(int phuongThucThanhToan) throws Exception {
		if (phuongThucThanhToan == 0 || phuongThucThanhToan == 1)
			this.phuongThucThanhToan = phuongThucThanhToan;
		else
			throw new Exception("Phương thức thanh toán chỉ 0 hoặc 1");
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanvien() {
		return nhanVien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanVien = nhanvien;
	}

	public KhuyenMai getKhuyenmai() {
		return khuyenMai;
	}

	public void setKhuyenmai(KhuyenMai khuyenmai) {
		this.khuyenMai = khuyenmai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maDonHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof DonHang))
			return false;
		DonHang other = (DonHang) obj;
		return Objects.equals(maDonHang, other.maDonHang);
	}

	@Override
	public String toString() {
		return maDonHang + ";" + khachTra + ";" + thue + ";" + thoiGianTao + ";" + thoiGianIn + ";" + ghiChu + ";"
				+ phuongThucThanhToan + ";" + khachHang + ";" + nhanVien + ";" + khuyenMai;
	}


	public double tinhTongTien() {
		double sum=0;
		for(ChiTietDonHang chitiet : danhsach)
			sum += chitiet.tinhThanhTien();
		return sum*(1+0.08);
	}

	public double tinhTienThua() {
		return khachTra - tinhTongTien();
	}
}
