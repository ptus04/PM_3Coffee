package coffee.entity;

import java.util.Objects;

public class TaiKhoan {
	private String tenDangNhap;
	private String matKhau;
	private NhanVien nhanVien;

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public TaiKhoan(String tenDangNhap, String matKhau, NhanVien nhanVien) {
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matKhau, tenDangNhap);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TaiKhoan))
			return false;
		TaiKhoan other = (TaiKhoan) obj;
		return Objects.equals(matKhau, other.matKhau) && Objects.equals(tenDangNhap, other.tenDangNhap);
	}

	@Override
	public String toString() {
		return "TaiKhoan {tenDangNhap: " + tenDangNhap + ", nhanVien: " + nhanVien + "}";
	}

}
