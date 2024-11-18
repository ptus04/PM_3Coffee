package coffee.controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import coffee.Application;
import coffee.dao.CaLamViecDAO;
import coffee.dao.ChiTietDonHangDAO;
import coffee.dao.DonHang_DAO;
import coffee.dao.KhachHangDAO;
import coffee.dao.KhuyenMaiDAO;
import coffee.dao.NhanVienDAO;
import coffee.dao.SanPhamDAO;
import coffee.entity.CaLamViec;
import coffee.entity.ChiTietDonHang;
import coffee.entity.DonHang;
import coffee.entity.KhachHang;
import coffee.entity.KhuyenMai;
import coffee.entity.NhanVien;
import coffee.entity.SanPham;
import coffee.model.ThongKeNgay;
import coffee.model.ThongKeTheoTieuChi;
import coffee.model.ThongKeTuan;
import coffee.model.TieuChiThongKe;
import coffee.shared.utility.PrinterUtilities;
import coffee.view.KetQuaThongKeView;
import coffee.view.TaoMoiView;
import coffee.view.TongQuanView;

public class BaoCaoThongKeController {

	private static BaoCaoThongKeController instance;

	public static BaoCaoThongKeController getInstance() {
		if (instance == null)
			instance = new BaoCaoThongKeController();
		return instance;
	}

	private TongQuanView view;

	public JFrame getView() {
		return view;
	}

	public BaoCaoThongKeController() {
		this.view = new TongQuanView();

		view.getBtnTaoMoi().addActionListener(e -> showTaoMoiView());
		view.getBtnInBaoCao()
				.addActionListener(e -> PrinterUtilities.getInstance().print(view, "In báo cáo tổng quan"));
	}

	public void reloadThongKe() {
		ThongKeNgay thongKeNgay = taoThongKeNgay(LocalDate.now());
		ThongKeTuan thongKeTuan = taoThongKeTuan(LocalDate.now().minusDays(1), 7);
		if (thongKeNgay == null || thongKeTuan == null)
			JOptionPane.showMessageDialog(view, "Không thể tạo thống kê!", "Lỗi truy xuất", JOptionPane.ERROR_MESSAGE);
		view.setThongKe(thongKeNgay, thongKeTuan);
	}

