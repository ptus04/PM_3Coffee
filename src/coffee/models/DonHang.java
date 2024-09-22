package coffee.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import coffee.enums.DonViKhuyenMai;
import coffee.exceptions.GiaTriKhongDungException;

public class DonHang {
	private final String maDonHang;
	private double khachTra;
	private double thue;
	private final LocalDateTime taoLuc = LocalDateTime.now();
	private LocalDateTime inLuc;
	private String ghiChu;

	private KhachHang khachHang;
	private final List<ChiTietDonHang> listChiTietDonHang = new ArrayList<>();
	private KhuyenMai khuyenMai;

	public double getKhachTra() {
		return khachTra;
	}

	public void setKhachTra(double khachTra) throws GiaTriKhongDungException {
		if (khachTra < 0) {
			throw new GiaTriKhongDungException("Số tiền khách trả không được là số âm");
		}
		this.khachTra = khachTra;
	}

	public double getThue() {
		return thue;
	}

	public void setThue(double thue) throws GiaTriKhongDungException {
		if (thue < 0) {
			throw new GiaTriKhongDungException("Thuế không được là số âm");
		}
		this.thue = thue;
	}

	public LocalDateTime getTaoLuc() {
		return taoLuc;
	}

	public LocalDateTime getInLuc() {
		return inLuc;
	}

	public void setInLuc(LocalDateTime inLuc) throws GiaTriKhongDungException {
		if (inLuc.isBefore(LocalDateTime.now())) {
			throw new GiaTriKhongDungException("Thời gian in phải sau thời gian hiện tại");
		}
		this.inLuc = inLuc;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public List<ChiTietDonHang> getListChiTietDonHang() {
		return listChiTietDonHang;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public String getMaDonHang() {
		return maDonHang;
	}

	public DonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}

	public DonHang(String maDonHang, double khachTra, double thue, LocalDateTime inLuc, String ghiChu,
			KhachHang khachHang, KhuyenMai khuyenMai) throws GiaTriKhongDungException {
		this.maDonHang = maDonHang;
		this.ghiChu = ghiChu;
		this.khachHang = khachHang;
		this.khuyenMai = khuyenMai;

		setKhachTra(khachTra);
		setThue(thue);
		setInLuc(inLuc);
	}

	public void xoaChiTiet(ChiTietDonHang chiTiet) {
		listChiTietDonHang.remove(chiTiet);
	}

	public void themChiTiet(ChiTietDonHang chiTiet) {
		listChiTietDonHang.add(chiTiet);
	}

	public double tinhTongTien() {
		double tong = 0;
		for (ChiTietDonHang chiTietDonHang : listChiTietDonHang) {
			tong += chiTietDonHang.tinhTongPhu();
		}

		if (khuyenMai != null) {
			if (khuyenMai.getDonViKhuyenMai() == DonViKhuyenMai.PHAN_TRAM) {
				tong -= khuyenMai.getTriGia() * tong;
			} else {
				tong -= khuyenMai.getTriGia();
			}
		}

		return tong += tong * thue;
	}

	public double tinhTienThua() {
		return tinhTongTien() - khachTra;
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
		return "DonHang [maDonHang=" + maDonHang + ", khachTra=" + khachTra + ", thue=" + thue + ", taoLuc=" + taoLuc
				+ ", inLuc=" + inLuc + ", ghiChu=" + ghiChu + ", khachHang=" + khachHang + ", listChiTietDonHang="
				+ listChiTietDonHang + ", khuyenMai=" + khuyenMai + ", tinhTongTien()=" + tinhTongTien()
				+ ", tinhTienThua()=" + tinhTienThua() + "]";
	}

}
