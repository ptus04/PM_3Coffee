package coffee.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.Box;
import javax.swing.JDialog;
import javax.swing.JFrame;

import coffee.entity.CaLamViec;
import coffee.entity.KhachHang;
import coffee.entity.KhuyenMai;
import coffee.entity.NhanVien;
import coffee.entity.SanPham;
import coffee.gui.CoffeeComboBox;
import coffee.gui.CoffeeDateTimeField;
import coffee.gui.CoffeeLabel;
import coffee.gui.CoffeePanel;
import coffee.gui.DateTimePicker;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;

public class TaoMoiView extends JDialog {

	private static final long serialVersionUID = -3683814467745193485L;

	private Box pCenter;
	private Box pSouth;

	private CoffeeLabel lLoaiBaoCao;
	private CoffeeLabel lNhanVien;
	private CoffeeLabel lKhachHang;
	private CoffeeLabel lSanPham;
	private CoffeeLabel lKhuyenMai;
	private CoffeeLabel lCaLam;
	private CoffeeLabel lTuLuc;
	private CoffeeLabel lDenLuc;

	private CoffeeComboBox<String> cbLoaiBaoCao;
	private CoffeeComboBox<String> cbNhanVien;
	private CoffeeComboBox<String> cbKhachHang;
	private CoffeeComboBox<String> cbSanPham;
	private CoffeeComboBox<String> cbKhuyenMai;
	private CoffeeComboBox<String> cbCaLam;

	private CoffeeDateTimeField tfTuNgay;
	private CoffeeDateTimeField tfDenNgay;

	private SecondaryButton btnXoaRong;
	private PrimaryButton btnXemBaoCao;

	private CoffeePanel pLoaiBaoCao;
	private CoffeePanel pNhanVien;
	private CoffeePanel pKhachHang;
	private CoffeePanel pSanPham;
	private CoffeePanel pKhuyenMai;
	private CoffeePanel pCaLam;
	private CoffeePanel pThoiGian;

	private JFrame owner;

	public CoffeeComboBox<String> getCbLoaiBaoCao() {
		return cbLoaiBaoCao;
	}

	public CoffeeComboBox<String> getCbNhanVien() {
		return cbNhanVien;
	}

	public CoffeeComboBox<String> getCbKhachHang() {
		return cbKhachHang;
	}

	public CoffeeComboBox<String> getCbSanPham() {
		return cbSanPham;
	}

	public CoffeeComboBox<String> getCbKhuyenMai() {
		return cbKhuyenMai;
	}

	public CoffeeComboBox<String> getCbCaLam() {
		return cbCaLam;
	}

	public CoffeeDateTimeField getTfTuNgay() {
		return tfTuNgay;
	}

	public CoffeeDateTimeField getTfDenNgay() {
		return tfDenNgay;
	}

	public PrimaryButton getBtnXemBaoCao() {
		return btnXemBaoCao;
	}

