package coffee.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import coffee.constant.Colors;

public class CoffeePanel extends JPanel {

	private static final long serialVersionUID = 6923009636167227556L;

	private String path;
	private Image image;
	private Insets insets;

	public CoffeePanel() {
		setOpaque(false);
		setBackground(Colors.BACKGROUND);
	}

	public CoffeePanel(String path) {
		this.path = path;
		setOpaque(false);
	}

	public CoffeePanel(LayoutManager layout, String path) {
		this.path = path;
		setOpaque(false);
		setLayout(layout);
	}

	public CoffeePanel(Color color, Insets insets) {
		setOpaque(false);
		setBackground(color);
		this.insets = insets;
	}

	public CoffeePanel(Color color) {
		setOpaque(false);
		setBackground(color);

	}

	public CoffeePanel(LayoutManager layout) {
		setOpaque(false);
		setBackground(Colors.BACKGROUND);
		setLayout(layout);
	}

	public CoffeePanel(LayoutManager layout, Color color) {
		setOpaque(false);
		setOpaque(false);
		setBackground(color);
		setLayout(layout);
	}

	public CoffeePanel(LayoutManager layout, Insets insets) {
		this.insets = insets;
		setOpaque(false);
		setBackground(Colors.BACKGROUND);
		setLayout(layout);
	}

	@Override
	public Insets getInsets() {
		return insets == null ? super.getInsets() : insets;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (path == null) {
			g.setColor(getBackground());
			g.fillRect(0, 0, getWidth(), getHeight());
			return;
		}

		try {
			if (image == null)
				image = ImageIO.read(getClass().getResource(path));
		} catch (IOException | IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi phần mềm", JOptionPane.ERROR_MESSAGE);
			return;
		}

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}

}
