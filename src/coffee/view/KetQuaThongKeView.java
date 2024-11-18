package coffee.view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Objects;

import javax.swing.Box;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;

import coffee.constant.Colors;
import coffee.entity.CaLamViec;
import coffee.entity.KhachHang;
import coffee.entity.KhuyenMai;
import coffee.entity.NhanVien;
import coffee.entity.SanPham;
import coffee.gui.BieuDo;
import coffee.gui.CoffeeLabel;
import coffee.gui.CoffeePanel;
import coffee.gui.CoffeeScrollPane;
import coffee.gui.TieuDe;
import coffee.model.ThongKeTheoTieuChi;
import coffee.model.TieuChiThongKe;
import coffee.shared.PrimaryButton;
import coffee.shared.SecondaryButton;
import coffee.shared.utility.PrinterUtilities;

public class KetQuaThongKeView extends JFrame implements Printable {

	private static final long serialVersionUID = 6103116999501325988L;

	private CoffeePanel pMain;
	private Box pSouth;

	private CoffeeLabel lNguoiTao;
	private CoffeeLabel lThoiGian;
	private CoffeeLabel lLoaiBaoCao;
	private CoffeeLabel lTuNgay;
	private CoffeeLabel lDenNgay;
	private CoffeeLabel lNhanVien;
	private CoffeeLabel lKhachHang;
	private CoffeeLabel lSanPham;
	private CoffeeLabel lCaLam;
	private CoffeeLabel lTuLuc;
	private CoffeeLabel lDenLuc;
	private CoffeeLabel lMaKhuyenMai;

	private SecondaryButton btnXuatPDF;
	private PrimaryButton btnIn;

	private ChartPanel pChart;

	private NhanVien nhanVien;
	private TieuChiThongKe tieuChi;

	private Box pNorth;
	private Box p1;
	private Box p2;
	private Box p3;
	private Box p4;
	private Box p5;
	private Box p6;
	private Box p7;
	private Box p8;
	private Box p9;

	private Box pCharts;

	public KetQuaThongKeView(NhanVien nhanVien, TieuChiThongKe tieuChi, ThongKeTheoTieuChi thongKe) {
		setTitle("Kết quả thống kê");
		setContentPane(new CoffeePanel(new BorderLayout(), new Insets(16, 16, 16, 16)));
		getContentPane().setBackground(Colors.BACKGROUND);

		add(new CoffeeScrollPane(pMain = new CoffeePanel(new BorderLayout(), new Insets(16, 16, 16, 16))));

		String nguoiTao = nhanVien.getHoTen();
		String tg = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));
		String loai = tieuChi.getLoaiThongKe();
		LocalDate tu = tieuChi.getTuNgay();
		LocalDate den = tieuChi.getDenNgay();
		NhanVien nv = tieuChi.getNhanVien();
		KhachHang kh = tieuChi.getKhachHang();
		SanPham sp = tieuChi.getSanPham();
		KhuyenMai km = tieuChi.getKhuyenMai();
