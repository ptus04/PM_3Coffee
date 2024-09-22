package coffee.models;

public class TaiKhoan {
	private final String tenDangNhap;
	private String matKhau;

	private NhanVien nhanVien;

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

	public String getTenDangNhap() {
		return tenDangNhap;
	}

	public TaiKhoan(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}

	public TaiKhoan(String tenDangNhap, String matKhau, NhanVien nhanVien) {
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		return "TaiKhoan [tenDangNhap=" + tenDangNhap + ", nhanVien=" + nhanVien + "]";
	}
}
