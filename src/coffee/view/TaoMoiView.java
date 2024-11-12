package coffee.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import coffee.constant.Colors;
import coffee.constant.Fonts;
import coffee.gui.CoffeeComboBox;
import coffee.gui.CoffeeTextField;
import coffee.gui.TieuDe;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;

public class TaoMoiView extends JFrame {

	private static final long serialVersionUID = -3683814467745193485L;

	private TieuDe tieuDeMoi;

	private JPanel pNew;
	private Box pLeftContainer;
	private Box pSouth;

	private JLabel lLoai;
	private JLabel lNhanVien;
	private JLabel lKhachHang;
	private JLabel lSanPham;
	private JLabel lKhuyenMai;
	private JLabel lCaLam;
	private JLabel lTuLuc;
	private JLabel lDenLuc;

	private CoffeeComboBox cbLoaiBaoCao;
	private CoffeeComboBox cbNhanVien;
	private CoffeeComboBox cbKhachHang;
	private CoffeeComboBox cbSanPham;
	private CoffeeComboBox cbKhuyenMai;
	private CoffeeComboBox cbCaLam;

	private CoffeeTextField tfTuNgay;
	private CoffeeTextField tfDenNgay;

	private SecondaryButton btnXoaRong;
	private PrimaryButton btnXemBaoCao;

	public TaoMoiView() {
		getContentPane().setBackground(Colors.BACKGROUND);

		renderUI();
	}

	private void renderUI() {
		add(pLeftContainer = Box.createVerticalBox(), BorderLayout.NORTH);
		pLeftContainer.add(pNew = new JPanel(new GridBagLayout()));

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 8, 8, 0);
		c.gridwidth = GridBagConstraints.REMAINDER;
		pNew.add(tieuDeMoi = new TieuDe("Tạo báo cáo mới"), c);
		c.gridwidth = 1;
		c.gridy = 1;
		pNew.add(lLoai = new JLabel("Loại báo cáo"), c);
		c.gridy = 2;
		pNew.add(lNhanVien = new JLabel("Nhân viên"), c);
		c.gridy = 3;
		pNew.add(lKhachHang = new JLabel("Khách hàng"), c);
		c.gridy = 4;
		pNew.add(lSanPham = new JLabel("Sản phẩm"), c);
		c.gridy = 5;
		pNew.add(lKhuyenMai = new JLabel("Khuyến mãi"), c);
		c.gridy = 6;
		pNew.add(lCaLam = new JLabel("Ca làm"), c);
		c.gridy = 7;
		pNew.add(lTuLuc = new JLabel("Thống kê từ ngày"), c);
		c.gridx = 2;
		pNew.add(lDenLuc = new JLabel("Thống kê đến ngày"), c);

		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		pNew.add(
				cbLoaiBaoCao = new CoffeeComboBox(
						new String[] { "Doanh thu", "Số lượng hóa đơn", "Số lượng khách hàng", "Số lượng khuyến mãi" }),
				c);
		c.gridy = 2;
		pNew.add(cbNhanVien = new CoffeeComboBox(new String[] { "-- Tất cả --" }), c);
		c.gridy = 3;
		pNew.add(cbKhachHang = new CoffeeComboBox(new String[] { "-- Tất cả --" }), c);
		c.gridy = 4;
		pNew.add(cbSanPham = new CoffeeComboBox(new String[] { "-- Tất cả --" }), c);
		c.gridy = 5;
		pNew.add(cbKhuyenMai = new CoffeeComboBox(new String[] { "-- Tất cả --" }), c);
		c.gridy = 6;
		pNew.add(cbCaLam = new CoffeeComboBox(new String[] { "-- Tất cả --" }), c);
		c.gridy = 7;
		pNew.add(tfTuNgay = new CoffeeTextField("Chưa chọn"), c);
		c.gridx = 3;
		pNew.add(tfDenNgay = new CoffeeTextField("Chưa chọn"), c);

		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.add(btnXoaRong = new SecondaryButton("Xóa rỗng"), c);
		pSouth.add(Box.createGlue());
		pSouth.add(btnXemBaoCao = new PrimaryButton("Xem báo cáo"), c);

		pNew.setOpaque(false);

		lLoai.setFont(Fonts.TEXT);
		lNhanVien.setFont(Fonts.TEXT);
		lKhachHang.setFont(Fonts.TEXT);
		lSanPham.setFont(Fonts.TEXT);
		lKhuyenMai.setFont(Fonts.TEXT);
		lCaLam.setFont(Fonts.TEXT);
		lTuLuc.setFont(Fonts.TEXT);
		lDenLuc.setFont(Fonts.TEXT);

	}

}
