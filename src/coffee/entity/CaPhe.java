package coffee.entity;

import java.util.Objects;

public class CaPhe {

	private String maCaPhe;
	private String tenCaPhe;
	private String loaiCaPhe;
	private double donGia;
	private String thanhPhan;
	private String moTa;
	private boolean con;

	private HinhAnh hinhAnh;

	public String getTenCaPhe() {
		return tenCaPhe;
	}

	public void setTenCaPhe(String tenCaPhe) {
		if (tenCaPhe.isBlank()) {
			throw new IllegalArgumentException("Tên cà phê không được rỗng hoặc chỉ có khoảng trắng");
		}

		this.tenCaPhe = tenCaPhe;
	}

	public String getLoaiCaPhe() {
		return loaiCaPhe;
	}

	public void setLoaiCaPhe(String loaiCaPhe) {
		if (loaiCaPhe.isBlank()) {
			throw new IllegalArgumentException("Loại cà phê không được rỗng hoặc chỉ có khoảng trắng");
		}

		this.loaiCaPhe = loaiCaPhe;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		if (donGia <= 0) {
			throw new IllegalArgumentException("Đơn giá phải là số dương");
		}

		this.donGia = donGia;
	}

	public String getThanhPhan() {
		return thanhPhan;
	}

	public void setThanhPhan(String thanhPhan) {
		if (thanhPhan.isBlank()) {
			throw new IllegalArgumentException("Thành phần không được rỗng hoặc chỉ có khoảng trắng");
		}

		this.thanhPhan = thanhPhan;
	}

	public HinhAnh getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(HinhAnh hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	private void setMaCaPhe(String maCaPhe) {
		if (!maCaPhe.matches("CP[0-9]{8}")) {
			throw new IllegalArgumentException("Mã cà phê phải bắt đầu bằng CP, theo sau là 8 ký số");
		}
		this.maCaPhe = maCaPhe;
	}

	public String getMaCaPhe() {
		return maCaPhe;
	}

	public boolean isCon() {
		return con;
	}

	public void setCon(boolean con) {
		this.con = con;
	}

	public CaPhe(String maCaPhe) {
		setMaCaPhe(maCaPhe);
	}

	public CaPhe(String maCaPhe, String tenCaPhe, String loaiCaPhe, double donGia, String thanhPhan, HinhAnh hinhAnh,
			String moTa, boolean con) {
		setMaCaPhe(maCaPhe);
		setTenCaPhe(tenCaPhe);
		setLoaiCaPhe(loaiCaPhe);
		setDonGia(donGia);
		setThanhPhan(thanhPhan);
		setHinhAnh(hinhAnh);
		this.moTa = moTa;
		this.con = con;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCaPhe);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CaPhe))
			return false;
		CaPhe other = (CaPhe) obj;
		return Objects.equals(maCaPhe, other.maCaPhe);
	}

	@Override
	public String toString() {
		return "CaPhe {maCaPhe: " + maCaPhe + ", tenCaPhe: " + tenCaPhe + ", loaiCaPhe: " + loaiCaPhe + ", donGia: "
				+ donGia + ", thanhPhan: " + thanhPhan + ", moTa: " + moTa + ", con: " + con + "}";
	}

}
