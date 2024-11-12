package coffee.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import coffee.constant.Colors;
import coffee.entity.NhanVien;
import coffee.gui.NavigationMenu;

public class TrangChuView extends JFrame {

	private static final long serialVersionUID = 1004693482472628969L;

	private NavigationMenu navigationMenu = new NavigationMenu();
	private JPanel pMain;
	private CardLayout cardLayout;

	private TongQuanView tongQuanView = new TongQuanView();
	private TaoMoiView taoMoiView = new TaoMoiView();
	private KetQuaThongKeView ketQuaThongKeView = new KetQuaThongKeView();
	private TraCuuCaLamView traCuuCaLamView = new TraCuuCaLamView();

	private NhanVien nhanVien;

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
		navigationMenu.setNhanVien(nhanVien);
	}

	public NavigationMenu getNavigationMenu() {
		return navigationMenu;
	}

	public TrangChuView() {
		setTitle("3Coffee Sale Assistant");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setSize(1366, 960);
		setLocationRelativeTo(null);

		add(navigationMenu, BorderLayout.WEST);

		add(pMain = new JPanel(cardLayout = new CardLayout(8, 8)), BorderLayout.CENTER);
		pMain.setBackground(Colors.BACKGROUND);

//		pMain.add(tongQuanView.getContentPane(), TongQuanView.class.getName());
//		pMain.add(taoMoiView.getContentPane(), TaoMoiView.class.getName());
//		pMain.add(ketQuaThongKeView.getContentPane(), KetQuaThongKeView.class.getName());
		pMain.add(traCuuCaLamView.getContentPane(), TraCuuCaLamView.class.getName());
	}
}
