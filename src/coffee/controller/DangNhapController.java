package coffee.controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.JOptionPane;

import coffee.dao.LichSuHoatDong_DAO;
import coffee.dao.TaiKhoanDAO;
import coffee.entity.LichSuHoatDong;
import coffee.entity.LoaiLichSu;
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
		view.getTfUsername().requestFocus();
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

		// LOGGING
		List<LichSuHoatDong> lichSuHoatDongList = LichSuHoatDong_DAO.getInstance().selectAll();

		LocalDateTime thoiGian = LocalDateTime.now();
		long count = lichSuHoatDongList.stream().count();
		String maLichSu = "LS" + thoiGian.getYear() % 100 + String.format("%02d", thoiGian.getMonthValue())
				+ String.format("%02d", thoiGian.getDayOfMonth()) + String.format("%04d", count + 1);
		String noiDung = "Đăng nhập hệ thống";
		double soTienBanGiao = 0;
		if (!lichSuHoatDongList.isEmpty()) {
			lichSuHoatDongList.sort((a, b) -> a.getThoiGian().compareTo(b.getThoiGian()));
			soTienBanGiao = lichSuHoatDongList.get(lichSuHoatDongList.size()-1).getsoTienBanGiao();
		}

		try {
			LichSuHoatDong_DAO.getInstance().add(new LichSuHoatDong(maLichSu, thoiGian, noiDung, soTienBanGiao,
					LoaiLichSu.DANG_NHAP, taiKhoan.getNhanVien()));
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(view, ex.getMessage(), "Lỗi cập nhật dữ liệu", JOptionPane.ERROR_MESSAGE);
			return;
		}

		TrangChuController.getInstance().showView(taiKhoan);
		hideView();
	}

}
