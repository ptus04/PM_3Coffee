package coffee.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CoffeePanel extends JPanel {

	private static final long serialVersionUID = 6923009636167227556L;

	private String path;
	private Image image;

	public CoffeePanel(String path) {
		this.path = path;
		initialize();
	}

	public CoffeePanel(Color color) {
		setBackground(color);
		initialize();
	}

	private void initialize() {
		setLayout(new BorderLayout());
		setOpaque(false);
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
