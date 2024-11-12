package coffee.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import coffee.constant.Colors;
import coffee.constant.Fonts;
import coffee.entity.NhanVien;
import coffee.shared.utility.ColorUtilities;

public class NavigationMenu extends JPanel {

	private static final long serialVersionUID = -5834633481890605718L;

	private String showingPage = "Trang chủ";

	private Box pInfo;
	private Box pPersonal;
	private JLabel lName;
	private JLabel lNumber;

	private NavigationButton btnTrangChu;
	private NavigationButton btnQuanLyCaPhe;
	private NavigationButton btnQuanLyKhachHang;
	private NavigationButton btnQuanLyKhuyenMai;
	private NavigationButton btnTraCuuHoaDon;
	private NavigationButton btnTraCuuNhanVien;
	private NavigationButton btnTraCuuCaLam;
	private NavigationButton btnBaoCaoThongKe;
	private NavigationButton btnDangXuat;

	private NhanVien nhanVien;

	public JLabel getlName() {
		return lName;
	}

	public NavigationButton getBtnTrangChu() {
		return btnTrangChu;
	}

	public NavigationButton getBtnQuanLyCaPhe() {
		return btnQuanLyCaPhe;
	}

	public NavigationButton getBtnQuanLyKhachHang() {
		return btnQuanLyKhachHang;
	}

	public NavigationButton getBtnQuanLyKhuyenMai() {
		return btnQuanLyKhuyenMai;
	}

	public NavigationButton getBtnTraCuuHoaDon() {
		return btnTraCuuHoaDon;
	}

	public NavigationButton getBtnTraCuuNhanVien() {
		return btnTraCuuNhanVien;
	}

	public NavigationButton getBtnTraCuuCaLam() {
		return btnTraCuuCaLam;
	}

	public NavigationButton getBtnBaoCaoThongKe() {
		return btnBaoCaoThongKe;
	}

	public NavigationButton getBtnDangXuat() {
		return btnDangXuat;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
		lName.setText(nhanVien.getHoTen());
		lNumber.setText(nhanVien.getMaNhanVien());

		pInfo.setMaximumSize(new Dimension(pInfo.getPreferredSize().width, pInfo.getPreferredSize().height));
	}

	public NavigationMenu() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(ColorUtilities.darken(Colors.BACKGROUND));

		if (nhanVien == null) {
			nhanVien = new NhanVien();
		}

		add(pInfo = Box.createHorizontalBox());
		pInfo.add(new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/image/8.png")).getImage()
				.getScaledInstance(52, 52, Image.SCALE_SMOOTH))));
		pInfo.add(Box.createHorizontalStrut(8));
		pInfo.add(pPersonal = Box.createVerticalBox());
		pPersonal.add(lName = new JLabel(nhanVien.getHoTen()));
		pPersonal.add(lNumber = new JLabel(nhanVien.getMaNhanVien()));
		lName.setFont(Fonts.HEADING_5);
		lNumber.setFont(Fonts.CAPTION);
		pInfo.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		pInfo.setMaximumSize(new Dimension(pInfo.getPreferredSize().width, pInfo.getPreferredSize().height));

		add(Box.createVerticalStrut(16));

		add(btnTrangChu = new NavigationButton("Trang chủ", "/image/home.png"));
		btnTrangChu.setSelected(true);
		add(Box.createVerticalStrut(8));
		add(btnQuanLyCaPhe = new NavigationButton("Quản lý menu", "/image/coffee-bean.png"));
		add(Box.createVerticalStrut(8));
		add(btnQuanLyKhachHang = new NavigationButton("Tra cứu khách hàng", "/image/users.png"));
		add(Box.createVerticalStrut(8));
		add(btnQuanLyKhuyenMai = new NavigationButton("Quản lý khuyến mãi", "/image/discount.png"));
		add(Box.createVerticalStrut(8));
		add(btnTraCuuHoaDon = new NavigationButton("Tra cứu hóa đơn", "/image/history.png"));
		add(Box.createVerticalStrut(8));
		add(btnTraCuuNhanVien = new NavigationButton("Tra cứu nhân viên", "/image/users.png"));
		add(Box.createVerticalStrut(8));
		add(btnTraCuuCaLam = new NavigationButton("Tra cứu ca làm", "/image/shift.png"));
		add(Box.createVerticalStrut(8));
		add(btnBaoCaoThongKe = new NavigationButton("Báo cáo và thống kê", "/image/report.png"));
		add(Box.createGlue());
		add(btnDangXuat = new NavigationButton("Đăng xuất", "/image/logout.png", Colors.DANGER));

		for (Component component : getComponents()) {
			if (component instanceof NavigationButton target) {
				target.setAlignmentX(JComponent.CENTER_ALIGNMENT);
				target.setMaximumSize(new Dimension(getPreferredSize().width, target.getPreferredSize().height));

				if (!target.getName().equals("Đăng xuất")) {
					target.addActionListener(this::onNavigationButtonClicked);
				}

			}
		}

	}

	private void onNavigationButtonClicked(ActionEvent e) {
		for (Component component : getComponents()) {
			if (component instanceof NavigationButton target && target.getName().equals(showingPage))
				target.setSelected(false);
		}

		NavigationButton target = ((NavigationButton) e.getSource());
		target.setSelected(true);
		showingPage = target.getName();
	}

	@Override
	public Insets getInsets() {
		return new Insets(8, 8, 8, 8);
	}

}
