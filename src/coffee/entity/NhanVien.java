package coffee.entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {

	private String maNhanVien;
	private String hoTen;
	private boolean gioiTinh;
	private LocalDate ngaySinh;
	private String soCanCuoc;
	private String soDienThoai;
	private String diaChi;
	private float heSoLuong;
	private boolean laQuanLy;
	private String hinhAnh;
	private TaiKhoan taiKhoan;
	private TrangThaiLamViec trangThaiLamViec;

	public String getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(String maNhanVien) {
		if (maNhanVien.matches("NV\\d{6}")) {
			this.maNhanVien = maNhanVien;
		} else {
			throw new IllegalArgumentException("Mã nhân viên không hợp lệ. Định dạng phải là NV[YYMM][##]");
		}
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}


	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getSoCanCuoc() {
		return soCanCuoc;
	}

	public void setSoCanCuoc(String soCanCuoc) {
		if (soCanCuoc.matches("0\\d{11}")) {
			this.soCanCuoc = soCanCuoc;
		} else {
			throw new IllegalArgumentException("Số căn cước phải gồm 12 chữ số và bắt đầu bằng số 0.");
		}
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		if (soDienThoai.matches("0\\d{9}")) {
			this.soDienThoai = soDienThoai;
		} else {
			throw new IllegalArgumentException("Số điện thoại phải gồm 10 chữ số và bắt đầu bằng số 0.");
		}
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public float getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(float heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	public boolean isLaQuanLy() {
		return laQuanLy;
	}

	public void setLaQuanLy(boolean laQuanLy) {
		this.laQuanLy = laQuanLy;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public TrangThaiLamViec getTrangThaiLamViec() {
		return trangThaiLamViec;
	}

	public void setTrangThaiLamViec(TrangThaiLamViec trangThaiLamViec) {
		this.trangThaiLamViec = trangThaiLamViec;
	}

	public NhanVien() {
	}

	public NhanVien(String maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
	//constructor

	public NhanVien(String maNhanVien, String hoTen, boolean gioiTinh, LocalDate ngaySinh, String soCanCuoc,
			String soDienThoai, String diaChi, float heSoLuong, boolean laQuanLy, String hinhAnh, TaiKhoan taiKhoan,
			TrangThaiLamViec trangThaiLamViec) {
		this.setMaNhanVien(maNhanVien);
		this.diaChi=diaChi;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.setSoCanCuoc(soCanCuoc);
		this.setSoDienThoai(soDienThoai);
		this.heSoLuong = heSoLuong;
		this.laQuanLy = laQuanLy;
		this.hinhAnh = hinhAnh;
		this.taiKhoan = taiKhoan;
		this.trangThaiLamViec = trangThaiLamViec;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof NhanVien))
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}

	@Override
	public String toString() {
		return "NhanVien {maNhanVien: " + maNhanVien + ", hoTen: " + hoTen + ", gioiTinh: " + gioiTinh + ", ngaySinh: "
				+ ngaySinh + ", soCanCuoc: " + soCanCuoc + ", soDienThoai: " + soDienThoai + ", diaChi: " + diaChi
				+ ", heSoLuong: " + heSoLuong + ", laQuanLy: " + laQuanLy + ", hinhAnh: " + hinhAnh + ", taiKhoan: "
				+ taiKhoan + ", trangThaiLamViec: " + trangThaiLamViec + "}";
	}

}
