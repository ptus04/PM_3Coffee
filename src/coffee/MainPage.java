package coffee;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;

import coffee.guis.SearchBox;
import coffee.guis.LeftSidePanel;
import coffee.guis.RightSidePanel;

public class MainPage extends JFrame {

	private static final long serialVersionUID = 1004693482472628969L;
	private Box mainPanel;
	private JPanel searchBox;

	public MainPage() {
		setTitle("Coffee Sale Assistant");
//		setUndecorated(true);
//		setShape(new RoundRectangle2D.Double(0, 0, 100, 100, 50, 50));
//		setLocationRelativeTo(null);

		LeftSidePanel sidePanel = new LeftSidePanel();
		add(sidePanel, BorderLayout.WEST);

		mainPanel = Box.createVerticalBox();
		mainPanel.setOpaque(true);
		mainPanel.setBackground(new Color(249, 248, 251));
//		mainPanel.add(searchBox = new SearchBox());
		add(mainPanel, BorderLayout.CENTER);

		RightSidePanel rightSidePanel = new RightSidePanel();
		add(rightSidePanel, BorderLayout.EAST);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(1200, 900);
//		pack();
	}
}
