package coffee.models;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import coffee.exceptions.GiaTriKhongDungException;

public class KhachHang {
	private final String soDienThoai;
	private String tenKhachHang;

	public String getTenKhachHang() {
		return tenKhachHang;
	}

	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public KhachHang(String soDienThoai) throws GiaTriKhongDungException {
		this(soDienThoai, "");
	}

	public KhachHang(String soDienThoai, String tenKhachHang) throws GiaTriKhongDungException {
		Pattern soDienThoaiPattern = Pattern.compile("\\d{10}");
		Matcher soDienThoatMatcher = soDienThoaiPattern.matcher(soDienThoai);
		if (!soDienThoatMatcher.hasMatch()) {
			throw new GiaTriKhongDungException("Số điện thoại không hợp lệ");
		}

		this.soDienThoai = soDienThoai;
		this.tenKhachHang = tenKhachHang;
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