//		CaLamViec cl = tieuChi.getCaLam();
//
//		LocalTime clBd = null;
//		LocalTime clKt = null;
//		if (cl != null) {
//			clBd = cl.getThoiGianBatDau();
//			clKt = cl.getThoiGianKetThuc();
//		}

		pMain.add(pNorth = Box.createVerticalBox(), BorderLayout.NORTH);
		pNorth.add(new TieuDe("Báo cáo"));

		pNorth.add(p1 = Box.createHorizontalBox());
		p1.add(lNguoiTao = new CoffeeLabel("Người tạo báo cáo: " + nguoiTao));
		p1.add(Box.createHorizontalGlue());
		p1.add(lThoiGian = new CoffeeLabel("Thời gian tạo báo cáo: " + tg));

		pNorth.add(p2 = Box.createHorizontalBox());
		p2.add(lLoaiBaoCao = new CoffeeLabel("Loại báo cáo: " + (loai.equals("Tất cả")
				? "Doanh thu; Số lượng hóa đơn; Số lượng khách hàng; Số lượng khuyến mãi đã dùng"
				: loai)));
		p2.add(Box.createHorizontalGlue());
		p2.add(lTuNgay = new CoffeeLabel("Thống kê từ ngày: "
				+ (tu == null ? "Không chọn" : tu.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));

		pNorth.add(p3 = Box.createHorizontalBox());
		p3.add(Box.createHorizontalGlue());
		p3.add(lDenNgay = new CoffeeLabel("Thống kê đến ngày: "
				+ (den == null ? "Không chọn" : den.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));

		pNorth.add(p4 = Box.createHorizontalBox());
		p4.add(lNhanVien = new CoffeeLabel(
				"Nhân viên được thống kê: " + (nv == null ? "Tất cả" : nv.getMaNhanVien() + " - " + nv.getHoTen())));
		p4.add(Box.createHorizontalGlue());

		pNorth.add(p5 = Box.createHorizontalBox());
		p5.add(lKhachHang = new CoffeeLabel("Khách hàng được thống kê: "
				+ (kh == null ? "Tất cả" : kh.getSoDienThoai() + " - " + kh.getTenKhachHang())));
		p5.add(Box.createHorizontalGlue());

		pNorth.add(p6 = Box.createHorizontalBox());
		p6.add(lSanPham = new CoffeeLabel(
				"Sản phẩm được thống kê: " + (sp == null ? "Tất cả" : sp.getMaSanPham() + " - " + sp.getTenSanPham())));
		p6.add(Box.createHorizontalGlue());

		pNorth.add(p7 = Box.createHorizontalBox());
		p7.add(lMaKhuyenMai = new CoffeeLabel("Mã khuyến mãi được thống kê: "
				+ (km == null ? "Tất cả" : km.getMaKhuyenMai() == null ? "Không áp dụng " : km.getMaKhuyenMai())));
		p7.add(Box.createHorizontalGlue());

//		pNorth.add(p8 = Box.createHorizontalBox());
//		p8.add(lCaLam = new CoffeeLabel(
//				"Ca làm được thống kê: " + (cl == null ? "Tất cả" : cl.getMaCaLam() + " - " + cl.getTenCaLam())));
//		p8.add(Box.createHorizontalGlue());
//		p8.add(lTuLuc = new CoffeeLabel("Thời gian bắt đầu ca làm: "
//				+ (clBd == null ? "Không chọn" : clBd.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy")))));
//
//		pNorth.add(p9 = Box.createHorizontalBox());
//		p9.add(Box.createHorizontalGlue());
//		p9.add(lDenLuc = new CoffeeLabel("Thời gian kết thúc ca làm: "
//				+ (clKt == null ? "Không chọn" : clKt.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy")))));

		pMain.add(pCharts = Box.createVerticalBox(), BorderLayout.CENTER);
		if (tieuChi.getLoaiThongKe().equals("Tất cả") || tieuChi.getLoaiThongKe().equals("Doanh thu")) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (int i = 0; i < thongKe.getNgay().size(); i++) {
				dataset.addValue(thongKe.getDoanhThu().get(i), "Doanh thu", thongKe.getNgay().get(i));
			}
			pCharts.add(new TieuDe("Biểu đồ doanh thu"));
			pCharts.add(pChart = BieuDo.taoBieuDoDuong(dataset, "", "Ngày", "Doanh thu (VNĐ)"));
		}
		if (tieuChi.getLoaiThongKe().equals("Tất cả") || tieuChi.getLoaiThongKe().equals("Số lượng hóa đơn")) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (int i = 0; i < thongKe.getNgay().size(); i++) {
				dataset.addValue(thongKe.getSoLuongHoaDon().get(i), "Số lượng hóa đơn", thongKe.getNgay().get(i));
			}
			pCharts.add(new TieuDe("Biểu đồ số lượng hóa đơn"));
			pCharts.add(pChart = BieuDo.taoBieuDoDuong(dataset, "", "Ngày", "Số lượng hóa đơn"));
		}
		if (tieuChi.getLoaiThongKe().equals("Tất cả") || tieuChi.getLoaiThongKe().equals("Số lượng khách hàng")) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (int i = 0; i < thongKe.getNgay().size(); i++) {
				dataset.addValue(thongKe.getSoLuongKhachHang().get(i), "Số lượng khách hàng", thongKe.getNgay().get(i));
			}
			pCharts.add(new TieuDe("Biểu đồ số lượng khách hàng"));
			pCharts.add(pChart = BieuDo.taoBieuDoDuong(dataset, "", "Ngày", "Số lượng khách hàng"));
		}
		if (tieuChi.getLoaiThongKe().equals("Tất cả")
				|| tieuChi.getLoaiThongKe().equals("Số lượng khuyến mãi đã dùng")) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for (int i = 0; i < thongKe.getNgay().size(); i++) {
				dataset.addValue(thongKe.getSoLuongKhuyenMai().get(i), "Số lượng khuyến mãi đã dùng",
						thongKe.getNgay().get(i));
			}
			pCharts.add(new TieuDe("Biểu đồ số lượng khuyến mãi đã dùng"));
			pCharts.add(pChart = BieuDo.taoBieuDoDuong(dataset, "", "Ngày", "Số lượng khuyến mãi đã dùng"));

		}

		add(pSouth = Box.createHorizontalBox(), BorderLayout.SOUTH);
		pSouth.setBorder(new EmptyBorder(16, 16, 16, 16));
		pSouth.add(Box.createGlue());
		pSouth.add(btnIn = new PrimaryButton("In báo cáo"));

		btnIn.addActionListener(e -> PrinterUtilities.getInstance().print(this, "In kết quả thống kê"));

		setSize(1280, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
