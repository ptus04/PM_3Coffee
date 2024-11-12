package coffee.entity;

public class ChiTietCaLamViec {

	private NhanVien nhanvien;
	private CaLamViec calam;

	public NhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}

	public CaLamViec getCalam() {
		return calam;
	}

	public void setCalam(CaLamViec calam) {
		this.calam = calam;
	}

	public ChiTietCaLamViec(NhanVien nhanvien, CaLamViec calam) {
		this.nhanvien = nhanvien;
		this.calam = calam;
	}

}