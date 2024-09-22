package coffee.guis;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class NavigationPanel extends JScrollPane {

	private static final long serialVersionUID = -1925218247097421296L;

	private JPanel viewPort = new JPanel();

	public NavigationPanel() {
		viewPort.setLayout(new BoxLayout(viewPort, BoxLayout.Y_AXIS));
		viewPort.setBackground(Color.WHITE);

		setViewportView(viewPort);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

		viewPort.add(ButtonFactory.createNavigationButton("/image/home.png", 30, "Trang chủ"));
		viewPort.add(ButtonFactory.createNavigationButton("/image/history.png", 30, "Lịch sử giao dịch"));
		viewPort.add(ButtonFactory.createNavigationButton("/image/users.png", 40, "Quản lý nhân viên"));
		viewPort.add(ButtonFactory.createNavigationButton("/image/coffee-bean.png", 40, "Quản lý cà phê"));
		viewPort.add(ButtonFactory.createNavigationButton("/image/discount.png", 40, "Quản lý khuyến mãi"));
		viewPort.add(ButtonFactory.createNavigationButton("/image/report.png", 40, "Báo cáo và thống kê"));

		for (Component component : viewPort.getComponents()) {
			JComponent target = (JComponent) component;
			target.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			target.setMaximumSize(new Dimension(viewPort.getPreferredSize().width, target.getPreferredSize().height));
		}
	}
}
