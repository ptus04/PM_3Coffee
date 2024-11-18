package coffee.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.function.Consumer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import coffee.constant.Colors;
import coffee.constant.Fonts;
import coffee.entity.NhanVien;
import coffee.gui.CoffeeLabel;
import coffee.gui.CoffeePanel;
import coffee.gui.NavigationButton;
import coffee.shared.utility.ColorUtilities;

public class TrangChuView extends JFrame {

	private static final long serialVersionUID = 1004693482472628969L;

	private CoffeePanel pMain;
	private CardLayout cardLayout;
	private NhanVien nhanVien;
	private Consumer<Void> windowClosingListener;

	private String showingPage = "Trang chủ";

	private Box pInfo;
	private Box pPersonal;

	private NavigationButton btnTrangChu;
	private NavigationButton btnCaPhe;
	private NavigationButton btnKhachHang;
	private NavigationButton btnKhuyenMai;
	private NavigationButton btnHoaDon;
	private NavigationButton btnNhanVien;
	private NavigationButton btnCaLam;
	private NavigationButton btnThongKe;
	private NavigationButton btnDangXuat;

	private JPanel pNavigation;

	public TrangChuView() {
		setTitle("3Coffee Sale Assistant");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1366, 960);
		setLocationRelativeTo(null);

		this.pMain = new CoffeePanel(cardLayout = new CardLayout());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				windowClosingListener.accept(null);
			}
		});
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;

		getContentPane().removeAll();

		add(pNavigation = new CoffeePanel(ColorUtilities.darken(Colors.BACKGROUND), new Insets(16, 16, 16, 16)),
				BorderLayout.WEST);
		pNavigation.setLayout(new BoxLayout(pNavigation, BoxLayout.Y_AXIS));

		pNavigation.add(pInfo = Box.createHorizontalBox());
		pInfo.add(new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/image/8.png")).getImage()
				.getScaledInstance(52, 52, Image.SCALE_SMOOTH))));
		pInfo.add(Box.createHorizontalStrut(8));
		pInfo.add(pPersonal = Box.createVerticalBox());
		pPersonal.add(new CoffeeLabel(nhanVien.getHoTen(), Fonts.HEADING_5));
		pPersonal.add(new CoffeeLabel(nhanVien.getMaNhanVien(), Fonts.CAPTION));
		pInfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		pInfo.setMaximumSize(new Dimension(pInfo.getPreferredSize().width, pInfo.getPreferredSize().height));

		pNavigation.add(Box.createVerticalStrut(16));

		pNavigation.add(btnTrangChu = new NavigationButton("Trang chủ", "/image/home.png"));
		btnTrangChu.setSelected(true);
		pNavigation.add(Box.createVerticalStrut(8));

		if (nhanVien.isLaQuanLy()) {
			pNavigation.add(btnCaPhe = new NavigationButton("Quản lý menu", "/image/coffee-bean.png"));
			pNavigation.add(Box.createVerticalStrut(8));
			pNavigation.add(btnKhuyenMai = new NavigationButton("Quản lý khuyến mãi", "/image/discount.png"));
			pNavigation.add(Box.createVerticalStrut(8));
			pNavigation.add(btnKhachHang = new NavigationButton("Tra cứu khách hàng", "/image/users.png"));
			pNavigation.add(Box.createVerticalStrut(8));
			pNavigation.add(btnHoaDon = new NavigationButton("Tra cứu đơn hàng", "/image/history.png"));
			pNavigation.add(Box.createVerticalStrut(8));
			pNavigation.add(btnNhanVien = new NavigationButton("Tra cứu nhân viên", "/image/users.png"));
			pNavigation.add(Box.createVerticalStrut(8));
			pNavigation.add(btnCaLam = new NavigationButton("Tra cứu ca làm", "/image/shift.png"));
		} else {
			pNavigation.add(btnCaPhe = new NavigationButton("Tra cứu menu", "/image/coffee-bean.png"));
			pNavigation.add(Box.createVerticalStrut(8));
			pNavigation.add(btnKhuyenMai = new NavigationButton("Tra cứu khuyến mãi", "/image/discount.png"));
			pNavigation.add(Box.createVerticalStrut(8));
			pNavigation.add(btnHoaDon = new NavigationButton("Tra cứu đơn hàng", "/image/history.png"));
//			pNavigation.add(Box.createVerticalStrut(8));
//			pNavigation.add(btnNhanVien = new NavigationButton("Sửa thông tin cá nhân", "/image/users.png"));
			pNavigation.add(Box.createVerticalStrut(8));
			pNavigation.add(btnThongKe = new NavigationButton("Thống kê", "/image/report.png"));
		}

		pNavigation.add(Box.createGlue());
		pNavigation.add(btnDangXuat = new NavigationButton("Đăng xuất", "/image/logout.png", Colors.DANGER));

		btnTrangChu.setSelected(true);

		for (Component component : pNavigation.getComponents()) {
			if (component instanceof NavigationButton target) {
				target.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				target.setMaximumSize(new Dimension(getPreferredSize().width, target.getPreferredSize().height));

				if (!target.getName().equals("Đăng xuất"))
					target.addActionListener(this::dangXuat);

			}
		}

		add(pMain, BorderLayout.CENTER);
	}

	private void dangXuat(ActionEvent e) {
		for (Component component : pNavigation.getComponents())
			if (component instanceof NavigationButton target && target.getName().equals(showingPage))
				target.setSelected(false);

		NavigationButton target = ((NavigationButton) e.getSource());
		target.setSelected(true);
		showingPage = target.getName();
	}

	public void addBtnTrangChuListener(ActionListener listener) {
		btnTrangChu.addActionListener(listener);
	}

	public void addBtnCaPheListener(ActionListener listener) {
		btnCaPhe.addActionListener(listener);
	}

	public void addBtnKhachHangListener(ActionListener listener) {
		btnKhachHang.addActionListener(listener);
	}

	public void addBtnKhuyenMaiListener(ActionListener listener) {
		btnKhuyenMai.addActionListener(listener);
	}

	public void addBtnHoaDonListener(ActionListener listener) {
		btnHoaDon.addActionListener(listener);
	}

	public void addBtnNhanVienListener(ActionListener listener) {
		btnNhanVien.addActionListener(listener);
	}

	public void addBtnCaLamListener(ActionListener listener) {
		btnCaLam.addActionListener(listener);
	}

	public void addBtnThongKeListener(ActionListener listener) {
		btnThongKe.addActionListener(listener);
	}

	public void addBtnDangXuatListener(ActionListener listener) {
		btnDangXuat.addActionListener(listener);
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void addView(String viewName, Component view) {
		pMain.add(view, viewName);
	}

	public void showView(String viewName) {
		cardLayout.show(pMain, viewName);
	}

	public void addWindowClosingListener(Consumer<Void> lambda) {
		windowClosingListener = lambda;
	}
}
