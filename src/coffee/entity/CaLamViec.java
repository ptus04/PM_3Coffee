package coffee.entity;

import java.time.LocalDateTime;

public class CaLamViec {

	private String maCaLam;
	private String tenCaLam;
	private LocalDateTime thoiGianBatDau;
	private LocalDateTime thoiGianKetThuc;
	private String ghiChu;

	public String getMaCaLam() {
		return maCaLam;
	}

	public void setMaCaLam(String maCaLam) {
		// Kiểm tra mã ca làm phải bắt đầu bằng "CA" và có 8 chữ số
		if (maCaLam.matches("CA\\d{8}")) {
			this.maCaLam = maCaLam;
		} else {
			throw new IllegalArgumentException("Mã ca làm không hợp lệ. Định dạng phải là 'CA' theo sau là 8 chữ số.");
		}
	}

	public String getTenCaLam() {
		return tenCaLam;
	}

	public void setTenCaLam(String tenCaLam) {
		this.tenCaLam = tenCaLam;
	}

	public LocalDateTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public LocalDateTime getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public CaLamViec(String maCaLam) {
		this.setMaCaLam(maCaLam);
	}

	public CaLamViec(String maCaLam, String tenCaLam, LocalDateTime thoiGianBatDau, LocalDateTime thoiGianKetThuc,
			String ghiChu) {
		this.setMaCaLam(maCaLam); // Gọi setter để kiểm tra biểu thức chính quy
		this.tenCaLam = tenCaLam;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.ghiChu = ghiChu;
	}
}
