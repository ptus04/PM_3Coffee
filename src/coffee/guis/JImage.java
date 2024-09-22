package coffee.guis;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JImage extends JPanel implements ImageObserver {

	private static final long serialVersionUID = 2115443325124625961L;
	private byte[] image;

	public JImage(byte[] image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		ImageIcon ii = new ImageIcon(image);
		g.drawImage(ii.getImage(), 0, 0, ii.getIconWidth(), ii.getIconWidth(), (ImageObserver) this);
	}

}
