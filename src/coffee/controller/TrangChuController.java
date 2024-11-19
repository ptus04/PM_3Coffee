package coffee.controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import coffee.dao.LichSuHoatDong_DAO;
import coffee.dao.NhanVienDAO;
import coffee.entity.LichSuHoatDong;
import coffee.entity.LoaiLichSu;
import coffee.entity.NhanVien;
import coffee.entity.TaiKhoan;
import coffee.view.TraCuuCaLamView;
import coffee.view.TrangChuView;

public class TrangChuController {

	private static TrangChuController instance;

	public static TrangChuController getInstance() {
		if (instance == null)
			instance = new TrangChuController();
		return instance;
	}

	private TrangChuView view;
	private NhanVien nhanVien;

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	private TrangChuController() {
		this.view = new TrangChuView();

	}

	public void showView(TaiKhoan taiKhoan) {
		try {
			this.nhanVien = NhanVienDAO.getInstance().getByNhanVien(taiKhoan.getNhanVien());

			view.addView(MenuCaPheController.class.getSimpleName(),
					MenuCaPheController.getInstance().getView().getContentPane());
			view.addView(BaoCaoThongKeController.class.getSimpleName(),
					BaoCaoThongKeController.getInstance().getView().getContentPane());
			view.addView(HoaDonController.class.getSimpleName(),
					HoaDonController.getInstance().getView().getContentPane());

			view.addWindowClosingListener(e -> {
				int result = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn đăng xuất?",
						"Xác nhận đăng xuất", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.YES_OPTION) {
					List<LichSuHoatDong> lichSuHoatDongList = LichSuHoatDong_DAO.getInstance().selectAll();

					LocalDateTime thoiGian = LocalDateTime.now();
					long count = lichSuHoatDongList.stream().count();
					String maLichSu = "LS" + thoiGian.getYear() % 100 + String.format("%02d", thoiGian.getMonthValue())
							+ String.format("%02d", thoiGian.getDayOfMonth()) + String.format("%04d", count + 1);
					String noiDung = "Đăng xuất hệ thống";
					double soTienBanGiao = 100;

					try {
						LichSuHoatDong_DAO.getInstance().add(new LichSuHoatDong(maLichSu, thoiGian, noiDung,
								soTienBanGiao, LoaiLichSu.DANG_XUAT, nhanVien));
					} catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(view, ex.getMessage(), "Lỗi cập nhật dữ liệu",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					System.exit(0);
				}
			});

			view.setVisible(true);
			view.setNhanVien(this.nhanVien);

			if (nhanVien.isLaQuanLy()) {
				view.addView(KhachHangController.class.getSimpleName(),
						KhachHangController.getInstance().getView().getContentPane());
				view.addView(NhanVienController.class.getSimpleName(),
						NhanVienController.getInstance().getView().getContentPane());
				view.addView(TraCuuCaLamView.class.getSimpleName(), new TraCuuCaLamView().getContentPane());

				BaoCaoThongKeController.getInstance().reloadThongKe();
				view.showView(BaoCaoThongKeController.class.getSimpleName());

				view.addBtnTrangChuListener(e -> {
					view.showView(BaoCaoThongKeController.class.getSimpleName());
					BaoCaoThongKeController.getInstance().reloadThongKe();
				});
				view.addBtnKhachHangListener(e -> view.showView(KhachHangController.class.getSimpleName()));
				view.addBtnNhanVienListener(e -> view.showView(NhanVienController.class.getSimpleName()));
				view.addBtnCaLamListener(e -> view.showView(TraCuuCaLamView.class.getSimpleName()));

			} else {
				try {
					view.addView(BanCaPheController.class.getSimpleName(),
							BanCaPheController.getInstance().getGui().getContentPane());
					view.showView(BanCaPheController.class.getSimpleName());
					view.addBtnTrangChuListener(e -> view.showView(BanCaPheController.class.getSimpleName()));
					view.addBtnThongKeListener(e -> {
						view.showView(BaoCaoThongKeController.class.getSimpleName());
						BaoCaoThongKeController.getInstance().reloadThongKe();
					});
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			view.addBtnHoaDonListener(e -> {
				view.showView(HoaDonController.class.getSimpleName());
				HoaDonController.getInstance().reloadTable();
			});
			view.addBtnCaPheListener(e -> view.showView(MenuCaPheController.class.getSimpleName()));
			view.addBtnDangXuatListener(e -> logout());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Lỗi truy vấn dữ liệu", JOptionPane.ERROR_MESSAGE);
			hideView();
			DangNhapController.getInstance().showView();
			return;
		}
	}

	private void logout() {
		int result = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận đăng xuất",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			List<LichSuHoatDong> lichSuHoatDongList = LichSuHoatDong_DAO.getInstance().selectAll();

			LocalDateTime thoiGian = LocalDateTime.now();
			long count = lichSuHoatDongList.stream().count();
			String maLichSu = "LS" + thoiGian.getYear() % 100 + String.format("%02d", thoiGian.getMonthValue())
					+ String.format("%02d", thoiGian.getDayOfMonth()) + String.format("%04d", count + 1);
			String noiDung = "Đăng xuất hệ thống";
			double soTienBanGiao = 100;

			try {
				LichSuHoatDong_DAO.getInstance().add(
						new LichSuHoatDong(maLichSu, thoiGian, noiDung, soTienBanGiao, LoaiLichSu.DANG_XUAT, nhanVien));
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(view, ex.getMessage(), "Lỗi cập nhật dữ liệu", JOptionPane.ERROR_MESSAGE);
				return;
			}

			hideView();
			DangNhapController.getInstance().showView();
		}
	}

	public void hideView() {
		view.setVisible(false);
	}

}
