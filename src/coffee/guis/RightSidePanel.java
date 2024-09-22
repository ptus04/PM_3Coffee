package coffee.guis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.net.http.HttpClient;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import coffee.Application;

public class RightSidePanel extends JPanel {

	private static final long serialVersionUID = -5834633481890605718L;

	public RightSidePanel() {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		// TODO: User
		Box b = Box.createHorizontalBox();
		HttpClient.newHttpClient().
		b.add(new JImage())
		
		add(b, BorderLayout.NORTH);
		
		// TODO: Bills
	}

}
