package coffee.entity;

import java.util.Objects;

public class SanPham {

	private String maSanPham;
	private String tenSanPham;
	private String loaiSanPham;
	private double donGia;
	private String thanhPhan;
	private String moTa;
	private boolean con;
	private boolean laDoAn;
	private String hinhAnh;

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		if (tenSanPham.isBlank())
			throw new IllegalArgumentException("Tên sản phẩm không được rỗng hoặc chỉ có khoảng trắng");
		this.tenSanPham = tenSanPham;
	}

	public String getLoaiSanPham() {
		return loaiSanPham;
	}

	public void setLoaiSanPham(String loaiSanPham) {
		if (loaiSanPham.isBlank())
			throw new IllegalArgumentException("Loại sản phẩm không được rỗng hoặc chỉ có khoảng trắng");
		this.loaiSanPham = loaiSanPham;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		if (donGia <= 0)
			throw new IllegalArgumentException("Đơn giá phải là số dương");

		this.donGia = donGia;
	}

	public String getThanhPhan() {
		return thanhPhan;
	}

	public void setThanhPhan(String thanhPhan) {
		if (thanhPhan.isBlank())
			throw new IllegalArgumentException("Thành phần không được rỗng hoặc chỉ có khoảng trắng");

		this.thanhPhan = thanhPhan;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	private void setMaSanPham(String maSanPham) {
		if (!maSanPham.matches("SP\\d{3}"))
			throw new IllegalArgumentException("Mã sản phẩm phải bắt đầu bằng SP, theo sau là 3 ký số");
		this.maSanPham = maSanPham;
	}

	public String getMaSanPham() {
		return maSanPham;
	}

	public boolean isCon() {
		return con;
	}

	public void setCon(boolean con) {
		this.con = con;
	}

	public boolean isLaDoAn() {
		return laDoAn;
	}

	public void setLaDoAn(boolean laDoAn) {
		this.laDoAn = laDoAn;
	}

	public SanPham(String maSanPham) {
		setMaSanPham(maSanPham);
	}

	public SanPham(String maSanPham, String tenSanPham, String loaiCaPhe, double donGia, String thanhPhan, String moTa,
			boolean con, boolean laDonAn, String hinhAnh) {
		setMaSanPham(maSanPham);
		setTenSanPham(tenSanPham);
		setLoaiSanPham(loaiCaPhe);
		setDonGia(donGia);
		setThanhPhan(thanhPhan);
		setHinhAnh(hinhAnh);
		this.moTa = moTa;
		this.con = con;
		this.laDoAn = laDonAn;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maSanPham);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof SanPham))
			return false;
		SanPham other = (SanPham) obj;
		return Objects.equals(maSanPham, other.maSanPham);
	}

	@Override
	public String toString() {
		return "SanPham {maSanPham: " + maSanPham + ", tenSanPham: " + tenSanPham + ", loaiSanPham: " + loaiSanPham
				+ ", donGia: " + donGia + ", thanhPhan: " + thanhPhan + ", moTa: " + moTa + ", con: " + con
				+ ", laDoAn: " + laDoAn + ", hinhAnh: " + hinhAnh + "}";
	}

}
