package coffee.models;

import java.util.Objects;

import coffee.enums.TrangThaiCaPhe;
import coffee.exceptions.GiaTriKhongDungException;

public class CaPhe {

	private final String maCaPhe;
	private String tenCaPhe;
	private String loaiCaPhe;
	private double donGia;
	private String thanhPhan;
	private byte[] hinhAnh;
	private String moTa;

	private TrangThaiCaPhe trangThaiCaPhe;

	public String getTenCaPhe() {
		return tenCaPhe;
	}

	public void setTenCaPhe(String tenCaPhe) {
		this.tenCaPhe = tenCaPhe;
	}

	public String getLoaiCaPhe() {
		return loaiCaPhe;
	}

	public void setLoaiCaPhe(String loaiCaPhe) {
		this.loaiCaPhe = loaiCaPhe;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) throws GiaTriKhongDungException {
		if (donGia < 0) {
			throw new GiaTriKhongDungException("Đơn giá không được là số âm");
		}
		this.donGia = donGia;
	}

	public String getThanhPhan() {
		return thanhPhan;
	}

	public void setThanhPhan(String thanhPhan) {
		this.thanhPhan = thanhPhan;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public TrangThaiCaPhe getTrangThai() {
		return trangThaiCaPhe;
	}

	public void setTrangThai(TrangThaiCaPhe trangThai) {
		this.trangThaiCaPhe = trangThai;
	}

	public String getMaCaPhe() {
		return maCaPhe;
	}

	public CaPhe(String maCaPhe) {
		this.maCaPhe = maCaPhe;
	}

	public CaPhe(String maCaPhe, String tenCaPhe, String loaiCaPhe, double donGia, String thanhPhan, byte[] hinhAnh,
			String moTa, TrangThaiCaPhe trangThai) throws GiaTriKhongDungException {
		this.maCaPhe = maCaPhe;
		this.tenCaPhe = tenCaPhe;
		this.loaiCaPhe = loaiCaPhe;
		this.thanhPhan = thanhPhan;
		this.hinhAnh = hinhAnh;
		this.moTa = moTa;
		this.trangThaiCaPhe = trangThai;

		setDonGia(donGia);
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
		return "CaPhe [maCaPhe=" + maCaPhe + ", tenCaPhe=" + tenCaPhe + ", loaiCaPhe=" + loaiCaPhe + ", donGia="
				+ donGia + ", thanhPhan=" + thanhPhan + ", moTa=" + moTa + ", trangThaiCaPhe=" + trangThaiCaPhe + "]";
	}

}