	public TaoMoiView(JFrame owner) {
		super(owner, "Tạo báo cáo mới", true);
		this.owner = owner;
		setContentPane(new CoffeePanel(new BorderLayout(), new Insets(8, 8, 8, 8)));

		add(pCenter = Box.createVerticalBox());

		String[] loaiBaoCao = { "Tất cả", "Doanh thu", "Số lượng hóa đơn", "Số lượng khách hàng",
				"Số lượng khuyến mãi" };
		pCenter.add(pLoaiBaoCao = new CoffeePanel(new FlowLayout(FlowLayout.LEFT)));
		pLoaiBaoCao.add(lLoaiBaoCao = new CoffeeLabel("Loại báo cáo"));
		pLoaiBaoCao.add(cbLoaiBaoCao = new CoffeeComboBox<>(loaiBaoCao));

		pCenter.add(pNhanVien = new CoffeePanel(new FlowLayout(FlowLayout.LEFT)));
		pNhanVien.add(lNhanVien = new CoffeeLabel("Nhân viên"));
		pNhanVien.add(cbNhanVien = new CoffeeComboBox<>(new String[] { "Tất cả" }));

		pCenter.add(pKhachHang = new CoffeePanel(new FlowLayout(FlowLayout.LEFT)));
		pKhachHang.add(lKhachHang = new CoffeeLabel("Khách hàng"));
		pKhachHang.add(cbKhachHang = new CoffeeComboBox<>(new String[] { "Tất cả" }));

		pCenter.add(pSanPham = new CoffeePanel(new FlowLayout(FlowLayout.LEFT)));
		pSanPham.add(lSanPham = new CoffeeLabel("Sản phẩm"));
		pSanPham.add(cbSanPham = new CoffeeComboBox<>(new String[] { "Tất cả" }));

		pCenter.add(pKhuyenMai = new CoffeePanel(new FlowLayout(FlowLayout.LEFT)));
		pKhuyenMai.add(lKhuyenMai = new CoffeeLabel("Khuyến mãi"));
		pKhuyenMai.add(cbKhuyenMai = new CoffeeComboBox<>(new String[] { "Tất cả", "Không áp dụng" }));

//		pCenter.add(pCaLam = new CoffeePanel(new FlowLayout(FlowLayout.LEFT)));
//		pCaLam.add(lCaLam = new CoffeeLabel("Ca làm"));
//		pCaLam.add(cbCaLam = new CoffeeComboBox<>(new String[] { "Tất cả" }));

		pCenter.add(pThoiGian = new CoffeePanel(new FlowLayout(FlowLayout.LEFT)));
		pThoiGian.add(lTuLuc = new CoffeeLabel("Thống kê từ ngày"));
		pThoiGian.add(tfTuNgay = new CoffeeDateTimeField(DateTimePicker.DATE_ONLY));
		pThoiGian.add(lDenLuc = new CoffeeLabel("Thống kê đến ngày"));
		pThoiGian.add(tfDenNgay = new CoffeeDateTimeField(DateTimePicker.DATE_ONLY));

		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.add(btnXoaRong = new SecondaryButton("Xóa rỗng"));
		pSouth.add(Box.createGlue());
		pSouth.add(btnXemBaoCao = new PrimaryButton("Xem báo cáo"));

		lLoaiBaoCao.setPreferredSize(lDenLuc.getPreferredSize());
		lNhanVien.setPreferredSize(lDenLuc.getPreferredSize());
		lKhachHang.setPreferredSize(lDenLuc.getPreferredSize());
		lSanPham.setPreferredSize(lDenLuc.getPreferredSize());
		lKhuyenMai.setPreferredSize(lDenLuc.getPreferredSize());
//		lCaLam.setPreferredSize(lDenLuc.getPreferredSize());
		lTuLuc.setPreferredSize(lDenLuc.getPreferredSize());

		btnXoaRong.addActionListener(e -> onXoaRong());

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void load(List<NhanVien> nhanVienList, List<KhachHang> khachHangList, List<SanPham> sanPhamList,
			List<KhuyenMai> khuyenMaiList, List<CaLamViec> caLamList) {

		nhanVienList.forEach(x -> cbNhanVien.addItem(x.getMaNhanVien() + " - " + x.getHoTen()));
		khachHangList.forEach(x -> cbKhachHang.addItem(x.getSoDienThoai() + " - " + x.getTenKhachHang()));
		sanPhamList.forEach(x -> cbSanPham.addItem(x.getMaSanPham() + " - " + x.getTenSanPham()));
		khuyenMaiList.forEach(x -> cbKhuyenMai.addItem(x.getMaKhuyenMai()));
//		caLamList.forEach(x -> cbCaLam.addItem(x.getMaCaLam() + " - " + x.getTenCaLam()));

		cbLoaiBaoCao.setPreferredSize(cbSanPham.getPreferredSize());
		cbNhanVien.setPreferredSize(cbSanPham.getPreferredSize());
		cbKhachHang.setPreferredSize(cbSanPham.getPreferredSize());
		cbKhuyenMai.setPreferredSize(cbSanPham.getPreferredSize());
//		cbCaLam.setPreferredSize(cbSanPham.getPreferredSize());
		tfTuNgay.setPreferredSize(cbSanPham.getPreferredSize());
		tfDenNgay.setPreferredSize(cbSanPham.getPreferredSize());

		pack();
		setLocationRelativeTo(owner);
	}

	private void onXoaRong() {
		cbLoaiBaoCao.setSelectedIndex(0);
		cbLoaiBaoCao.requestFocus();
		cbNhanVien.setSelectedIndex(0);
		cbKhachHang.setSelectedIndex(0);
		cbSanPham.setSelectedIndex(0);
		cbKhuyenMai.setSelectedIndex(0);
		cbCaLam.setSelectedIndex(0);
		tfTuNgay.setText("Chưa chọn");
		tfDenNgay.setText("Chưa chọn");
	}

}
