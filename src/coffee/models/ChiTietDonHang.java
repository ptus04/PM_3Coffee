package coffee.models;

import java.util.Objects;

import coffee.exceptions.GiaTriKhongDungException;

public class ChiTietDonHang {
	private final String maChiTietDonHang;
	private int soLuong;

	private CaPhe caPhe;

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) throws GiaTriKhongDungException {
		if (soLuong <= 0) {
			throw new GiaTriKhongDungException("Số lượng phải là số dương");
		}
		this.soLuong = soLuong;
	}

	public String getMaChiTietDonHang() {
		return maChiTietDonHang;
	}

	public CaPhe getCaPhe() {
		return caPhe;
	}

	public ChiTietDonHang(String maChiTietDonHang) {
		this.maChiTietDonHang = maChiTietDonHang;
	}

	public ChiTietDonHang(String maChiTietDonHang, int soLuong, CaPhe caPhe) {
		this.maChiTietDonHang = maChiTietDonHang;
		this.soLuong = soLuong;
		this.caPhe = caPhe;
	}

	public double tinhTongPhu() {
		return soLuong * caPhe.getDonGia();
	}

	@Override
	public int hashCode() {
		return Objects.hash(maChiTietDonHang);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof ChiTietDonHang))
			return false;
		ChiTietDonHang other = (ChiTietDonHang) obj;
		return Objects.equals(maChiTietDonHang, other.maChiTietDonHang);
	}

	@Override
	public String toString() {
		return "ChiTietDonHang [maChiTietDonHang=" + maChiTietDonHang + ", soLuong=" + soLuong + ", caPhe=" + caPhe
				+ "]";
	}

}
