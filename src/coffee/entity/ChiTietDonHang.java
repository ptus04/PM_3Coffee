package coffee.entity;

public class ChiTietDonHang {
	private int soLuong;
	private String ghiChu;

	private SanPham sanPham;
	private DonHang donHang;

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		if (soLuong <= 0)
			throw new IllegalArgumentException("Số lượng phải là số dương");
		this.soLuong = soLuong;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setDonHang(DonHang donHang) {
		this.donHang = donHang;
	}

	public DonHang getDonHang() {
		return donHang;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public ChiTietDonHang(int soLuong, String ghiChu, SanPham sanPham, DonHang donHang) {
		setSoLuong(soLuong);
		this.ghiChu = ghiChu;
		this.sanPham = sanPham;
		this.donHang = donHang;
	}

	public double tinhTongPhu() {
		return soLuong * sanPham.getDonGia();
	}

	@Override
	public String toString() {
		return "ChiTietDonHang {soLuong: " + soLuong + ", ghiChu: " + ghiChu + ", sanPham: " + sanPham
				+ ", tinhTongPhu(): " + tinhTongPhu() + "}";
	}

}