	private ThongKeNgay taoThongKeNgay(LocalDate date) {
		try {
			List<DonHang> donHangList = DonHang_DAO.getInstance().selectAll();
			List<ChiTietDonHang> chiTietDonHangList = ChiTietDonHangDAO.getInstance().getAll();
			List<SanPham> sanPhamList = SanPhamDAO.getInstance().getAll();

			List<DonHang> donHangHomNayList = donHangList.stream()
					.filter(p -> p.getThoiGianTao().toLocalDate().equals(date)).toList();
			List<ChiTietDonHang> chiTietDonHangHomNayList = chiTietDonHangList.stream().filter(p -> donHangHomNayList
					.stream().anyMatch(q -> q.getMaDonHang().equals(p.getDonHang().getMaDonHang()))).toList();
			Map<String, Double> mapDonGia = sanPhamList.stream()
					.collect(Collectors.toMap(SanPham::getMaSanPham, SanPham::getDonGia));

			double doanhThu = chiTietDonHangHomNayList.stream()
					.mapToDouble(p -> mapDonGia.get(p.getSanPham().getMaSanPham()) * p.getSoLuong()).sum();
			int soLuongHoaDon = donHangHomNayList.size();
			int soLuongKhachHang = (int) donHangHomNayList.stream().map(DonHang::getKhachHang).distinct().count();
			int soLuongKhuyenMaiDaDung = (int) donHangHomNayList.stream().filter(p -> p.getKhuyenmai() != null).count();

			return new ThongKeNgay(date, doanhThu, soLuongHoaDon, soLuongKhachHang, soLuongKhuyenMaiDaDung);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private ThongKeTuan taoThongKeTuan(LocalDate endDate, int numOfDays) {
		ThongKeNgay[] thongKeNgays = new ThongKeNgay[numOfDays];
		for (int i = 0; i < thongKeNgays.length; i++) {
			thongKeNgays[i] = taoThongKeNgay(endDate.minusDays(numOfDays - 1 - i));
		}

		LocalDate[] ngayTaoThongKe = Stream.of(thongKeNgays).map(m -> m.getNgayTaoThongKe()).toArray(LocalDate[]::new);
		double[] doanhThu = Stream.of(thongKeNgays).mapToDouble(m -> m.getDoanhThu()).toArray();
		int[] soLuongHoaDon = Stream.of(thongKeNgays).mapToInt(m -> m.getSoLuongHoaDon()).toArray();
		int[] soLuongKhachHang = Stream.of(thongKeNgays).mapToInt(m -> m.getSoLuongKhachHang()).toArray();
		int[] soLuongKhuyenMaiDaDung = Stream.of(thongKeNgays).mapToInt(m -> m.getSoLuongKhuyenMaiDaDung()).toArray();
		return new ThongKeTuan(ngayTaoThongKe, doanhThu, soLuongHoaDon, soLuongKhachHang, soLuongKhuyenMaiDaDung);
	}

	private ThongKeTheoTieuChi taoThongKeTheoTieuChi(TieuChiThongKe tieuChi) {
		try {
			List<DonHang> lstDonHang = DonHang_DAO.getInstance().selectAll();
			List<ChiTietDonHang> lstChiTiet = ChiTietDonHangDAO.getInstance().getAll();
			List<SanPham> lstSanPham = SanPhamDAO.getInstance().getAll();

			Stream<DonHang> str = lstDonHang.stream();
			if (tieuChi.getNhanVien() != null) {
				str = str.filter(p -> p.getNhanvien().getMaNhanVien().equals(tieuChi.getNhanVien().getMaNhanVien()));
			}
			if (tieuChi.getKhachHang() != null) {
				str = str
						.filter(p -> p.getKhachHang().getSoDienThoai().equals(tieuChi.getKhachHang().getSoDienThoai()));
			}
			if (tieuChi.getSanPham() != null) {
				List<ChiTietDonHang> chiTietDonHangListFilter = lstChiTiet.stream()
						.filter(p -> p.getSanPham().getMaSanPham().equals(tieuChi.getSanPham().getMaSanPham()))
						.toList();
				str = str.filter(p -> chiTietDonHangListFilter.stream()
						.anyMatch(q -> q.getDonHang().getMaDonHang().equals(p.getMaDonHang())));
			}
			if (tieuChi.getKhuyenMai() != null) {
				if (tieuChi.getKhuyenMai().getMaKhuyenMai() == null) {
					str = str.filter(p -> p.getKhuyenmai() == null);
				} else {
					str = str.filter(p -> p.getKhuyenmai() != null
							&& p.getKhuyenmai().getMaKhuyenMai().equals(tieuChi.getKhuyenMai().getMaKhuyenMai()));
				}

			}
//			if (tieuChi.getCaLam() != null) {
//				CaLamViec cl = CaLamViecDAO.getInstance().getCaLamViecByMa(tieuChi.getCaLam().getMaCaLam());
//				str = str.filter(p -> p.getThoiGianTao().isAfter(cl.getThoiGianBatDau())
//						&& p.getThoiGianTao().isBefore(cl.getThoiGianKetThuc()));
//			}
			if (tieuChi.getTuNgay() != null) {
				str = str.filter(p -> p.getThoiGianTao().toLocalDate().isAfter(tieuChi.getTuNgay())
						|| p.getThoiGianTao().toLocalDate().isEqual(tieuChi.getTuNgay()));
			}
			if (tieuChi.getDenNgay() != null) {
				str = str.filter(p -> p.getThoiGianTao().toLocalDate().isBefore(tieuChi.getDenNgay())
						|| p.getThoiGianTao().toLocalDate().isEqual(tieuChi.getDenNgay()));
			}

			List<DonHang> lstDonHang2 = str.toList();

			List<LocalDate> lstNgay = lstDonHang2.stream().map(p -> p.getThoiGianTao().toLocalDate()).distinct()
					.sorted().toList();
			List<Double> lstdoanhThu = lstNgay.stream()
					.map(p -> lstDonHang2.stream().filter(q -> q.getThoiGianTao().toLocalDate().equals(p))
							.mapToDouble(q -> lstChiTiet.stream()
									.filter(r -> r.getDonHang().getMaDonHang().equals(q.getMaDonHang()))
									.mapToDouble(r -> lstSanPham.stream()
											.filter(s -> s.getMaSanPham().equals(r.getSanPham().getMaSanPham()))
											.mapToDouble(s -> s.getDonGia()).sum() * r.getSoLuong())
									.sum())
							.sum())
					.toList();

			List<Integer> lstSoLuongHoaDon = lstNgay.stream().map(
					p -> (int) lstDonHang2.stream().filter(q -> q.getThoiGianTao().toLocalDate().equals(p)).count())
					.toList();

			List<Integer> lstSoLuongKhachHang = lstNgay.stream()
					.map(p -> (int) lstDonHang2.stream().filter(q -> q.getThoiGianTao().toLocalDate().equals(p))
							.map(DonHang::getKhachHang).distinct().count())
					.toList();

			List<Integer> lstSoLuongKhuyenMai = lstNgay.stream()
					.map(p -> (int) lstDonHang2.stream().filter(q -> q.getThoiGianTao().toLocalDate().equals(p))
							.filter(q -> q.getKhuyenmai() != null).count())
					.toList();

			return new ThongKeTheoTieuChi(lstNgay, lstdoanhThu, lstSoLuongHoaDon, lstSoLuongKhachHang,
					lstSoLuongKhuyenMai);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void showTaoMoiView() {
		try {
			List<NhanVien> nhanVienList = NhanVienDAO.getInstance().getAllNhanVien();
			List<KhachHang> khachHangList = KhachHangDAO.getInstance().getAll();
			List<SanPham> sanPhamList = SanPhamDAO.getInstance().getAll();
			List<KhuyenMai> khuyenMai = KhuyenMaiDAO.getInstance().getAll();
			List<CaLamViec> caLamViec = CaLamViecDAO.getInstance().getAllCaLamViec();

			TaoMoiView taoMoiView = new TaoMoiView(view);
			taoMoiView.load(nhanVienList, khachHangList, sanPhamList, khuyenMai, caLamViec);
			taoMoiView.getBtnXemBaoCao().addActionListener(e -> xemBaoCao(taoMoiView));
			taoMoiView.setVisible(true);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage(), "Lỗi truy vấn dữ liệu", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void xemBaoCao(TaoMoiView taoMoiView) {
		String loai = (String) taoMoiView.getCbLoaiBaoCao().getSelectedItem();
		String maNV = ((String) taoMoiView.getCbNhanVien().getSelectedItem());
		String maKH = ((String) taoMoiView.getCbKhachHang().getSelectedItem());
		String maSP = ((String) taoMoiView.getCbSanPham().getSelectedItem());
		String maKM = ((String) taoMoiView.getCbKhuyenMai().getSelectedItem());
//		String maCL = ((String) taoMoiView.getCbCaLam().getSelectedItem());

		try {
			NhanVien nv = maNV.equals("Tất cả") ? null
					: NhanVienDAO.getInstance().getByNhanVien(new NhanVien(maNV.split("-")[0].trim()));
			KhachHang kh = maKH.equals("Tất cả") ? null : KhachHangDAO.getInstance().getById(maKH.split("-")[0].trim());
			SanPham sp = maSP.equals("Tất cả") ? null : SanPhamDAO.getInstance().getById(maSP.split("-")[0].trim());
			KhuyenMai km = maKM.equals("Tất cả") ? null
					: maKM.equals("Không áp dụng") ? new KhuyenMai()
							: KhuyenMaiDAO.getInstance().getById(maKM.split("-")[0].trim());
//			CaLamViec cl = maCL.equals("Tất cả") ? null
//					: CaLamViecDAO.getInstance().getCaLamViecByMa(maCL.split("-")[0].trim());

			String tu = taoMoiView.getTfTuNgay().getText();
			String den = taoMoiView.getTfDenNgay().getText();

			TieuChiThongKe tieuChi = new TieuChiThongKe(loai, nv, kh, sp, km, null,
					tu.equals("Chưa chọn") ? null : LocalDate.parse(tu, Application.FMT_DATE),
					den.equals("Chưa chọn") ? null : LocalDate.parse(den, Application.FMT_DATE));

			taoMoiView.dispose();
			KetQuaThongKeView kq = new KetQuaThongKeView(TrangChuController.getInstance().getNhanVien(), tieuChi,
					taoThongKeTheoTieuChi(tieuChi));
			kq.setVisible(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
