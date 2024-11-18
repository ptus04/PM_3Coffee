package coffee;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import coffee.constant.Colors;
import coffee.controller.DangNhapController;
import coffee.controller.TrangChuController;
import coffee.entity.NhanVien;
import coffee.entity.TaiKhoan;
import coffee.shared.utility.ColorUtilities;

public class Application {

	public static final DateTimeFormatter FMT_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter FMT_TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
	public static final DateTimeFormatter FMT_DATE_TIME = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
	public static final NumberFormat FMT_CURRENCY = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			UIManager.put("Button.focus", ColorUtilities.darken(Colors.GRAY_50));

//			DangNhapController.getInstance().showView();
			TrangChuController.getInstance().showView(new TaiKhoan("vantu", "0", new NhanVien("NV241101")));
		});

	}

}
