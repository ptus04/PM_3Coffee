package coffee.entity;

public class ChiTietKhuyenMai {

	private KhuyenMai khuyenMai;
	private SanPham sanPham;
	private int muaToiThieu;

	public ChiTietKhuyenMai(KhuyenMai khuyenMai, SanPham sanPham, int muaToiThieu) throws Exception {
		this.khuyenMai = khuyenMai;
		this.sanPham = sanPham;
		this.muaToiThieu = muaToiThieu;
	}

	public ChiTietKhuyenMai() {

	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) throws Exception {
		this.sanPham = sanPham;
	}

	public int getMuaToiThieu() {
		return muaToiThieu;
	}

	public void setMuaToiThieu(int muaToiThieu) throws Exception {
		if (muaToiThieu > 0)
			this.muaToiThieu = muaToiThieu;
		else
			throw new Exception("Số lượng mua không được rỗng");
	}

	@Override
	public String toString() {
		return khuyenMai + ";" + sanPham + ";" + muaToiThieu;
	}

}
