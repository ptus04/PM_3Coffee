package coffee.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import coffee.constant.Colors;
import coffee.constant.Fonts;

public class TheThongKe extends JPanel {

	private static final long serialVersionUID = -2454984566733070396L;

	private JLabel lName, lValue;
	private int arc = 16;

	public TheThongKe(String name, String value) {
		lName = new JLabel(name);
		lValue = new JLabel(value);

		lName.setFont(Fonts.HEADING_5);
		lValue.setFont(Fonts.HEADING_2B);

		lName.setForeground(Colors.WHITE);
		lValue.setForeground(Colors.ACCENT);

		add(lName);
		add(Box.createGlue());
		add(lValue);

		setOpaque(false);
		setPreferredSize(new Dimension(300, 125));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

	public void setNameText(String name) {
		lName.setText(name);
	}

	public void setValueText(String value) {
		lValue.setText(value);
	}

	@Override
	public Insets getInsets() {
		return new Insets(8, 16, 8, 16);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Colors.PRIMARY);
		g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
	}

}
