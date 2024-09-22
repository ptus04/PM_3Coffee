package coffee.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import coffee.exceptions.GiaTriKhongDungException;

public class CaLamViec {

	private final String maCaLamViec;
	private LocalDateTime thoiGianBatDau;
	private LocalDateTime thoiGianKetThuc;
	private List<NhanVien> listNhanVien;

	public LocalDateTime getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalDateTime thoiGianBatDau) throws GiaTriKhongDungException {
		if (thoiGianBatDau.isAfter(thoiGianKetThuc)) {
			throw new GiaTriKhongDungException("Thời gian bắt đầu phải trước thời gian kết thúc");
		}
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public LocalDateTime getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(LocalDateTime thoiGianKetThuc) throws GiaTriKhongDungException {
		if (thoiGianKetThuc.isBefore(thoiGianBatDau)) {
			throw new GiaTriKhongDungException("Thời gian kết thúc phải sau thời gian bắt đầu");
		}
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public List<NhanVien> getListNhanVien() {
		return listNhanVien;
	}

	public String getMaCaLamViec() {
		return maCaLamViec;
	}

	public CaLamViec(String maCaLamViec) {
		this.maCaLamViec = maCaLamViec;
	}

	public CaLamViec(String maCaLamViec, LocalDateTime thoiGianBatDau, LocalDateTime thoiGianKetThuc)
			throws GiaTriKhongDungException {
		this.maCaLamViec = maCaLamViec;
		setThoiGianBatDau(thoiGianBatDau);
		setThoiGianKetThuc(thoiGianKetThuc);
		this.listNhanVien = new ArrayList<>();
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCaLamViec);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CaLamViec))
			return false;
		CaLamViec other = (CaLamViec) obj;
		return Objects.equals(maCaLamViec, other.maCaLamViec);
	}

	@Override
	public String toString() {
		return "CaLamViec [maCaLamViec=" + maCaLamViec + ", thoiGianBatDau=" + thoiGianBatDau + ", thoiGianKetThuc="
				+ thoiGianKetThuc + ", listNhanVien=" + listNhanVien + "]";
	}

}
