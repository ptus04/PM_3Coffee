package coffee.entity;

import java.util.Objects;

public class KhachHang {
	private String soDienThoai;
	private String tenKhachHang;

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		if (tenKhachHang.isBlank()) {
			throw new IllegalArgumentException("Tên khách hàng không được rỗng hoặc chỉ chứa khoảng trắng");

		}
		this.tenKhachHang = tenKhachHang;
	}

	private void setSoDienThoai(String soDienThoai) {
		if (!soDienThoai.matches("0[0-9]{9}")) {
			throw new IllegalArgumentException("Số điện thoại không hợp lệ");
		}

		this.soDienThoai = soDienThoai;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public KhachHang(String soDienThoai) {
		setSoDienThoai(soDienThoai);
		this.tenKhachHang = "Khách thường";
	}

	public KhachHang(String soDienThoai, String tenKhachHang) {
		setSoDienThoai(soDienThoai);
		setTenKhachHang(tenKhachHang);
	}

	@Override
	public int hashCode() {
		return Objects.hash(soDienThoai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof KhachHang))
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(soDienThoai, other.soDienThoai);
	}

	@Override
	public String toString() {
		return "KhachHang [soDienThoai=" + soDienThoai + ", tenKhachHang=" + tenKhachHang + "]";
	}

}
