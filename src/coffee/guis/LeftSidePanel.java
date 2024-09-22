package coffee.guis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import coffee.Application;

public class LeftSidePanel extends JPanel {

	private static final long serialVersionUID = -5834633481890605718L;

	public LeftSidePanel() {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		JLabel logo = new JLabel("3Coffee");
		logo.setForeground(Application.COLOR_PRIMARY);
		logo.setFont(new Font("Inter", Font.BOLD, 30));
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		add(logo, BorderLayout.NORTH);
		
		add(new NavigationPanel(), BorderLayout.CENTER);
		
		add(ButtonFactory.createNavigationButton("/image/logout.png", 30, "Đăng xuất", Application.COLOR_DANGER), BorderLayout.SOUTH);
	}

}
