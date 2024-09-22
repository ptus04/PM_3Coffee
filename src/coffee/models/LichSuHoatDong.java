package coffee.models;

import java.time.LocalDateTime;
import java.util.Objects;

import coffee.enums.LoaiLichSu;

public class LichSuHoatDong {

	private final String maLichSu;
	private LoaiLichSu loaiLichSu;
	private String noiDung;
	private LocalDateTime thoiGian;
	private NhanVien nhanVien;

	public LoaiLichSu getLoaiLichSu() {
		return loaiLichSu;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public String getMaLichSu() {
		return maLichSu;
	}

	public LocalDateTime getThoiGian() {
		return thoiGian;
	}

	public LichSuHoatDong(String maLichSu) {
		this.maLichSu = maLichSu;
	}

	public LichSuHoatDong(LoaiLichSu loaiLichSu, String noiDung, NhanVien nhanVien) {
		LocalDateTime thoiGian = LocalDateTime.now();
		String sufix = "" + thoiGian.getYear()
				+ (thoiGian.getMonthValue() < 10 ? "0" + thoiGian.getMonthValue() : thoiGian.getMonthValue())
				+ thoiGian.getDayOfMonth() + Math.random();

		this.maLichSu = "LS" + loaiLichSu.ordinal() + "-" + sufix;
		this.loaiLichSu = loaiLichSu;
		this.noiDung = noiDung;
		this.thoiGian = thoiGian;
		this.nhanVien = nhanVien;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLichSu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof LichSuHoatDong))
			return false;
		LichSuHoatDong other = (LichSuHoatDong) obj;
		return Objects.equals(maLichSu, other.maLichSu);
	}

	@Override
	public String toString() {
		return "LichSuHoatDong {maLichSu: " + maLichSu + ", loaiLichSu: " + loaiLichSu + ", noiDung: " + noiDung
				+ ", thoiGian: " + thoiGian + ", nhanVien: " + nhanVien + "}";
	}

}
