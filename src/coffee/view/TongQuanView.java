package coffee.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFrame;

import org.jfree.data.category.DefaultCategoryDataset;

import coffee.Application;
import coffee.gui.BieuDo;
import coffee.gui.CoffeePanel;
import coffee.gui.CoffeeScrollPane;
import coffee.gui.TheThongKe;
import coffee.gui.TieuDe;
import coffee.model.ThongKeNgay;
import coffee.model.ThongKeTuan;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;

public class TongQuanView extends JFrame implements Printable {

	private static final long serialVersionUID = 4640477356217922276L;

	private TieuDe td0;
	private TieuDe td1;
	private TieuDe td2;
	private TieuDe td3;
	private TieuDe td4;

	private DefaultCategoryDataset ds1;
	private DefaultCategoryDataset ds2;
	private DefaultCategoryDataset ds3;
	private DefaultCategoryDataset ds4;

	private TheThongKe theDoanhThu;
	private TheThongKe theSoLuongHoaDon;
	private TheThongKe theSoLuongKhachHang;
	private TheThongKe theSoLuongKhuyenMaiDaDung;

	private Box pNorth;
	private Box pCenter;
	private Box pSouth;

	private CoffeePanel pMain;

	private SecondaryButton btnTaoMoi;
	private PrimaryButton btnInBaoCao;

	public SecondaryButton getBtnTaoMoi() {
		return btnTaoMoi;
	}

	public PrimaryButton getBtnInBaoCao() {
		return btnInBaoCao;
	}

	public TongQuanView() {
		setContentPane(new CoffeePanel(new BorderLayout(16, 16)));

		add(new CoffeeScrollPane(pMain = new CoffeePanel(new BorderLayout(), new Insets(16, 16, 16, 16))));

		// NORTH
		pMain.add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);

		pNorth.add(td0 = new TieuDe("Tổng quan trong ngày (dd/MM/yyyy)"));
		td0.setAlignmentX(LEFT_ALIGNMENT);

		CoffeePanel pThe = new CoffeePanel(new FlowLayout(FlowLayout.LEFT));
		pThe.setAlignmentX(LEFT_ALIGNMENT);

		pThe.add(theDoanhThu = new TheThongKe("Doanh thu", "0"));
		pThe.add(theSoLuongHoaDon = new TheThongKe("Số lượng hóa đơn", "0"));
		pThe.add(theSoLuongKhachHang = new TheThongKe("Số lượng khách hàng", "0"));
		pThe.add(theSoLuongKhuyenMaiDaDung = new TheThongKe("Khuyến mãi đã dùng", "0"));
		pThe.setMaximumSize(pThe.getPreferredSize());
		pNorth.add(pThe);

		pNorth.add(Box.createVerticalStrut(16));

		// CENTER
		pMain.add(pCenter = Box.createVerticalBox(), BorderLayout.CENTER);
		pCenter.add(td1 = new TieuDe("Tổng quan doanh thu trong 7 ngày (dd/MM/yyyy - dd/MM/yyyy)"));
		pCenter.add(BieuDo.taoBieuDoDuong(ds1 = new DefaultCategoryDataset(), "", "Ngày", "Doanh thu (VNĐ)"));
		pCenter.add(Box.createVerticalStrut(16));
		pCenter.add(td2 = new TieuDe("Tổng quan số lượng hóa đơn trong 7 ngày (dd/MM/yyyy - dd/MM/yyyy)"));
		pCenter.add(BieuDo.taoBieuDoDuong(ds2 = new DefaultCategoryDataset(), "", "Ngày", "Số lượng hóa đơn"));
		pCenter.add(Box.createVerticalStrut(16));
		pCenter.add(td3 = new TieuDe("Tổng quan số lượng khách hàng trong 7 ngày (dd/MM/yyyy - dd/MM/yyyy)"));
		pCenter.add(BieuDo.taoBieuDoDuong(ds3 = new DefaultCategoryDataset(), "", "Ngày", "Số lượng khách hàng"));
		pCenter.add(Box.createVerticalStrut(16));
		pCenter.add(td4 = new TieuDe("Tổng quan số lượng khuyến mãi đã dùng trong 7 ngày (dd/MM/yyyy - dd/MM/yyyy)"));
		pCenter.add(BieuDo.taoBieuDoDuong(ds4 = new DefaultCategoryDataset(), "", "Ngày", "SL khuyến mãi đã dùng"));
		pCenter.add(Box.createVerticalStrut(16));

