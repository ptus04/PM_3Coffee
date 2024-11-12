package coffee.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DonHang {
	private String maDonHang;
	private double khachTra;
	private BigDecimal thue;
	private LocalDateTime thoiGianTao;
	private LocalDateTime thoiGianIn;
	private String ghiChu;
	private int phuongThucThanhToan;
	private KhachHang khachHang;
	private NhanVien nhanVien;
	private KhuyenMai khuyenMai;

	public DonHang(String maDonHang, double khachTra, BigDecimal thue, LocalDateTime thoiGianTao,
			LocalDateTime thoiGianIn, String ghiChu, int phuongThucThanhToan, KhachHang khachHang, NhanVien nhanvien,
			KhuyenMai khuyenmai) throws Exception {
		setMaDonHang(maDonHang);
		setKhachTra(khachTra);
		setThue(thue);
		this.thoiGianTao = (thoiGianTao != null) ? thoiGianTao : LocalDateTime.now();
		this.thoiGianIn = (thoiGianIn != null) ? thoiGianIn : LocalDateTime.now();
		this.ghiChu = ghiChu;
		setPhuongThucThanhToan(phuongThucThanhToan);
		this.khachHang = khachHang;
		this.nhanVien = nhanvien;
		this.khuyenMai = khuyenmai;
	}

	public DonHang(String maDonHang) {
		this.maDonHang = maDonHang;
	}

	public DonHang() {
		this.thoiGianTao = LocalDateTime.now();
		this.thoiGianIn = LocalDateTime.now();
	}

	public String getMaDonHang() {
		return maDonHang;
	}

	public void setMaDonHang(String maDonHang) throws Exception {
		if (maDonHang.length() > 0 && maDonHang.matches("DH\\d{10}"))
			this.maDonHang = maDonHang;
		else
			throw new Exception("Lỗi mã đơn hàng phải có dạng DH<10 chữ số>");
	}

	public double getKhachTra() {
		return khachTra;
	}

	public void setKhachTra(double khachTra) throws Exception {
		if (khachTra > 0)
			this.khachTra = khachTra;
		else
			throw new Exception("Số tiền khách trả phải > 0");
	}

	public BigDecimal getThue() {
		return thue;
	}

	public void setThue(BigDecimal thue) {
		if (thue != null)
			this.thue = thue;
	}

	public LocalDateTime getThoiGianTao() {
		return thoiGianTao;
	}

	public void setThoiGianTao(LocalDateTime thoiGianTao) throws Exception {
		if (thoiGianTao.isAfter(LocalDateTime.now()) || thoiGianTao.isEqual(LocalDateTime.now()))
			this.thoiGianTao = thoiGianTao;
		else
			throw new Exception("Thời gian tạo phải >= thời gian hiện hành");
	}

	public LocalDateTime getThoiGianIn() {
		return thoiGianIn;
	}

	public void setThoiGianIn(LocalDateTime thoiGianIn) throws Exception {
		if (thoiGianTao.isAfter(this.thoiGianTao) || thoiGianTao.isEqual(LocalDateTime.now()))
			this.thoiGianIn = thoiGianIn;
		else
			throw new Exception("Thời gian in phải >= thời gian tạo");
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public int getPhuongThucThanhToan() {
		return phuongThucThanhToan;
	}

	public void setPhuongThucThanhToan(int phuongThucThanhToan) throws Exception {
		if (phuongThucThanhToan == 0 || phuongThucThanhToan == 1)
			this.phuongThucThanhToan = phuongThucThanhToan;
		else
			throw new Exception("Phương thức thanh toán chỉ 0 hoặc 1");
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public NhanVien getNhanvien() {
		return nhanVien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanVien = nhanvien;
	}

	public KhuyenMai getKhuyenmai() {
		return khuyenMai;
	}

	public void setKhuyenmai(KhuyenMai khuyenmai) {
		this.khuyenMai = khuyenmai;
	}

	@Override
	public String toString() {
		String thoiGianTao_String = thoiGianTao.toString().replace("T", " ");
		String thoiGianIn_String = thoiGianTao.toString().replace("T", " ");
		return maDonHang + ";" + khachTra + ";" + thue + ";" + thoiGianTao_String + ";" + thoiGianIn_String + ";"
				+ ghiChu + ";" + phuongThucThanhToan + ";" + khachHang + ";" + nhanVien + ";" + khuyenMai;
	}

	public double tinhTongTien() {
		return 0;
	}

	public double tinhTienThua() {
		return 0;
	}
}
