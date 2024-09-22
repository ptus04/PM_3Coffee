package coffee.models;

import java.time.LocalDateTime;
import java.util.Objects;

import coffee.enums.DonViKhuyenMai;

public class KhuyenMai {
	private final String maKhuyenMai;
	private double triGia;
	private final LocalDateTime taoLuc = LocalDateTime.now();
	private int muaToiThieu;
	private String ghiChu;

	private DonViKhuyenMai donViKhuyenMai;

	public double getTriGia() {
		return triGia;
	}

	public void setTriGia(double triGia) {
		this.triGia = triGia;
	}

	public LocalDateTime getTaoLuc() {
		return taoLuc;
	}

	public int getMuaToiThieu() {
		return muaToiThieu;
	}

	public void setMuaToiThieu(int muaToiThieu) {
		this.muaToiThieu = muaToiThieu;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public DonViKhuyenMai getDonViKhuyenMai() {
		return donViKhuyenMai;
	}

	public void setDonViKhuyenMai(DonViKhuyenMai donViKhuyenMai) {
		this.donViKhuyenMai = donViKhuyenMai;
	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public KhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public KhuyenMai(String maKhuyenMai, double triGia, int muaToiThieu, String ghiChu, DonViKhuyenMai donViKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
		this.triGia = triGia;
		this.muaToiThieu = muaToiThieu;
		this.ghiChu = ghiChu;
		this.donViKhuyenMai = donViKhuyenMai;
	}

	public KhuyenMai(String maKhuyenMai, double triGia, LocalDateTime taoLuc, int muaToiThieu, String ghiChu,
			DonViKhuyenMai donViKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
		this.triGia = triGia;
		this.muaToiThieu = muaToiThieu;
		this.ghiChu = ghiChu;
		this.donViKhuyenMai = donViKhuyenMai;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maKhuyenMai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof KhuyenMai))
			return false;
		KhuyenMai other = (KhuyenMai) obj;
		return Objects.equals(maKhuyenMai, other.maKhuyenMai);
	}

	@Override
	public String toString() {
		return "KhuyenMai [maKhuyenMai=" + maKhuyenMai + ", triGia=" + triGia + ", taoLuc=" + taoLuc + ", muaToiThieu="
				+ muaToiThieu + ", ghiChu=" + ghiChu + ", donViKhuyenMai=" + donViKhuyenMai + "]";
	}

}
