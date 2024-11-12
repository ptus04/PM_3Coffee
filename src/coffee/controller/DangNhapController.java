package coffee.controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import coffee.dao.TaiKhoanDAO;
import coffee.entity.TaiKhoan;
import coffee.shared.utility.AESUtilities;
import coffee.view.DangNhapView;

public class DangNhapController {

	private static DangNhapController instance;

	public static DangNhapController getInstance() {
		if (instance == null)
			instance = new DangNhapController();
		return instance;
	}

	private DangNhapView view;

	public DangNhapController() {
		this.view = new DangNhapView();

		view.getBtnDangNhap().addActionListener(this::onDangNhap);
	}

	public void showView() {
		view.setVisible(true);
	}

	public void hideView() {
		view.resetView();
		view.setVisible(false);
	}

	private void onDangNhap(ActionEvent e) {
		String username = view.getTfUsername().getText();
		String password = new String(view.getTfPassword().getPassword());

		if (username.isBlank() || password.isBlank()) {
			JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin", "Lỗi đăng nhập",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		TaiKhoan taiKhoan = null;
		try {
			String encryptedPassword = AESUtilities.encrypt(password);
			taiKhoan = TaiKhoanDAO.getInstance().get(username, encryptedPassword);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage(), "Lỗi cơ sở dữ liệu", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage(), "Lỗi mã hóa mật khẩu", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (taiKhoan == null) {
			JOptionPane.showMessageDialog(view, "Thông tin đăng nhập không đúng", "Lỗi đăng nhập",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		TrangChuController.getInstance().showView(taiKhoan);
		hideView();
	}

}
