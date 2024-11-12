package coffee.view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import coffee.constant.Colors;
import coffee.constant.Fonts;
import coffee.gui.BieuDo;
import coffee.gui.TieuDe;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;

public class KetQuaThongKeView extends JFrame {

	private static final long serialVersionUID = 6103116999501325988L;

	private JScrollPane pScroll;
	private JPanel pMain;
	private Box bThaoTac;

	private TieuDe tieuDe;
	private TieuDe tieuDeBieuDo;

	private JLabel lNguoiTao;
	private JLabel lThoiGianTao;
	private JLabel lLoaiBaoCao;
	private JLabel lTuNgay;
	private JLabel lDenNgay;
	private JLabel lNhanVien;
	private JLabel lKhachHang;
	private JLabel lSanPham;
	private JLabel lCaLam;
	private JLabel lTuLuc;
	private JLabel lDenLuc;
	private JLabel lMaKhuyenMai;

	private SecondaryButton btnXuatPDF;
	private PrimaryButton btnIn;

	private ChartPanel pChart;

	public KetQuaThongKeView() {
		setLayout(new BorderLayout());
		getContentPane().setBackground(Colors.BACKGROUND);

		renderUI();
	}

	public void renderUI() {
		add(pScroll = new JScrollPane(pMain = new JPanel(new GridBagLayout())));

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(5000000, "doanh thu", "04/11");
		dataset.addValue(4500000, "doanh thu", "05/11");
		dataset.addValue(6200000, "doanh thu", "06/11");
		dataset.addValue(5800000, "doanh thu", "07/11");
		dataset.addValue(7000000, "doanh thu", "08/11");
		dataset.addValue(6500000, "doanh thu", "09/11");
		dataset.addValue(7500000, "doanh thu", "10/11");

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTHWEST;
		c.weightx = 1;
		c.insets = new Insets(0, 0, 8, 0);
		pMain.add(tieuDe = new TieuDe("Báo cáo"), c);

		// Cột 1
		c.insets = new Insets(4, 4, 4, 4);
		c.gridy = 1;
		pMain.add(lNguoiTao = new JLabel("Người tạo báo cáo: Phùng Văn Tú"), c);
		c.gridy = 3;
		pMain.add(lLoaiBaoCao = new JLabel("Loại báo cáo: Doanh thu"), c);
		c.gridy = 4;
		pMain.add(lNhanVien = new JLabel("Nhân viên được thống kê: --"), c);
		c.gridy = 5;
		pMain.add(lKhachHang = new JLabel("Khách hàng được thống kê: --"), c);
		c.gridy = 6;
		pMain.add(lSanPham = new JLabel("Sản phẩm được thống ke: --"), c);
		c.gridy = 7;
		pMain.add(lMaKhuyenMai = new JLabel("Mã khuyến mãi được thống kê: Không áp dụng cho báo cáo này"), c);
		c.gridy = 8;
		pMain.add(lCaLam = new JLabel("Ca làm được thống kê: --"), c);

		// Cột 2
		c.anchor = GridBagConstraints.NORTHEAST;
		c.gridx = 2;
		c.gridy = 1;
		pMain.add(lThoiGianTao = new JLabel("Thời gian tạo báo cáo: 17:45:29 11/11/2024"), c);
		c.gridy = 2;
		pMain.add(lTuNgay = new JLabel("Thống kê từ ngày: 04/11/2024"), c);
		c.gridy = 3;
		pMain.add(lDenNgay = new JLabel("Thống kê đến ngày: 10/11/2024"), c);
		c.gridy = 8;
		pMain.add(lTuLuc = new JLabel("Thời gian bắt đầu ca làm: --"), c);
		c.gridy = 9;
		pMain.add(lDenLuc = new JLabel("Thời gian kết thúc ca làm: --"), c);

		c.anchor = GridBagConstraints.NORTHWEST;
		c.gridx = 0;
		c.gridy = 10;
		c.insets = new Insets(4, 0, 8, 0);
		pMain.add(tieuDeBieuDo = new TieuDe("Biểu đồ"), c);

		c.gridy = 11;
		c.insets = new Insets(4, 4, 4, 4);
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weighty = 1;
		pMain.add(pChart = BieuDo.taoBieuDoDuong(dataset, "", "Ngày", "Doanh thu (VNĐ)"), c);

		add(bThaoTac = Box.createHorizontalBox(), BorderLayout.SOUTH);
		bThaoTac.add(btnXuatPDF = new SecondaryButton("Xuất PDF"));
		bThaoTac.add(Box.createGlue());
		bThaoTac.add(btnIn = new PrimaryButton("In báo cáo"));

		bThaoTac.setBorder(new EmptyBorder(16, 0, 0, 0));

		pScroll.setBorder(null);
		pScroll.getVerticalScrollBar().setUnitIncrement(8);
		pScroll.getHorizontalScrollBar().setUnitIncrement(8);
		pScroll.getVerticalScrollBar().setBackground(Colors.SECONDARY);
		pScroll.getHorizontalScrollBar().setBackground(Colors.SECONDARY);
		pScroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Colors.PRIMARY;
				this.thumbDarkShadowColor = Colors.PRIMARY;
				this.thumbHighlightColor = Colors.PRIMARY;
				this.thumbLightShadowColor = Colors.PRIMARY;
			}

			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton button = super.createDecreaseButton(orientation);
				button.setBackground(Colors.WHITE);
				return button;
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton button = super.createIncreaseButton(orientation);
				button.setBackground(Colors.WHITE);
				return button;
			}
		});
		pScroll.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Colors.PRIMARY;
			}

			@Override
			protected JButton createDecreaseButton(int orientation) {
				JButton button = super.createDecreaseButton(orientation);
				button.setBackground(Colors.WHITE);
				return button;
			}

			@Override
			protected JButton createIncreaseButton(int orientation) {
				JButton button = super.createIncreaseButton(orientation);
				button.setBackground(Colors.WHITE);
				return button;
			}
		});

		pMain.setBackground(Colors.BACKGROUND);

		lNguoiTao.setFont(Fonts.TEXT);
		lThoiGianTao.setFont(Fonts.TEXT);
		lLoaiBaoCao.setFont(Fonts.TEXT);
		lTuNgay.setFont(Fonts.TEXT);
		lDenNgay.setFont(Fonts.TEXT);
		lNhanVien.setFont(Fonts.TEXT);
		lKhachHang.setFont(Fonts.TEXT);
		lSanPham.setFont(Fonts.TEXT);
		lTuLuc.setFont(Fonts.TEXT);
		lDenLuc.setFont(Fonts.TEXT);
		lMaKhuyenMai.setFont(Fonts.TEXT);
		lCaLam.setFont(Fonts.TEXT);
	}

	public static void main(String[] args) {
		KetQuaThongKeView view = new KetQuaThongKeView();
		view.setSize(1280, 720);
		view.setDefaultCloseOperation(EXIT_ON_CLOSE);
		view.setLocationRelativeTo(null);
		view.setVisible(true);
	}

}
