package coffee.controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JFrame;

import coffee.dao.ChiTietDonHangDAO;
import coffee.dao.DonHang_DAO;
import coffee.dao.SanPhamDAO;
import coffee.entity.ChiTietDonHang;
import coffee.entity.DonHang;
import coffee.entity.SanPham;
import coffee.model.ThongKeNgay;
import coffee.shared.utility.PrinterUtilities;
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

		view.getBtnTaoMoi().addActionListener(this::showTaoMoiView);
		view.getBtnInBaoCao().addActionListener(this::inBaoCao);
	}

	public ThongKeNgay taoThongKeNgay(LocalDate date) {
		try {
			List<DonHang> donHangs = DonHang_DAO.getInstance().selectAll();
			List<ChiTietDonHang> chiTietDonHangs = ChiTietDonHangDAO.getInstance().getAll();
			List<SanPham> sanPhams = SanPhamDAO.getInstance().getAll();

			List<DonHang> filteredDonHangs = donHangs.stream()
					.filter(p -> p.getThoiGianTao().toLocalDate().equals(date)).toList();
			List<ChiTietDonHang> filteredChiTietDonHangs = chiTietDonHangs.stream().filter(p -> filteredDonHangs
					.stream().anyMatch(q -> q.getMaDonHang().equals(p.getDonHang().getMaDonHang()))).toList();
			Map<String, Double> filteredSanPhams = sanPhams.stream()
					.filter(p -> filteredChiTietDonHangs.stream()
							.anyMatch(q -> q.getSanPham().getMaSanPham().equals(p.getMaSanPham())))
					.collect(Collectors.toMap(SanPham::getMaSanPham, SanPham::getDonGia));

			System.out.println(filteredChiTietDonHangs.stream()
					.collect(Collectors.groupingBy(t -> t.getDonHang().getMaDonHang(), Collectors.summingDouble(
							t1 -> t1.getSoLuong() * filteredSanPhams.get(t1.getSanPham().getMaSanPham())))));

			int doanhThu = 0;

			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

//	private ThongKeTuan taoThongKeTuan(LocalDate endDate, int numOfDays) {
//		ThongKeNgay[] thongKeNgays = new ThongKeNgay[numOfDays];
//		for (int i = 0; i < thongKeNgays.length; i++) {
//			thongKeNgays[i] = taoThongKeNgay(endDate.minusDays(numOfDays - i));
//		}
//
//		double[] doanhThu = Stream.of(thongKeNgays).mapToDouble(m -> m.getDoanhThu()).toArray();
//		int[] soLuongHoaDon = Stream.of(thongKeNgays).mapToInt(m -> m.getSoLuongHoaDon()).toArray();
//		int[] soLuongVeBanRa = Stream.of(thongKeNgays).mapToInt(m -> m.getSoLuongVeBanRa()).toArray();
//		int[] soLuongVeDaHuy = Stream.of(thongKeNgays).mapToInt(m -> m.getSoLuongVeDaHuy()).toArray();
//		int[] soLuongKhuyenMaiDaDung = Stream.of(thongKeNgays).mapToInt(m -> m.getSoLuongKhuyenMaiDaDung()).toArray();
//		return new ThongKeTuan(endDate, doanhThu, soLuongHoaDon, soLuongVeBanRa, soLuongVeDaHuy,
//				soLuongKhuyenMaiDaDung);
//	}

	private void showTaoMoiView(ActionEvent e) {
		TaoMoiView taoMoiView = new TaoMoiView(null);
		taoMoiView.setVisible(true);
	}

	private void inBaoCao(ActionEvent e) {
		PrinterUtilities.getInstance().print(view, "In báo cáo tổng quan");
	}

	public static void main(String[] args) {
		BaoCaoThongKeController controller = new BaoCaoThongKeController();
		System.out.println(controller.taoThongKeNgay(LocalDate.of(2024, 11, 14)));
	}

}
