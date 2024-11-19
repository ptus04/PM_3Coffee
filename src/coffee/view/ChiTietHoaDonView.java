package coffee.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.List;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import coffee.Application;
import coffee.constant.Fonts;
import coffee.entity.ChiTietDonHang;
import coffee.entity.DonHang;
import coffee.gui.CoffeeLabel;
import coffee.gui.CoffeePanel;
import coffee.gui.CoffeeTable;
import coffee.gui.TieuDe;

public class ChiTietHoaDonView extends JFrame implements Printable {

	private static final long serialVersionUID = 2433203477901881250L;
	private Box pNorth;
	private Box pSouth;
	private Box p1;
	private Box p2;
	private Box p3;
	private Box p4;
	private Box p5;
	private Box p6;
	private Box p7;
	private Box p8;
	private Box pTongThanhTien;
	private Box pThue;
	private Box pTongCong;
	private Box pTienKhachTra;
	private Box pTienThua;

	private CoffeeTable table;

	public ChiTietHoaDonView(DonHang donHang, List<ChiTietDonHang> chiTietDonHangList) {
		setTitle("Chi tiết hóa đơn");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(new CoffeePanel(new BorderLayout(), new Insets(16, 16, 16, 16)));

		add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);
		pNorth.add(p1 = Box.createHorizontalBox());
		p1.add(Box.createHorizontalGlue());
		p1.add(new CoffeeLabel("In lúc " + Application.FMT_DATE_TIME.format(donHang.getThoiGianIn())));

		pNorth.add(p2 = Box.createHorizontalBox());
		p2.add(new TieuDe("3COFFEE", Fonts.HEADING_1B));

		pNorth.add(p3 = Box.createHorizontalBox());
		p3.add(new CoffeeLabel("12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Hồ Chí Minh"));

		pNorth.add(p4 = Box.createHorizontalBox());
		p4.add(new TieuDe("Hóa đơn thanh toán", Fonts.HEADING_5));

		pNorth.add(p5 = Box.createHorizontalBox());
		p5.add(new CoffeeLabel("Mã hóa đơn:"));
		p5.add(Box.createHorizontalGlue());
		p5.add(new CoffeeLabel(donHang.getMaDonHang()));

		pNorth.add(p6 = Box.createHorizontalBox());
		p6.add(new CoffeeLabel("Khách hàng:"));
		p6.add(Box.createHorizontalGlue());
		p6.add(new CoffeeLabel(donHang.getKhachHang().getTenKhachHang()));

		pNorth.add(p7 = Box.createHorizontalBox());
		p7.add(new CoffeeLabel("Nhân viên:"));
		p7.add(Box.createHorizontalGlue());
		p7.add(new CoffeeLabel(donHang.getNhanvien().getHoTen()));

		pNorth.add(p8 = Box.createHorizontalBox());
		p8.add(new CoffeeLabel("Thời gian:"));
		p8.add(Box.createHorizontalGlue());
		p8.add(new CoffeeLabel(Application.FMT_DATE_TIME.format(donHang.getThoiGianTao())));

		add(new JScrollPane(table = new CoffeeTable()), BorderLayout.CENTER);
		table.setDefaultEditor(Object.class, null);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(new String[] { "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền" });
		for (ChiTietDonHang chiTietDonHang : chiTietDonHangList) {
			model.addRow(new Object[] { chiTietDonHang.getSanPham().getMaSanPham(),
					chiTietDonHang.getSanPham().getTenSanPham(), chiTietDonHang.getSoLuong(),
					Application.FMT_CURRENCY.format(chiTietDonHang.getSanPham().getDonGia()),
					Application.FMT_CURRENCY.format(chiTietDonHang.tinhThanhTien()) });
		}

		add(pSouth = Box.createVerticalBox(), BorderLayout.SOUTH);
		pSouth.add(pTongThanhTien = Box.createHorizontalBox());
		pTongThanhTien.add(new CoffeeLabel("Tổng thành tiền:"));
		pTongThanhTien.add(Box.createHorizontalGlue());
		pTongThanhTien.add(new CoffeeLabel(Application.FMT_CURRENCY.format(donHang.tinhTongTien())));

		pSouth.add(pThue = Box.createHorizontalBox());
		pThue.add(new CoffeeLabel("Thuế (8%):"));
		pThue.add(Box.createHorizontalGlue());
		pThue.add(new CoffeeLabel(Application.FMT_CURRENCY.format(donHang.tinhTongTien() * 0.08)));

		pSouth.add(pTongCong = Box.createHorizontalBox());
		pTongCong.add(new CoffeeLabel("Tổng cộng:"));
		pTongCong.add(Box.createHorizontalGlue());
		pTongCong.add(new CoffeeLabel(Application.FMT_CURRENCY.format(donHang.tinhTongTien() * 1.08), Fonts.HEADING_4));

		pSouth.add(pTienKhachTra = Box.createHorizontalBox());
		pTienKhachTra.add(new CoffeeLabel("Tiền khách trả:"));
		pTienKhachTra.add(Box.createHorizontalGlue());
		pTienKhachTra.add(new CoffeeLabel(Application.FMT_CURRENCY.format(donHang.getKhachTra())));

		pSouth.add(pTienThua = Box.createHorizontalBox());
		pTienThua.add(new CoffeeLabel("Tiền thừa:"));
		pTienThua.add(Box.createHorizontalGlue());
		pTienThua.add(
				new CoffeeLabel(Application.FMT_CURRENCY.format(donHang.getKhachTra() - donHang.tinhTongTien() * 1.08),
						Fonts.HEADING_5));

		pSouth.add(Box.createVerticalStrut(16));
		pSouth.add(new CoffeeLabel("Xin cảm ơn quý khách!"));

		setSize(700, 860);
		setLocationRelativeTo(null);
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		this.setVisible(true);

		Graphics2D g2d = (Graphics2D) graphics;
		g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

		double pageWidth = pageFormat.getImageableWidth();
		double pageHeight = pageFormat.getImageableHeight();
		double scaleX = pageWidth / getContentPane().getWidth();
		g2d.scale(scaleX, scaleX);

		double scaledPageHeight = pageHeight / scaleX;
		int totalPages = (int) Math.ceil(getContentPane().getHeight() / scaledPageHeight);

		if (pageIndex >= totalPages) {
			this.dispose();
			return NO_SUCH_PAGE;
		}

		g2d.translate(pageFormat.getImageableX(), -pageIndex * scaledPageHeight);

		getContentPane().printAll(g2d);

		return PAGE_EXISTS;
	}

}