		// SOUTH
		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
		pSouth.add(btnTaoMoi = new SecondaryButton("Tạo báo cáo mới"));
		pSouth.add(Box.createGlue());
		pSouth.add(btnInBaoCao = new PrimaryButton("In thống kê"));

		pack();
	}

	public void setThongKe(ThongKeNgay thongKeNgay, ThongKeTuan thongKeTuan) {
		td0.setText("Tổng quan trong ngày (" + Application.FMT_DATE.format(thongKeNgay.getNgayTaoThongKe()) + ")");
		String doanhThu = Application.FMT_CURRENCY.format(thongKeNgay.getDoanhThu());
		String soLuongHoaDon = Integer.toString(thongKeNgay.getSoLuongHoaDon());
		String soLuongKhachHang = Integer.toString(thongKeNgay.getSoLuongKhachHang());
		String soLuongKhuyenMai = Integer.toString(thongKeNgay.getSoLuongKhuyenMaiDaDung());
		theDoanhThu.setValueText(doanhThu);
		theSoLuongHoaDon.setValueText(soLuongHoaDon);
		theSoLuongKhachHang.setValueText(soLuongKhachHang);
		theSoLuongKhuyenMaiDaDung.setValueText(soLuongKhuyenMai);

		String dateRange = Application.FMT_DATE.format(thongKeTuan.getNgayThongKe()[0]) + " - "
				+ Application.FMT_DATE.format(thongKeTuan.getNgayThongKe()[6]);
		td1.setText("Tổng quan doanh thu trong 7 ngày (" + dateRange + ")");
		td2.setText("Tổng quan số lượng hóa đơn trong 7 ngày (" + dateRange + ")");
		td3.setText("Tổng quan số lượng khách hàng trong 7 ngày (" + dateRange + ")");
		td4.setText("Tổng quan số lượng khuyến mãi đã dùng trong 7 ngày (" + dateRange + ")");

		ds1.clear();
		ds2.clear();
		ds3.clear();
		ds4.clear();
		for (int i = 0; i < 7; i++) {
			String ngayThongKe = Application.FMT_DATE.format(thongKeTuan.getNgayThongKe()[i]);
			ds1.addValue(thongKeTuan.getDoanhThu()[i], "Doanh thu", ngayThongKe);
			ds2.addValue(thongKeTuan.getSoLuongHoaDon()[i], "Số lượng hóa đơn", ngayThongKe);
			ds3.addValue(thongKeTuan.getSoLuongKhachHang()[i], "Số lượng khách hàng", ngayThongKe);
			ds4.addValue(thongKeTuan.getSoLuongKhuyenMaiDaDung()[i], "Số lượng khuyến mãi đã dùng", ngayThongKe);
		}
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		double pageWidth = pageFormat.getImageableWidth();
		double pageHeight = pageFormat.getImageableHeight();
		double scaleX = pageWidth / pMain.getWidth();
		g2d.scale(scaleX, scaleX);

		double scaledPageHeight = pageHeight / scaleX;
		int totalPages = (int) Math.ceil(pMain.getHeight() / scaledPageHeight);

		if (pageIndex >= totalPages) {
			return NO_SUCH_PAGE;
		}

		g2d.translate(pageFormat.getImageableX(), -pageIndex * scaledPageHeight);

		pMain.printAll(g2d);

		return PAGE_EXISTS;
	}

}
