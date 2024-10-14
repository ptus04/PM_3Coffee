package coffee.entity;

import java.util.Objects;

public class ChiTietDonHang {
	private String maChiTietDonHang;
	private int soLuong;

	private CaPhe caPhe;

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		if (soLuong <= 0) {
			throw new IllegalArgumentException("Số lượng phải là số dương");
		}

		this.soLuong = soLuong;
	}

	public void setMaChiTietDonHang(String maChiTietDonHang) {
		if (!maChiTietDonHang.matches("CTDH[0-9]{12}")) {
			throw new IllegalArgumentException("Mã chi tiết đơn hàng phải bắt đầu bằng CTDH, theo sau là 12 ký số");
		}
		this.maChiTietDonHang = maChiTietDonHang;
	}

	public void setCaPhe(CaPhe caPhe) {
		this.caPhe = caPhe;
	}

	public String getMaChiTietDonHang() {
		return maChiTietDonHang;
	}

	public CaPhe getCaPhe() {
		return caPhe;
	}

	public ChiTietDonHang(String maChiTietDonHang) {
		setMaChiTietDonHang(maChiTietDonHang);
	}

	public ChiTietDonHang(String maChiTietDonHang, int soLuong, CaPhe caPhe) {
		setMaChiTietDonHang(maChiTietDonHang);
		setSoLuong(soLuong);
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
		return "ChiTietDonHang {maChiTietDonHang: " + maChiTietDonHang + ", soLuong: " + soLuong + ", caPhe: " + caPhe
				+ ", tinhTongPhu(): " + tinhTongPhu() + "}";
	}

}
