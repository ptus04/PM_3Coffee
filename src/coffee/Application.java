package coffee;

import java.awt.Color;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import coffee.controller.DangNhapController;
import coffee.controller.TrangChuController;
import coffee.entity.NhanVien;
import coffee.entity.TaiKhoan;
import coffee.entity.TrangThaiLamViec;
import coffee.shared.utility.ColorUtilities;

public class Application {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			UIManager.put("Button.focus", ColorUtilities.darken(Color.GRAY));

//			DangNhapController.getInstance().showView();
			TrangChuController.getInstance().showView(new TaiKhoan("vantu", "0", new NhanVien("NV241101")));
		});

	}

}
