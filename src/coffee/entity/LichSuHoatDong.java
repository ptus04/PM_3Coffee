package coffee.entity;

import java.time.LocalDateTime;

public class LichSuHoatDong {
	private String maLichSu;
	private LocalDateTime thoiGian;
	private String noiDung;
	private double soTienBanGiao;
	private LoaiLichSu loaiLichSu;
	private NhanVien nhanVien;

	public LichSuHoatDong(String maLichSu, LocalDateTime thoiGian, String noiDung, double soTienBanGiao,
			LoaiLichSu loaiLichSu, NhanVien nhanVien) throws Exception {
		setMaLichSu(maLichSu);
		setThoiGian(thoiGian);
		setNoiDung(noiDung);
		this.soTienBanGiao = soTienBanGiao;
		setLoaiLichSu(loaiLichSu);
		this.nhanVien = nhanVien;
	}

	public LichSuHoatDong() {

	}

	public String getMaLichSu() {
		return maLichSu;
	}

	public void setMaLichSu(String maLichSu) throws Exception {
		if (maLichSu.length() > 0 && maLichSu.matches("LS\\d{10}"))
			this.maLichSu = maLichSu;
		else
			throw new Exception("Mã lịch sử phải có dạng LS<10 chữ số>");
	}

	public LocalDateTime getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(LocalDateTime thoiGian) throws Exception {
		String thoiGian_String = thoiGian.toString();
		if (thoiGian_String != null)
			this.thoiGian = thoiGian;
		else
			throw new Exception("Thời gian không được rỗng");
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) throws Exception {
		if (noiDung != null)
			this.noiDung = noiDung;
		else
			throw new Exception("Nội dung không được rỗng");
	}

	public double getsoTienBanGiao() {
		return soTienBanGiao;
	}

	public void setsoTienBanGiao(double soTienBanGiao) {
		this.soTienBanGiao = soTienBanGiao;
	}

	public LoaiLichSu getLoaiLichSu() {
		return loaiLichSu;
	}

	public void setLoaiLichSu(LoaiLichSu loaiLichSu) {
		this.loaiLichSu = loaiLichSu;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	@Override
	public String toString() {
		String thoiGian_String = thoiGian.toString().replaceAll("T", " ");
		return maLichSu + ";" + thoiGian_String + ";" + noiDung + ";" + soTienBanGiao + ";" + loaiLichSu + ";"
				+ nhanVien;
	}

}
