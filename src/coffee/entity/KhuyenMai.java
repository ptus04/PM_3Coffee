package coffee.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class KhuyenMai {
	private String maKhuyenMai;
	private double triGia;
	private LocalDateTime ngayHieuLuc;
	private LocalDateTime ngayHetHan;
	private String ghiChu;

	public double getTriGia() {
		return triGia;
	}

	public void setTriGia(double triGia) {
		if (triGia <= 0) {
			throw new IllegalArgumentException("Trị giá phải là số dương");
		}

		this.triGia = triGia;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public LocalDateTime getNgayHieuLuc() {
		return ngayHieuLuc;
	}

	public void setNgayHieuLuc(LocalDateTime ngayHieuLuc) {
		if (ngayHieuLuc.isAfter(ngayHetHan)) {
			throw new IllegalArgumentException("Ngày hiệu lực phải trước ngày hết hạn");
		}

		this.ngayHieuLuc = ngayHieuLuc;
	}

	public LocalDateTime getNgayHetHan() {
		return ngayHetHan;
	}

	public void setNgayHetHan(LocalDateTime ngayHetHan) {
		if (ngayHetHan.isBefore(ngayHieuLuc)) {
			throw new IllegalArgumentException("Ngày hết hạn phải sau ngày hiệu lực");
		}

		this.ngayHetHan = ngayHetHan;
	}

	private void setMaKhuyenMai(String maKhuyenMai) {
		if (!maKhuyenMai.matches("KM[0-9]{8}")) {
			throw new IllegalArgumentException("Mã khuyến mãi phải bắt đầu bằng KM, theo sau là 8 ký số");
		}

		this.maKhuyenMai = maKhuyenMai;
	}

	public KhuyenMai() {
	}

	public KhuyenMai(String maKhuyenMai) {
		setMaKhuyenMai(maKhuyenMai);
	}

	public KhuyenMai(String maKhuyenMai, double triGia, LocalDateTime ngayHieuLuc, LocalDateTime ngayHetHan,
			String ghiChu) {
		setMaKhuyenMai(maKhuyenMai);
		setTriGia(triGia);
		this.ngayHieuLuc = ngayHieuLuc;
		setNgayHetHan(ngayHetHan);
		this.ghiChu = ghiChu;
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
		return "KhuyenMai {maKhuyenMai: " + maKhuyenMai + ", triGia: " + triGia + ", ngayHieuLuc: " + ngayHieuLuc
				+ ", ngayHetHan: " + ngayHetHan + ", ghiChu: " + ghiChu + "}";
	}

}
