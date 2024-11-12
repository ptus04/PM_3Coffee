package coffee.controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import coffee.dao.NhanVienDAO;
import coffee.entity.NhanVien;
import coffee.entity.TaiKhoan;
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

	private TrangChuController() {
		this.view = new TrangChuView();

		view.getNavigationMenu().getBtnDangXuat().addActionListener(this::onLogout);
	}

	public void showView(TaiKhoan taiKhoan) {
		try {
			this.nhanVien = NhanVienDAO.getInstance().getByNhanVien(taiKhoan.getNhanVien());
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(view, e.getMessage(), "Lỗi truy vấn dữ liệu", JOptionPane.ERROR_MESSAGE);
			logout();
			return;
		}

		view.setVisible(true);
		view.setNhanVien(this.nhanVien);
	}

	private void onLogout(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận đăng xuất",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.YES_OPTION) {
			logout();
			// TODO: Thêm lịch sử đăng xuất
		}
	}

	private void logout() {
		hideView();
		DangNhapController.getInstance().showView();
	}

	public void hideView() {
		view.setVisible(false);
	}

}
