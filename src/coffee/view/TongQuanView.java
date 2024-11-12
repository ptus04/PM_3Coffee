package coffee.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import coffee.constant.Colors;
import coffee.gui.BieuDo;
import coffee.gui.TheThongKe;
import coffee.gui.TieuDe;
import coffee.model.ThongKeNgay;
import coffee.model.ThongKeTuan;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;

public class TongQuanView extends JFrame implements Printable {

	private static final long serialVersionUID = 4640477356217922276L;

	private TieuDe lLoading;
	private TieuDe tieuDeNgay;
	private TieuDe tieuDeTuan;

	private TheThongKe theDoanhThu;
	private TheThongKe theSoLuongHoaDon;
	private TheThongKe theSoLuongKhachHang;
	private TheThongKe theSoLuongKhuyenMaiDaDung;

	private Box pNorth;
	private Box pCenter;
	private Box pSouth;

	private SecondaryButton btnTaoMoi;
	private PrimaryButton btnIn;

	private DefaultCategoryDataset doanhThuDataset = new DefaultCategoryDataset();
	private ChartPanel chart;

	private CardLayout cardLayout;

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

	public SecondaryButton getBtnTaoMoi() {
		return btnTaoMoi;
	}

	public PrimaryButton getBtnIn() {
		return btnIn;
	}

	public TongQuanView() {
		setLayout(cardLayout = new CardLayout());
		getContentPane().setBackground(Colors.BACKGROUND);

		add(lLoading = new TieuDe("Đang tải dữ liệu..."));
		lLoading.setHorizontalAlignment(SwingConstants.CENTER);

		add(createUI());

		cardLayout.last(getContentPane());
	}

	private JPanel createUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(Colors.BACKGROUND);

		// NORTH
		pNorth = Box.createVerticalBox();

		pNorth.add(tieuDeNgay = new TieuDe("Tổng quan trong ngày (dd/MM/yyyy)"));
		tieuDeNgay.setAlignmentX(LEFT_ALIGNMENT);

		JPanel pThe = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pThe.setOpaque(false);
		pThe.setAlignmentX(LEFT_ALIGNMENT);

		pThe.add(theDoanhThu = new TheThongKe("Doanh thu", "0"));
		pThe.add(theSoLuongHoaDon = new TheThongKe("Số lượng hóa đơn", "0"));
		pThe.add(theSoLuongKhachHang = new TheThongKe("Số lượng khách hàng", "0"));
		pThe.add(theSoLuongKhuyenMaiDaDung = new TheThongKe("Khuyến mãi đã dùng", "0"));
		pThe.setMaximumSize(pThe.getPreferredSize());
		pNorth.add(pThe);

		pNorth.add(Box.createVerticalStrut(16));

		panel.add(pNorth, BorderLayout.NORTH);

		// CENTER
		pCenter = Box.createVerticalBox();
		pCenter.add(tieuDeTuan = new TieuDe("Tổng quan doanh thu trong 7 ngày (dd/MM/yyyy - dd/MM/yyyy)"));
		pCenter.add(chart = BieuDo.taoBieuDoDuong(doanhThuDataset, "", "Ngày", "Doanh thu (VNĐ)"));
		panel.add(pCenter, BorderLayout.CENTER);

		// SOUTH
		pSouth = Box.createHorizontalBox();
		pSouth.add(btnTaoMoi = new SecondaryButton("Tạo báo cáo mới"));
		pSouth.add(Box.createGlue());
		pSouth.add(btnIn = new PrimaryButton("In thống kê"));
		panel.add(pSouth, BorderLayout.SOUTH);

		return panel;
	}

	public void loadThongKe(ThongKeNgay thongKeNgay, ThongKeTuan thongKeTuan) {
		if (thongKeNgay == null || thongKeTuan == null) {
			JOptionPane.showMessageDialog(this, "Không thể tạo thống kê!", "Lỗi truy xuất", JOptionPane.ERROR_MESSAGE);
			return;
		}

		tieuDeNgay.setText("Tổng quan trong ngày (" + dateFormatter.format(thongKeNgay.getNgayTaoThongKe()) + ")");
		String doanhThu = currencyFormatter.format(thongKeNgay.getDoanhThu());
		String soLuongHoaDon = Integer.toString(thongKeNgay.getSoLuongHoaDon());
		String soLuongKhachHang = Integer.toString(thongKeNgay.getSoLuongKhachHang());
		String soLuongKhuyenMai = Integer.toString(thongKeNgay.getSoLuongKhuyenMaiDaDung());
		theDoanhThu.setValueText(doanhThu);
		theSoLuongHoaDon.setValueText(soLuongHoaDon);
		theSoLuongKhuyenMaiDaDung.setValueText(soLuongKhuyenMai);

		tieuDeTuan
				.setText("Tổng quan doanh thu trong 7 ngày (" + dateFormatter.format(thongKeTuan.getNgayTaoThongKe()[0])
						+ " - " + dateFormatter.format(thongKeTuan.getNgayTaoThongKe()[6]) + ")");

		doanhThuDataset.clear();
		for (int i = 0; i < 7; i++) {
			doanhThuDataset.addValue(thongKeTuan.getDoanhThu()[i], "Doanh thu",
					dateFormatter.format(thongKeTuan.getNgayTaoThongKe()[i]));
		}
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		pSouth.setVisible(false);
		if (pageIndex > 0) {
			pSouth.setVisible(true);
			return NO_SUCH_PAGE;
		}

		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		double pageWidth = pageFormat.getImageableWidth();
		double pageHeight = pageFormat.getImageableHeight();
		double panelWidth = this.getWidth();
		double panelHeight = this.getHeight();
		double scaleX = pageWidth / panelWidth;
		double scaleY = pageHeight / panelHeight;
		double scale = Math.min(scaleX, scaleY);
		g2d.scale(scale, scale);

		this.printAll(g2d);

		return PAGE_EXISTS;
	}

}
